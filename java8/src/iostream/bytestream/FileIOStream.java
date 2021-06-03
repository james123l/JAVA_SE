package iostream.bytestream;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileIOStream {
    private static String path = ".";
    public static void main(String[] args) throws Exception {
        ReadFile();
        System.out.println();

    }
    public static void ReadFile()  {
        File file = new File(path);
        InputStream inputStream;
        try {
            //文件可能找不到，必须有异常处理 也可以不处理 在main 抛出异常
            //子类FileInputStream的构造参数是文件名
            inputStream= new FileInputStream(file);  //  多态
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //读取操作
        //inputStream.available() 文件可读取的大小
        byte[] bytes= new byte[20000];
        //把所有的文件内容读取到byte数组
        /*
        int count = 0;
        while ((bytes[count] = (byte)inputStream.read())!=-1){
        //读到-1，即是EOF
            count++;
        }
         */
        //等价写法
        try {
            inputStream.read(bytes);
            String content = new String(bytes);     //将读取的文件转换为字符串以方便打印
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(inputStream!=null) inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    public static void WriteFile() {
        path+="/java.md";   //不存在的文件 （可以自动创建）也可以写已经存在的
        File file = new File(path);
        OutputStream outputStream;
        try {
            //文件可能找不到，必须有异常处理 也可以不处理 在main 抛出异常
            //子类FileInputStream的构造参数是文件名
            outputStream= new FileOutputStream(file);  //  多态
            //outputStream= new FileOutputStream(file,true);  //  第二个参数是代表追加写入 false就会覆盖原有内容
        } catch (FileNotFoundException e) {
            outputStream= new FileOutputStream(".");
            e.printStackTrace();
        }
        try {
            String content = "public static void main(String[] args) throws Exception {\n";
            content+= "System.out.println(\"Hello,world!\")\n};";
            //写入文件HelloWorld
            outputStream.write(content.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                if(outputStream!=null){
                    //关闭资源 避免错误
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
