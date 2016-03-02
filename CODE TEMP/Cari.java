package kp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

/**
 *
 * @author Gina
 */
public class Cari {

    
            
    Cari() {
        JFrame frame1 = new JFrame("Daftar Surat");
        frame1.setVisible(true);
        frame1.setSize(300, 400);
        frame1.setLayout(new FlowLayout(FlowLayout.CENTER));
        frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel panel = new JPanel();
        JButton buttonOK = new JButton("OK");
        panel.add(buttonOK);
        frame1.add(buttonOK);
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        JButton buttonReset = new JButton("Reset");
        panel.add(buttonReset);
        frame1.add(buttonReset);
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        //pas dicancel dia nutup 
        JButton buttonCancel = new JButton("Cancel");
        panel.add(buttonCancel);
        frame1.add(buttonCancel);
        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
    
    /**
     * Adding Button
     * Reset, OK, Cancel
     *
    */
    

    public static void main(String[] args) {
        Cari cr = new Cari();
    }
}
