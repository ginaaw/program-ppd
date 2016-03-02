/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this tempMasuklate file, choose Tools | Templates
 * and open the tempMasuklate in the editor.
 */
package kp;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


/**
 *
 * @author Gina Andriyani dan Siti Jumaliaya
 */
public class FormSurat extends JFrame {

    JFrame frameForm;
    JPanel panelForm, panelChooser;
    JLabel label_NoSurat, label_TglTerima, label_TglSurat, label_Pengirim, label_Perihal, label_Penerima, label_BoxFile, label_JenisSurat;
    JTextField field_NoSurat, field_TglTerima, field_TglSurat, field_Pengirim, field_Perihal, field_Penerima, field_BoxFile;
    JLabel label_File = new JLabel();
    JTextField field_File = new JTextField();
    JButton button1, botton2, del;
    String No_Surat, Tgl_Terima, Tgl_Surat, Pengirim, Penerima, Perihal, Box_File;
    String File_Surat = "belum diisi";
    String Jenis_Surat = "belum diisi";
    
    JComboBox inte, eks;
    String temp = null;
    File[] sf;
    JFileChooser choose;

    public FormSurat() {

    }

    public void labelNo_Surat(String s) {
        label_NoSurat = new JLabel("No Surat");
        field_NoSurat = new JTextField(20);
        field_NoSurat.setText(s);
    }

    public void labelTgl_Terima(String s) {
        label_TglTerima = new JLabel("Tgl Terima");
        field_TglTerima = new JTextField(20);
        field_TglTerima.setText(s);
    }

    public void labelTgl_Surat(String s) {
        label_TglSurat = new JLabel("Tgl Surat");
        field_TglSurat = new JTextField(20);
        field_TglSurat.setText(s);
    }

    public void labelPengirim(String s) {
        label_Pengirim = new JLabel("Pengirim");
        field_Pengirim = new JTextField(20);
        field_Pengirim.setText(s);

    }

    public void labelPenerima(String s) {
        label_Penerima = new JLabel("Penerima");
        field_Penerima = new JTextField(20);
        field_Penerima.setText(s);
    }

    public void labelPerihal(String s) {
        label_Perihal = new JLabel("Perihal");
        field_Perihal = new JTextField(20);
        field_Perihal.setText(s);
    }

    public void labelBoxFile(String s) {
        label_BoxFile = new JLabel("BoxFile");
        field_BoxFile = new JTextField(20);
        field_BoxFile.setText(s);
    }

    public void labelJenisSurat(String s) {
        label_JenisSurat = new JLabel("JenisSurat");
        panelForm.add(label_JenisSurat);
        frameForm.add(label_JenisSurat);

        final JComboBox eks = new JComboBox(new String[]{"Internal", "Eksternal"});
        if (s.equalsIgnoreCase("Internal")) {
            eks.setSelectedIndex(0);
        } else {
            eks.setSelectedIndex(1);
        }
        eks.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (eks.getSelectedIndex() == 0) {
                    Jenis_Surat = "Internal";
                } else {
                    Jenis_Surat = "Eksternal";
                }
            }
        });
        panelForm.add(eks);
        frameForm.add(eks);
    }

    public void fileSurat(String s) {
        label_File = new JLabel(s);

        JLabel label_9 = new JLabel("File");
        panelForm.add(label_9);
        frameForm.add(label_9);
        field_File = new JTextField(20);
        if(s.equalsIgnoreCase("belum diisi")){
            field_File.setText(field_File.getText());
        }
        else{
            field_File.setText(s);
        }
        panelForm.add(field_File);
        frameForm.add(field_File);
        
        /*
         Button ini digunakan untuk mencari berkas yang bersangkutan
         */
        JButton buttonChooser = new JButton("Browse");
        panelForm.add(buttonChooser);
        frameForm.add(buttonChooser);
        // Create a file chooser that opens up as an Open dialog
        choose = new JFileChooser();
        sf = choose.getSelectedFiles();
        buttonChooser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                choose.setMultiSelectionEnabled(true);
                int option = choose.showOpenDialog(FormSurat.this);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File[] sf = choose.getSelectedFiles();
                    String filelist = "nothing";
                    if (sf.length > 0) {
                        File_Surat = sf[0].getName();
                        filelist = sf[0].getName();
                    }
                    for (int i = 1; i < sf.length; i++) {
                        filelist += ", " + sf[i].getName();
                    }
                    field_File.setText(filelist);
                } else {
                    field_File.setText("");
                }
            }
        });
    }

    public void bikinFrame() {
        frameForm = new JFrame("Form Surat Masuk");
        frameForm.setVisible(true);
        frameForm.setSize(260, 500);
        frameForm.setLayout(new FlowLayout(FlowLayout.LEADING));
        frameForm.setResizable(false);
        frameForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void MasukFrame(JLabel label1, JLabel label2, JLabel label3, JLabel label4, JLabel label5, JLabel label6, JLabel label7, JLabel label8, JTextField field1, JTextField field2, JTextField field3, JTextField field4, JTextField field5, JTextField field6, JTextField field7, JTextField field8) {
        frameForm.add(label1);
        frameForm.add(field1);
        frameForm.add(label2);
        frameForm.add(field2);
        frameForm.add(label3);
        frameForm.add(field3);
        frameForm.add(label4);
        frameForm.add(field4);
        frameForm.add(label5);
        frameForm.add(field5);
        frameForm.add(label6);
        frameForm.add(field6);
        frameForm.add(label7);
        frameForm.add(field7);
        panelForm.add(label8);
        frameForm.add(field8);
    }

    public void masukPanel(JLabel label1, JLabel label2, JLabel label3, JLabel label4, JLabel label5, JLabel label6, JLabel label7, JLabel label8, JTextField field1, JTextField field2, JTextField field3, JTextField field4, JTextField field5, JTextField field6, JTextField field7, JTextField field8) {
        panelForm = new JPanel();
        panelForm.add(label1);
        panelForm.add(field1);
        panelForm.add(label2);
        panelForm.add(field2);
        panelForm.add(label3);
        panelForm.add(field3);
        panelForm.add(label4);
        panelForm.add(field4);
        panelForm.add(label5);
        panelForm.add(field5);
        panelForm.add(label6);
        panelForm.add(field6);
        panelForm.add(label7);
        panelForm.add(field7);
        panelForm.add(label8);
        frameForm.add(field8);
    }

    public void KeluarFrame(JLabel label1, JLabel label2, JLabel label3, JLabel label4, JLabel label5, JLabel label6, JLabel label7, JTextField field1, JTextField field2, JTextField field3, JTextField field4, JTextField field5, JTextField field6, JTextField field7) {
        frameForm.add(label1);
        frameForm.add(field1);
        frameForm.add(label2);
        frameForm.add(field2);
        frameForm.add(label3);
        frameForm.add(field3);
        frameForm.add(label4);
        frameForm.add(field4);
        frameForm.add(label5);
        frameForm.add(field5);
        frameForm.add(label6);
        frameForm.add(field6);
        frameForm.add(label7);
        frameForm.add(field7);
    }

    public void KeluarPanel(JLabel label1, JLabel label2, JLabel label3, JLabel label4, JLabel label5, JLabel label6, JLabel label7, JTextField field1, JTextField field2, JTextField field3, JTextField field4, JTextField field5, JTextField field6, JTextField field7) {
        panelForm = new JPanel();
        panelForm.add(label1);
        panelForm.add(field1);
        panelForm.add(label2);
        panelForm.add(field2);
        panelForm.add(label3);
        panelForm.add(field3);
        panelForm.add(label4);
        panelForm.add(field4);
        panelForm.add(label5);
        panelForm.add(field5);
        panelForm.add(label6);
        panelForm.add(field6);
        panelForm.add(label7);
        panelForm.add(field7);
    }

    public void bikinFormMasuk(String No_Surat, String Tgl_Terima, String Tgl_Surat, String Pengirim, String Penerima, String Perihal, String Box_File, String jenis_Surat, String file_Surat) throws SQLException {

        bikinFrame();
        labelNo_Surat(No_Surat);
        labelTgl_Terima(Tgl_Terima);
        labelTgl_Surat(Tgl_Surat);
        labelPengirim(Pengirim);
        labelPenerima(Penerima);
        labelPerihal(Perihal);
        labelBoxFile(Box_File);
        masukPanel(label_NoSurat, label_TglTerima, label_TglSurat, label_Pengirim, label_Perihal, label_Penerima, label_BoxFile, label_File, field_NoSurat, field_TglTerima, field_TglSurat, field_Pengirim, field_Perihal, field_Penerima, field_BoxFile, field_File);
        MasukFrame(label_NoSurat, label_TglTerima, label_TglSurat, label_Pengirim, label_Perihal, label_Penerima, label_BoxFile, label_File, field_NoSurat, field_TglTerima, field_TglSurat, field_Pengirim, field_Perihal, field_Penerima, field_BoxFile, field_File);
        labelJenisSurat(jenis_Surat);
        Jenis_Surat=jenis_Surat;
        fileSurat(file_Surat);
        File_Surat = file_Surat;
        TombolOKMasuk();
        TombolReset();
        TombolCancel();
        frameForm.add(panelForm);
    }

    public void bikinFormKeluar(String No_Surat, String Tgl_Surat, String Pengirim, String Penerima, String Perihal, String Box_File, String jenis_Surat, String file_Surat) {

        bikinFrame();
        labelNo_Surat(No_Surat);
        labelTgl_Surat(Tgl_Surat);
        labelPengirim(Pengirim);
        labelPenerima(Penerima);
        labelPerihal(Perihal);
        labelBoxFile(Box_File);
        KeluarPanel(label_NoSurat, label_TglSurat, label_Pengirim, label_Perihal, label_Penerima, label_BoxFile, label_File, field_NoSurat, field_TglSurat, field_Pengirim, field_Perihal, field_Penerima, field_BoxFile, field_File);
        KeluarFrame(label_NoSurat, label_TglSurat, label_Pengirim, label_Perihal, label_Penerima, label_BoxFile, label_File, field_NoSurat, field_TglSurat, field_Pengirim, field_Perihal, field_Penerima, field_BoxFile, field_File);
                labelJenisSurat(jenis_Surat);
        Jenis_Surat=jenis_Surat;
        fileSurat(file_Surat);
        File_Surat = file_Surat;
        TombolOKKeluar();
        TombolReset();
        TombolCancel();
        frameForm.add(panelForm);
    }

    public void bikinCariMasuk(String No_Surat, String Tgl_Terima, String Tgl_Surat, String Pengirim, String Penerima, String Perihal, String Box_File, String Jenis_Surat) {

        bikinFrame();
        labelNo_Surat(No_Surat);
        labelTgl_Terima(Tgl_Terima);
        labelTgl_Surat(Tgl_Surat);
        labelPengirim(Pengirim);
        labelPenerima(Penerima);
        labelPerihal(Perihal);
        labelBoxFile(Box_File);
        masukPanel(label_NoSurat, label_TglTerima, label_TglSurat, label_Pengirim, label_Perihal, label_Penerima, label_BoxFile, label_File, field_NoSurat, field_TglTerima, field_TglSurat, field_Pengirim, field_Perihal, field_Penerima, field_BoxFile, field_File);
        MasukFrame(label_NoSurat, label_TglTerima, label_TglSurat, label_Pengirim, label_Perihal, label_Penerima, label_BoxFile, label_File, field_NoSurat, field_TglTerima, field_TglSurat, field_Pengirim, field_Perihal, field_Penerima, field_BoxFile, field_File);
        labelJenisSurat(Jenis_Surat);
        TombolCariMasuk();
        TombolReset();
        TombolCancel();
        frameForm.add(panelForm);
    }

    public void bikinCariKeluar(String No_Surat, String Tgl_Surat, String Pengirim, String Penerima, String Perihal, String Box_File, String Jenis_Surat, String File_Surat) {

        bikinFrame();
        labelNo_Surat(No_Surat);
        labelTgl_Surat(Tgl_Surat);
        labelPengirim(Pengirim);
        labelPenerima(Penerima);
        labelPerihal(Perihal);
        labelBoxFile(Box_File);
        KeluarPanel(label_NoSurat, label_TglSurat, label_Pengirim, label_Perihal, label_Penerima, label_BoxFile, label_File, field_NoSurat, field_TglSurat, field_Pengirim, field_Perihal, field_Penerima, field_BoxFile, field_File);
        KeluarFrame(label_NoSurat, label_TglSurat, label_Pengirim, label_Perihal, label_Penerima, label_BoxFile, label_File, field_NoSurat, field_TglSurat, field_Pengirim, field_Perihal, field_Penerima, field_BoxFile, field_File);
        labelJenisSurat(Jenis_Surat);
        fileSurat(File_Surat);
        TombolCariKeluar();
        TombolReset();
        TombolCancel();
        frameForm.add(panelForm);
    }

    public void setLagiNo(String s) {
        temp = s;
    }

    public void TombolOKMasuk() throws SQLException {
        final Daftar_SuratMasuk df = new Daftar_SuratMasuk();
        JButton buttonOK = new JButton("OK");
        panelForm.add(buttonOK);
        frameForm.add(buttonOK);
        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread() {
                    @Override
                    public void run() {
                        ConnectDB c = new ConnectDB();
                        c.makeConnection();
                        c.removeSurat_Masuk(temp);

                        if (field_NoSurat.getText() == null) {
                            JOptionPane.showMessageDialog(inte, "Nomor surat belum diisi");
                        } else if (field_TglSurat.getText() == null) {
                            JOptionPane.showMessageDialog(inte, "Tanggal surat belum diisi");
                        } else if (field_Pengirim.getText() == null) {
                            JOptionPane.showMessageDialog(inte, "Nama pengirim surat belum diisi");
                        } else if (field_Penerima.getText() == null) {
                            JOptionPane.showMessageDialog(inte, "Nama penerima surat belum diisi");
                        } else if (field_BoxFile.getText() == null) {
                            JOptionPane.showMessageDialog(inte, "Informasi BOX surat belum diisi");
                        }
                        c.makeConnection();
                        No_Surat = field_NoSurat.getText();
                        Tgl_Terima = field_TglTerima.getText();
                        Tgl_Surat = field_TglSurat.getText();
                        Pengirim = field_Pengirim.getText();
                        Penerima = field_Penerima.getText();
                        Perihal = field_Perihal.getText();
                        Box_File = field_BoxFile.getText();
                        c.makeConnection();
                        c.addInformasiSuratMasuk(No_Surat, Tgl_Terima, Tgl_Surat, Pengirim, Penerima, Perihal, Box_File, Jenis_Surat, File_Surat);
                        File folderBesar = new File("D:\\SURAT");
                        File folderKecil = new File("D:\\SURAT\\Surat Masuk");
                        File folderKecilInt = new File("D:\\SURAT\\Surat Masuk\\Internal");
                        File folderKecilEks = new File("D:\\SURAT\\Surat Masuk\\Eksternal");
                        try {
                            if (folderBesar.exists() == false) {
                                folderBesar.mkdir();
                                //System.out.println("direktori created");
                            } else {
                                //System.out.println("direktori is not created");

                            }

                            if (folderKecil.exists() == false) {
                                folderKecil.mkdir();
                                //System.out.println("direktori created");
                            } else {
                                //System.out.println("direktori is not created");
                            }
                            if (folderKecilInt.exists() == false) {
                                folderKecilInt.mkdir();
                                //System.out.println("direktori created");
                            } else {
                                //System.out.println("direktori is not created");
                            }
                            if (folderKecilEks.exists() == false) {
                                folderKecilEks.mkdir();
                                //System.out.println("direktori created");
                            } else {
                                //System.out.println("direktori is not created");
                            }

                            Files fileDalam;
                        } catch (Exception en) {
                            en.printStackTrace();
                        }

                        sf = choose.getSelectedFiles();
                        String filelist = "nothing";
//                System.out.println(sf[0].getAbsolutePath());
                        if (sf.length > 0) {
                            for (int i = 0; i < sf.length; i++) {
                                String fileName = sf[i].getName();
                                String ext = fileName.substring(fileName.lastIndexOf('.'), fileName.length());
                                if (Jenis_Surat.equalsIgnoreCase("Internal")) {
                                    System.out.println("masukINternal");
                                    Path dari = Paths.get(sf[i].getPath());
                                    Path ke = Paths.get("D:\\SURAT\\Surat Masuk\\Internal\\" + No_Surat + ext);
                                    try {
                                        Files.copy(dari, ke, StandardCopyOption.COPY_ATTRIBUTES);
                                    } catch (IOException ex) {
                                        Logger.getLogger(FormSurat.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                } else {
                                    //System.out.println("masukINternal");
                                    Path dari = Paths.get(sf[i].getPath());
                                    Path ke = Paths.get("D:\\SURAT\\Surat Masuk\\Eksternal\\" + No_Surat + ext);
                                    try {
                                        Files.copy(dari, ke, StandardCopyOption.COPY_ATTRIBUTES);
                                    } catch (IOException ex) {
                                        Logger.getLogger(FormSurat.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            }

//                    
                        } else {

                        }
                        System.out.println(Jenis_Surat);
                        c.showSurat_Keluar();

                        //
                        int selectedOption = JOptionPane.showConfirmDialog(null,
                                "Data sudah tersimpan. Ingin entry lagi ? ",
                                "Choose",
                                JOptionPane.YES_NO_OPTION);
                        if (selectedOption == JOptionPane.NO_OPTION) {
                            frameForm.dispose();
                            c.closeConnection();
                        } else {
                            frameForm.dispose();
                            try {
                                Surat_Masuk sma = new Surat_Masuk();
                            } catch (SQLException ex) {
                                Logger.getLogger(FormSurat.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
//                                df.panel.removeAll();
//                                df.bikinDaftarSuratMasuk();
                            }
                        });
                    }
                }.start();

            }
            
        });

    }

    public void TombolOKKeluar() {
        JButton buttonOK = new JButton("OK");
        panelForm.add(buttonOK);
        frameForm.add(buttonOK);
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                /*If OK ditekan, maka masukkan file ke dalam folder yang berkaitan
                 ......
                 */
                //Digunakan untuk insert data ke database
                ConnectDB c = new ConnectDB();
                c.makeConnection();
                c.removeSurat_Keluar(temp);
                try {
                    Daftar_SuratMasuk ds = new Daftar_SuratMasuk();
                    //ds.panel.removeAll();
                    ds.bikinDaftarSuratMasuk();
                } catch (SQLException ex) {
                    Logger.getLogger(FormSurat.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (field_NoSurat.getText() == null) {
                    JOptionPane.showMessageDialog(inte, "Nomor surat belum diisi");
                } else if (field_TglSurat.getText() == null) {
                    JOptionPane.showMessageDialog(inte, "Tanggal surat belum diisi");
                } else if (field_Pengirim.getText() == null) {
                    JOptionPane.showMessageDialog(inte, "Nama pengirim surat belum diisi");
                } else if (field_Penerima.getText() == null) {
                    JOptionPane.showMessageDialog(inte, "Nama penerima surat belum diisi");
                } else if (field_BoxFile.getText() == null) {
                    JOptionPane.showMessageDialog(inte, "Informasi BOX surat belum diisi");
                }
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
                c.addInformasiSuratKeluar(No_Surat, Tgl_Surat, Pengirim, Penerima, Perihal, Box_File, Jenis_Surat, File_Surat);
                File folderBesar = new File("D:\\SURAT");
                File folderKecil = new File("D:\\SURAT\\Surat Keluar");
                File folderKecilInt = new File("D:\\SURAT\\Surat Keluar\\Internal");
                File folderKecilEks = new File("D:\\SURAT\\Surat Keluar\\Eksternal");
                try {
                    if (folderBesar.exists() == false) {
                        folderBesar.mkdir();
                        //System.out.println("direktori created");
                    } else {
                        //System.out.println("direktori is not created");

                    }

                    if (folderKecil.exists() == false) {
                        folderKecil.mkdir();
                        //System.out.println("direktori created");
                    } else {
                        //System.out.println("direktori is not created");
                    }
                    if (folderKecilInt.exists() == false) {
                        folderKecilInt.mkdir();
                        //System.out.println("direktori created");
                    } else {
                        //System.out.println("direktori is not created");
                    }
                    if (folderKecilEks.exists() == false) {
                        folderKecilEks.mkdir();
                        //System.out.println("direktori created");
                    } else {
                        //System.out.println("direktori is not created");
                    }

                    Files fileDalam;
                } catch (Exception en) {
                    en.printStackTrace();
                }

                sf = choose.getSelectedFiles();
                String filelist = "nothing";
                //System.out.println(sf[0].getAbsolutePath());
               
                if (sf.length > 0) {
                    for (int i = 0; i < sf.length; i++) {
                        String fileName = sf[i].getName();
                        String ext = fileName.substring(fileName.lastIndexOf('.'), fileName.length());
                        //System.out.println(ext);
                        if (Jenis_Surat.equalsIgnoreCase("Internal")) {
                            //System.out.println("masukINternal");
                            Path dari = Paths.get(sf[i].getPath());
                            Path ke = Paths.get("D:\\SURAT\\Surat Keluar\\Internal\\" + No_Surat + ext);
                            //System.out.println(ke);
                            try {
                                Files.copy(dari, ke, StandardCopyOption.COPY_ATTRIBUTES);
                            } catch (IOException ex) {
                                Logger.getLogger(FormSurat.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            //System.out.println("masukINternal");
                            Path dari = Paths.get(sf[i].getPath());
                            Path ke = Paths.get("D:\\SURAT\\Surat Keluar\\Eksternal\\" + No_Surat + ext);
                            try {
                                Files.copy(dari, ke, StandardCopyOption.COPY_ATTRIBUTES);
                            } catch (IOException ex) {
                                Logger.getLogger(FormSurat.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }

//                    
                } else {

                }

                //System.out.println(Jenis_Surat);
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
                    frameForm.dispose();
                    c.closeConnection();
                } else {
                    frameForm.dispose();
                    Surat_Keluar sk = new Surat_Keluar();
                }
            }

        });
    }

    public void TombolCariMasuk() {
        JButton buttonCari = new JButton("Cari");
        panelForm.add(buttonCari);
        frameForm.add(buttonCari);
        buttonCari.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConnectDB c = new ConnectDB();
                c.makeConnection();
                No_Surat = field_NoSurat.getText();
                Tgl_Terima = field_TglTerima.getText();
                Tgl_Surat = field_TglSurat.getText();
                Pengirim = field_Pengirim.getText();
                Penerima = field_Penerima.getText();
                Perihal = field_Perihal.getText();
                Box_File = field_BoxFile.getText();

                c.showHasilCari(No_Surat, Tgl_Terima, Tgl_Surat, Pengirim, Penerima, Perihal, Box_File, Jenis_Surat);
                JFrame frameHome = new JFrame("Home");
                JPanel panel = new JPanel();
                frameHome.setBackground(Color.BLUE);
                frameHome.setSize(630, 460);
                frameHome.setVisible(true);
                //frameHome.setLayout(new FlowLayout(FlowLayout.CENTER));
                frameForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                ImageIcon imgcari = new ImageIcon(getClass().getResource("pencarian.jpg"));
                JLabel label = new JLabel(imgcari);
                label.setSize(50, 600);
                label.setFont(new Font("Times New Roman", Font.BOLD, 18));
                panel.add(label);
                panel.add(c.showHasilCari(No_Surat, Tgl_Terima, Tgl_Surat, Pengirim, Penerima, Perihal, Box_File, Jenis_Surat));
                frameHome.add(panel);

                TombolCancel();
            }

        });
    }

    public void TombolCariKeluar() {
        JButton buttonCari = new JButton("Cari");
        panelForm.add(buttonCari);
        frameForm.add(buttonCari);
        buttonCari.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConnectDB c = new ConnectDB();
                c.makeConnection();
                No_Surat = field_NoSurat.getText();
                Tgl_Surat = field_TglSurat.getText();
                Pengirim = field_Pengirim.getText();
                Penerima = field_Penerima.getText();
                Perihal = field_Perihal.getText();
                Box_File = field_BoxFile.getText();

                c.showHasilCariK(No_Surat, Tgl_Surat, Pengirim, Penerima, Perihal, Box_File, Jenis_Surat);
                JFrame frameHome = new JFrame("Cari Daftar Surat");
                JPanel panel = new JPanel();
                frameHome.setBackground(Color.BLUE);
                frameHome.setSize(630, 460);
                frameHome.setVisible(true);
                //frameHome.setLayout(new FlowLayout(FlowLayout.CENTER));
                frameForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                ImageIcon imgcari = new ImageIcon(getClass().getResource("pencarian.jpg"));
                JLabel label = new JLabel(imgcari);
                label.setSize(50, 600);
                panel.add(label);
                panel.add(c.showHasilCariK(No_Surat, Tgl_Surat, Pengirim, Penerima, Perihal, Box_File, Jenis_Surat));
                frameHome.add(panel);

                TombolCancel();
            }

        });
    }
    /*
     Close Window Surat Masuk
     */

    public void TombolCancel() {
        JButton buttonCancel = new JButton("Cancel");
        panelForm.add(buttonCancel);
        frameForm.add(buttonCancel);
        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //System.exit(0);
                frameForm.dispose();
            }
        });
    }
    /*
     Pembuatan button reset, fungsinya reset semua isian ketika ditekan
     */

    public void TombolReset() {
        JButton buttonReset = new JButton("Reset");
        panelForm.add(buttonReset);
        frameForm.add(buttonReset);
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
    }

    public static void main(String[] args) throws SQLException {
        FormSurat f = new FormSurat();
        f.bikinFormMasuk("", "", "", "", "", "", "", "", "");
    }
}
