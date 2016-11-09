package com.marsch.java;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by marsch on 10/4/16.
 */
public class Server implements Runnable{
    ServerSocket serverSocket;
    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    Thread thread;
    private int port;

    public Server(int port){
        this.port = port;
        try {
            serverSocket = new ServerSocket(port);
            socket = serverSocket.accept();
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        thread = new Thread(this);
    }

    public void start(){
        thread.start();
    }

    @Override
    public void run() {
        while(true){
            try {
                String input = dataInputStream.readUTF();
                dataOutputStream.writeUTF("Hello from server");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] agrs){
        new Server(7777).start();
    }
}
