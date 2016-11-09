package com.marsch.java;

/**
 * Created by marsch on 10/18/16.
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FlowLayoutDemo {
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;
    private JLabel msglabel;

    public FlowLayoutDemo(){
        prepareGUI();
    }

    public static void main(String[] args){
        FlowLayoutDemo swingLayoutDemo = new FlowLayoutDemo();
        swingLayoutDemo.showFlowLayoutDemo();
    }

    private void prepareGUI(){
        mainFrame = new JFrame("Java SWING Examples");
        mainFrame.setSize(400,400);
        GridLayout gridLayout = new GridLayout(3,1);
        mainFrame.setLayout(gridLayout);

        headerLabel = new JLabel("",JLabel.CENTER );
        headerLabel.setOpaque(true);
        headerLabel.setBackground(Color.GREEN);

        statusLabel = new JLabel("Status",JLabel.CENTER);
        statusLabel.setOpaque(true);
        statusLabel.setSize(350,100);
        statusLabel.setBackground(Color.CYAN);

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);
    }

    private void showFlowLayoutDemo(){
        headerLabel.setText("Layout in action: FlowLayout");

        JPanel panel = new JPanel();
        panel.setBackground(Color.darkGray);
        panel.setSize(200,200);
        FlowLayout layout = new FlowLayout();
        layout.setHgap(10);
        layout.setVgap(10);
        panel.setLayout(layout);

        panel.add(new JButton("OK"));
        panel.add(new JButton("Cancel"));

        controlPanel.add(panel);

        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.GREEN);
        panel1.setSize(200,200);
        FlowLayout layout1 = new FlowLayout();
        layout1.setHgap(10);
        layout1.setVgap(10);
        panel1.setLayout(layout1);

        panel1.add(new JButton("OK"));
        panel1.add(new JButton("Cancel"));

        controlPanel.add(panel1);

        mainFrame.setVisible(true);
    }
}







