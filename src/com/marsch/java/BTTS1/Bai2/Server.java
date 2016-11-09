package com.marsch.java.BTTS1.Bai2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

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

    public static String caculator(String string) throws ScriptException {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        String result = engine.eval(string).toString();
        return result;
    }


    @Override
    public void run() {
        try {
            String message = inputStream.readUTF();
            message = caculator(message);
            outputStream.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ScriptException e) {
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
