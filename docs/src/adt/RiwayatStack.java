package adt;

import java.util.ArrayList;

/**
 * ADT Stack (LIFO) untuk menyimpan riwayat aktivitas KRS.
 * Struktur internal disembunyikan; pengguna hanya memakai operasi publik.
 */
public class RiwayatStack {
    private static final int KAPASITAS_DEFAULT = 10;

    private ArrayList<String> data;
    private int kapasitas;

    public RiwayatStack(int kapasitas) {
        this.data = new ArrayList<>();
        if (kapasitas <= 0) {
            System.out.println("Kapasitas tidak valid, memakai kapasitas default " + KAPASITAS_DEFAULT + ".");
            this.kapasitas = KAPASITAS_DEFAULT;
        } else {
            this.kapasitas = kapasitas;
        }
    }

    /**
     * Prasyarat: stack tidak penuh. Efek: elemen baru menjadi teratas.
     * @return true jika berhasil, false jika stack penuh
     */
    public boolean push(String aktivitas) {
        if (isFull()) {
            System.out.println("Kesalahan: riwayat penuh (kapasitas " + kapasitas + ").");
            return false;
        }
        data.add(aktivitas);
        return true;
    }

    /**
     * Prasyarat: stack tidak kosong. Efek: elemen teratas dikeluarkan.
     * @return elemen teratas, atau null jika stack kosong
     */
    public String pop() {
        if (isEmpty()) {
            System.out.println("Kesalahan: riwayat kosong, tidak ada yang bisa di-pop.");
            return null;
        }
        return data.remove(data.size() - 1);
    }

    /** Melihat elemen teratas tanpa mengeluarkannya; null jika kosong. */
    public String peek() {
        if (isEmpty()) {
            System.out.println("Kesalahan: riwayat kosong.");
            return null;
        }
        return data.get(data.size() - 1);
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public boolean isFull() {
        return data.size() >= kapasitas;
    }

    public int size() {
        return data.size();
    }

    /** Menampilkan isi dari elemen teratas ke terbawah. */
    public void tampilkan() {
        if (isEmpty()) {
            System.out.println("Riwayat kosong.");
            return;
        }
        System.out.println("Isi riwayat (atas ke bawah), " + data.size() + "/" + kapasitas + ":");
        for (int i = data.size() - 1; i >= 0; i--) {
            System.out.println("   " + (data.size() - i) + ". " + data.get(i));
        }
    }
}