package kp;

import java.sql.SQLException;

/**
 * Memanggil method untuk membuat form surat baru khusus untuk surat masuk
 */
/**
 *
 * @author Gina Andriyani dan Siti Jumaliaya
 */
public class Surat_Masuk {

    public Surat_Masuk() throws SQLException {
        FormSurat fs = new FormSurat();
        fs.bikinFormMasuk("", "", "", "", "", "", "", "", "Upload File Surat");
    }
}
