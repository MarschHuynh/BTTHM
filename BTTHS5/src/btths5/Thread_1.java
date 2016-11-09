/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btths5;

/**
 *
 * @author zaku
 */
 
class Thread_a extends Thread {
    public void run() {
        for (int i = 1; i < 10; i += 2) {
            System.out.print(i + " ");
            try {
                Thread.sleep(500);
            } catch (Exception e) {
            }
        }
    }
}
class Thread_b extends Thread{
    public void run(){
        for(int i=2; i<10;i+=2){
            System.out.print(i+" ");
            try{
                Thread.sleep(100);
            }catch(Exception e){}
        }
    }
}
 
public class Thread_1 {
 
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Thread t1 = new Thread_a();
        Thread t2 = new Thread_b();
        t1.start();
        t2.start();
       
    }
 
}