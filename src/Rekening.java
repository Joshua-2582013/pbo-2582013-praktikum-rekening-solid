import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * Abstract Class Rekening:
 * Tanggung jawab tunggal (SRP): Mengelola data dasar rekening (nomor, pemilik, saldo)
 * dan aturan transaksi dasar (setor, tarik, potong biaya admin).
 *
 * PERUBAHAN DARI BAB 11 (Penerapan SRP):
 * Method cetakInfo() dihapus dari sini karena urusan tampilan/format laporan bukan tanggung jawab class model data.
 * Sebagai gantinya, disediakan method abstract keterangan() agar setiap subclass cukup menyediakan teks deskripsinya saja.
 */
public abstract class Rekening {
    private String nomor;
    private String pemilik;
    protected int saldo; // protected agar bisa langsung diakses/diubah oleh subclass (Tabungan, Giro, Deposito)

    public Rekening(String nomor, String pemilik, int saldo) {
        this.nomor = nomor;
        this.pemilik = pemilik;
        this.saldo = saldo;
    }

    public String getNomor() {
        return nomor;
    }

    public String getPemilik() {
        return pemilik;
    }

    public int getSaldo() {
        return saldo;
    }

    // Menambah saldo jika nominal setor valid (> 0)
    public boolean setor(int jumlah) {
        if (jumlah <= 0) {
            return false;
        }
        saldo += jumlah;
        return true;
    }

    /**
     * Kontrak tarik:
     * Mengembalikan true kalau penarikan berhasil,
     * false kalau gagal,
     * dan tidak pernah melempar exception.
     *
     * Catatan untuk dosen (LSP): Kontrak ini menjadi patokan bagi semua subclass.
     * Subclass boleh memperketat/mengubah syarat penarikan (seperti Giro dengan overdraft atau Deposito yang selalu false),
     * tetapi TIDAK BOLEH melempar exception yang membuat program berhenti tiba-tiba.
     */
    public boolean tarik(int jumlah) {
        if (jumlah <= 0 || jumlah > saldo) {
            return false;
        }
        saldo -= jumlah;
        return true;
    }

    // Setiap jenis rekening punya aturan biaya admin berbeda, wajib di-override oleh subclass
    public abstract int biayaAdminBulanan();

    // Mengembalikan informasi spesifik rekening (baris kedua di laporan), misal suku bunga atau limit overdraft
    public abstract String keterangan();

    // Template method sederhana: mengambil biaya admin spesifik subclass lalu memotong saldo
    public int potongBiayaAdmin() {
        int biaya = biayaAdminBulanan();
        saldo -= biaya;
        return biaya;
    }

    // Helper static untuk memformat angka int menjadi format Rupiah dengan titik pemisah ribuan (contoh: Rp1.000.000)
    public static String formatRupiah(int jumlah) {
        DecimalFormatSymbols simbol = new DecimalFormatSymbols(Locale.US);
        simbol.setGroupingSeparator('.');
        return "Rp" + new DecimalFormat("#,##0", simbol).format(jumlah);
    }
}