package model;

import adt.RiwayatStack;
import java.util.ArrayList;

/**
 * Class pusat yang mengelola mahasiswa, mata kuliah, KRS, dan riwayat aktivitas.
 * Riwayat bersifat komposisi: dibuat dan dimiliki penuh oleh Kampus.
 */
public class Kampus {
    private static final int KAPASITAS_RIWAYAT = 10;
    private static final String PREFIX_KRS = "KRS";

    private String nama;
    private String alamat;
    private ArrayList<Mahasiswa> daftarMahasiswa;
    private ArrayList<MataKuliah> daftarMataKuliah;
    private ArrayList<KRS> daftarKrs;
    private RiwayatStack riwayat;

    public Kampus() {
        this.nama = "-";
        this.alamat = "-";
        this.daftarMahasiswa = new ArrayList<>();
        this.daftarMataKuliah = new ArrayList<>();
        this.daftarKrs = new ArrayList<>();
        this.riwayat = new RiwayatStack(KAPASITAS_RIWAYAT);
    }

    public Kampus(String nama, String alamat) {
        this();
        setNama(nama);
        setAlamat(alamat);
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        if (nama == null || nama.trim().isEmpty()) {
            System.out.println("Kesalahan: nama kampus tidak boleh kosong.");
            return;
        }
        this.nama = nama.trim();
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        if (alamat == null || alamat.trim().isEmpty()) {
            System.out.println("Kesalahan: alamat tidak boleh kosong.");
            return;
        }
        this.alamat = alamat.trim();
    }

    public ArrayList<Mahasiswa> getDaftarMahasiswa() {
        return new ArrayList<>(daftarMahasiswa);
    }

    public ArrayList<MataKuliah> getDaftarMataKuliah() {
        return new ArrayList<>(daftarMataKuliah);
    }

    public ArrayList<KRS> getDaftarKrs() {
        return new ArrayList<>(daftarKrs);
    }

    /** Riwayat diberikan agar menu bisa memakai operasi ADT secara langsung. */
    public RiwayatStack getRiwayat() {
        return riwayat;
    }

    public boolean tambahMahasiswa(Mahasiswa m) {
        if (m == null) {
            return false;
        }
        if (cariMahasiswa(m.getNim()) != null) {
            System.out.println("Kesalahan: NIM " + m.getNim() + " sudah terdaftar.");
            return false;
        }
        daftarMahasiswa.add(m);
        return true;
    }

    public boolean tambahMataKuliah(MataKuliah mk) {
        if (mk == null) {
            return false;
        }
        if (cariMataKuliah(mk.getKode()) != null) {
            System.out.println("Kesalahan: kode mata kuliah " + mk.getKode() + " sudah terdaftar.");
            return false;
        }
        daftarMataKuliah.add(mk);
        return true;
    }

    /** @return mahasiswa dengan NIM tersebut, atau null jika tidak ditemukan */
    public Mahasiswa cariMahasiswa(String nim) {
        for (Mahasiswa m : daftarMahasiswa) {
            if (m.getNim().equalsIgnoreCase(nim)) {
                return m;
            }
        }
        return null;
    }

    /** @return mata kuliah dengan kode tersebut, atau null jika tidak ditemukan */
    public MataKuliah cariMataKuliah(String kode) {
        for (MataKuliah mk : daftarMataKuliah) {
            if (mk.getKode().equalsIgnoreCase(kode)) {
                return mk;
            }
        }
        return null;
    }

    /**
     * Mendaftarkan satu mata kuliah ke KRS aktif mahasiswa (dibuat baru jika belum ada),
     * lalu mencatat aktivitasnya ke riwayat.
     */
    public boolean buatKrs(String nim, String kodeMk) {
        Mahasiswa m = cariMahasiswa(nim);
        if (m == null) {
            System.out.println("Kesalahan: mahasiswa dengan NIM " + nim + " tidak ditemukan.");
            return false;
        }
        MataKuliah mk = cariMataKuliah(kodeMk);
        if (mk == null) {
            System.out.println("Kesalahan: mata kuliah dengan kode " + kodeMk + " tidak ditemukan.");
            return false;
        }
        // cek riwayat dulu supaya tidak ada perubahan data bila riwayat penuh
        if (riwayat.isFull()) {
            System.out.println("Kesalahan: riwayat penuh. Batalkan atau hapus aktivitas terakhir dulu.");
            return false;
        }

        KRS krs = cariKrsAktif(m);
        boolean krsBaru = false;
        if (krs == null) {
            krs = new KRS(String.format("KRS%03d", daftarKrs.size() + 1), m);
            krsBaru = true;
        }
        if (!krs.tambahMataKuliah(mk)) {
            return false;
        }
        if (krsBaru) {
            daftarKrs.add(krs);
        }
        riwayat.push(PREFIX_KRS + "|" + krs.getIdKrs() + "|" + mk.getKode());
        System.out.println("Berhasil: " + m.getNama() + " mengambil " + mk.getNama()
                + " (" + krs.getIdKrs() + ").");
        return true;
    }

    /**
     * Mengambil aktivitas teratas dari riwayat (pop). Jika aktivitas itu berupa
     * penambahan mata kuliah ke KRS, perubahannya dibatalkan dan kuota dikembalikan.
     */
    public boolean batalkanKrsTerakhir() {
        String aktivitas = riwayat.pop();
        if (aktivitas == null) {
            return false;
        }
        String[] bagian = aktivitas.split("\\|");
        if (bagian.length != 3 || !bagian[0].equals(PREFIX_KRS)) {
            System.out.println("Catatan '" + aktivitas + "' dihapus dari riwayat (bukan aktivitas KRS).");
            return true;
        }
        KRS krs = cariKrs(bagian[1]);
        if (krs == null || !krs.hapusMataKuliah(bagian[2])) {
            System.out.println("Kesalahan: aktivitas tidak dapat dibatalkan.");
            return false;
        }
        System.out.println("Dibatalkan: mata kuliah " + bagian[2] + " dari " + bagian[1] + ".");
        return true;
    }

    private KRS cariKrs(String id) {
        for (KRS k : daftarKrs) {
            if (k.getIdKrs().equalsIgnoreCase(id)) {
                return k;
            }
        }
        return null;
    }

    private KRS cariKrsAktif(Mahasiswa m) {
        for (KRS k : daftarKrs) {
            if (k.getMahasiswa() == m && k.isAktif()) {
                return k;
            }
        }
        return null;
    }

    public void tampilkanSemuaMahasiswa() {
        if (daftarMahasiswa.isEmpty()) {
            System.out.println("Belum ada data mahasiswa.");
            return;
        }
        for (Mahasiswa m : daftarMahasiswa) {
            m.tampilkanInfo();
        }
    }

    public void tampilkanSemuaMataKuliah() {
        if (daftarMataKuliah.isEmpty()) {
            System.out.println("Belum ada data mata kuliah.");
            return;
        }
        for (MataKuliah mk : daftarMataKuliah) {
            mk.tampilkanInfo();
        }
    }

    public void tampilkanSemuaKrs() {
        if (daftarKrs.isEmpty()) {
            System.out.println("Belum ada KRS.");
            return;
        }
        for (KRS k : daftarKrs) {
            k.tampilkanInfo();
        }
    }

    public void tampilkanRingkasan() {
        int totalSks = 0;
        for (KRS k : daftarKrs) {
            totalSks += k.hitungTotalSks();
        }
        System.out.println("=== Ringkasan " + nama + " ===");
        System.out.println("Jumlah mahasiswa     : " + daftarMahasiswa.size());
        System.out.println("Jumlah mata kuliah   : " + daftarMataKuliah.size());
        System.out.println("Jumlah KRS           : " + daftarKrs.size());
        System.out.println("Total SKS semua KRS  : " + totalSks);
        System.out.println("Isi riwayat          : " + riwayat.size());
    }
}