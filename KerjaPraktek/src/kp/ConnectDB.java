package kp;

/**
 * Opens a Microsoft Access Database without having need to have access rights
 * to the Administrative Tools on Windows to set up any ODBC connections.
 */
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import static javax.swing.JComponent.WHEN_FOCUSED;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Gina Andriyani dan Siti Jumaliaya
 */
public class ConnectDB {

    DefaultTableModel dtm;
    JTable table;
    JTable tableMasuk, tableKeluar;
    String kondisi_1;
    String kondisi_2;
    String kondisi_3;
    String kondisi_4;
    String kondisi_5;
    String kondisi_6;
    String kondisi_7;
    String kondisi_8;
    String coba = "";

    Connection con;
    final String driver = "sun.jdbc.odbc.JdbcOdbcDriver";
    final String url = "jdbc:odbc:"
            + "DRIVER={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=";
    String path = "c:" + File.separator + "Projects"
            + File.separator + "INFORMASI_SURAT.accdb;PWD=ADMIN"; // where the database can be found

    /**
     * Sets the path to the database.
     */
    public ConnectDB() {

    }

    /**
     * Creates the database connection.
     */
    public void makeConnection() {
        //System.out.println("Opening database...\n");
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url + path, "", "ADMIN");
        } catch (Exception ex) {
            //System.out.println("Error opening the database!");
            //System.out.println(ex);
            System.exit(0);
        }
        //System.out.println("Success!");
    }

    /**
     * Displays all the recipes in the database.
     */
    /**
     * Closes the connection cleanly.
     */
    public void closeConnection() {
        //System.out.println("\nClosing database.");
        try {
            con.close();
        } catch (Exception ex) {
            //System.out.println("Error closing the database!");
            //System.out.println(ex);
        }
    }

    /**
     * Adds a recipe to the database.
     */
    public void addInformasiSuratMasuk(String No_Surat, String Tgl_Terima, String Tgl_Surat, String Pengirim, String Penerima, String Perihal, String Box_File, String Jenis_Surat, String File_Surat) {
        String sql = "INSERT INTO Surat_Masuk(No_Surat,Tgl_Terima, Tgl_Surat, Pengirim, Penerima, Perihal, Box_File, Jenis_Surat, File_Surat)"
                + " VALUES('" + No_Surat + "','" + Tgl_Terima + "','" + Tgl_Surat + "','" + Pengirim + "', '" + Penerima + "','" + Perihal + "','" + Box_File + "','" + Jenis_Surat + "','" + File_Surat + "')";
        //System.out.println(Jenis_Surat);
       // System.out.print("\nAdding a recipe : ");
        try {
            Statement statement = con.createStatement();
            int result = statement.executeUpdate(sql);
            //System.out.println(" Added " + result + " recipe(s).");
        } catch (Exception ex) {
            //System.out.println("Error adding a recipe!");
            //System.out.println(ex);
            JOptionPane.showMessageDialog(null, "Nomor surat tersebut sudah ada, Mohon periksa kembali");
        }
    }

    public void addInformasiSuratKeluar(String No_Surat, String Tgl_Surat, String Pengirim, String Penerima, String Perihal, String Box_File, String Jenis_Surat, String File_Surat) {
        String sql = "INSERT INTO Surat_Keluar(No_Surat,Tgl_Surat, Pengirim, Penerima, Perihal, Box_File, Jenis_Surat,File_Surat)"
                + " VALUES('" + No_Surat + "','" + Tgl_Surat + "','" + Pengirim + "', '" + Penerima + "','" + Perihal + "','" + Box_File + "','" + Jenis_Surat + "','" + File_Surat + "')";
        //System.out.println(Jenis_Surat);
        //System.out.print("\nAdding a recipe : ");
        try {
            Statement statement = con.createStatement();
            int result = statement.executeUpdate(sql);
            //System.out.println(" Added " + result + " recipe(s).");
        } catch (Exception ex) {
            //System.out.println("Error adding a recipe!");
            //System.out.println(ex);
        }
    }

    /**
     * Removes a recipe from the database.
     */
    public void removeSurat_Masuk(String input) {
        String sql = "DELETE FROM Surat_Masuk WHERE No_Surat='" + input + "'";
        //System.out.println("remove surat " + sql);
        //System.out.print("\nRemoving a recipe : ");
        try {
            Statement statement = con.createStatement();
            int result = statement.executeUpdate(sql);
            //System.out.println(" Removed " + result + " recipe(s).");
        } catch (Exception ex) {
            //System.out.println("Error removing a recipe!");
            //System.out.println(ex);
        }
    }

    public void removeSurat_Keluar(String input) {
        String sql = "DELETE FROM Surat_Keluar WHERE No_Surat='" + input + "'";
        //System.out.println(sql);
        //System.out.print("\nRemoving a recipe : ");
        try {
            Statement statement = con.createStatement();
            int result = statement.executeUpdate(sql);
            //System.out.println(" Removed " + result + " recipe(s).");
        } catch (Exception ex) {
           // System.out.println("Error removing a recipe!");
            //System.out.println(ex);
        }
    }

    /**
     * Modifies an existing record.
     */
    public JScrollPane showSurat_Masuk() {

        String sql = "SELECT * FROM Surat_Masuk ORDER BY No_Surat";
       // System.out.println("\nRecipes in the database : \n");
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            if (rs != null) {
                while (rs.next()) {
                    tableMasuk = new JTable(buildTableModel(rs));
                    tableMasuk.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                    tableMasuk.setPreferredScrollableViewportSize(new Dimension(600, 200));
                    tableMasuk.setFillsViewportHeight(true);

                    JScrollPane scr = new JScrollPane(tableMasuk);
                    return scr;
                }
            }
            rs.close();
            statement.close();
        } catch (Exception ex) {
            //System.out.println("Error reading database information!");
            //System.out.println(ex);
        }
        return null;
    }

    public String cariSuratMasuk(String input) {
        String sql = "SELECT File_Surat FROM Surat_Masuk WHERE No_Surat='" + input + "'";
        //System.out.println(sql);
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                return rs.getString(1);
            } else {
                return "nothing";
            }
        } catch (Exception ex) {
            //System.out.println("Error reading database information!");
            //System.out.println(ex);
        }
        return null;

    }

    public String cariSuratKeluar(String input) {
        String sql = "SELECT File_Surat FROM Surat_Keluar WHERE No_Surat='" + input + "'";
        //System.out.println(sql);
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                return rs.getString(1);
            } else {
                return "nothing";
            }
        } catch (Exception ex) {
            //System.out.println("Error reading database information!");
            //System.out.println(ex);
        }
        return null;

    }

    public ResultSet takeRow(String s) {

        String sql = "SELECT * FROM Surat_Masuk WHERE No_Surat='" + s + "'";
        //System.out.println("takeRow " + sql);
        //System.out.println("\nRecipes in the database : \n");
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            if (rs != null) {
                return rs;
            }
            rs.close();
            statement.close();
        } catch (Exception ex) {
            //System.out.println("Error reading database information!");
            //System.out.println(ex);
        }
        return null;
    }

    public JScrollPane showSurat_Keluar() {

        String sql = "SELECT * FROM Surat_Keluar";
        //System.out.println("\nRecipes in the database : \n");
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs != null) {
                tableKeluar = new JTable(buildTableModel(rs));
                rs.next();
                tableKeluar.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                tableKeluar.setPreferredScrollableViewportSize(new Dimension(600, 200));
                tableKeluar.setFillsViewportHeight(true);

                JScrollPane scr = new JScrollPane(tableKeluar);
                return scr;
            }

            rs.close();
            statement.close();
        } catch (Exception ex) {
            //System.out.println("Error reading database information!");
            //System.out.println(ex);
        }
        return null;
    }

    public void cekKondisi(String satu, String dua, String tiga, String empat, String lima, String enam, String tujuh, String delapan) {
        //System.out.println("Okay" + " " + delapan);
        String temp = "null";
        //System.out.println(temp);
        if (delapan.matches("belum diisi")) {
            kondisi_8 = " " + "Jenis_Surat IS NOT NULL";
        } else {
            kondisi_8 = " " + "Jenis_Surat ='" + delapan + "' ";
        }

        System.out.println("Okay" + " " + satu);
        if (satu.equals("")) {
            kondisi_1 = " " + "No_Surat IS NOT NULL" + " ";
        } else {
            kondisi_1 = "No_Surat='" + satu + "' ";
        }
        if (dua.equals("")) {
            kondisi_2 = " " + "Tgl_Terima IS NOT NULL" + " ";
        } else {
            kondisi_2 = " " + "Tgl_Terima='" + dua + "' ";
        }
        if (tiga.equals("")) {
            kondisi_3 = " " + "Tgl_Surat IS NOT NULL" + " ";
        } else {
            kondisi_3 = " " + "Tgl_Surat='" + tiga + "' ";
        }
        if (empat.equals("")) {
            kondisi_4 = " " + "Pengirim IS NOT NULL" + " ";
        } else {
            kondisi_4 = " " + "Pengirim='" + empat + "' ";
        }
        if (lima.equals("")) {
            kondisi_5 = " " + "Penerima IS NOT NULL" + " ";
        } else {
            kondisi_5 = " " + "Penerima='" + lima + "' ";
        }
        if (enam.equals("")) {
            kondisi_6 = " " + "Perihal IS NOT NULL" + " ";
        } else {
            kondisi_6 = " " + "Perihal='" + enam + "' ";
        }
        if (tujuh.equals("")) {
            kondisi_7 = " " + "Box_File IS NOT NULL" + " ";
        } else {
            kondisi_7 = " " + "Box_File ='" + tujuh + "' ";
        }

    }

    public JScrollPane showHasilCari(String No_Surat, String Tgl_Terima, String Tgl_Surat, String Pengirim, String Penerima, String Perihal, String Box_File, String Jenis_Surat) {
        cekKondisi(No_Surat, Tgl_Terima, Tgl_Surat, Pengirim, Penerima, Perihal, Box_File, Jenis_Surat);
        String sql = "SELECT * FROM Surat_Masuk WHERE " + kondisi_1 + "AND" + kondisi_2 + "AND" + kondisi_3 + "AND" + kondisi_4 + "AND" + kondisi_5 + "AND" + kondisi_6 + "AND" + kondisi_7 + "AND" + kondisi_8;

        //System.out.println("\nRecipes in the database : \n");
        //System.out.println(No_Surat);
        //System.out.println(Box_File);
        //System.out.println(sql);
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs != null) {

                table = new JTable(buildTableModel(rs));

                table.setPreferredScrollableViewportSize(new Dimension(600, 200));
                table.setFillsViewportHeight(true);

                JScrollPane scr = new JScrollPane(table);
                return scr;
            }

            rs.close();
            statement.close();
        } catch (Exception ex) {
            //System.out.println("Error reading database information!");
            //System.out.println(ex);
        }
        return null;
    }

    public JScrollPane showHasilCariK(String No_Surat, String Tgl_Surat, String Pengirim, String Penerima, String Perihal, String Box_File, String Jenis_Surat) {
        cekKondisi(No_Surat, "", Tgl_Surat, Pengirim, Penerima, Perihal, Box_File, Jenis_Surat);
        String sql = "SELECT * FROM Surat_Keluar WHERE " + kondisi_1 + "AND" + kondisi_3 + "AND" + kondisi_4 + "AND" + kondisi_5 + "AND" + kondisi_6 + "AND" + kondisi_7 + "AND" + kondisi_8;

        //System.out.println("\nRecipes in the database : \n");
        //System.out.println(No_Surat);
        //System.out.println(Box_File);
        //System.out.println(sql);
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs != null) {

                table = new JTable(buildTableModel(rs));

                table.setPreferredScrollableViewportSize(new Dimension(600, 200));
                table.setFillsViewportHeight(true);

                JScrollPane scr = new JScrollPane(table);
                return scr;
            }

            rs.close();
            statement.close();
        } catch (Exception ex) {
            //System.out.println("Error reading database information!");
            //System.out.println(ex);
        }
        return null;
    }

    public DefaultTableModel buildTableModel(ResultSet rs)
            throws SQLException {

        ResultSetMetaData metaData = rs.getMetaData();

        // names of columns
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // data of the table
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));

            }
            data.add(vector);
        }
        dtm = new DefaultTableModel(data, columnNames);
        return dtm;
    }

}
