package com.marsch.java.BTTS2.Bai3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;

/**
 * Created by marsch on 10/18/16.
 */

class ProcessClient implements Runnable {

    DataOutputStream outputStream = null;
    DataInputStream inputStream = null;
    String name;
    DatagramSocket socket;

    byte[] sendData = new byte[1024];
    byte[] receiveData = new byte[1024];

    public ProcessClient(DatagramSocket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        while(true){
            try {
                DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);
                socket.receive(receivePacket);

                String message = new String(receivePacket.getData());
                System.out.println(">>>>"+message.substring(0,receivePacket.getLength()));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

public class Client {

    public static void main(String agrs[]) throws IOException {
        DatagramSocket socket = new DatagramSocket();
        byte[] sendData = new byte[1024];
        ProcessClient process = new ProcessClient(socket);
        new Thread(process).start();

        Inet4Address ipAddress = (Inet4Address) Inet4Address.getByName("localhost");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Please input your name:");
        String name = scanner.nextLine();
        String messageStart = name + " connected";
        sendData = messageStart.getBytes();
        DatagramPacket sendPaket = new DatagramPacket(sendData,messageStart.length(),ipAddress,8787);
        socket.send(sendPaket);

        boolean stop = false;

        while (!stop){
            String message = scanner.nextLine();
            message = name +": "+ message;
            sendData = message.getBytes();
            sendPaket = new DatagramPacket(sendData,message.length(),ipAddress,8787);
            socket.send(sendPaket);
        }
    }
}
