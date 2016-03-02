package kp;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Gina Andriyani dan Siti Jumaliaya
 */
public class Jendela {

    private static int maxW = 0;
    private static int maxH = 0;
    JFrame  f = new JFrame();;

    Jendela() throws SQLException {
        
    }
    public JFrame fr() throws SQLException{
        Daftar_SuratMasuk ds = new Daftar_SuratMasuk();
        Daftar_SuratKeluar dk = new Daftar_SuratKeluar();
        Cari cr = new Cari();
        

        final JTabbedPane tabs = new JTabbedPane();

        tabs.add(ds.bikinDaftarSuratMasuk(), "DAFTAR SURAT MASUK");
        tabs.add(dk.bikinDaftarSuratKeluar(), "DAFTAR SURAT KELUAR");
        tabs.add(cr.intipSurat(), "PENCARIAN BERKAS SURAT");

        final Dimension originalTabsDim = tabs.getPreferredSize();

        tabs.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {

            }

        });
        f.setSize(645, 500);
        f.setContentPane(tabs);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(false);
        f.setVisible(true);
        return f;
    }
    public static void main(String[] args) throws SQLException {
        Jendela jen = new Jendela();
    }

    private static final JPanel createPanel(JPanel panel, int w, int h) {

        JPanel p = new JPanel();
        p.setPreferredSize(new Dimension(w, h));

        maxW = Math.max(w, maxW);
        maxH = Math.max(h, maxH);

        return panel;

    }

}
