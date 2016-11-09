package com.marsch.java;

import javax.swing.*;
import javax.swing.text.html.ListView;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by marsch on 10/4/16.
 */
public class Client {
    Socket socket ;
    DataOutputStream outputStream;
    DataInputStream inputStream;
    String ipAddress;
    int port;
    int width = 800;
    int height = 600;
    JFrame mainFrame;

    JPanel userNamePanel;
    JPanel chatViewPanel;
    JPanel chatTextPanel;

    public void StartUI(){
        mainFrame = new JFrame();
        mainFrame.setSize(800,600);
        mainFrame.setLayout(new GridLayout(4,1));

        userNamePanel = new JPanel();
        userNamePanel.setLayout(new FlowLayout());

        userNamePanel.add(new JLabel("Name:"));
        userNamePanel.add(new JTextField(20));
        userNamePanel.setSize(100,200);
        userNamePanel.setBackground(Color.GREEN);

        mainFrame.add(userNamePanel);

        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });

        mainFrame.setVisible(true);
    }
    public Client(String ip,int port){

        this.ipAddress = ip;
        this.port = port;

//        try {
//            socket = new Socket(ip,port);
//            inputStream = new DataInputStream(socket.getInputStream());
//            outputStream = new DataOutputStream(socket.getOutputStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public static void main(String[] agrs){
        new Client("127.0.0.1",7777).StartUI();
    }
}
