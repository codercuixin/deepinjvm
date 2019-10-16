package test.src.main.java.org.sample;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.openjdk.jcstress.infra.runners.TestConfig;
import org.openjdk.jcstress.infra.collectors.TestResultCollector;
import org.openjdk.jcstress.infra.runners.Runner;
import org.openjdk.jcstress.infra.runners.StateHolder;
import org.openjdk.jcstress.util.Counter;
import org.openjdk.jcstress.vm.WhiteBoxSupport;
import org.openjdk.jcstress.util.OpenAddressHashCounter;
import java.util.concurrent.ExecutionException;
import test.src.main.java.org.sample.UnsafePublication;
import org.openjdk.jcstress.infra.results.IntResult2_jcstress;

public class UnsafePublication_jcstress extends Runner<IntResult2_jcstress> {

    OpenAddressHashCounter<IntResult2_jcstress> counter_publish;
    OpenAddressHashCounter<IntResult2_jcstress> counter_consume;
    volatile StateHolder<Pair> version;

    public UnsafePublication_jcstress(TestConfig config, TestResultCollector collector, ExecutorService pool) {
        super(config, collector, pool, "test.src.main.java.org.sample.UnsafePublication");
    }

    @Override
    public void sanityCheck() throws Throwable {
        sanityCheck_API();
        sanityCheck_Footprints();
    }

    private void sanityCheck_API() throws Throwable {
        final UnsafePublication t = new UnsafePublication();
        final UnsafePublication s = new UnsafePublication();
        final IntResult2_jcstress r = new IntResult2_jcstress();
        Collection<Future<?>> res = new ArrayList<>();
        res.add(pool.submit(() -> t.publish()));
        res.add(pool.submit(() -> t.consume(r)));
        for (Future<?> f : res) {
            try {
                f.get();
            } catch (ExecutionException e) {
                throw e.getCause();
            }
        }
    }

    private void sanityCheck_Footprints() throws Throwable {
        config.adjustStrides(size -> {
            version = new StateHolder<>(new Pair[size], 2, config.spinLoopStyle);
            final UnsafePublication t = new UnsafePublication();
            for (int c = 0; c < size; c++) {
                Pair p = new Pair();
                p.r = new IntResult2_jcstress();
                p.s = new UnsafePublication();
                version.pairs[c] = p;
                p.s.publish();
                p.s.consume(p.r);
            }
        });
    }

    @Override
    public Counter<IntResult2_jcstress> internalRun() {
        version = new StateHolder<>(new Pair[0], 2, config.spinLoopStyle);
        counter_publish = new OpenAddressHashCounter<>();
        counter_consume = new OpenAddressHashCounter<>();

        control.isStopped = false;
        Collection<Future<?>> tasks = new ArrayList<>();
        tasks.add(pool.submit(this::publish));
        tasks.add(pool.submit(this::consume));

        try {
            TimeUnit.MILLISECONDS.sleep(config.time);
        } catch (InterruptedException e) {
        }

        control.isStopped = true;

        waitFor(tasks);

        Counter<IntResult2_jcstress> counter = new OpenAddressHashCounter<>();
        counter.merge(counter_publish);
        counter.merge(counter_consume);
        return counter;
    }

    public final void jcstress_consume(StateHolder<Pair> holder, OpenAddressHashCounter<IntResult2_jcstress> cnt, int a, int actors) {
        Pair[] pairs = holder.pairs;
        int len = pairs.length;
        int left = a * len / actors;
        int right = (a + 1) * len / actors;
        for (int c = left; c < right; c++) {
            Pair p = pairs[c];
            IntResult2_jcstress r = p.r;
            UnsafePublication s = p.s;
            p.s = new UnsafePublication();
            cnt.record(r);
            r.r1 = 0;
            r.r2 = 0;
        }
    }

    public final void jcstress_updateHolder(StateHolder<Pair> holder) {
        if (!holder.tryStartUpdate()) return;
        Pair[] pairs = holder.pairs;
        int len = pairs.length;

        int newLen = holder.updateStride ? Math.max(config.minStride, Math.min(len * 2, config.maxStride)) : len;

        Pair[] newPairs = pairs;
        if (newLen > len) {
            newPairs = Arrays.copyOf(pairs, newLen);
            for (int c = len; c < newLen; c++) {
                Pair p = new Pair();
                p.r = new IntResult2_jcstress();
                p.s = new UnsafePublication();
                newPairs[c] = p;
            }
         }

        version = new StateHolder<>(control.isStopped, newPairs, 2, config.spinLoopStyle);
        holder.finishUpdate();
   }

    public final Void publish() {

        OpenAddressHashCounter<IntResult2_jcstress> counter = counter_publish;
        while (true) {
            StateHolder<Pair> holder = version;
            if (holder.stopped) {
                return null;
            }

            Pair[] pairs = holder.pairs;

            holder.preRun();

            for (Pair p : pairs) {
                p.s.publish();
            }

            holder.postRun();

            jcstress_consume(holder, counter, 0, 2);
            jcstress_updateHolder(holder);

            holder.postUpdate();
        }
    }

    public final Void consume() {

        OpenAddressHashCounter<IntResult2_jcstress> counter = counter_consume;
        while (true) {
            StateHolder<Pair> holder = version;
            if (holder.stopped) {
                return null;
            }

            Pair[] pairs = holder.pairs;

            holder.preRun();

            for (Pair p : pairs) {
                IntResult2_jcstress r = p.r;
                r.trap = 0;
                p.s.consume(r);
            }

            holder.postRun();

            jcstress_consume(holder, counter, 1, 2);
            jcstress_updateHolder(holder);

            holder.postUpdate();
        }
    }

    static class Pair {
        public UnsafePublication s;
        public IntResult2_jcstress r;
    }
}
