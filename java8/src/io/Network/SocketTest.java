package io.Network;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
//Tcp协议的socket传输文件和信息

public class SocketTest {
    public static void main(String[] args) {
        new Thread(new Server()).start();
        new Thread(new Client()).start();
    }
}

class Client implements Runnable {
    public void run() {
        InetAddress inetAddress = null;
        OutputStream outputStream = null;
        Socket socket = null;
        ByteArrayOutputStream byteArrayOutputStream = null;

        //文件读写
        FileInputStream fileInputStream = null;
        //接收server的信息
        InputStream inputStream = null;
        try {
            inetAddress = InetAddress.getByName("127.0.0.1");
            socket = new Socket(inetAddress, 3380);
            outputStream = socket.getOutputStream();

            //传输字符串
//            outputStream.write("Hello! This is client!".getBytes());
//            System.out.println("Client end task done!");

            //传输文件
            fileInputStream = new FileInputStream(new File("readme.txt"));
            byte[] buffer = new byte[10];
            int length;
            while ((length = fileInputStream.read(buffer)) != -1) {
                outputStream.write(buffer,0,length);
            }
            //关闭socket输出流 通知服务器端传输结束
            socket.shutdownOutput();

            //接收server的信息
            inputStream = socket.getInputStream();
            byteArrayOutputStream = new ByteArrayOutputStream();
            while((length = inputStream.read(buffer)) != -1){
                byteArrayOutputStream.write(buffer);
            }
            System.out.println(byteArrayOutputStream.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //自上而下关闭
                if(byteArrayOutputStream!= null) byteArrayOutputStream.close();
                if(fileInputStream != null) fileInputStream.close();
                if(inputStream!= null) inputStream.close();
                if (outputStream != null) outputStream.close();
                if (socket != null) socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}


class Server implements Runnable {
    public void run() {
        ByteArrayOutputStream byteArrayOutputStream = null;
        ServerSocket serverSocket = null;
        Socket socket = null;
        InputStream inputStream = null;

        //文件读写的文件流
        FileOutputStream fileOutputStream = null;
        //给client发送信息
        OutputStream outputStream = null;

        try {
            serverSocket = new ServerSocket(3380);
            socket = serverSocket.accept();//接收来自client的socket
            inputStream = socket.getInputStream();
            //因为不同字符集一个字符占用字节数不同 直接用bytearray会出现乱码
            byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[10];

            //接收字符串，打印到控制台
//            int length;
//            while ((length = inputStream.read(buffer)) != -1) {
//                byteArrayOutputStream.write(buffer,0,length);
//            }
//            System.out.println(socket.getInetAddress().getHostName()+"\t"+socket.getInetAddress().getHostAddress()+"\t:"+byteArrayOutputStream.toString());

            //接收文件
            fileOutputStream = new FileOutputStream( new File("readme_server.txt"));
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                //inputStream.read 是阻塞式的， 如果client不执行shutdown 那么server就不会停止接收
                fileOutputStream.write(buffer,0,length);
            }

            //给客户端返回信息
            outputStream = socket.getOutputStream();
            outputStream.write("Get file!".getBytes());
            socket.shutdownOutput();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //应该自下而上关闭
            if(fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}