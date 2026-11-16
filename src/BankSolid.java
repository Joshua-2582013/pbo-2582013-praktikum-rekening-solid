/**
 * Class BankSolid (Pengganti BankPolimorfik):
 * Berperan sebagai entry point (titik masuk) dan komposer/perakit objek.
 *
 * Penerapan SRP di main():
 * Tidak ada lagi logika bisnis (perhitungan bunga/admin) maupun looping pencetakan laporan di sini.
 * main() hanya bertugas:
 * 1. Menginstansiasi objek data rekening.
 * 2. Mengelompokkannya ke dalam array poliformik (Rekening[] dan BerBunga[]).
 * 3. Memanggil service ProsesAkhirBulan dan LaporanBank sesuai urutan yang ditentukan.
 */
public class BankSolid {
    public static void main(String[] args) {
        // 1. Pembuatan objek data rekening awal
        Tabungan ani = new Tabungan("101", "Ani", 1_000_000, 2.5);
        Tabungan citra = new Tabungan("102", "Citra", 300_000, 2.0);
        Giro budi = new Giro("201", "Budi", 200_000, 500_000);

        // 2. Pengelompokan polimorfik:
        // Semua jenis rekening masuk ke array Rekening[]
        Rekening[] semua = { ani, citra, budi };
        // Hanya rekening yang mengimplementasikan BerBunga yang masuk ke BerBunga[]
        BerBunga[] berbunga = { ani, citra };

        // Inisialisasi objek pengelola laporan dan proses akhir bulan
        LaporanBank laporan = new LaporanBank();
        ProsesAkhirBulan proses = new ProsesAkhirBulan();

        // 3. Eksekusi alur program:
        // Cetak kondisi saldo awal sebelum diproses
        laporan.cetakDaftar("========== REKENING AWAL ==========", semua);

        // Jalankan proses akhir bulan dengan urutan wajib: Admin -> Bunga -> Autodebet
        System.out.println("========== AKHIR BULAN ============");
        proses.potongBiayaAdmin(semua);
        proses.tambahBunga(berbunga);
        proses.autodebet(semua, 50_000);
        System.out.println();

        // Cetak kondisi saldo akhir dan total dana bank setelah semua proses selesai
        laporan.cetakDaftar("========== REKENING AKHIR =========", semua);
        laporan.cetakTotal(semua);
    }
}