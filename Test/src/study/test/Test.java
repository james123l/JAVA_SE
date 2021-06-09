package study.test;

import java.nio.charset.StandardCharsets;

public class Test {
    public static void main(String[] args) {
        byte[] bytes = "1".getBytes(StandardCharsets.UTF_8);
        for (Byte b:bytes) {
            System.out.println(b);
        }
    }

}
