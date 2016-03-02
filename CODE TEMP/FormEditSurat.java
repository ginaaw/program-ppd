/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kp;

import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Gina
 */
public class FormEditSurat {
    public FormEditSurat(){
        JFrame frameForm = new JFrame("Form Edit Surat");
        frameForm.setSize(300, 400);
        frameForm.setVisible(true);
        frameForm.setLayout(new FlowLayout(FlowLayout.CENTER));
        frameForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel panel = new JPanel();
        
        JLabel labelNo_Surat = new JLabel("No_Surat");
        panel.add(labelNo_Surat);
        frameForm.add(labelNo_Surat);
        JTextField fieldNo_Surat = new JTextField(20);
        panel.add(fieldNo_Surat);
        frameForm.add(fieldNo_Surat);
        
        
        
    }
    public static void main(String[] args){
        
    }
}
