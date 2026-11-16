import java.util.Locale;

/**
 * Subclass Deposito (Penerapan Open/Closed Principle - OCP):
 * Menambahkan jenis rekening baru dengan cara memperluas sistem (extends Rekening, implements BerBunga),
 * TANPA mengubah satu baris pun kode di ProsesAkhirBulan maupun LaporanBank.
 *
 * Karakteristik bisnis Deposito:
 * - Tanpa biaya admin (return 0)
 * - Berbunga (implements BerBunga)
 * - Tidak bisa ditarik sebelum jatuh tempo
 */
public class Deposito extends Rekening implements BerBunga {
    private double bungaPersen;

    public Deposito(String nomor, String pemilik, int saldo, double bungaPersen) {
        super(nomor, pemilik, saldo);
        this.bungaPersen = bungaPersen;
    }

    /**
     * SIMULASI PELANGGARAN LSP :
     * Di sini kita sengaja melempar UnsupportedOperationException untuk membuktikan bahwa
     * mengubah perilaku method yang tidak sesuai kontrak superclass (Rekening.tarik)
     * akan menyebabkan program crash saat Deposito diproses secara polimorfik di ProsesAkhirBulan.autodebet().
     */
    @Override
    public boolean tarik(int jumlah) {
        throw new UnsupportedOperationException("Deposito tidak bisa ditarik");
    }

    @Override
    public int biayaAdminBulanan() {
        // Deposito bebas biaya admin bulanan
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