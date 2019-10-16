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
import test.src.main.java.org.sample.ConcurrencyTest;
import org.openjdk.jcstress.infra.results.IntResult2_jcstress;

public class ConcurrencyTest_jcstress extends Runner<IntResult2_jcstress> {

    OpenAddressHashCounter<IntResult2_jcstress> counter_method1;
    OpenAddressHashCounter<IntResult2_jcstress> counter_method2;
    volatile StateHolder<Pair> version;

    public ConcurrencyTest_jcstress(TestConfig config, TestResultCollector collector, ExecutorService pool) {
        super(config, collector, pool, "test.src.main.java.org.sample.ConcurrencyTest");
    }

    @Override
    public void sanityCheck() throws Throwable {
        sanityCheck_API();
        sanityCheck_Footprints();
    }

    private void sanityCheck_API() throws Throwable {
        final ConcurrencyTest t = new ConcurrencyTest();
        final ConcurrencyTest s = new ConcurrencyTest();
        final IntResult2_jcstress r = new IntResult2_jcstress();
        Collection<Future<?>> res = new ArrayList<>();
        res.add(pool.submit(() -> t.method1(r)));
        res.add(pool.submit(() -> t.method2(r)));
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
            final ConcurrencyTest t = new ConcurrencyTest();
            for (int c = 0; c < size; c++) {
                Pair p = new Pair();
                p.r = new IntResult2_jcstress();
                p.s = new ConcurrencyTest();
                version.pairs[c] = p;
                p.s.method1(p.r);
                p.s.method2(p.r);
            }
        });
    }

    @Override
    public Counter<IntResult2_jcstress> internalRun() {
        version = new StateHolder<>(new Pair[0], 2, config.spinLoopStyle);
        counter_method1 = new OpenAddressHashCounter<>();
        counter_method2 = new OpenAddressHashCounter<>();

        control.isStopped = false;
        Collection<Future<?>> tasks = new ArrayList<>();
        tasks.add(pool.submit(this::method1));
        tasks.add(pool.submit(this::method2));

        try {
            TimeUnit.MILLISECONDS.sleep(config.time);
        } catch (InterruptedException e) {
        }

        control.isStopped = true;

        waitFor(tasks);

        Counter<IntResult2_jcstress> counter = new OpenAddressHashCounter<>();
        counter.merge(counter_method1);
        counter.merge(counter_method2);
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
            ConcurrencyTest s = p.s;
            p.s = new ConcurrencyTest();
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
                p.s = new ConcurrencyTest();
                newPairs[c] = p;
            }
         }

        version = new StateHolder<>(control.isStopped, newPairs, 2, config.spinLoopStyle);
        holder.finishUpdate();
   }

    public final Void method1() {

        OpenAddressHashCounter<IntResult2_jcstress> counter = counter_method1;
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
                p.s.method1(r);
            }

            holder.postRun();

            jcstress_consume(holder, counter, 0, 2);
            jcstress_updateHolder(holder);

            holder.postUpdate();
        }
    }

    public final Void method2() {

        OpenAddressHashCounter<IntResult2_jcstress> counter = counter_method2;
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
                p.s.method2(r);
            }

            holder.postRun();

            jcstress_consume(holder, counter, 1, 2);
            jcstress_updateHolder(holder);

            holder.postUpdate();
        }
    }

    static class Pair {
        public ConcurrencyTest s;
        public IntResult2_jcstress r;
    }
}
