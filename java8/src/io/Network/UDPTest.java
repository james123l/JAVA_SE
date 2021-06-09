package io.Network;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPTest {
    public void Client() throws Exception {
        DatagramSocket datagramSocket = new DatagramSocket();

        String string ="Message by UDP";
        //数据
        byte[] data = string.getBytes();
        //接收者的地址
        InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
        DatagramPacket datagramPacket = new DatagramPacket(data,0,data.length,inetAddress,3380);
        datagramSocket.send(datagramPacket);
        datagramSocket.close();
    }
    public void Server() throws Exception {
        //构造 需要端口号
        DatagramSocket datagramSocket = new DatagramSocket(3380);

        byte[] data = new byte[100];
        DatagramPacket datagramPacket = new DatagramPacket(data,0,data.length);
        datagramSocket.receive(datagramPacket);
        System.out.println(new String(datagramPacket.getData(),0,datagramPacket.getLength()));
        datagramSocket.close();

    }
}
