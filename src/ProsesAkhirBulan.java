/**
 * Class ProsesAkhirBulan (Penerapan Single Responsibility Principle - SRP):
 * Alasan perubahan class ini HANYA SATU: jika aturan bisnis transaksi akhir bulan bank berubah.
 * Sebelumnya logika looping ini menumpuk di method main() milik BankPolimorfik.
 *
 * Class ini juga menerapkan Polimorfisme murni tanpa menggunakan 'instanceof'.
 */
public class ProsesAkhirBulan {

    // Memotong biaya admin untuk semua jenis rekening menggunakan dynamic dispatch (polimorfisme)
    public void potongBiayaAdmin(Rekening[] semua) {
        for (Rekening r : semua) {
            int biaya = r.potongBiayaAdmin(); // Otomatis memanggil biayaAdminBulanan() milik masing-masing subclass
            System.out.printf("%s biaya admin  : %s%n", r.getNomor(), Rekening.formatRupiah(biaya));
        }
    }

    // Menambahkan bunga khusus untuk array objek yang mengimplementasikan interface BerBunga
    public void tambahBunga(BerBunga[] berbunga) {
        for (BerBunga b : berbunga) {
            int bunga = b.tambahBunga();
            System.out.printf("%s bunga        : %s%n", b.getNomor(), Rekening.formatRupiah(bunga));
        }
    }

    // Melakukan penarikan otomatis (autodebet) sebesar 'jumlah' ke seluruh rekening
    public void autodebet(Rekening[] semua, int jumlah) {
        for (Rekening r : semua) {
            // Memanfaatkan kontrak boolean tarik(): true jika berhasil, false jika ditolak/saldo kurang
            if (r.tarik(jumlah)) {
                System.out.printf("%s autodebet    : %s%n", r.getNomor(), Rekening.formatRupiah(jumlah));
            } else {
                System.out.printf("%s autodebet    : gagal%n", r.getNomor());
            }
        }
    }
}