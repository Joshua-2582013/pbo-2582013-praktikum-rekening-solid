/**
 * Class LaporanBank (Penerapan Single Responsibility Principle - SRP):
 * Alasan perubahan class ini HANYA SATU: jika format tampilan/cetakan laporan bank berubah.
 * Sebelumnya tanggung jawab pencetakan tersebar di Rekening.cetakInfo() dan looping di main().
 */
public class LaporanBank {

    // Mencetak header judul beserta daftar seluruh rekening dan keterangannya
    public void cetakDaftar(String judul, Rekening[] semua) {
        System.out.println(judul);
        for (Rekening r : semua) {
            // Baris 1: Nomor, Nama Pemilik (rata kiri 6 karakter), dan Saldo (rata kanan 12 karakter)
            System.out.printf("%s | %-6s | %12s%n", r.getNomor(), r.getPemilik(), Rekening.formatRupiah(r.getSaldo()));
            // Baris 2: Memanggil keterangan() secara polimorfik sesuai jenis rekeningnya
            System.out.println("      " + r.keterangan());
        }
        System.out.println();
    }

    // Menghitung dan mencetak akumulasi total seluruh dana yang tersimpan di bank
    public void cetakTotal(Rekening[] semua) {
        int total = 0;
        for (Rekening r : semua) {
            total += r.getSaldo();
        }
        System.out.println("-----------------------------------");
        System.out.println("Total dana bank : " + Rekening.formatRupiah(total));
    }
}