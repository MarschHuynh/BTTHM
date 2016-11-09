/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bai3;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 *
 * @author zaku
 */
public class HandlingString extends Applet implements Runnable,ActionListener{
    Button countWordButton,printWordButton,frequencyWordButton;
    Thread t;
    int count=0;
    Label stringHandelLabel,infoAppLabel,resultLabel;
    TextField stringHandelTextField;
    TextArea resultTextField;
    public void init(){
        setLayout(new BorderLayout());
        infoAppLabel = new Label();
        add(infoAppLabel,BorderLayout.NORTH);
        Panel contentPanel  = new Panel();
        contentPanel.setLayout(new GridLayout(2,2));
        stringHandelLabel = new Label("Nhap String");
        countWordButton = new Button("Dem so tu");
        printWordButton = new Button("List cac tu");
        frequencyWordButton = new Button("Tan so cac tu");
        resultLabel = new Label("Result");
        stringHandelTextField = new TextField("");
        resultTextField = new TextArea();
        
        Panel buttonPanel = new Panel();
        buttonPanel.setLayout(new GridLayout(1,3));
        buttonPanel.add(printWordButton);
        buttonPanel.add(countWordButton);
        buttonPanel.add(frequencyWordButton);
        
        add(buttonPanel,BorderLayout.SOUTH);
        contentPanel.add(stringHandelLabel);
        contentPanel.add(stringHandelTextField);
        contentPanel.add(resultLabel);
        contentPanel.add(resultTextField);
        add(contentPanel,BorderLayout.CENTER);
        
        countWordButton.addActionListener(this);         
        printWordButton.addActionListener(this);
        frequencyWordButton.addActionListener(this);
    }
    public void start(){
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        String startText= "Xu ly xau bai3";
        int i=0;
        while(true){
            try{
                if (startText.length()-1==i) i=1;
                else i++;
                infoAppLabel.setText(startText.substring(0,i+1));
                Thread.sleep(100);
                count++;
            }catch(InterruptedException e){}
        }
    }

    public int countWordInString(String input){
        String trimStringInput;
        trimStringInput=input.trim();
        if(trimStringInput.isEmpty()){
            return 0;
        } 
        return trimStringInput.split("\\s+").length;
    }
    public String[] wordsInString(String input){
        String trimStringInput;
        trimStringInput=input.trim();
        if(trimStringInput.isEmpty()){
           return null;
        } 
        return trimStringInput.split("\\s+");
    }
    public void actionPerformed(ActionEvent evt) {
        String stringInput = stringHandelTextField.getText();
        String[] wordsPrint;
        String result = new String();
        if(evt.getSource()==printWordButton){
            wordsPrint = wordsInString(stringInput);
            for(int i =0;i<wordsPrint.length;i++){
                result+=wordsPrint[i]+"\n";
            }
            resultTextField.setText(result);
        }
        if(evt.getSource()==countWordButton){
          resultTextField.setText(""+countWordInString(stringInput));
        }
        
        if(evt.getSource()==frequencyWordButton){
            wordsPrint = wordsInString(stringInput);
            Arrays.sort(wordsPrint);
            int frequencyNum=1;
            for(int i =0;i<wordsPrint.length;i++){
                if(i!=wordsPrint.length-1 &&wordsPrint[i].equals(wordsPrint[i+1])){
                    frequencyNum++;
                }else{
                    result+= wordsPrint[i]+ " : "+frequencyNum+"\n";
                    frequencyNum=1;
                }
            }
            resultTextField.setText(result);
        }
    }
}
