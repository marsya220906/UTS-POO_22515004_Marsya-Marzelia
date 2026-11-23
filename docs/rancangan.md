## Daftar Class

1. Mahasiswa (entitas)
2. MataKuliah (entitas)
3. KRS (entitas)
4. Kampus (entitas)
5. RiwayatStack (ADT)
6. Main (menu console)

## Mahasiswa
Atribut:
1. nim : String
2. nama : String
3. prodi : String
4. semester : int

Konstruktor:
1. Mahasiswa()
2. Mahasiswa(nim : String, nama : String, prodi : String, semester : int)

Method perilaku:
1. tampilkanInfo() : void
2. naikSemester() : void
3. isSemesterValid() : boolean

Getter dan setter:
1. getNim() : String
2. setNim(nim : String) : void
3. getNama() : String
4. setNama(nama : String) : void
5. getProdi() : String
6. setProdi(prodi : String) : void
7. getSemester() : int
8. setSemester(semester : int) : void

## MataKuliah
Atribut:
1. kode : String
2. nama : String
3. sks : int
4. kuota : int
5. jumlahTerisi : int

Konstruktor:
1. MataKuliah()
2. MataKuliah(kode : String, nama : String, sks : int, kuota : int)

Method perilaku:
1. isPenuh() : boolean
2. tambahPeserta() : boolean
3. kurangiPeserta() : void
4. sisaKuota() : int
5. tampilkanInfo() : void

Getter dan setter:
1. getter dan setter untuk semua atribut
2. setter memuat validasi (sks lebih dari 0, kuota lebih dari 0)

## KRS
Atribut:
1. idKrs : String
2. mahasiswa : Mahasiswa
3. daftarMataKuliah : ArrayList<MataKuliah>
4. aktif : boolean

Konstruktor:
1. KRS()
2. KRS(idKrs : String, mahasiswa : Mahasiswa)

Method perilaku:
1. tambahMataKuliah(mk : MataKuliah) : boolean (cek batas 24 SKS, kuota, dan duplikat)
2. hapusMataKuliah(kode : String) : boolean
3. hitungTotalSks() : int
4. tampilkanInfo() : void

Getter dan setter:
1. getter dan setter untuk semua atribut

## Kampus
Atribut:
1. nama : String
2. alamat : String
3. daftarMahasiswa : ArrayList<Mahasiswa>
4. daftarMataKuliah : ArrayList<MataKuliah>
5. daftarKrs : ArrayList<KRS>
6. riwayat : RiwayatStack

Konstruktor:
1. Kampus()
2. Kampus(nama : String, alamat : String)

Method perilaku:
1. tambahMahasiswa(m : Mahasiswa) : boolean
2. tambahMataKuliah(mk : MataKuliah) : boolean
3. cariMahasiswa(nim : String) : Mahasiswa
4. cariMataKuliah(kode : String) : MataKuliah
5. buatKrs(nim : String, kodeMk : String) : boolean
6. batalkanKrsTerakhir() : boolean (memakai pop pada RiwayatStack)
7. tampilkanSemuaMahasiswa() : void
8. tampilkanSemuaMataKuliah() : void
9. tampilkanRingkasan() : void

## RiwayatStack (ADT Stack)
Atribut (semua private):
1. data : ArrayList<String>
2. kapasitas : int

Konstruktor:
1. RiwayatStack(kapasitas : int)

Operasi publik:
1. push(aktivitas : String) : boolean
2. pop() : String
3. peek() : String
4. isEmpty() : boolean
5. isFull() : boolean
6. size() : int
7. tampilkan() : void

Penanganan kondisi batas:
1. push saat penuh: mengembalikan false dan menampilkan pesan stack penuh.
2. pop dan peek saat kosong: mengembalikan null dan menampilkan pesan stack kosong.

## Main
Atribut:
1. scanner : Scanner
2. kampus : Kampus

Method:
1. main(args : String[]) : void
2. tampilkanMenu() : void (private)
3. bacaInt(pesan : String) : int (private, validasi input angka)
4. bacaString(pesan : String) : String (private, validasi input tidak kosong)

Menu:
1. Tambah dan tampilkan mahasiswa
2. Tambah dan tampilkan mata kuliah
3. Buat KRS (menghubungkan mahasiswa dan mata kuliah)
4. Cari data (berdasarkan NIM atau kode)
5. Operasi stack (push, pop, peek, tampilkan isi)
6. Tampilkan ringkasan
7. Keluar

