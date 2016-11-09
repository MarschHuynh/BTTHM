/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bai5;

/**
 *
 * @author zaku
 */
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
public class HandlingMatrix extends Applet implements Runnable,ActionListener{

    int[][] matrix;
    int[] X=null;
    int column,row;
    TextArea inputTextArea,titleInputTextArea;
    TextField resultTextField,numberTextField;
    Button multiButton,createButton,deleteButton;
    Label inputLabel,resultLabel,numberLabel;
    public void init(){
      
        setLayout(new BorderLayout());
        Panel contentPanel = new Panel();
        contentPanel.setLayout(new GridLayout(3,2));
        inputLabel = new Label("<html>Nhap ma tran 2 chieu<br>1,2,3,4<br>2,3,4,5<br>1,2,6,4</html>");
//        titleInputTextArea = new TextArea("Nhap ma tran 2 chieu \nEx:{1,2,3,4}{2,3,4,5}:");
//        titleInputTextArea.setEditable(false);
//        titleInputTextArea.setBackground();
        inputTextArea = new TextArea("1,2,3,4\n2,3,4,5\n1,2,6,4");
        numberLabel = new Label("Nhap i ");
        numberTextField = new TextField("2");
        
        resultLabel = new Label("Result: ");
        resultTextField = new TextField();
        
        contentPanel.add(inputLabel);
        contentPanel.add(inputTextArea);
         contentPanel.add(numberLabel);
        contentPanel.add(numberTextField);
        contentPanel.add(resultLabel);
        contentPanel.add(resultTextField);
        
        add(contentPanel, BorderLayout.CENTER);
        
        Panel buttonPanel = new Panel();
        buttonPanel.setLayout(new GridLayout(3,1));
        
        multiButton = new Button("Nhan boi 3 dong dau tien");
        createButton = new Button("Tao mang X[i] lon nhat dong i");
        deleteButton = new Button("Xoa phan tu dau tien mang X[i]");
        buttonPanel.add(multiButton);
        buttonPanel.add(createButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel,BorderLayout.SOUTH);
        multiButton.addActionListener(this);
        createButton.addActionListener(this);
        deleteButton.addActionListener(this);
    }
    
    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public int[][] toMatrixInt(String inputString){
        String[] arrayString1,arrayString2;
        arrayString1 = inputString.split("\\n+");
        int[][] result = new int[arrayString1.length][];        
        for(int i=0;i<arrayString1.length;i++){
            arrayString2 = arrayString1[i].split(",");
            result[i] = new int[arrayString2.length];
            for(int j =0;j<arrayString2.length;j++){
                result[i][j]=Integer.parseInt(arrayString2[j]);
            }
        }
        return result;
    }
    @Override
    public void actionPerformed(ActionEvent evt) {
        Bai4.HandlingArray handlingArray = new Bai4.HandlingArray();
        String inputString = inputTextArea.getText();
        String numberString = numberTextField.getText();
        matrix = toMatrixInt(inputString);
//        System.out.println(inputString);
        String[] arrayString1,arrayString2;
        if(evt.getSource()==multiButton){
          int result=1;
          for(int j=0;j<matrix[0].length;j++){
              if(matrix[0][j]%3==0){
                  result*=matrix[0][j];
              }
          }
          resultTextField.setText(""+result);
        }
        if(evt.getSource()==createButton){
            X = new int[matrix[0].length];
            int rowFocusIndex= Integer.parseInt(numberString)-1;
            for(int j=0;j<matrix[0].length;j++){
                int max = matrix[0][j];
                for(int i=1;i<matrix.length;i++){
                    if(matrix[i][j]>max){
                        max = matrix[i][j];
                    }
                }
                X[j]=max;
            }
            resultTextField.setText(handlingArray.toString(X));
        }
        if(evt.getSource()==deleteButton){
            if(X==null){
                X = new int[matrix[0].length];
                int rowFocusIndex= Integer.parseInt(numberString)-1;
                for(int j=0;j<matrix[0].length;j++){
                    int max = matrix[0][j];
                    for(int i=1;i<matrix.length;i++){
                        if(matrix[i][j]>max){
                            max = matrix[i][j];
                        }
                    }
                    X[j]=max;
                }
            }
            for(int i=0;i<X.length-1;i++){
                X[i]=X[i+1];
            }
            if(X.length>1){
                X=Arrays.copyOf(X,X.length-1);
            }
            resultTextField.setText(handlingArray.toString(X));
        }
    }
    
}
