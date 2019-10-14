package tools;

import asm.tools.Foo3Dump;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * * @Author: cuixin
 * * @Date: 2019/10/14 13:52
 */
public class Foo3Wrapper {
    public static void main(String[] args) throws Exception {
        Files.write(Paths.get("tools/Foo3.class"), Foo3Dump.dump());
    }
}
