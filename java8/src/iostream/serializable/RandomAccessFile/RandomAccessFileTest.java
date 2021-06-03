package iostream.serializable.RandomAccessFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileTest {
    public static void main(String[] args) {
        test();
    }
    public static void test(){
        RandomAccessFile randomAccessFile = null;
        try {
            //实现在源文件第四个字母后添加“xxx”字符串
        /*mode参数：
        r 只读
        rw 读写
        rwd 读写， 同步文件内容的更新
        rws 读写， 同步文件内容和元数据的更新
         */
            randomAccessFile = new RandomAccessFile( new File("readme.txt"),"rw");

            //读取下标为4到EOF的数据
            //缓冲数组 获取要移动的字符串的最大长度
            //StringBuffer stringBuffer = new StringBuffer((int) new File("readme.txt").length());
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            randomAccessFile.seek(4l);
            //randomAccessFile.getFilePointer();
            byte[] buffer = new byte[10];
            int length;
            while( ( length = randomAccessFile.read(buffer)) != -1){
                //讲读取到的需要移动的数据写入ByteArrayOutputStream的数组中去
                byteArrayOutputStream.write(buffer);
            }

            //写入xxx
            randomAccessFile.seek(4l); //指回指针
            randomAccessFile.write("xxx".getBytes());
            randomAccessFile.write(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(randomAccessFile != null) try {
                randomAccessFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
