package com.marsch.java.BTTS2.Bai1;

import sun.security.x509.IPAddressName;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

/**
 * Created by marsch on 11/9/16.
 */

public class Server {
    public static void main(String agrs[]) throws IOException {
        DatagramSocket socket = new DatagramSocket(8787);

        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];

        while (true){
            DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);
            socket.receive(receivePacket);
            int port = receivePacket.getPort();
            InetAddress inetAddress = receivePacket.getAddress();
            String receiveString = new String(receivePacket.getData());
            receiveString = receiveString.substring(0,receivePacket.getLength());

            String sendString = receiveString.toUpperCase() + "\n" +receiveString.toLowerCase() + "\n" + receiveString.split(" ").length;
            sendData = sendString.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData,sendString.length(),inetAddress,port);

            socket.send(sendPacket);
        }
//        socket.close();
    }
}
