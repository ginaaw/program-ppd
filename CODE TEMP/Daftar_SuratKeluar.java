package kp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import static javax.swing.JComponent.WHEN_FOCUSED;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerListModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Gina
 */
public class Daftar_SuratKeluar {

  

    Daftar_SuratKeluar() throws SQLException {
        JFrame frameSuratKeluar = new JFrame("Daftar Surat Kelaur");
        frameSuratKeluar.setVisible(true);
        frameSuratKeluar.setSize(300, 400);
        frameSuratKeluar.setLayout(new FlowLayout(FlowLayout.CENTER));
        frameSuratKeluar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelSuratKeluar = new JPanel();
        JLabel label_1 = new JLabel("Daftar Surat Pusat Pengendalian Data (PPD) PT PP Tbk. Cabang IX Pekanbaru - Riau");
        panelSuratKeluar.add(label_1);
        frameSuratKeluar.add(label_1);

        String[] mths = (new DateFormatSymbols()).getMonths();
        String[] hari = (new DateFormatSymbols()).getWeekdays();
        String[] tgl = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
        
        String[] years = getNextTenYears();
        
        JComboBox mon = new JComboBox(mths);
        panelSuratKeluar.add(mon);
        frameSuratKeluar.add(mon);
        
        JComboBox day = new JComboBox(hari);
        panelSuratKeluar.add(day);
        frameSuratKeluar.add(day);
        
        JComboBox year = new JComboBox(years);
        panelSuratKeluar.add(year);
        frameSuratKeluar.add(year);
        
        JComboBox tanggal = new JComboBox(tgl);
        panelSuratKeluar.add(tanggal);
        frameSuratKeluar.add(tanggal);
        
        

        JTextField field_1 = new JTextField(20);
        panelSuratKeluar.add(field_1);
        frameSuratKeluar.add(field_1);

        JButton buttonCari = new JButton("Cari");
        panelSuratKeluar.add(buttonCari);
        frameSuratKeluar.add(buttonCari);
        buttonCari.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Cari cr = new Cari();
            }
        });
        final ConnectDB c = new ConnectDB();

        c.makeConnection();
        c.showSurat_Masuk();
        frameSuratKeluar.add(new JScrollPane(c.showSurat_Masuk()));

        c.closeConnection();
        //c.closeConnection();

        JButton baru = new JButton("NEW");
        panelSuratKeluar.add(baru);
        frameSuratKeluar.add(baru);
        baru.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Surat_Keluar sm = new Surat_Keluar();
                } catch (SQLException ex) {
                    Logger.getLogger(Daftar_SuratMasuk.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        JButton del = new JButton("delete");
        panelSuratKeluar.add(del);
        frameSuratKeluar.add(del);
        del.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                c.makeConnection();
                int row = c.table.getSelectedRow();
                String ambil = (String) c.table.getValueAt(row, 0);
                c.removeSurat_Masuk(ambil);
                c.showSurat_Masuk();
                c.closeConnection();
            }
        });

        JButton edit = new JButton("Edit");
        panelSuratKeluar.add(edit);
        frameSuratKeluar.add(edit);
        del.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                c.makeConnection();
                int row = c.table.getSelectedRow();
                int column = c.table.getSelectedColumn();
                c.table.editCellAt(row, column);
                String editWord = (String) c.table.getValueAt(row, column);
                System.out.println(editWord);
                //gimana caranya biar ngupdatenya itu bisa lebih dari satu.
                c.closeConnection();
            }
        });
    }

    private String[] getNextTenYears() {
        String[] years = new String[10];
        Calendar cal = Calendar.getInstance();
        int thisYear = cal.get(Calendar.YEAR);
        for (int i = 0; i < years.length; i++) {
            years[i] = Integer.toString(2000 + i);
        }

        return years;
    }

    public static void main(String[] args) throws SQLException {
        Daftar_SuratKeluar df = new Daftar_SuratKeluar();
    }

    /**
     * Daftar surat yang ditampilkan baru yang untuk surat keluar aja.
     */
}
