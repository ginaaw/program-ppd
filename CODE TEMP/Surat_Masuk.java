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
public class Surat_Masuk extends JFrame {

    JFrame frame1;
    JPanel panelMasuk, panelChooser;
    JLabel label1, label2, label3, label4, label5, label6;
    public final JTextField field_NoSurat, field_TglTerima, field_TglSurat, field_Pengirim, field_Perihal, field_Penerima, field_BoxFile;
    JComboBox combo1, combo2;
    JButton button1, botton2, del;
    String No_Surat, Tgl_Terima, Tgl_Surat, Pengirim, Penerima, Perihal, Box_File, Jenis_Surat;

    /*
     Digunakan untuk mengakses semua method yang berkaitan dengan database
     */
    ConnectDB c = new ConnectDB();

    public Surat_Masuk() throws SQLException {

        /*
         Pembuatan Frame
         */
        final JFrame frame1 = new JFrame("Masukkan Surat");
        frame1.setVisible(true);
        frame1.setSize(267, 500);
        frame1.setLayout(new FlowLayout(FlowLayout.LEADING));
        frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelMasuk = new JPanel();
        /*
         Dibutuhkan untuk memunculkan form isian
         */
        JLabel label_NoSurat = new JLabel("No Surat");
        panelMasuk.add(label_NoSurat);
        frame1.add(label_NoSurat);
        field_NoSurat = new JTextField(20);
        panelMasuk.add(field_NoSurat);
        frame1.add(field_NoSurat);
        JLabel label_TglTerima = new JLabel("Tgl Terima");
        panelMasuk.add(label_TglTerima);
        frame1.add(label_TglTerima);
        field_TglTerima = new JTextField(20);
        panelMasuk.add(field_TglTerima);
        frame1.add(field_TglTerima);
        JLabel label_TglSurat = new JLabel("Tgl Surat");
        panelMasuk.add(label_TglSurat);
        frame1.add(label_TglSurat);
        field_TglSurat = new JTextField(20);
        panelMasuk.add(field_TglSurat);
        frame1.add(field_TglSurat);
        JLabel label_Pengirim = new JLabel("Pengirim");
        panelMasuk.add(label_Pengirim);
        frame1.add(label_Pengirim);
        field_Pengirim = new JTextField(20);
        panelMasuk.add(field_Pengirim);
        frame1.add(field_Pengirim);
        JLabel label_Penerima = new JLabel("Penerima");
        panelMasuk.add(label_Penerima);
        frame1.add(label_Penerima);
        field_Penerima = new JTextField(20);
        panelMasuk.add(field_Penerima);
        frame1.add(field_Penerima);
        JLabel label_Peirhal = new JLabel("Perihal");
        panelMasuk.add(label_Peirhal);
        frame1.add(label_Peirhal);
        field_Perihal = new JTextField(20);
        panelMasuk.add(field_Perihal);
        frame1.add(field_Perihal);
        JLabel label_BoxFile = new JLabel("Box File");
        panelMasuk.add(label_BoxFile);
        frame1.add(label_BoxFile);
        field_BoxFile = new JTextField(20);
        panelMasuk.add(field_BoxFile);
        frame1.add(field_BoxFile);
        JLabel label_NoSurat1 = new JLabel("Jenis_Surat");
        panelMasuk.add(label_NoSurat1);
        frame1.add(label_NoSurat1);

        /*
         Digunakan untuk memilih Jenis_Surat
         Tidak boleh bernilai NULL
         */
        final JRadioButton eks= new JRadioButton("Eksternal");
        panelMasuk.add(eks);
        frame1.add(eks);
                eks.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Jenis_Surat = "Eksternal";

            }
        });
                
       final JRadioButton inte= new JRadioButton("Internal");
        panelMasuk.add(inte);
        frame1.add(inte);
                inte.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Jenis_Surat = "Internal";

            }
        });

        JLabel label_8 = new JLabel("File");
        panelMasuk.add(label_8);
        frame1.add(label_8);
        JLabel label_9 = new JLabel("");
        panelMasuk.add(label_9);
        frame1.add(label_9);
        final JLabel label_NoSurat0 = new JLabel();
        panelMasuk.add(label_NoSurat0);
        frame1.add(label_NoSurat0);

        /*
         Button ini digunakan unutk mencari berkas yang bersangkutan
         */
        JButton buttonChooser = new JButton("Browse");
        panelMasuk.add(buttonChooser);
        frame1.add(buttonChooser);
        // Create a file chooser that opens up as an Open dialog
        final JFileChooser choose = new JFileChooser();
        File[] sf = choose.getSelectedFiles();
        buttonChooser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                choose.setMultiSelectionEnabled(true);
                int option = choose.showOpenDialog(Surat_Masuk.this);
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
        panelMasuk.add(buttonOK);
        frame1.add(buttonOK);
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
                Tgl_Terima = field_TglTerima.getText();
                Tgl_Surat = field_TglSurat.getText();
                Pengirim = field_Pengirim.getText();
                Penerima = field_Perihal.getText();
                Perihal = field_BoxFile.getText();
                Box_File = field_BoxFile.getText();
                
                /**
                 * Membaca masukkan Jenis Surat Jenis_Surat tidak boleh NULL
                 */

                /**
                 * Memanggil method untuk INSERT database
                 */
                c.makeConnection();
                c.addInformasiSuratMasuk(No_Surat, Tgl_Terima, Tgl_Surat, Pengirim, Penerima, Perihal, Box_File, Jenis_Surat);
                System.out.println(Jenis_Surat);
                /*
                 Setelah selesai Entry, akan ada pilihan apakah ingin melanjutkan entry informasi surat baru lagi
                 atau tidak
                 */
                int selectedOption = JOptionPane.showConfirmDialog(null,
                        "Continue Entry?",
                        "Choose",
                        JOptionPane.YES_NO_OPTION);
                if (selectedOption == JOptionPane.NO_OPTION) {
                    frame1.dispose();
                    c.closeConnection();
                } else {
                    try {
                        frame1.dispose();
                        Surat_Masuk sma = new Surat_Masuk();
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
        panelMasuk.add(buttonReset);
        frame1.add(buttonReset);
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //semua isian dikosongkan
                field_NoSurat.setText("");
                field_TglTerima.setText("");
                field_TglSurat.setText("");
                field_Pengirim.setText("");
                field_Perihal.setText("");
                field_BoxFile.setText("");
                field_BoxFile.setText("");

            }
        });

        /*
         Close Window Surat Masuk
         */
        JButton buttonCancel = new JButton("Cancel");
        panelMasuk.add(buttonCancel);
        frame1.add(buttonCancel);
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
        Surat_Masuk sm = new Surat_Masuk();

    }
}
