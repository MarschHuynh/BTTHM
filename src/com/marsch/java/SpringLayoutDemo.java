package com.marsch.java;

/**
 * Created by marsch on 10/18/16.
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SpringLayoutDemo {
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;
    private JLabel msglabel;

    public SpringLayoutDemo(){
        prepareGUI();
    }

    public static void main(String[] args){
        SpringLayoutDemo swingLayoutDemo = new SpringLayoutDemo();
        swingLayoutDemo.showSpringLayoutDemo();
    }

    private void prepareGUI(){
        mainFrame = new JFrame("Java SWING Examples");
        mainFrame.setSize(400,400);
        mainFrame.setLayout(new GridLayout(3, 1));

        headerLabel = new JLabel("",JLabel.CENTER );
        statusLabel = new JLabel("",JLabel.CENTER);

        statusLabel.setSize(350,100);
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

    private void showSpringLayoutDemo(){

        headerLabel.setText("Layout in action: SpringLayout");
        SpringLayout layout = new SpringLayout();

        JPanel panel = new JPanel();
        panel.setLayout(layout);
        JLabel label = new JLabel("Enter Name: ");
        JTextField textField = new JTextField("", 15);
        panel.add(label);
        panel.add(textField);

        layout.putConstraint(SpringLayout.WEST, label,5,
                SpringLayout.WEST, controlPanel);
        layout.putConstraint(SpringLayout.NORTH, label,5,
                SpringLayout.NORTH, controlPanel);
        layout.putConstraint(SpringLayout.WEST, textField,5,
                SpringLayout.EAST, label);
        layout.putConstraint(SpringLayout.NORTH, textField,5,
                SpringLayout.NORTH, controlPanel);
        layout.putConstraint(SpringLayout.EAST, panel,5,
                SpringLayout.EAST, textField);
        layout.putConstraint(SpringLayout.SOUTH, panel,5,
                SpringLayout.SOUTH, textField);
        controlPanel.add(panel);
        mainFrame.setVisible(true);
    }
}
