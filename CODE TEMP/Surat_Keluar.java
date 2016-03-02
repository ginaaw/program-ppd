package kp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.io.File;
import java.sql.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.*;

/**
 *
 * @author Gina
 */
public class Surat_Keluar extends JFrame {

    JFrame frameKeluar;
    JPanel panelKeluar, panelChooser;
    public final JTextField field_NoSurat, field_TglSurat, field_Pengirim, field_Penerima, field_Perihal, field_BoxFile;
    String No_Surat, Tgl_Surat, Pengirim, Penerima, Perihal, Box_File, Jenis_Surat;

    /*
     Digunakan untuk mengakses semua method yang berkaitan dengan database
     */
    ConnectDB c = new ConnectDB();

    public Surat_Keluar() throws SQLException {

        /*
         Pembuatan Frame
         */
        final JFrame frameKeluar = new JFrame("Masukkan Surat");
        frameKeluar.setVisible(true);
        frameKeluar.setSize(267, 500);
        frameKeluar.setLayout(new FlowLayout(FlowLayout.LEADING));
        frameKeluar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelKeluar = new JPanel();
        /*
         Dibutuhkan untuk memunculkan form isian
         */
        JLabel label_NoSurat = new JLabel("No Surat");
        panelKeluar.add(label_NoSurat);
        frameKeluar.add(label_NoSurat);
        field_NoSurat = new JTextField(20);
        panelKeluar.add(field_NoSurat);
        frameKeluar.add(field_NoSurat);
        JLabel label_TglSurat = new JLabel("Tgl Surat");
        panelKeluar.add(label_TglSurat);
        frameKeluar.add(label_TglSurat);
        field_TglSurat = new JTextField(20);
        panelKeluar.add(field_TglSurat);
        frameKeluar.add(field_TglSurat);
        JLabel label_Pengirim = new JLabel("Pengirim");
        panelKeluar.add(label_Pengirim);
        frameKeluar.add(label_Pengirim);
        field_Pengirim = new JTextField(20);
        panelKeluar.add(field_Pengirim);
        frameKeluar.add(field_Pengirim);
        JLabel label_Penerima = new JLabel("Penerima");
        panelKeluar.add(label_Penerima);
        frameKeluar.add(label_Penerima);
        field_Penerima = new JTextField(20);
        panelKeluar.add(field_Penerima);
        frameKeluar.add(field_Penerima);
        JLabel label_Perihal = new JLabel("Perihal");
        panelKeluar.add(label_Perihal);
        frameKeluar.add(label_Perihal);
        field_Perihal = new JTextField(20);
        panelKeluar.add(field_Perihal);
        frameKeluar.add(field_Perihal);
        JLabel label_BoxFile = new JLabel("Box File");
        panelKeluar.add(label_BoxFile);
        frameKeluar.add(label_BoxFile);
        field_BoxFile = new JTextField(20);
        panelKeluar.add(field_BoxFile);
        frameKeluar.add(field_BoxFile);
        JLabel field_JenisSurat = new JLabel("Jenis_Surat");
        panelKeluar.add(field_JenisSurat);
        frameKeluar.add(field_JenisSurat);

        /*
         Digunakan untuk memilih Jenis_Surat
         Tidak boleh bernilai NULL
         */
        final JRadioButton eks= new JRadioButton("Eksternal");
        panelKeluar.add(eks);
        frameKeluar.add(eks);
                eks.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Jenis_Surat = "Eksternal";

            }
        });
                
       final JRadioButton inte= new JRadioButton("Internal");
        panelKeluar.add(inte);
        frameKeluar.add(inte);
                inte.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Jenis_Surat = "Internal";

            }
        });
        JLabel label_8 = new JLabel("File");
        panelKeluar.add(label_8);
        frameKeluar.add(label_8);
        JLabel label_9 = new JLabel("");
        panelKeluar.add(label_9);
        frameKeluar.add(label_9);
        final JLabel label_NoSurat0 = new JLabel();
        panelKeluar.add(label_NoSurat0);
        frameKeluar.add(label_NoSurat0);

        /*
         Button ini digunakan unutk mencari berkas yang bersangkutan
         */
        JButton buttonChooser = new JButton("Browse");
        panelKeluar.add(buttonChooser);
        frameKeluar.add(buttonChooser);
        // Create a file chooser that opens up as an Open dialog
        final JFileChooser choose = new JFileChooser();
        final File[] sf = choose.getSelectedFiles();
        buttonChooser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                choose.setMultiSelectionEnabled(true);
                int option = choose.showOpenDialog(Surat_Keluar.this);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File[] sf = choose.getSelectedFiles();
                    String filelist = "nothing";
                    if (sf.length > 0) {
                        filelist = sf[0].getName();
                    }
                    for (int i = 1; i < sf.length; i++) {
                        filelist += ", " + sf[i].getName();
                    }
                    label_NoSurat0.setText(filelist);
                } else {
                    label_NoSurat0.setText("");
                }
            }
        });

        /**
         * Pembuatan button OK
         *
         */
        JButton buttonOK = new JButton("OK");
        panelKeluar.add(buttonOK);
        frameKeluar.add(buttonOK);
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                /*If OK ditekan, maka masukkan file ke dalam folder yang berkaitan
                 ......
                 */
                //Digunakan untuk insert data ke database
                if(field_NoSurat.getText()== null){
                    JOptionPane.showMessageDialog(inte, "Nomor surat belum diisi");
                }
                else if(field_TglSurat.getText()== null){
                    JOptionPane.showMessageDialog(inte, "Tanggal surat belum diisi");
                }
                else if(field_Pengirim.getText()==null){
                    JOptionPane.showMessageDialog(inte, "Nama pengirim surat belum diisi");
                }
                else if(field_Penerima.getText()==null){
                    JOptionPane.showMessageDialog(inte, "Nama penerima surat belum diisi");
                }
                                else if(field_BoxFile.getText()==null){
                    JOptionPane.showMessageDialog(inte, "Informasi BOX surat belum diisi");
                }
//                else if(sf.length==0){
//                    JOptionPane.showMessageDialog(inte, "File belum diunggah");
//                }
                c.makeConnection();
                No_Surat = field_NoSurat.getText();
                Tgl_Surat = field_TglSurat.getText();
                Pengirim = field_Pengirim.getText();
                Penerima = field_Penerima.getText();
                Perihal = field_Perihal.getText();
                Box_File = field_BoxFile.getText();

                /**
                 * Memanggil method untuk INSERT database
                 */
                c.addInformasiSuratKeluar(No_Surat, Tgl_Surat, Pengirim, Penerima, Perihal, Box_File, Jenis_Surat);
                System.out.println(Jenis_Surat);
                /*
                 Setelah selesai Entry, akan ada pilihan apakah ingin melanjutkan entry informasi surat baru lagi
                 atau tidak
                 */
                /*
                Warning
                - Selain Perihal tidak ada field yang boleh kosong
                */
                int selectedOption = JOptionPane.showConfirmDialog(null,
                        "Continue Entry?",
                        "Choose",
                        JOptionPane.YES_NO_OPTION);
                if (selectedOption == JOptionPane.NO_OPTION) {
                    frameKeluar.dispose();
                } else {
                    try {
                        frameKeluar.dispose();
                        Surat_Keluar sk = new Surat_Keluar();
                    } catch (SQLException ex) {
                        Logger.getLogger(Surat_Masuk.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        });
        /*
         Pembuatan button reset, fungsinya reset semua isian ketika ditekan
         */
        JButton buttonReset = new JButton("Reset");
        panelKeluar.add(buttonReset);
        frameKeluar.add(buttonReset);
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //semua isian dikosongkan
                field_NoSurat.setText("");
                field_TglSurat.setText("");
                field_Pengirim.setText("");
                field_Penerima.setText("");
                field_Perihal.setText("");
                field_BoxFile.setText("");

            }
        });

        /*
         Close Window Surat Masuk
         */
        JButton buttonCancel = new JButton("Cancel");
        panelKeluar.add(buttonCancel);
        frameKeluar.add(buttonCancel);
        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);

            }
        });
    }

    /**
     *
     * @param args
     * @throws SQLException
     */
    public static void main(String[] args) throws SQLException {
        Surat_Keluar sm = new Surat_Keluar();

    }
}
