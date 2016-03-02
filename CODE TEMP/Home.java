package kp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Gina
 */
public class Home extends JFrame {

    JFrame frameHome;
    JPanel panelHome;
    JLabel labelPP;
    JButton buttonGo;

    Home() {
        /*
         Pembuatan jendela aplikasi
         */
        final JFrame frameHome = new JFrame("Home");
        frameHome.setSize(300, 400);
        frameHome.setVisible(true);
        frameHome.setLayout(new FlowLayout(FlowLayout.CENTER));
        frameHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /*
        
         */
        JPanel panelHome = new JPanel();
        /**
         * Pembuatan Label untuk halaman utama
         */
        labelPP = new JLabel("Pusat Pengendalian Data" + " " + "(PPD)" + " " + "PT PP Tbk. Cabang IX" + " " + "Pekanbaru - Riau");
        labelPP.setFont(new Font("Serif", Font.BOLD, 24));
        panelHome.add(labelPP);
        frameHome.add(labelPP);

        buttonGo = new JButton("ENTER HOME");
        panelHome.add(buttonGo);
        frameHome.add(buttonGo);
        buttonGo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Masukkan_Surat ms = new Masukkan_Surat();
                frameHome.setVisible(false);
            }
        });
    }

    public static void main(String[] args) {
        Home home = new Home();
    }

}

/**
 * Ketika data dimasukkan, maka langsung masuk ke folder yang ada di komputer,
 * nah ketika daftar surat di buka maka dia langsung ngambil data - data yang
 * ada di folder.
 */
