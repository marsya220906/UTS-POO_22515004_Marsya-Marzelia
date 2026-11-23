package model;

/**
 * Mewakili mata kuliah beserta kuota pesertanya.
 */
public class MataKuliah {
    private String kode;
    private String nama;
    private int sks;
    private int kuota;
    private int jumlahTerisi;

    public MataKuliah() {
        this.kode = "-";
        this.nama = "-";
        this.sks = 1;
        this.kuota = 1;
        this.jumlahTerisi = 0;
    }

    public MataKuliah(String kode, String nama, int sks, int kuota) {
        this();
        setKode(kode);
        setNama(nama);
        setSks(sks);
        setKuota(kuota);
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        if (kode == null || kode.trim().isEmpty()) {
            System.out.println("Kesalahan: kode mata kuliah tidak boleh kosong.");
            return;
        }
        this.kode = kode.trim();
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        if (nama == null || nama.trim().isEmpty()) {
            System.out.println("Kesalahan: nama mata kuliah tidak boleh kosong.");
            return;
        }
        this.nama = nama.trim();
    }

    public int getSks() {
        return sks;
    }

    public void setSks(int sks) {
        if (sks <= 0) {
            System.out.println("Kesalahan: SKS harus lebih dari 0.");
            return;
        }
        this.sks = sks;
    }

    public int getKuota() {
        return kuota;
    }

    public void setKuota(int kuota) {
        // kuota baru tidak boleh lebih kecil dari peserta yang sudah terdaftar
        if (kuota <= 0 || kuota < jumlahTerisi) {
            System.out.println("Kesalahan: kuota harus lebih dari 0 dan tidak kurang dari jumlah peserta.");
            return;
        }
        this.kuota = kuota;
    }

    public int getJumlahTerisi() {
        return jumlahTerisi;
    }

    public void setJumlahTerisi(int jumlahTerisi) {
        if (jumlahTerisi < 0 || jumlahTerisi > kuota) {
            System.out.println("Kesalahan: jumlah terisi harus antara 0 dan kuota.");
            return;
        }
        this.jumlahTerisi = jumlahTerisi;
    }

    public boolean isPenuh() {
        return jumlahTerisi >= kuota;
    }

    /**
     * Menambah satu peserta.
     * @return true jika berhasil, false jika kuota penuh
     */
    public boolean tambahPeserta() {
        if (isPenuh()) {
            System.out.println("Kesalahan: kuota mata kuliah " + kode + " sudah penuh.");
            return false;
        }
        this.jumlahTerisi++;
        return true;
    }

    public void kurangiPeserta() {
        if (jumlahTerisi > 0) {
            this.jumlahTerisi--;
        }
    }

    public int sisaKuota() {
        return kuota - jumlahTerisi;
    }

    public void tampilkanInfo() {
        System.out.println("Kode: " + kode + " | Nama: " + nama + " | SKS: " + sks
                + " | Terisi: " + jumlahTerisi + "/" + kuota);
    }
}