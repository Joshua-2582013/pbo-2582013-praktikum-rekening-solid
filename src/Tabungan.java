import java.util.Locale;

/**
 * Subclass Tabungan:
 * Mewarisi sifat dasar Rekening (extends Rekening) sekaligus memiliki perilaku berbunga (implements BerBunga).
 */
public class Tabungan extends Rekening implements BerBunga {
    private double bungaPersen;

    public Tabungan(String nomor, String pemilik, int saldo, double bungaPersen) {
        super(nomor, pemilik, saldo); // Memanggil constructor superclass Rekening
        this.bungaPersen = bungaPersen;
    }

    @Override
    public int tambahBunga() {
        // Menghitung bunga dari saldo setelah dipotong biaya admin, dibulatkan ke bilangan bulat terdekat
        int bunga = (int) Math.round(saldo * bungaPersen / 100);
        setor(bunga); // Menambahkan bunga ke saldo menggunakan method setor() milik superclass
        return bunga;
    }

    @Override
    public int biayaAdminBulanan() {
        // Aturan bisnis Tabungan: gratis biaya admin jika saldo minimal Rp1.000.000, selain itu kena Rp5.000
        if (saldo >= 1_000_000) {
            return 0;
        }
        return 5_000;
    }

    @Override
    public String keterangan() {
        // Hanya mengembalikan String data tanpa mencetak langsung ke layar
        return String.format(Locale.US, "Tabungan, bunga %.1f%% per bulan", bungaPersen);
    }
}