package model;

/**
 * Mewakili seorang mahasiswa pada sistem akademik mini.
 */
public class Mahasiswa {
    private static final int SEMESTER_MAKS = 14;

    private String nim;
    private String nama;
    private String prodi;
    private int semester;

    /** Konstruktor default dengan nilai awal aman. */
    public Mahasiswa() {
        this.nim = "-";
        this.nama = "-";
        this.prodi = "-";
        this.semester = 1;
    }

    /** Konstruktor berparameter; memakai setter agar validasi ikut berjalan. */
    public Mahasiswa(String nim, String nama, String prodi, int semester) {
        this();
        setNim(nim);
        setNama(nama);
        setProdi(prodi);
        setSemester(semester);
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        if (nim == null || nim.trim().isEmpty()) {
            System.out.println("Kesalahan: NIM tidak boleh kosong.");
            return;
        }
        this.nim = nim.trim();
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        if (nama == null || nama.trim().isEmpty()) {
            System.out.println("Kesalahan: nama tidak boleh kosong.");
            return;
        }
        this.nama = nama.trim();
    }

    public String getProdi() {
        return prodi;
    }

    public void setProdi(String prodi) {
        if (prodi == null || prodi.trim().isEmpty()) {
            System.out.println("Kesalahan: program studi tidak boleh kosong.");
            return;
        }
        this.prodi = prodi.trim();
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        if (semester < 1 || semester > SEMESTER_MAKS) {
            System.out.println("Kesalahan: semester harus 1 sampai " + SEMESTER_MAKS + ".");
            return;
        }
        this.semester = semester;
    }

    /** Menaikkan semester satu tingkat jika belum mencapai batas. */
    public void naikSemester() {
        if (semester >= SEMESTER_MAKS) {
            System.out.println("Kesalahan: semester sudah mencapai batas maksimal.");
            return;
        }
        this.semester++;
    }

    /** Mengecek apakah semester berada pada rentang yang sah. */
    public boolean isSemesterValid() {
        return semester >= 1 && semester <= SEMESTER_MAKS;
    }

    public void tampilkanInfo() {
        System.out.println("NIM: " + nim + " | Nama: " + nama
                + " | Prodi: " + prodi + " | Semester: " + semester);
    }
}