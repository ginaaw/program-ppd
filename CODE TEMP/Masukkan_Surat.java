package kp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Gina
 */
public class Masukkan_Surat extends JFrame {

    JFrame frame1;
    JPanel panel1, panel2;
    JLabel label1, label2;
    JButton buttonMasuk, buttonKeluar, buttonBack;

    Masukkan_Surat() {
        final JFrame frame1 = new JFrame("Masukkan Surat");
        frame1.setSize(300, 400);
        frame1.setVisible(true);
        frame1.setLayout(new FlowLayout(FlowLayout.CENTER));
        frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();

        buttonMasuk = new JButton("Surat Masuk");
        buttonMasuk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Daftar_SuratMasuk sm = new Daftar_SuratMasuk();
                } catch (SQLException ex) {
                    Logger.getLogger(Masukkan_Surat.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        panel.add(buttonMasuk);
        frame1.add(buttonMasuk);

        buttonKeluar = new JButton("Surat Keluar");
        buttonKeluar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Daftar_SuratKeluar sk = new Daftar_SuratKeluar();
                } catch (SQLException ex) {
                    Logger.getLogger(Masukkan_Surat.class.getName()).log(Level.SEVERE, null, ex);
                }
                frame1.setVisible(false);
            }
        });
        panel.add(buttonKeluar);
        frame1.add(buttonKeluar);
        
        

        buttonBack = new JButton("Kembali");
        buttonBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Home hm = new Home();
                frame1.setVisible(false);
            }
        });
        panel.add(buttonBack);
        frame1.add(buttonBack);

    }

    public static void main(String[] args) {

        Masukkan_Surat ms = new Masukkan_Surat();

    }
}
