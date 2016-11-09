package com.marsch.java.BTTS1.Bai2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by marsch on 11/8/16.
 */

public class Client {
    public static void main(String agrs[]) throws IOException {
        Socket socket = new Socket("localhost",5050);

        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

        Scanner scanner = new Scanner(System.in);
        System.out.print("Please input a string:");
        String message = scanner.nextLine();
        dataOutputStream.writeUTF(message);

        message = dataInputStream.readUTF();
        System.out.print(message);
    }
}
