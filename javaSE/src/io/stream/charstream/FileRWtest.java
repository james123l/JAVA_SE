package io.stream.charstream;

import java.io.*;

public class FileRWtest {
    //Reader Writer test demo
    public static void main(String[] args) {

    }
    public static void testReader()  {
        //file必须在入读操作时存在，否则异常
        //try catch保证必然执行流的关闭 免于报错
        FileReader fr = null;
        try {
            //写入的file对象
            File file = new File("helloworld.txt");
            //FileReader 流,抛出异常
            fr = new FileReader(file);
            /*
            //单个字符读取的方法读取数据 返回一个字符。如果达到文件末尾 返回-1
            int data ;
            while((data = fr.read() )!= -1){
                System.out.print((char) data);
            }
             */
            //按照字符数组的方式读取
            //每次读取10个字节
            char[] charbuffer = new char[10];
            int length;
            //讲文件内容读取到charbuffer数组中，并返回读取的字符个数，如果EOF 则返回-1
            while((length= fr.read(charbuffer)) != -1){
                for (int i = 0; i < length; i++) {
                    //这里不能写charbuffer.length 因为数组不断利用，含有旧的数据，如果最后一次没有沾满数组，则会输出错误数据
                    System.out.print(charbuffer[i]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                //如果文件流不是null才能关闭
                if(fr!=null) fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    public static void writeTest(){
        FileWriter fileWriter = null;
        try {
            File file = new File("helloworld.txt");
            fileWriter = new FileWriter(file);
            //FileWriter fileWriter = new FileWriter(file,false);   第二个参数boolean append表示是否在原有的文件上追加，true则追加， false则会生成新的文件覆盖原有的旧的文件
            //写出操作,如果文件不存在。则会创建文件。
            fileWriter.write("helloworld");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void copypaste(){
        //只能复制文本 不能复制图片
        FileReader fileReader = null;
        FileWriter fileWriter = null;
        try {
            File srcFile = new File("helloworld.txt");
            File destFile = new File("destination.txt");
            fileReader = new FileReader(srcFile);
            fileWriter = new FileWriter( destFile);
            char[] charbuffer = new char[5];
            int length ;
            while((length = fileReader.read(charbuffer))!= -1){
                fileWriter.write(charbuffer,0,length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                if(fileWriter!=null )  fileWriter.close();
                if(fileReader!=null ) fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
