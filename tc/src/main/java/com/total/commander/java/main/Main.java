package com.total.commander.java.main;

import com.total.commander.java.view.TCWindow;

/**
 * Created by badmin on 2017.08.28..
 */
public class Main {

    public static void main(String[] args){
        System.out.println("Creating window ...");
        TCWindow window = new TCWindow("/home/badmin", "/home/badmin/Hobby");
        System.out.println("Done");
    }

}
