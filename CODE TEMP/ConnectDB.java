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
 * @author Ewald Horn
 * @company JavaK
 */
public class ConnectDB {
    
    JTable table;
            
    Connection con;
    final String driver = "sun.jdbc.odbc.JdbcOdbcDriver";
    final String url = "jdbc:odbc:"
            + "DRIVER={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=";
    String path = "c:" + File.separator + "Projects"
            + File.separator + "INFORMASI_SURAT.accdb"; // where the database can be found

    ////////////////////////////////////////////////////////////
    /**
     * Sets the path to the database.
     */
    public ConnectDB() {

    }

    ////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////
    /**
     * Creates the database connection.
     */
    public void makeConnection() {
        System.out.println("Opening database...\n");
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url + path);
        } catch (Exception ex) {
            System.out.println("Error opening the database!");
            System.out.println(ex);
            System.exit(0);
        }
        System.out.println("Success!");
    }
////////////////////////////////////////////////////////////

    /**
     * Displays all the recipes in the database.
     */

    /**
     * Closes the connection cleanly.
     */
    public void closeConnection() {
        System.out.println("\nClosing database.");
        try {
            con.close();
        } catch (Exception ex) {
            System.out.println("Error closing the database!");
            System.out.println(ex);
        }
    }
    ///////////////////////////////////////////////////////////

    /**
     * Adds a recipe to the database.
     */
    public void addInformasiSuratMasuk(String No_Surat, String Tgl_Terima, String Tgl_Surat, String Pengirim, String Penerima, String Perihal, String Box_File, String Jenis_Surat) {
        String sql = "INSERT INTO Surat_Masuk(No_Surat,Tgl_Terima, Tgl_Surat, Pengirim, Penerima, Perihal, Box_File, Jenis_Surat)"
                + " VALUES('" + No_Surat + "','" + Tgl_Terima + "','" + Tgl_Surat + "','" + Pengirim + "', '" + Penerima + "','" + Perihal + "','" + Box_File + "','" + Jenis_Surat + "')";
        System.out.println(Jenis_Surat);
        System.out.print("\nAdding a recipe : ");
        try {
            Statement statement = con.createStatement();
            int result = statement.executeUpdate(sql);
            System.out.println(" Added " + result + " recipe(s).");
        } catch (Exception ex) {
            System.out.println("Error adding a recipe!");
            System.out.println(ex);
        }
    }
        public void addInformasiSuratKeluar(String No_Surat, String Tgl_Surat, String Pengirim, String Penerima, String Perihal, String Box_File, String Jenis_Surat) {
        String sql = "INSERT INTO Surat_Keluar(No_Surat,Tgl_Terima, Tgl_Surat, Pengirim, Penerima, Perihal, Box_File, Jenis_Surat)"
                + " VALUES('" + No_Surat + "','" + Tgl_Surat + "','" + Pengirim + "', '" + Penerima + "','" + Perihal + "','" + Box_File + "','" + Jenis_Surat + "')";
        System.out.println(Jenis_Surat);
        System.out.print("\nAdding a recipe : ");
        try {
            Statement statement = con.createStatement();
            int result = statement.executeUpdate(sql);
            System.out.println(" Added " + result + " recipe(s).");
        } catch (Exception ex) {
            System.out.println("Error adding a recipe!");
            System.out.println(ex);
        }
    }
    ////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////
    /**
     * Removes a recipe from the database.
     */
    public void removeSurat_Masuk(String input) {
        String sql = "DELETE FROM Surat_Masuk WHERE No_Surat='"+input+"'";
        System.out.print("\nRemoving a recipe : ");
        try {
            Statement statement = con.createStatement();
            int result = statement.executeUpdate(sql);
            System.out.println(" Removed " + result + " recipe(s).");
        } catch (Exception ex) {
            System.out.println("Error removing a recipe!");
            System.out.println(ex);
        }
    }
    
        public void removeSurat_Keluar(String input) {
        String sql = "DELETE FROM Surat_Keluar WHERE No_Surat='"+input+"'";
        System.out.print("\nRemoving a recipe : ");
        try {
            Statement statement = con.createStatement();
            int result = statement.executeUpdate(sql);
            System.out.println(" Removed " + result + " recipe(s).");
        } catch (Exception ex) {
            System.out.println("Error removing a recipe!");
            System.out.println(ex);
        }
    }
    
        public void updateSurat_Masuk(String atribut, String ubahKe) {
        String sql = "UPDATE Surat_Masuk SET cnt_emp = ";
        System.out.print("\nRemoving a recipe : ");
        try {
            Statement statement = con.createStatement();
            int result = statement.executeUpdate(sql);
            System.out.println(" Removed " + result + " recipe(s).");
        } catch (Exception ex) {
            System.out.println("Error removing a recipe!");
            System.out.println(ex);
        }
    }
       public void updateSurat_Keluar(String atribut, String ubahKe) {
        String sql = "UPDATE Surat_Kelaur SET cnt_emp = ";
        System.out.print("\nRemoving a recipe : ");
        try {
            Statement statement = con.createStatement();
            int result = statement.executeUpdate(sql);
            System.out.println(" Removed " + result + " recipe(s).");
        } catch (Exception ex) {
            System.out.println("Error removing a recipe!");
            System.out.println(ex);
        }
    }

    ////////////////////////////////////////////////////////////
    /**
     * Modifies an existing record.
     */
    public JScrollPane showSurat_Masuk() {

        String sql = "SELECT * FROM Surat_Masuk";
        System.out.println("\nRecipes in the database : \n");
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            if (rs != null) {
                while (rs.next()) {
                    table = new JTable(buildTableModel(rs));
                    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                    table.setPreferredScrollableViewportSize(new Dimension(600, 200));
                    table.setFillsViewportHeight(true);
                    //table.columnSelectionChanged(null);
                    
                    JScrollPane scr = new JScrollPane(table);
                    return scr;
                    //System.out.println(No_Surat + Tgl_Terima +Tgl_Surat+ Pengirim+ Penerima+ Perihal+ Box_File);
                }
            }
            rs.close();
            statement.close();
        } catch (Exception ex) {
            System.out.println("Error reading database information!");
            System.out.println(ex);
        }
        return null;
    }
    
        public JScrollPane showSurat_Keluar() {

        String sql = "SELECT * FROM Surat_Keluar";
        System.out.println("\nRecipes in the database : \n");
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            if (rs != null) {
                while (rs.next()) {
                    table = new JTable(buildTableModel(rs));
                    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                    table.setPreferredScrollableViewportSize(new Dimension(600, 200));
                    table.setFillsViewportHeight(true);
                    //table.columnSelectionChanged(null);
                    
                    JScrollPane scr = new JScrollPane(table);
                    return scr;
                    //System.out.println(No_Surat + Tgl_Terima +Tgl_Surat+ Pengirim+ Penerima+ Perihal+ Box_File);
                }
            }
            rs.close();
            statement.close();
        } catch (Exception ex) {
            System.out.println("Error reading database information!");
            System.out.println(ex);
        }
        return null;
    }

    public static DefaultTableModel buildTableModel(ResultSet rs)
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

        return new DefaultTableModel(data, columnNames);

    }
/////////////////////////////////////////////////////////

    /**
     * Main method for ConnectDB.java
     */
//    public static void main(String args[]) {
//        ConnectDB testApp = new ConnectDB();
//        testApp.go();
//    }
}
