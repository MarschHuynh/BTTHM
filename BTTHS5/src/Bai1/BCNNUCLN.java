/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bai1;

/**
 *
 * @author zaku
 */
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
public class BCNNUCLN extends Applet implements Runnable,ActionListener{
    Button UCLNButton,BCNNButton;
    Thread t;
    int count=0;
    Label number1Label,resultLabel,infoAppLabel;
    Label number2Label;
    TextField number1TextField;
    TextField number2TextField,resultTextField;
    public int LCM(int num1,int num2){
       return num1*num2/GCD(num1,num2); 
    }
    public int GCD(int num1,int num2){ //Uoc chung lon nhat Gratest common divisor
         int temp;
        while(num1!=num2){
         if(num1>num2){
             num1-=num2;
         }else{
             num2-=num1;
         }   
        }
        return num1;
    }
    public void init(){
        setLayout(new BorderLayout());
        infoAppLabel = new Label("Tim UCLN,BCNN");
        add(infoAppLabel,BorderLayout.NORTH);
        Panel contentPanel  = new Panel();
        contentPanel.setLayout(new GridLayout(4,2));
        number1Label = new Label("Number 1: ");
        number2Label = new Label("Number 2: ");
        UCLNButton = new Button("Tinh UCLN");
        BCNNButton = new Button("Tinh BCNN");
        number1TextField = new TextField("0");
        number2TextField = new TextField("0");
        resultLabel = new Label("Result");
        resultTextField = new TextField("0");
            
        contentPanel.add(number1Label);
        contentPanel.add(number1TextField);
        contentPanel.add(number2Label);
        contentPanel.add(number2TextField);
        contentPanel.add(UCLNButton);
        contentPanel.add(BCNNButton);
        contentPanel.add(resultLabel);
        contentPanel.add(resultTextField);
        add(contentPanel,BorderLayout.CENTER);
        UCLNButton.addActionListener(this);         
        BCNNButton.addActionListener(this);
        
    }
  
    public void start(){
        t = new Thread(this);
        t.start();
                
    }
    public void run(){
        String startText= "Tim Uoc chung lon nhat, Boi chung nho nhat";
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
   
    public void actionPerformed(ActionEvent evt){
        if(evt.getSource()==UCLNButton){
            resultTextField.setText(""+this.GCD(Integer.parseInt(number1TextField.getText()),Integer.parseInt(number2TextField.getText())));
        }
        if(evt.getSource()==BCNNButton){
            resultTextField.setText(""+this.LCM(Integer.parseInt(number1TextField.getText()),Integer.parseInt(number2TextField.getText())));
        }
    }
}

