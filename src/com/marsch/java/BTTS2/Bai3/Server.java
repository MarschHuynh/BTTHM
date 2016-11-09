package com.marsch.java.BTTS2.Bai3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by marsch on 10/18/16.
 */
class IpClient{
    InetAddress ip;
    int port;

    public IpClient(InetAddress ip, int port){
        this.ip = ip;
        this.port = port;
    }
}
class Process implements Runnable {

    DataOutputStream outputStream = null;
    DataInputStream inputStream = null;
    byte[] receiveData = new byte[1024];
    byte[] sendData = new byte[1024];

    static ArrayList<IpClient> listClient = new ArrayList<IpClient>();
    static ArrayList<InetAddress> listIp = new ArrayList<InetAddress>();
    static ArrayList<Integer> listPort = new ArrayList<Integer>();

    DatagramSocket socket;

    public Process(DatagramSocket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        boolean stop = false;
        while (!stop) {
            try {
                DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);
                socket.receive(receivePacket);

                int port = receivePacket.getPort();
                InetAddress ipAddress = receivePacket.getAddress();
                String message = new String(receivePacket.getData());
                message = message.substring(0,receivePacket.getLength());
                System.out.println(message);

                if (!listPort.contains(port)){
                    listClient.add(new IpClient(ipAddress,port));
                    listIp.add(ipAddress);
                    listPort.add(port);
                } else {
                    if (!listIp.contains(ipAddress)){
                        listClient.add(new IpClient(ipAddress,port));
                        listIp.add(ipAddress);
                        listPort.add(port);
                    }
                }

                System.out.println(listClient.size());

                for (IpClient item: listClient){
                    sendData = message.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendData,message.length(),item.ip,item.port);
                    socket.send(sendPacket);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

public class Server {
    public static void main(String agrs[]) throws SocketException {
        DatagramSocket socket = new DatagramSocket(8787);
        Process process = new Process(socket);
        new Thread(process).start();
    }
}

