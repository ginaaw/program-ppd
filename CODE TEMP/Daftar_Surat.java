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
public class Daftar_Surat {

  

    Daftar_Surat() throws SQLException {
        JFrame frame1 = new JFrame("Daftar Surat");
        frame1.setVisible(true);
        frame1.setSize(300, 400);
        frame1.setLayout(new FlowLayout(FlowLayout.CENTER));
        frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        JLabel label_1 = new JLabel("Daftar Surat Pusat Pengendalian Data (PPD) PT PP Tbk. Cabang IX Pekanbaru - Riau");
        panel.add(label_1);
        frame1.add(label_1);

        String[] mths = (new DateFormatSymbols()).getMonths();
        String[] hari = (new DateFormatSymbols()).getWeekdays();
        String[] tgl = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
        
        String[] years = getNextTenYears();
        
        JComboBox mon = new JComboBox(mths);
        panel.add(mon);
        frame1.add(mon);
        
        JComboBox day = new JComboBox(hari);
        panel.add(day);
        frame1.add(day);
        
        JComboBox year = new JComboBox(years);
        panel.add(year);
        frame1.add(year);
        
        JComboBox tanggal = new JComboBox(tgl);
        panel.add(tanggal);
        frame1.add(tanggal);
        
        

        JTextField field_1 = new JTextField(20);
        panel.add(field_1);
        frame1.add(field_1);

        JButton buttonCari = new JButton("Cari");
        panel.add(buttonCari);
        frame1.add(buttonCari);
        buttonCari.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Cari cr = new Cari();
            }
        });
        final ConnectDB c = new ConnectDB();

        c.makeConnection();
        c.showSurat_Masuk();
        frame1.add(new JScrollPane(c.showSurat_Masuk()));

        c.closeConnection();
        //c.closeConnection();

        JButton del = new JButton("delete");
        panel.add(del);
        frame1.add(del);
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
        panel.add(edit);
        frame1.add(edit);
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
        Daftar_Surat df = new Daftar_Surat();
    }

    /**
     * Daftar surat yang ditampilkan baru yang untuk surat keluar aja.
     */
}
