/**
 * Subclass Giro:
 * Mewarisi Rekening, namun TIDAK mengimplementasikan BerBunga karena Giro tidak mendapat bunga bulanan.
 * Memiliki keunikan berupa batas overdraft (saldo boleh minus hingga batas tertentu).
 */
public class Giro extends Rekening {
    private int batasOverdraft;

    public Giro(String nomor, String pemilik, int saldo, int batasOverdraft) {
        super(nomor, pemilik, saldo);
        this.batasOverdraft = batasOverdraft;
    }

    @Override
    public boolean tarik(int jumlah) {
        // Override aturan tarik: penarikan tetap diizinkan meskipun saldo tidak cukup,
        // selama sisa saldo setelah ditarik tidak melewati batas negatif (-batasOverdraft)
        if (jumlah <= 0 || saldo - jumlah < -batasOverdraft) {
            return false;
        }
        saldo -= jumlah;
        return true;
    }

    @Override
    public int biayaAdminBulanan() {
        // Biaya admin flat untuk semua rekening Giro
        return 15_000;
    }

    @Override
    public String keterangan() {
        // Menggunakan helper formatRupiah() yang diwarisi dari Rekening
        return "Giro, batas overdraft " + formatRupiah(batasOverdraft);
    }
}