package com.marsch.java.BTTS2.Bai1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.Scanner;

/**
 * Created by marsch on 11/9/16.
 */
public class Client {
    public static void main(String agrs[]) throws IOException {
        Scanner scanner = new Scanner(System.in);

        DatagramSocket datagramSocket = new DatagramSocket();
        InetAddress ipAddressName = Inet4Address.getByName("localhost");

        byte[] receviveData = new byte[1024];
        byte[] sendData = new byte[1024];

        System.out.print("Please input a string: ");
        String message = scanner.nextLine();
        sendData = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData,message.length(),ipAddressName,8787);
        datagramSocket.send(sendPacket);

        DatagramPacket receivePacket = new DatagramPacket(receviveData,receviveData.length);
        datagramSocket.receive(receivePacket);
        String receiveMessage = new String(receivePacket.getData());

        System.out.print(receiveMessage.substring(0,receivePacket.getLength()));
        datagramSocket.close();
    }
}
