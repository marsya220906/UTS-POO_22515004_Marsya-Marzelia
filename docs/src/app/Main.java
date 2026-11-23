package app;

import model.Kampus;
import model.Mahasiswa;
import model.MataKuliah;
import java.util.Scanner;

/**
 * Menu console untuk sistem akademik mini.
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Kampus kampus = new Kampus("Kampus Utama", "Alamat belum diisi");

    public static void main(String[] args) {
        boolean jalan = true;
        while (jalan) {
            tampilkanMenu();
            int pilihan = bacaInt("Pilih menu: ");
            switch (pilihan) {
                case 1: menuMahasiswa(); break;
                case 2: menuMataKuliah(); break;
                case 3: menuBuatKrs(); break;
                case 4: menuCari(); break;
                case 5: menuStack(); break;
                case 6: kampus.tampilkanRingkasan(); break;
                case 7:
                    System.out.println("Terima kasih.");
                    jalan = false;
                    break;
                default:
                    System.out.println("Kesalahan: pilihan menu tidak tersedia.");
            }
        }
    }

    private static void tampilkanMenu() {
        System.out.println();
        System.out.println("=== SISTEM AKADEMIK MINI ===");
        System.out.println("1. Tambah dan tampilkan mahasiswa");
        System.out.println("2. Tambah dan tampilkan mata kuliah");
        System.out.println("3. Buat KRS (mahasiswa mengambil mata kuliah)");
        System.out.println("4. Cari data");
        System.out.println("5. Operasi riwayat (stack)");
        System.out.println("6. Tampilkan ringkasan");
        System.out.println("7. Keluar");
    }

    private static void menuMahasiswa() {
        System.out.println("1. Tambah mahasiswa  2. Tampilkan semua");
        int pilih = bacaInt("Pilih: ");
        if (pilih == 1) {
            String nim = bacaString("NIM: ");
            String nama = bacaString("Nama: ");
            String prodi = bacaString("Program studi: ");
            int semester = bacaInt("Semester: ");
            Mahasiswa m = new Mahasiswa(nim, nama, prodi, semester);
            // setter menolak nilai tidak valid, jadi cek apakah semester diterima
            if (m.getSemester() != semester) {
                System.out.println("Data tidak disimpan karena semester tidak valid.");
            } else if (kampus.tambahMahasiswa(m)) {
                System.out.println("Mahasiswa berhasil ditambahkan.");
            }
        }
        kampus.tampilkanSemuaMahasiswa();
    }

    private static void menuMataKuliah() {
        System.out.println("1. Tambah mata kuliah  2. Tampilkan semua");
        int pilih = bacaInt("Pilih: ");
        if (pilih == 1) {
            String kode = bacaString("Kode: ");
            String nama = bacaString("Nama: ");
            int sks = bacaInt("SKS: ");
            int kuota = bacaInt("Kuota: ");
            MataKuliah mk = new MataKuliah(kode, nama, sks, kuota);
            if (mk.getSks() != sks || mk.getKuota() != kuota) {
                System.out.println("Data tidak disimpan karena SKS atau kuota tidak valid.");
            } else if (kampus.tambahMataKuliah(mk)) {
                System.out.println("Mata kuliah berhasil ditambahkan.");
            }
        }
        kampus.tampilkanSemuaMataKuliah();
    }

    private static void menuBuatKrs() {
        String nim = bacaString("NIM mahasiswa: ");
        String kode = bacaString("Kode mata kuliah: ");
        kampus.buatKrs(nim, kode);
        kampus.tampilkanSemuaKrs();
    }

    private static void menuCari() {
        System.out.println("1. Cari mahasiswa (NIM)  2. Cari mata kuliah (kode)");
        int pilih = bacaInt("Pilih: ");
        if (pilih == 1) {
            Mahasiswa m = kampus.cariMahasiswa(bacaString("NIM: "));
            if (m == null) {
                System.out.println("Data tidak ditemukan.");
            } else {
                m.tampilkanInfo();
            }
        } else if (pilih == 2) {
            MataKuliah mk = kampus.cariMataKuliah(bacaString("Kode: "));
            if (mk == null) {
                System.out.println("Data tidak ditemukan.");
            } else {
                mk.tampilkanInfo();
            }
        } else {
            System.out.println("Kesalahan: pilihan tidak tersedia.");
        }
    }

    private static void menuStack() {
        System.out.println("1. Push catatan  2. Pop / batalkan aktivitas terakhir");
        System.out.println("3. Peek          4. Tampilkan isi        5. Ukuran");
        int pilih = bacaInt("Pilih: ");
        switch (pilih) {
            case 1:
                if (kampus.getRiwayat().push(bacaString("Catatan: "))) {
                    System.out.println("Catatan ditambahkan ke riwayat.");
                }
                break;
            case 2:
                kampus.batalkanKrsTerakhir();
                break;
            case 3:
                String teratas = kampus.getRiwayat().peek();
                if (teratas != null) {
                    System.out.println("Teratas: " + teratas);
                }
                break;
            case 4:
                kampus.getRiwayat().tampilkan();
                break;
            case 5:
                System.out.println("Ukuran riwayat: " + kampus.getRiwayat().size()
                        + " | Kosong: " + kampus.getRiwayat().isEmpty()
                        + " | Penuh: " + kampus.getRiwayat().isFull());
                break;
            default:
                System.out.println("Kesalahan: pilihan tidak tersedia.");
        }
    }

    /** Membaca bilangan bulat; mengulang sampai input valid agar program tidak crash. */
    private static int bacaInt(String pesan) {
        while (true) {
            System.out.print(pesan);
            String masukan = scanner.nextLine().trim();
            try {
                return Integer.parseInt(masukan);
            } catch (NumberFormatException e) {
                System.out.println("Kesalahan: masukkan angka yang valid.");
            }
        }
    }

    /** Membaca teks; mengulang sampai tidak kosong. */
    private static String bacaString(String pesan) {
        while (true) {
            System.out.print(pesan);
            String masukan = scanner.nextLine().trim();
            if (!masukan.isEmpty()) {
                return masukan;
            }
            System.out.println("Kesalahan: input tidak boleh kosong.");
        }
    }
}