package kp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Gina Andriyani dan Siti Jumaliaya
 */
public class Cari {

    JPanel panelCari = new JPanel();

    String cariMasuk = "belum ada isinya";
    String cariKeluar = "belum ada isinya";

    Cari() {
    }

    public JPanel intipSurat() {


        ImageIcon berkasSurat = new ImageIcon(getClass().getResource("BerkasSurat.jpg"));
        JLabel labelCari = new JLabel(berkasSurat);
        labelCari.setFont(new Font("Times New Roman", Font.BOLD, 72));
        panelCari.add(labelCari);

        final JTextField teks = new JTextField(40);
        teks.setSize(30, 5);
        panelCari.add(teks);

        ImageIcon buttonSurat = new ImageIcon(getClass().getResource("cariFile.jpg"));
        JButton buttonCari = new JButton(buttonSurat);
        buttonCari.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ConnectDB c = new ConnectDB();
                c.makeConnection();
                //mencari nama file surat
                //mengembalikan nama file
                cariMasuk = c.cariSuratMasuk(teks.getText());
                
                cariKeluar = c.cariSuratKeluar(teks.getText());
                
                if (!cariMasuk.equalsIgnoreCase("nothing")) {
                    String extM = cariMasuk.substring(cariMasuk.lastIndexOf('.'), cariMasuk.length());
                    try {
                        File filess = new File("D:" + File.separator + "SURAT" + File.separator + "Surat Masuk" + File.separator + "Internal" + File.separator + teks.getText() + extM);
                        Desktop.getDesktop().open(filess);
                    } catch (IOException ex) {
                        Logger.getLogger(Cari.class.getName()).log(Level.SEVERE, null, ex);
                        try {
                            File filess = new File("D:" + File.separator + "SURAT" + File.separator + "Surat Masuk" + File.separator + "Eksternal" + File.separator + teks.getText() + extM);
                            Desktop.getDesktop().open(filess);
                        } catch (IOException ex2) {
                            Logger.getLogger(Cari.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(null, "File Surat tidak ada");
                        }
                    }
                } else if (!cariKeluar.equalsIgnoreCase("nothing")) {
                    String extK = cariKeluar.substring(cariKeluar.lastIndexOf('.'), cariKeluar.length());
                    try {
                        File filess = new File("D:" + File.separator + "SURAT" + File.separator + "Surat Keluar" + File.separator + "Internal" + File.separator + teks.getText() + extK);
                        Desktop.getDesktop().open(filess);
                    } catch (IOException ex) {
                        try {
                            File filess = new File("D:" + File.separator + "SURAT" + File.separator + "Surat Keluar" + File.separator + "Eksternal" + File.separator + teks.getText() + extK);
                            Desktop.getDesktop().open(filess);
                        } catch (IOException ex2) {
                            Logger.getLogger(Cari.class.getName()).log(Level.SEVERE, null, ex);
                            JOptionPane.showMessageDialog(null, "File Surat tidak ada");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "File Surat tidak ada");
                }

            }
        });

        panelCari.add(buttonCari);

        return panelCari;
    }

    public static void main(String[] args) {
        Cari cr = new Cari();
    }
}
