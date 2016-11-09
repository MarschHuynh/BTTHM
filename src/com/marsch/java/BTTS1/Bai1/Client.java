package com.marsch.java.BTTS1.Bai1;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by marsch on 11/8/16.
 */

class C_Process implements Runnable{
    DataOutputStream outputStream;
    DataInputStream inputStream;
    Socket socket;

    public C_Process(Socket socket) throws IOException {
        socket = socket;
        inputStream = new DataInputStream(socket.getInputStream());
        outputStream = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        while(true) {
            try {
                while (inputStream.available() == 0) {
                    String message = inputStream.readUTF();
                    System.out.print(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
public class Client {
    public static void main(String agrs[]) throws IOException {
        Socket socket = new Socket("localhost",5050);
        C_Process process = new C_Process(socket);
        new Thread(process).start();

        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

        Scanner scanner = new Scanner(System.in);
        System.out.print("Please input a string:");
        String message = scanner.nextLine();
        dataOutputStream.writeUTF(message);
    }
}
