/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bai4;

import java.applet.Applet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author zaku
 */
import java.awt.*;
import java.util.Arrays;
public class HandlingArray extends Applet implements Runnable,ActionListener {
    
    Label arrayLabel,infoAppLabel,numberLabel,resultLabel;
    TextField arrayTextField,numberTextField,resultTextField;
    Button sumPositiveButton,searchButton,sortButton,insertButton;
    public void init(){
        setLayout(new BorderLayout());
        infoAppLabel = new Label();
        add(infoAppLabel,BorderLayout.NORTH);
        arrayLabel = new Label("Input Array, Ex: 1,2,3: ");
        arrayTextField = new TextField("");
        numberLabel= new Label("Input a number: ");
        numberTextField = new TextField("0");
        resultLabel = new Label("Ket qua: ");
        resultTextField = new TextField();
        Panel contentPanel = new Panel();
        contentPanel.setLayout(new GridLayout(3,2));
        contentPanel.add(arrayLabel);
        contentPanel.add(arrayTextField);
        contentPanel.add(numberLabel);
        contentPanel.add(numberTextField);
        contentPanel.add(resultLabel);
        contentPanel.add(resultTextField);

        add(contentPanel,BorderLayout.CENTER);
        
        sumPositiveButton = new Button("Tong so duong");
        searchButton = new Button("Tim kiem");
        sortButton = new Button("Sap xep");
        insertButton = new Button("Chen");
        Panel buttonPanel = new Panel();
        buttonPanel.setLayout(new GridLayout(1,4));
        buttonPanel.add(sumPositiveButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(sortButton);
        buttonPanel.add(insertButton);
        add(buttonPanel,BorderLayout.SOUTH);
        
        sumPositiveButton.addActionListener(this);
        searchButton.addActionListener(this);
        sortButton.addActionListener(this);
        insertButton.addActionListener(this);
        

    }
    
    @Override
    public void run() {
        
    }
    int[] toArray(String str){
        str =str.trim();
        String[] arrayString= str.replaceAll("\\[", "").replaceAll("\\]", "").split(",");
        int[] results = new int[arrayString.length];
        for(int i=0,j=0;i<arrayString.length;i++){
            try{
                 results[j++]=Integer.parseInt(arrayString[i]);
            }catch(Exception ex){
            }
        }
        return results;
    }
    int sumPositiveNumber(int[] arr){
        int result=0;
        for(int i=0;i<arr.length;i++){
            if(arr[i]>0&&arr[i]%2==1){
                result+=arr[i];
            }
        }
        return result;
    } 
    int searchNumber(int number,int[] arr){
        for(int i=0;i<arr.length;i++){
            if(number==arr[i]){
                return i+1;
            }
        }
        return 0;
    }
    public String toString(int[] arr){
       String result="[";
       for(int i=0;i<arr.length-1;i++){
           result+=arr[i]+",";
       }
       result+=arr[arr.length-1]+"]";
       return result;
    }
    @Override
    public void actionPerformed(ActionEvent evt) {
        String inputString = arrayTextField.getText();
        int[] arrayNumber= toArray(inputString);
        if (evt.getSource()==sumPositiveButton){
            resultTextField.setText(""+sumPositiveNumber(arrayNumber));
        }
        if (evt.getSource()==searchButton){
            int number =0;
            try{
                number = Integer.parseInt(numberTextField.getText());
            }catch(Exception ex){
            }
            resultTextField.setText(""+searchNumber(number,arrayNumber));
        }
        if(evt.getSource()==sortButton){
            Arrays.sort(arrayNumber);
            resultTextField.setText(toString(arrayNumber));

        }
        if (evt.getSource()==insertButton){
            int number =0;
            try{
                number = Integer.parseInt(numberTextField.getText());
            }catch(Exception ex){
            }
           arrayNumber=Arrays.copyOf(arrayNumber, arrayNumber.length + 1);
           arrayNumber[arrayNumber.length-1] = number;
           Arrays.sort(arrayNumber);
           resultTextField.setText(toString(arrayNumber));
        }
        
    }
    
}
