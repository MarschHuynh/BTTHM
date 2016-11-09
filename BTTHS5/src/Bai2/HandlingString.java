/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bai2;

/**
 *
 * @author zaku
 */
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
public class HandlingString extends Applet implements Runnable,ActionListener{
    Button reverseButton,upperButton,lowerButton,convertButton;
    Thread t;
    int count=0;
    Label stringHandelLabel,infoAppLabel,resultLabel;
    TextField stringHandelTextField,resultTextField;
    public void init(){
        setLayout(new BorderLayout());
        infoAppLabel = new Label();
        add(infoAppLabel,BorderLayout.NORTH);
        Panel contentPanel  = new Panel();
        contentPanel.setLayout(new GridLayout(2,2));
        stringHandelLabel = new Label("Nhap String");
        reverseButton = new Button("Dao");
        upperButton = new Button("Chu Hoa");
        lowerButton = new Button("Chu thuong");
        convertButton = new Button("Chuyen doi Hoa Thuong");
        resultLabel = new Label("Result");
        stringHandelTextField = new TextField("");
        resultTextField = new TextField("0");
        
        Panel buttonPanel = new Panel();
        buttonPanel.setLayout(new GridLayout(1,4));
        buttonPanel.add(reverseButton);
        buttonPanel.add(upperButton);
        buttonPanel.add(lowerButton);
        buttonPanel.add(convertButton);
        
        add(buttonPanel,BorderLayout.SOUTH);
        contentPanel.add(stringHandelLabel);
        contentPanel.add(stringHandelTextField);
        contentPanel.add(resultLabel);
        contentPanel.add(resultTextField);
        add(contentPanel,BorderLayout.CENTER);
        
        reverseButton.addActionListener(this);         
        upperButton.addActionListener(this);
        lowerButton.addActionListener(this);
        convertButton.addActionListener(this);
    }
    public void start(){
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
          String startText= "Xu ly xau";
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


    public void actionPerformed(ActionEvent evt) {
        String stringInput = stringHandelTextField.getText();
        StringBuilder convert = null ;
        char convertChart[]= stringInput.toCharArray();
        if(evt.getSource()==reverseButton){
            resultTextField.setText(new StringBuilder(stringInput).reverse().toString());
        }
        if(evt.getSource()==upperButton){
            resultTextField.setText(stringInput.toUpperCase());
        }
        if(evt.getSource()==lowerButton){
            resultTextField.setText(stringInput.toLowerCase());
        }
        if(evt.getSource()==convertButton)
            for(int i=0;i<stringInput.length();i++){
                if(convertChart[i]>='A'&& convertChart[i]<='Z'){
//                    convert.setCharAt(i,stringInput.toLowerCase().charAt(i));
                    convertChart[i]=Character.toLowerCase(convertChart[i]);
                }
                else if(convertChart[i]>='a'&&convertChart[i]<='z'){
//                    convert.setCharAt(i,stringInput.toUpperCase().charAt(i));
                    convertChart[i]=Character.toUpperCase(convertChart[i]);

                }
//                convert.setCharAt(i,stringInput.charAt(i));
            }
            resultTextField.setText(new String(convertChart));
        
        }
}
