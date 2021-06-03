package iostream.processstream;

import java.io.*;

public class IOputStreamRW {
    private static String src = "readme.txt";
    private static String dest = "readme.txt";
    public static void main(String[] args) {
        test01();
    }
    public static void test01(){
        File srcfile = null;
        File destfile = null;
        FileInputStream fileIstream = null;
        FileOutputStream  fileOstream = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter =null;
        try {
            srcfile = new File(src);
            destfile = new File(dest);
            fileIstream = new FileInputStream(srcfile);
            fileOstream = new FileOutputStream(destfile);
            //第一个参数是字符流 第二个参数是编码集，如果填写需要根据文件的编码确定
            inputStreamReader = new InputStreamReader(fileIstream);
            outputStreamWriter = new OutputStreamWriter(fileOstream);
            int length;
            char[] buffer = new char[1024];
            while((length=inputStreamReader.read(buffer))!=-1){
                outputStreamWriter.write(buffer,0,length);
                //清空缓冲区
                outputStreamWriter.flush();
            }
            //此处也可以使用readLine方法读取一行输入 返回String， 如果没有数据则返回null
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(outputStreamWriter!=null) outputStreamWriter.close();
                if(inputStreamReader!=null) inputStreamReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
