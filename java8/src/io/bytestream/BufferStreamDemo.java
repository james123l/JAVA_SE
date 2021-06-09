package io.bytestream;

import java.io.*;

public class BufferStreamDemo {
    private static String filepath = ".";

    public static void main(String[] args) {

        //缓存 读取
        BufferedInputStream bufferedInputStream = null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(new File(filepath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //buffer的缓存默认是8192，可以在BufferInputStream里面设置第二个参数为缓存空间大小。 本质是字节数组
        try {
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            byte[] bytes = new byte[bufferedInputStream.available()];
            bufferedInputStream.read(bytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //close函数有抛出异常
            try {
                bufferedInputStream.close();
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //写入
            BufferedOutputStream bufferedOutputStream = null;
            try {
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(filepath));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                bufferedOutputStream.write("Hello,world!".getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                //close函数有抛出异常
                try {
                    bufferedOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}