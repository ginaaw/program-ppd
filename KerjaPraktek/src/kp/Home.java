package kp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Gina Andriyani dan Siti Jumaliaya
 */
public class Home extends JFrame {

    JFrame frameHome;
    JPanel panelHome;
    JLabel labelPP;
    JButton buttonGo;
    JFrame frameCek;
    JPanel panelCek;
    JLabel pass;
    JTextField fieldCek;
    JButton okCek;
    String password = "ADMIN";
    String hasilCek = "belum ada";

    Home() {
        /*
         Pembuatan jendela aplikasi
         */
        frameHome = new JFrame("Home");
        frameHome.setBackground(Color.BLUE);
        frameHome.setSize(630, 480);
        JPanel panelHome = new JPanel(new GridLayout(3, 1));
        frameHome.setLayout(new FlowLayout(FlowLayout.CENTER));
        frameHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameHome.setResizable(false);

        /**
         * Pembuatan Label untuk halaman utama
         */
        ImageIcon img = new ImageIcon(getClass().getResource("background.jpg"));

        buttonGo = new JButton(img);
        buttonGo.setBounds(500, 380, 250, 100);
        getContentPane().add(buttonGo);
        panelHome.add(buttonGo);
        frameHome.add(buttonGo);
        frameHome.setVisible(true);
        panelHome.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelHome.setBackground(Color.YELLOW);
        buttonGo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    frameHome.dispose();
                    Jendela jen = new Jendela();
                    jen.fr();
                } catch (SQLException ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public static void main(String[] args) {
        Home home = new Home();
    }

}
