import java.util.Locale;

/**
 * Subclass Deposito (Penerapan OCP & LSP):
 * - OCP: Menambah jenis rekening baru tanpa mengubah ProsesAkhirBulan maupun LaporanBank.
 * - LSP: Mematuhi kontrak Rekening.tarik() dengan mengembalikan false (bukan melempar exception)
 *        saat penarikan ditolak sebelum jatuh tempo, sehingga bisa diproses aman oleh autodebet().
 */
public class Deposito extends Rekening implements BerBunga {
    private double bungaPersen;

    public Deposito(String nomor, String pemilik, int saldo, double bungaPersen) {
        super(nomor, pemilik, saldo);
        this.bungaPersen = bungaPersen;
    }

    // Exception in thread "main" java.lang.UnsupportedOperationException: Deposito tidak bisa ditarik
    @Override
    public boolean tarik(int jumlah) {
        // Mematuhi kontrak superclass Rekening:
        // Karena deposito tidak bisa ditarik sebelum jatuh tempo, kembalikan false tanpa melempar exception.
        return false;
    }

    @Override
    public int biayaAdminBulanan() {
        // Deposito tanpa biaya admin bulanan
        return 0;
    }

    @Override
    public int tambahBunga() {
        // Menghitung bunga berdasarkan saldo deposito lalu menambahkannya ke saldo
        int bunga = (int) Math.round(saldo * bungaPersen / 100);
        setor(bunga);
        return bunga;
    }

    @Override
    public String keterangan() {
        // Mengembalikan deskripsi baris kedua untuk LaporanBank
        return String.format(Locale.US, "Deposito, bunga %.1f%% per bulan", bungaPersen);
    }
}