package kp;
/**
 * Memanggil method untuk membuat form surat baru khusus untuk surat keluar
 */

/**
 *
 * @author Gina Andriyani dan Siti Jumaliaya
 */
public class Surat_Keluar {

    public Surat_Keluar() {
        FormSurat fs = new FormSurat();
        fs.bikinFormKeluar("", "", "", "", "", "", "", "Upload File Surat");
    }
}
