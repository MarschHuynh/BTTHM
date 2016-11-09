package com.marsch.java.BTTS1.Bai1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.AbstractCollection;

/**
 * Created by marsch on 11/8/16.
 */

class Process implements Runnable{
    DataOutputStream outputStream;
    DataInputStream inputStream;
    Socket socket;

    public Process(Socket socket) throws IOException {
        socket = socket;
        inputStream = new DataInputStream(socket.getInputStream());
        outputStream = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        try {
//            while(inputStream.available()==0){
                String message = inputStream.readUTF();
                outputStream.writeUTF(message.toUpperCase()+"\n"+message.toLowerCase()+"\n"+message.split(" ").length);
                outputStream.writeUTF(message.toLowerCase());
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
public class Server {
    public static void main(String agrs[]) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5050);

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("New client connected");
            Process process = new Process(socket);
            new Thread(process).start();
        }
    }
}
