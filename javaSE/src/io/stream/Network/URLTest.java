package io.stream.Network;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class URLTest {
    private static String path = "http://localhost:3380/instance";
    public static void main(String[] args) {
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            URL url = new URL(path);
            //常用方法
            System.out.println(url.getProtocol());//协议名
            System.out.println(url.getHost());//主机名
            System.out.println(url.getPort());//端口号
            System.out.println(url.getPath());//文件路径
            System.out.println(url.getFile());//文件名
            System.out.println(url.getQuery());//查询名
            //获取链接
            httpURLConnection = (HttpURLConnection) url.openConnection();
            //链接
            httpURLConnection.connect();
            inputStream = httpURLConnection.getInputStream();
            fileOutputStream = new FileOutputStream("file.txt");
            byte[] buffer = new byte[1024];
            int length;
            while((length = inputStream.read(buffer))!= -1){
                fileOutputStream.write(buffer,0,length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭资源
                if(inputStream!=null) inputStream.close();
                if(fileOutputStream!=null) fileOutputStream.close();
                if(httpURLConnection!=null) httpURLConnection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
