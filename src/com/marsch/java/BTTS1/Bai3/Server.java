package com.marsch.java.BTTS1.Bai3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by marsch on 10/18/16.
 */
class Process implements Runnable {

    DataOutputStream outputStream = null;
    DataInputStream inputStream = null;

    static ArrayList<Socket> listClient = new ArrayList<Socket>();

    public Process(Socket socket){
        try {
            listClient.add(socket);
            outputStream = new DataOutputStream(socket.getOutputStream());
            inputStream = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        boolean stop = false;
        while (!stop) {
            try {
//                while(inputStream.available()==0){
                String message = inputStream.readUTF();
                for (Socket socket : listClient) {
                    outputStream = new DataOutputStream(socket.getOutputStream());
                    outputStream.writeUTF(">> " + message);
                }
//                }
            } catch (Exception e) {
                try {
                    inputStream.close();
                    outputStream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                e.printStackTrace();
            }
        }
    }
}
public class Server {
    public static void main(String agrs[]){
        ServerSocket server;

        try {
            server = new ServerSocket(2000);
            while (true) {
                Socket socket = server.accept();
                Process process = new Process(socket);
                new Thread(process).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

