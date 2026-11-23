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