package com.marsch.java;

import java.awt.*;

/**
 * Created by marsch on 10/11/16.
 */
public class Test extends Frame{
    public static void main(String args[]){
        Test f= new Test();
        f.setTitle("Hello");
        f.setBounds(300,200,200,200);
        f.setLayout(new FlowLayout());
        f.add( new Checkbox("Sport"));
        f.add( new Checkbox("Music"));
        f.add( new Checkbox("Travel"));
        CheckboxGroup cg=new CheckboxGroup();
        f.add(new Checkbox ("Male", cg, false));
        f.add(new Checkbox ("Female", cg, true));
        f.setVisible(true);
    }
}

