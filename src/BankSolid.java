/**
 * Class BankSolid (Update Commit 2 - Penerapan OCP):
 * Satu-satunya perubahan di file ini hanyalah menambahkan objek Deposito milik Dewi
 * dan memasukkannya ke array semua[] dan berbunga[].
 * Alur pemanggilan LaporanBank dan ProsesAkhirBulan tidak berubah sama sekali.
 */
public class BankSolid {
    public static void main(String[] args) {
        // 1. Pembuatan objek data rekening (ditambah Deposito Dewi)
        Tabungan ani = new Tabungan("101", "Ani", 1_000_000, 2.5);
        Tabungan citra = new Tabungan("102", "Citra", 300_000, 2.0);
        Giro budi = new Giro("201", "Budi", 200_000, 500_000);
        Deposito dewi = new Deposito("301", "Dewi", 5_000_000, 4.0);

        // 2. Pengelompokan polimorfik:
        // Dewi otomatis bisa masuk ke Rekening[] karena Deposito extends Rekening
        Rekening[] semua = { ani, citra, budi, dewi };
        // Dewi juga bisa masuk ke BerBunga[] karena Deposito implements BerBunga
        BerBunga[] berbunga = { ani, citra, dewi };

        LaporanBank laporan = new LaporanBank();
        ProsesAkhirBulan proses = new ProsesAkhirBulan();

        // 3. Eksekusi alur program (tidak ada perubahan kode di sini - bukti OCP)
        laporan.cetakDaftar("========== REKENING AWAL ==========", semua);

        System.out.println("========== AKHIR BULAN ============");
        proses.potongBiayaAdmin(semua);
        proses.tambahBunga(berbunga);
        proses.autodebet(semua, 50_000); // Program akan crash di baris ini saat memproses Dewi!
        System.out.println();

        laporan.cetakDaftar("========== REKENING AKHIR =========", semua);
        laporan.cetakTotal(semua);
    }
}