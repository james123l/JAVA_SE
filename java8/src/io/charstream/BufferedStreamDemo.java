package io.charstream;

import java.io.*;

public class BufferedStreamDemo {
    private static String src = "readme.txt";
    private static String dest = "readme.txt";

    public static void main(String[] args) {

    }
    public static void testBufferedReaderWriter(){
        //bufferedInput/OutputStream方法和此类一致
        File srcfile = null;
        File destfile = null;
        FileReader fileReader = null;
        FileWriter fileWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            srcfile = new File(src);
            destfile = new File(dest);
            fileReader = new FileReader(srcfile);
            fileWriter = new FileWriter(destfile);
            bufferedReader = new BufferedReader(fileReader);
            bufferedWriter = new BufferedWriter(fileWriter);
            int length;
            char[] buffer = new char[1024];
            while((length=bufferedReader.read(buffer))!=-1){
                bufferedWriter.write(buffer,0,length);
                //清空缓冲区
                bufferedWriter.flush();
            }
            //此处也可以使用readLine方法读取一行输入 返回String， 如果没有数据则返回null
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(bufferedWriter!=null) bufferedWriter.close();
                if(bufferedReader!=null) bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
