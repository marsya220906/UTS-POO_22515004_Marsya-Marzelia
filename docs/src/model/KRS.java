package model;

import java.util.ArrayList;

/**
 * Kartu Rencana Studi: menghubungkan satu mahasiswa dengan beberapa mata kuliah.
 * Aturan: total SKS maksimal 24 dan kuota mata kuliah terbatas.
 */
public class KRS {
    private static final int SKS_MAKS = 24;

    private String idKrs;
    private Mahasiswa mahasiswa;
    private ArrayList<MataKuliah> daftarMataKuliah;
    private boolean aktif;

    public KRS() {
        this.idKrs = "-";
        this.mahasiswa = null;
        this.daftarMataKuliah = new ArrayList<>();
        this.aktif = true;
    }

    public KRS(String idKrs, Mahasiswa mahasiswa) {
        this();
        setIdKrs(idKrs);
        setMahasiswa(mahasiswa);
    }

    public String getIdKrs() {
        return idKrs;
    }

    public void setIdKrs(String idKrs) {
        if (idKrs == null || idKrs.trim().isEmpty()) {
            System.out.println("Kesalahan: ID KRS tidak boleh kosong.");
            return;
        }
        this.idKrs = idKrs.trim();
    }

    public Mahasiswa getMahasiswa() {
        return mahasiswa;
    }

    public void setMahasiswa(Mahasiswa mahasiswa) {
        if (mahasiswa == null) {
            System.out.println("Kesalahan: mahasiswa pada KRS tidak boleh kosong.");
            return;
        }
        this.mahasiswa = mahasiswa;
    }

    /** Mengembalikan salinan daftar agar list asli tidak bisa diubah dari luar. */
    public ArrayList<MataKuliah> getDaftarMataKuliah() {
        return new ArrayList<>(daftarMataKuliah);
    }

    public boolean isAktif() {
        return aktif;
    }

    public void setAktif(boolean aktif) {
        this.aktif = aktif;
    }

    /**
     * Menambahkan mata kuliah ke KRS.
     * Urutan cek: data kosong, duplikat, batas SKS, lalu kuota.
     * Kuota dicek terakhir karena berhasilnya akan mengurangi sisa kuota.
     */
    public boolean tambahMataKuliah(MataKuliah mk) {
        if (mk == null) {
            System.out.println("Kesalahan: mata kuliah tidak valid.");
            return false;
        }
        if (cariMataKuliah(mk.getKode()) != null) {
            System.out.println("Kesalahan: mata kuliah " + mk.getKode() + " sudah ada di KRS ini.");
            return false;
        }
        if (hitungTotalSks() + mk.getSks() > SKS_MAKS) {
            System.out.println("Kesalahan: total SKS akan melebihi batas " + SKS_MAKS + " SKS.");
            return false;
        }
        if (!mk.tambahPeserta()) {
            return false; // pesan kuota penuh sudah ditampilkan oleh MataKuliah
        }
        daftarMataKuliah.add(mk);
        return true;
    }

    public boolean hapusMataKuliah(String kode) {
        MataKuliah mk = cariMataKuliah(kode);
        if (mk == null) {
            System.out.println("Kesalahan: mata kuliah " + kode + " tidak ada di KRS ini.");
            return false;
        }
        daftarMataKuliah.remove(mk);
        mk.kurangiPeserta(); // kuota dikembalikan
        return true;
    }

    public int hitungTotalSks() {
        int total = 0;
        for (MataKuliah mk : daftarMataKuliah) {
            total += mk.getSks();
        }
        return total;
    }

    private MataKuliah cariMataKuliah(String kode) {
        for (MataKuliah mk : daftarMataKuliah) {
            if (mk.getKode().equalsIgnoreCase(kode)) {
                return mk;
            }
        }
        return null;
    }

    public void tampilkanInfo() {
        String namaMhs = (mahasiswa == null) ? "-" : mahasiswa.getNama();
        System.out.println("KRS " + idKrs + " | Mahasiswa: " + namaMhs
                + " | Total SKS: " + hitungTotalSks() + "/" + SKS_MAKS);
        if (daftarMataKuliah.isEmpty()) {
            System.out.println("   (belum ada mata kuliah)");
            return;
        }
        for (MataKuliah mk : daftarMataKuliah) {
            System.out.print("   - ");
            mk.tampilkanInfo();
        }
    }
}