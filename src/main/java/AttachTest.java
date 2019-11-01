/**
 * * @Author: cuixin
 * * @Date: 2019/10/31 19:07
 * need to use jdk10
 * todo run again
 */
import com.sun.tools.attach.*;

import java.io.IOException;

public class AttachTest {
    public static void main(String[] args) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException {
        if(args.length <= 1){
            System.out.println("Usage: java AttachTest <PID> /PATH/TO/AGENT.jar");
            return;
        }
        VirtualMachine vm = VirtualMachine.attach(args[0]);
        vm.loadAgent(args[1]);
    }
}
