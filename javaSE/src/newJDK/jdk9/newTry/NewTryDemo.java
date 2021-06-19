package newJDK.jdk9.newTry;

import java.io.*;

public class NewTryDemo {
    /*
    * jdk9 升级了try的结构
    * */
    private static final String src = ".";
    private static final String dest = ".";
    private static File srcfile = new File(src);
    private static File destfile = new File(dest);
    public void newTryJDK8(){
        //把需要关闭的资源的初始化放在try的（）中 可以自动关闭
        try( FileReader fileReader = new FileReader(srcfile);
             FileWriter fileWriter = new FileWriter(destfile);
             BufferedReader bufferedReader = new BufferedReader(fileReader);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
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
        }
    }
    public void newTryJDK9() throws Exception{
        final FileReader fileReader =  new FileReader(srcfile);
        final FileWriter fileWriter =  new FileWriter(destfile);
        final BufferedReader bufferedReader = new BufferedReader(fileReader);
        final BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        //jdk 9 可以把需要关闭的对象直接放在括号内 但是在try代码块中 只能用不能改，需要是final
        try (fileReader;fileWriter;bufferedReader;bufferedWriter){
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
        }
    }
}
