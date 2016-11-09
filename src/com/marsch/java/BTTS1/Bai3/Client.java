package com.marsch.java.BTTS1.Bai3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by marsch on 10/18/16.
 */

class ProcessClient implements Runnable {

    DataOutputStream outputStream = null;
    DataInputStream inputStream = null;
    String name;

    public ProcessClient(Socket socket){
        try {
            outputStream = new DataOutputStream(socket.getOutputStream());
            inputStream = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(true){
            try {
                while (inputStream.available()!=0){
                    String message = inputStream.readUTF();
                    System.out.println(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

public class Client {

    public static void main(String agrs[]){

        DataOutputStream outputStream = null;
        DataInputStream inputStream = null;

        try {
            Socket socket = new Socket("127.0.0.1",2000);
            outputStream = new DataOutputStream(socket.getOutputStream());
            ProcessClient process = new ProcessClient(socket);
            new Thread(process).start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        boolean stop = false;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input your name:");
        String name = scanner.nextLine();

        try {
            outputStream.writeUTF(name+" connected");
        } catch (IOException e) {
            e.printStackTrace();
        }

        while(!stop){

            String message = scanner.nextLine();

            try {
                outputStream.writeUTF(""+name+": "+message);
                if (message.equals("stop")){
                    System.out.println("STOP");
                    stop = true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
