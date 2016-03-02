package kp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.sun.java.accessibility.util.EventID;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import static javax.swing.JComponent.WHEN_FOCUSED;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerListModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Gina Andriyani dan Siti Jumaliaya
 */
public class Daftar_SuratMasuk {

    public String temp;
    public String No_SuratA, Tgl_TerimaA, Tgl_SuratA, PengirimA, PenerimaA, PerihalA, Box_FileA, Jenis_SuratA, File_SuratA;
    JPanel panel;

    Daftar_SuratMasuk() throws SQLException {

    }

    public JPanel bikinDaftarSuratMasuk() {
        //final JFrame frame1 = new JFrame("Daftar Surat");

        panel = new JPanel();
        ImageIcon daftarSurat = new ImageIcon(getClass().getResource("DaftarSuratMasuk.jpg"));
        JLabel label_1 = new JLabel(daftarSurat);
        panel.add(label_1);

        final ConnectDB c = new ConnectDB();
        /**
         * Connect, Display data in Tabla
         */
        c.makeConnection();
        panel.add(new JScrollPane(c.showSurat_Masuk()));
        //System.out.println("<html><br></html>");
        c.closeConnection();

        ImageIcon img = new ImageIcon(getClass().getResource("add.jpg"));
        JButton baru = new JButton(img);

        panel.add(baru);

        baru.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Surat_Masuk sm = new Surat_Masuk();
                } catch (SQLException ex) {
                    Logger.getLogger(Daftar_SuratMasuk.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        ImageIcon DELimg = new ImageIcon(getClass().getResource("delete.jpg"));
        JButton del = new JButton(DELimg);

        panel.add(del);

        final DefaultTableModel tableModel = (DefaultTableModel) c.tableMasuk.getModel();
        del.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedOption = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to delete this data permanently ?",
                        "Choose",
                        JOptionPane.YES_NO_OPTION);
                if (selectedOption == JOptionPane.NO_OPTION) {

                } else {
                    c.makeConnection();
                    int row = c.tableMasuk.getSelectedRow();
                    String ambil = (String) c.tableMasuk.getValueAt(row, 0);
                    String ambilFile = (String) c.tableMasuk.getValueAt(row, 8);
                    String ext = ambilFile.substring(ambilFile.lastIndexOf('.'), ambilFile.length());
                    String jenis = (String) c.tableMasuk.getValueAt(row, 7);
                    c.removeSurat_Masuk(ambil);
                    if(jenis.equalsIgnoreCase("Internal")){
                        File file = new File("D:" + File.separator + "SURAT" + File.separator + "Surat Masuk" + File.separator + "Internal" + File.separator + ambil + ext);
                    file.delete();
                    c.closeConnection();
                    panel.remove(c.tableMasuk);
                    c.closeConnection();
                    }
                    else{
                        File file = new File("D:" + File.separator + "SURAT" + File.separator + "Surat Masuk" + File.separator + "Eksternal" + File.separator + ambil + ext);
                    file.delete();
                    c.closeConnection();
                    panel.remove(c.tableMasuk);
                    c.closeConnection();
                    }
                    
                }

            }
        });

        ImageIcon EDITimg = new ImageIcon(getClass().getResource("edit.jpg"));
        JButton edit = new JButton(EDITimg);

        panel.add(edit);

        edit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                c.makeConnection();
                int klik = c.tableMasuk.getSelectedRow();

                No_SuratA = (String) c.tableMasuk.getValueAt(klik, 0);
                Tgl_TerimaA = (String) c.tableMasuk.getValueAt(klik, 1);
                Tgl_SuratA = (String) c.tableMasuk.getValueAt(klik, 2);
                PengirimA = (String) c.tableMasuk.getValueAt(klik, 3);
                PenerimaA = (String) c.tableMasuk.getValueAt(klik, 4);
                PerihalA = (String) c.tableMasuk.getValueAt(klik, 5);
                Box_FileA = (String) c.tableMasuk.getValueAt(klik, 6);
                Jenis_SuratA = (String) c.tableMasuk.getValueAt(klik, 7);
                File_SuratA = (String) c.tableMasuk.getValueAt(klik, 8);

                FormSurat fs = new FormSurat();
                fs.setLagiNo(No_SuratA);
                try {
                    fs.bikinFormMasuk(No_SuratA, Tgl_TerimaA, Tgl_SuratA, PengirimA, PenerimaA, PerihalA, Box_FileA, Jenis_SuratA, File_SuratA);
                } catch (SQLException ex) {
                    Logger.getLogger(Daftar_SuratMasuk.class.getName()).log(Level.SEVERE, null, ex);
                }
//
//                System.out.println(No_SuratA);
//                System.out.println(Tgl_TerimaA);
//                System.out.println(Tgl_SuratA);
//                System.out.println(PengirimA);
//                System.out.println(PenerimaA);
//                System.out.println(PerihalA);
//                System.out.println(Jenis_SuratA);
                c.closeConnection();
            }
        });
        ImageIcon SEARCHimg = new ImageIcon(getClass().getResource("search.png"));
        JButton cariList = new JButton(SEARCHimg);

        panel.add(cariList);

        cariList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FormSurat fs = new FormSurat();
                fs.bikinCariMasuk("", "", "", "", "", "", "", "");
            }
        });

        return panel;
    }

    public String setNo_Surat() {
        return temp;
    }

    public JPanel panelView(JTable table) {
        panel.remove(table);
        return panel;

    }

    public static void main(String[] args) throws SQLException {
        Daftar_SuratMasuk df = new Daftar_SuratMasuk();
    }

}
