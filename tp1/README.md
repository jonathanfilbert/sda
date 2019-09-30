**CSGE602040 - Struktur Data dan Algoritma
Semester Gasal - 2019/
Tugas Pemrograman 1
Deadline: Senin, 30-Sep-2019, 22.00 WIB
Donat DUAARRR**
Source: ​https://imgflip.com/memegenerator
**Deskripsi
Borman** ★ **dan Choco Chips**
Borman ★ sangat menggemari ​ _choco chips_ ​. Di kota tempat Borman ★ tinggal, ​ _chips_ ​tersebut dapat
diperoleh dari donat DUAARRR. Ada banyak variasi donat DUAARRR (setiap variasi donat memiliki
jumlah ​ _chips_ ​tersendiri). Di kota, terdapat sejumlah toko yang menawarkan donat DUAARRR.
Sayangnya, Borman ★ tidak punya uang untuk membeli donat DUAARRR. Namun, dia tetap
bermimpi bisa mengoleksi ​ _choco chips_ ​. Ketika keinginan mengumpulkan ​ _chips_ ​muncul di hari
tertentu, biasanya Borman ★ akan mengunjungi toko-toko donat di kota pada pagi hari. Dia
mencatat donat apa saja (beserta jumlah stok tersedia) yang dipajang di masing-masing toko yang
dikunjunginya.
Borman ★ pulang ke rumah dan memimpikan bahwa dia akan mengoleksi ​ **T** ​ **i** ​ _choco chips_ ​pada hari
tersebut (hari ke-​ **i** ​). Agar tidak terlalu putus asa, Borman ★ pun membayangkan bagaimana saja
caranya dia bisa mengumpulkan total ​ **T** ​ **i** ​ _choco chips_ ​. Misalnya untuk mengumpulkan 3 ​ _choco chips_ ​,
Borman ★ bisa saja memilih
● 3 buah donat bernilai masing-masing 1 ​ _chip_ ​ (cara A), atau
● 1 buah donat bernilai 1 ​ _chip_ ​ DAN 1 donat bernilai 2 ​ _chip_ ​ (cara B), atau
● .... (cara-cara lainnya)
Cara-cara di atas valid jika dan hanya jika toko-toko donat yang buka pada hari itu memang
menawarkan donat dengan jumlah ​ _chip_ ​yang sesuai dan stok yang mencukupi. Kalau misalnya
seluruh toko donat hanya menjual donat bernilai 1 ​ _chip_ ​, maka cara B di atas TIDAK valid. Kalau
misalnya total stok donat bernilai 1 ​ _chip_ di seluruh toko di kota kurang dari 3 buah, maka cara A di
atas TIDAK valid.
Pada akhirnya, Borman ★ tertarik menghitung ada berapa cara memilih donat-donat yang ​ _available_
sehingga terkumpul tepat ​ **T** ​ **i** ​ ​ _choco chips_ ​. Bantulah Borman ★ menghitungnya.
**R, IR, DN, FHP | TP 1 SDA Gasal 2019/**


**Donat DUAARRR dan Toko Donat**
Kenapa disebut donat DUAARRR? Karena donat bisa meledak sewaktu-waktu di sore hari. Pada hari
ke-0, setiap toko menginformasikan stok awal donat yang mereka miliki ke publik. Setiap hari,
informasi donat apa saja yang meledak dan di toko apa akan diumumkan ke penduduk kota.
Toko bisa menambah stok (​ _restock_ ​) donat di hari-hari tertentu. Antar dua toko juga bisa saling
melakukan transfer donat (sejumlah tertentu donat dipindahkan dari satu toko ke toko lain). Setiap
toko hanya menerima ​ _restock_ ​dan transfer jika donat yang di-​ _restock_ atau transfer memiliki jumlah
_chip_ yang sama. Misal, toko A memiliki stok donat X lebih dari 0 dengan ​ **P** buah ​ _choco chips_ ​. Jika
toko A melakukan ​ _restock_ atau menerima transfer donat X dari toko lain dengan jumlah ​ **Q** buah
_choco chips_ ​, maka transfer/​ _restock_ akan gagal dan donat akan dikembalikan ke asalnya (jika ​ _restock_
maka akan dikembalikan ke distributor).
Tidak seluruh toko buka setiap hari. Pelanggan / penikmat donat (termasuk Borman ★) hanya bisa
membeli / mengunjungi toko-toko yang buka pada hari itu saja.
Di sisi lain, ledakan donat (DUAARRR), proses ​ _restock_ ​, dan transfer donat bisa terjadi di toko mana
saja (termasuk yang sedang tutup). Aktivitas ini, jika terjadi, berlangsung pada sore hari. Namun,
tidak seluruh aktivitas harus terjadi setiap hari.


**Masukan**
Baris pertama berisi nilai ​ **N** ​ yang merupakan banyak toko.
Selanjutnya akan ada ​ **N** buah informasi toko donat. Setiap informasi toko donat terdiri dari 1 baris
yang berisi ​ **P** ​ **i** dan ​ **D** ​ **i** (masing-masing merupakan nama toko donat dan jumlah jenis donat yang
ditawarkan), diikuti ​ **D** ​ **i** baris berikutnya yang berisi ​ **S** ​ **i** ​, ​ **J** ​ **i** ​, dan ​ **C** ​ **i** (masing-masing merupakan nama
donat, jumlah stok donat, dan nilai ​ _choco chips_ ​ yang dimiliki donat tersebut).
Baris selanjutnya berisi nilai ​ **Q** ​ yang menyatakan jumlah hari.
Lalu akan ada ​ **Q** buah informasi (masing-masing adalah informasi untuk hari ke-1, ke-2, ... ke-Q)
mengenai toko donat yang buka, jumlah ​ _choco chips_ yang ingin didapatkan Borman ★, dan
donat-donat yang akan meledak di setiap toko.
Setiap informasi dimulai dengan ​ **X** ​ **i** ​yang merupakan banyak toko yang buka hari ke-​ **i** ​. Baris
berikutnya akan berisi ​ **X** ​ **i** ​ nama-nama toko yang buka pada hari ke-​ **i** ​.
Baris berikutnya akan berisi “Target ​ **T** ​i​” dimana ​ **T** ​ **i** ​menyatakan jumlah ​ _choco chips_ ​yang diinginkan
oleh Borman ★ pada hari ke​ **-i** ​. Ada kalanya, Borman ★ tidak menginginkan ​ _chips_ ​pada suatu hari.
Baris berikutnya akan berisi “Duar ​ **K** ​ **i** ​” di mana ​ **K** ​ **i** ​menyatakan banyak aktivitas ledakan donat yang
terjadi pada hari tersebut. ​ **K** ​i baris berikutnya akan berisi informasi donat mana yang akan meledak
dengan format “​ **P** ​ **i S** ​ **i B** ​ **i** ​” dimana ​ **P** ​ **i** ​, ​ **S** ​ **i** ​, dan ​ **B** ​ **i** ​masing-masing menyatakan nama toko tempat donat
yang meledak, jenis donat yang meledak, dan jumlah donat yang meledak.
Baris berikutnya akan berisi “Restock ​ **L** ​ **i** ​” dimana ​ **L** ​ **i** ​menyatakan banyak aktivitas ​ _restock_ ​yang akan
terjadi pada hari tersebut. ​ **L** ​ **i** ​baris berikutnya akan berisi informasi aktivitas ​ _restock_ ​dengan format
“​ **P** ​ **i S** ​ **i B** ​ **i** ​” dimana ​ **P** ​ **i** ​, ​ **S** ​ **i** ​, ​ **B** ​ **i** ​, dan ​ **C** ​ **i** ​masing-masing menyatakan nama toko tempat donat yang
di-​ _restock_ ​, jenis donat yang di-​ _restock_ ​, jumlah donat yang di-​ _restock_ ​, dan ​ _choco chips_ donat yang
di-restock.
Baris berikutnya akan berisi “Transfer ​ **M** ​ **i** ​” dimana ​ **M** ​ **i** ​menyatakan banyak aktivitas transfer yang
akan terjadi pada hari tersebut. ​ **M** ​ **i** ​baris berikutnya akan berisi informasi aktivitas transfer dengan
format “​ **P** ​ **i Z** ​ **i S** ​ **i B** ​ **i** ​” dimana ​ **P** ​ **i** ​, ​ **Z** ​ **i** ​, **S** ​ **i** ​, dan ​ **B** ​ **i** ​masing-masing menyatakan toko asal donat yang
ditransfer, toko destinasi donat yang ditransfer, jenis donat yang ditransfer, dan jumlah donat yang
ditransfer.


Untuk lebih jelasnya dapat melihat skema di bawah ini:
N
P​1​ D​ 1 
S​1​ J​1 ​C​ 1 
S​2​ J​2 ​C​ 2 
...
S​D1​ J​D1 ​C​D
...
P​N​ D​N
S​1​ J​1 ​C​ 1 
...
S​DN​ J​DN ​C​DN
Q
X​ 1 
P​1 ​P​2​ ... P​X
Target T​ 1 
Duar K​ 1 
P​1​ S​1​ B​ 1 
...
P​K1​ S​K1​ B​K
Restock L​ 1 
P​1​ S​1​ B​1 ​C​ 1 
...
P​L1​ S​L1​ B​L1 ​C​L
Transfer M​ 1 
P​1​ Z​1​ S​1​ B​ 1 
...
P​M1​ Z​M1​ S​M1​ B​M
...
...
X​Q
P​1 ​P​2​ ... P​XQ

## Target 0 

## Duar

#### P​1​ S​1​ B​ 1 

#### ...

#### P​KQ​ S​KQ​ B​KQ

## Restock

#### P​1​ S​1​ B​1 ​C​ 1 

#### ...

#### P​LQ​ S​LQ​ B​LQ ​C​LQ

## Transfer

#### P​1​ Z​1​ S​1​ B​ 1 

#### ...

#### P​MQ​ Z​MQ​ S​MQ​ B​MQ


**Keluaran**
Sebuah angka yang merupakan banyak kemungkinan Borman ★ untuk mendapatkan tepat ​ **T** ​ **i** ​ buah
_choco chips_ ​ dari donat DUAARRR pada hari ke-​ **i** ​, di-modulo 1.000.000.007 (10​^9 ​ + 7). Sebagai contoh,
jika banyaknya kemungkinan berjumlah 1.000.000.011 (10​^9 ​ + 11), maka keluarannya adalah 4.
**Batasan**
1 ≤ N ≤ 20 N adalah banyak toko.
1 ≤ D ≤ 20 D adalah banyak jenis donat pada setiap toko.
1 ≤ J ≤ 100 J adalah banyak donat untuk suatu jenis.
1 ≤ C ≤ 100 C adalah banyak ​ _choco chips_ ​ yang ada pada jenis donat.
1 ≤ Q ≤ 10 Q adalah banyak hari.
1 ≤ X ≤ N X adalah banyak toko yang buka.
0 ≤ T ≤ 100 T adalah target jumlah ​ _choco chips_ ​ yang ingin Borman kumpulkan.
1 ≤ K + L + M ≤ 100 K adalah banyak aktivitas donat meledak, L adalah banyak aktivitas ​ _restock_
donat dan M adalah banyak aktivitas transfer donat pada suatu hari.
1 ≤ B ≤ 100 B adalah jumlah donat yang meledak, di-​ _restock_ ​, atau ditransfer.
1 ≤ |S|, |P|, |Z| ≤
50
|P| dan |Z| adalah panjang nama toko yang terdiri dari ​ **huruf kapital**
semua. Sedangkan, |S| adalah panjang nama donat yang terdiri dari ​ **huruf
kecil** ​semua.
**Poin Penting**

1. Setiap nama toko dan setiap nama donat di tiap toko dijamin unik. Tapi bisa saja antar toko
    terdapat ​ **nama donat yang sama** ​ dengan ​ _choco chips_ ​ berbeda.
2. Jumlah donat setelah meledak, ​ _restock_ ​, ataupun transfer tidak akan ​ **tidak akan melebihi 100** ​.
3. Jumlah jenis donat suatu toko setelah ​ _restock_ ​, ataupun transfer ​ **tidak akan melebihi 20** ​.
4. Donat yang meledak atau ditransfer ​ **pasti ada** ​ dan ​ **tidak lebih banyak** ​ dari donat yang tersedia.
5. Jika jumlah suatu jenis donat pada suatu toko sudah habis, maka dia dapat menerima transfer
    donat yang sama dengan ​ **berapapun jumlah** ​ **_choco chips_** ​.
6. Jika suatu toko melakukan transfer suatu donat dengan nama yang sama ke toko lain dan jumlah
    _choco chips_ ​-nya berbeda, maka perintah ​ **transaksi tersebut akan gagal** ​.
7. Jika suatu toko melakukan ​ _restock_ dan jumlah ​ _choco chips_ ​-nya berbeda, maka ​ **_restock_** **akan**
    **gagal** ​.


**Masukan Soal #**
2 
ENAK 2
gula 4 3
coklat 3 2
KEREN 1
coklat 9 1
1 
2 
KEREN ENAK
Target 6
Duar 2
KEREN coklat 5
ENAK gula 4
Restock 0
Transfer 0
**Keluaran Soal #**
7 
**Penjelasan Soal #**
Pada soal di atas, terdapat dua toko donat yang bernama ​ **ENAK** ​ dan ​ **KEREN** ​.
Toko donat ​ **ENAK** ​ memiliki dua jenis donat, donat ​ **gula** ​ dan donat ​ **coklat** ​. Terdapat 4 stok donat ​ **gula**
di toko ​ **ENAK** ​ yang masing-masingnya bernilai 3 ​ _choco chips_ ​. Terdapat 3 stok donat ​ **coklat** ​ di toko
ENAK yang masing-masingnya bernilai 2 ​ _choco chips_ ​. Toko donat ​ **KEREN** ​ hanya memiliki satu jenis
donat, yaitu donat ​ **coklat** ​. Donat ​ **coklat** ​ di toko ​ **KEREN** ​ bernilai 1 ​ _choco chip_ ​dan terdapat 9 stok.
Borman akan mengunjungi toko donat pada pagi hari di hari pertama dan dia ingin mengumpulkan 6
_choco chips_ ​. Ada 2 toko donat yang buka di hari tersebut, yaitu toko ​ **KEREN** ​ dan ​ **ENAK** ​.
Sore hari di hari pertama, akan ada 5 donat ​ **coklat** ​meledak di toko ​ **KEREN** ​dan 4 donat ​ **gula** ​meledak
di toko ​ **ENAK** ​. Hari itu tidak ada ​ _restock_ ​ dan aktivitas transfer donat.
Borman dapat mengumpulkan 6 ​ _choco chips_ ​ dengan 7 kemungkinan berikut:

1. 6 donat ​ **coklat** ​ (@1) toko ​ **KEREN**
2. 4 donat ​ **coklat** ​ (@1) toko ​ **KEREN** ​ ​dan​ ​1 donat ​ **coklat** ​ (@2) toko ​ **ENAK**
3. 3 donat ​ **coklat** ​ (@1) toko ​ **KEREN** ​ ​dan​ ​1 donat ​ **gula** ​ (@3) toko ​ **ENAK**
4. 2 donat ​ **coklat** ​ (@1) toko ​ **KEREN** ​ ​dan​ ​2 donat ​ **coklat** ​ (@2) toko ​ **ENAK**
5. 3 donat ​ **coklat** ​ (@2) toko ​ **ENAK**
6. 2 donat ​ **gula** ​ (@3) toko ​ **ENAK**
7. 1 donat ​ **coklat** ​ (@1) toko ​ **KEREN** ​, 1 donat ​ **coklat** ​ (@2) toko ​ **ENAK** ​, dan​ ​1 donat ​ **gula** ​ (@3)
    toko ​ **ENAK**
Karena Borman hanya membeli di hari pertama saja, maka aktivitas donat yang meledak di sore hari
tersebut dapat diabaikan.


**Masukan Soal #**
3 
ENAK 2
gula 4 3
coklat 3 2
KEREN 1
coklat 9 1
JOMBLO 2
gula 5 90
anggur 6 4
4 
2 
KEREN ENAK
Target 6
Duar 2
KEREN coklat 5
ENAK gula 4
Restock 0
Transfer 0
3 
KEREN ENAK JOMBLO
Target 7
Duar 3
KEREN coklat 1
ENAK coklat 3
JOMBLO anggur 2
Restock 0
Transfer 0
2 
JOMBLO KEREN
Target 9
Duar 2
JOMBLO gula 1
JOMBLO anggur 2
Restock 0
Transfer 0
3 
ENAK JOMBLO KEREN
Target 87
Duar 0
Restock 0
Transfer 0
**Keluaran Soal #**
7 
4 
1 
0 


**Penjelasan Soal #**
Pada hari pertama sama seperti soal #1, terdapat 7 kemungkinan.
Setelah donat-donat meledak pada sore hari pertama, stok donat hari kedua berubah menjadi:
● Toko ​ **ENAK**

- Donat ​ **gula** ​ berjumlah 0
- Donat ​ **coklat** ​ berjumlah 3
● Toko ​ **KEREN**
- Donat ​ **coklat** ​ berjumlah 4
● Toko ​ **JOMBLO**
- Donat ​ **gula** ​ berjumlah 5
- Donat ​ **anggur** ​ berjumlah 6
Sehingga untuk mendapatkan 7 ​ _choco chips_ ​ di hari kedua, ada 4 kemungkinan, yaitu:
1. 3 donat ​ **coklat** ​ (@2) toko ​ **ENAK** ​ dan 1 donat ​ **coklat** ​ (@1) toko ​ **KEREN**
2. 2 donat ​ **coklat** ​ (@2) toko ​ **ENAK** ​ dan 3 donat ​ **coklat** ​ (@1) toko ​ **KEREN**
3. 1 donat ​ **coklat** ​ (@2) toko ​ **ENAK** ​, 1 donat ​ **coklat** ​ (@1) toko ​ **KEREN** ​, 1 donat ​ **anggur** ​ (@4) toko
**JOMBLO**
4. 3 donat ​ **coklat** ​ (@1) toko ​ **KEREN** ​, 1 donat ​ **anggur** ​ (@4) toko ​ **JOMBLO**
Stok donat pada pagi hari ketiga berubah menjadi:
● Toko ​ **ENAK**
- Donat ​ **gula** ​ berjumlah 0
- Donat ​ **coklat** ​ berjumlah 0
● Toko ​ **KEREN**
- Donat ​ **coklat** ​ berjumlah 3
● Toko ​ **JOMBLO**
- Donat ​ **gula** ​ berjumlah 5
- Donat ​ **anggur** ​ berjumlah 4
Sehingga untuk mendapatkan 9 ​ _choco chips_ ​, kemungkinannya hanya satu cara:
1. 2 donat ​ **anggur** ​ (@4) toko ​ **JOMBLO** ​ dan 1 donat ​ **coklat** ​ (@1) toko ​ **KEREN**
Stok donat pada pagi hari keempat berubah menjadi:
● Toko ​ **ENAK**
- Donat ​ **gula** ​ berjumlah 0
- Donat ​ **coklat** ​ berjumlah 0
● Toko ​ **KEREN**
- Donat ​ **coklat** ​ berjumlah 3
● Toko ​ **JOMBLO**
- Donat ​ **gula** ​ berjumlah 4
- Donat ​ **anggur** ​ berjumlah 2
Tidak mungkin untuk mendapatkan 87 ​ _choco chips_ ​ dari donat-donat yang tersedia di toko pada hari
keempat.


## 4 

#### 7 

#### 2 

#### 1 


#### 4 

#### 2 

#### 1 


**Keluaran Soal #**
0 
1 
0 
14 
41 
48 
1 
**Penjelasan Soal #**
Ada 4 toko donat di kota: ORION, OMEGA, TARUNG, QUANTA
Terdapat 10 variasi donat pada soal di atas:
● Kopyor bernilai 20 ​ _chips_
● Mint bernilai 10 ​ _chips_
● Durian bernilai 5 ​ _chips_
● Gula bernilai 3 ​ _chips_
● Coklat bernilai 2 ​ _chips_
● Blueberry bernilai 2 ​ _chips_
● Anggur bernilai 1 ​ _chip_
● Tiramisu bernilai 1 ​ _chip
●_ Vanilla​ ​bernilai 35 ​ _chips_ ​(pertama kali dijual di ORION)
_●_ Vanilla​ ​bernilai 30 ​ _chips_ ​(pertama kali dijual di QUANTA)
Stok donat di toko selama 7 hari dideskripsikan seperti tabel di bawah ini.
**ORION**

## Hari-1 Hari-2 Hari-3 Hari-4 Hari-5 Hari-6 Hari-

## gula - - - - - -

```
coklat 30 5 5 5 5 5 5
```
## tiramisu - - -

```
kopyor 5 5 5 1 1 1 1
```
## kopyor - 8 - - - blueberry 10 10 - - - - -

## vanilla -


## OMEGA

### TARUNG

- Masukan Soal #
   - ORION
   - kopyor
   - gula
   - coklat
   - tiramisu
   - vanilla
   - OMEGA
   - durian
   - coklat
   - TARUNG
   - blueberry
   - QUANTA
   - mint
   - blueberry
   - coklat
   - anggur
   - Target 0 
   - Duar
   - ORION coklat
   - ORION tiramisu
   - QUANTA blueberry
   - Restock
   - TARUNG kopyor
   - QUANTA vanilla
   - Transfer
   - Target TARUNG QUANTA
   - Duar
   - OMEGA durian
   - Restock
   - Transfer
   - TARUNG ORION blueberry
   - TARUNG OMEGA kopyor
   - Target TARUNG
   - Duar
   - ORION kopyor
   - OMEGA durian
   - OMEGA kopyor
   - QUANTA coklat
   - QUANTA anggur
   - Restock
   - TARUNG tiramisu
- QUANTA kopyor
- Transfer
- ORION TARUNG gula
- ORION QUANTA vanilla
- Target ORION OMEGA TARUNG QUANTA
- Duar
- Restock
- Transfer
- Target OMEGA TARUNG
- Duar
- QUANTA mint
- Restock
- OMEGA kopyor
- TARUNG kopyor
- TARUNG tiramisu
- TARUNG coklat
- QUANTA anggur
- Transfer
- TARUNG QUANTA tiramisu
- Target QUANTA
- Duar
- ORION gula
- OMEGA durian
- QUANTA kopyor
- QUANTA tiramisu
- Restock
- ORION coklat
- OMEGA durian
- TARUNG coklat
- QUANTA tiramisu
- Transfer
- ORION OMEGA gula
- OMEGA QUANTA kopyor
- Target 0 
- Duar
- Restock
- Transfer
- Hari-1 Hari-2 Hari-3 Hari-4 Hari-5 Hari-6 Hari-
- durian
- coklat
- kopyor - -
- gula - - - - - -
- Hari-1 Hari-2 Hari-3 Hari-4 Hari-5 Hari-6 Hari- TARUNG
- kopyor - 8 - - - blueberry 10 10 - - - - -
- tiramisu - - -
- coklat - - - - -
- gula - - -
- Hari-1 Hari-2 Hari-3 Hari-4 Hari-5 Hari-6 Hari- QUANTA
- mint
- blueberry
- anggur coklat 60 60 60 - - - -
- vanilla -
- kopyor - - -
- tiramisu - - - - -


**Penjelasan Aktivitas**
DUAARRR
Contoh:
Duar 3
ORION coklat 25
Terjadi ledakan donat coklat sejumlah 25 di toko ORION pada sore hari ke-1.
Pada (pagi) hari ke-1, terdapat 30 stok donat coklat di ORION.
Pada (pagi) hari ke-2, tersisa 5 donat coklat.

### Restock

### Restock

Contoh I (berhasil):
..
Restock 5

## OMEGA QUANTA kopyor

#### ..

Toko OMEGA menambah stok donat kopyor 9 buah (bernilai 20 ​ _chips_ ​) pada sore hari ke-5.
Pada (pagi) hari ke-5, hanya terdapat 1 stok donat kopyor di OMEGA.
Pada (pagi) hari ke-6, terdapat 10 donat kopyor.
Contoh II (berhasil):
..
Restock 2
TARUNG tiramisu 10 1
..
Toko TARUNG menambah stok donat tiramisu 10 buah (bernilai 1 ​ _chips_ ​) pada sore hari ke-3.
Pada (pagi) hari ke-3, TARUNG tidak memiliki stok tiramisu.
Pada (pagi) hari ke-4, tercatat 10 buah donat tiramisu tersedia di TARUNG.
Contoh III (gagal):
..
Restock 5
ORION coklat 10 3
..


Di (sore) hari ke-6, toko ORION ingin menambah stok donat coklat 10 buah. Namun, ORION salah
beli ke distributor (stok baru bernilai 3 ​ _chips_ ​, sementara donat coklat yang biasanya dijual di ORION
bernilai 2 ​ _chips_ ​). Jadi, penambahan stok ORION gagal.
Pada (pagi) hari ke-6, stok donat coklat (2 ​ _chips_ ​) ORION berjumlah 5. Pada (pagi) hari ke-7, stoknya
tidak berubah, tetap 5.
Contoh IV (berhasil):
Restock 2
QUANTA vanilla 5 30
QUANTA ingin menambah variasi donat baru pada (sore) hari ke-1, yaitu vanilla (senilai 30 ​ _chips_ ​).
Walaupun, donat vanilla yang sudah pernah dijual di kota itu biasanya senilai 35 ​ _chips_ ​ (donat vanilla
lebih dulu dijual di toko ORION), QUANTA boleh memperkenalkan donat vanilla dengan nilai berbeda
(lintas toko).

## Transfer

## Transfer

### Transfer

Contoh I (berhasil):
..
Transfer 2
TARUNG ORION blueberry 10
..
Pada (sore) hari ke-2, toko TARUNG menjual stok 10 donat blueberry ke toko ORION.
Sehingga, pada (pagi) hari ke-3, TARUNG tidak lagi memiliki stok blueberry (sehari sebelumnya
TARUNG punya 10 blueberry), sementara ORION sekarang punya 10 blueberry.
Contoh II (gagal):
..
Transfer 2
..
ORION QUANTA vanilla 3
..
Pada hari ke-3, ORION berniat menjual stok 3 donat vanilla ke QUANTA. Namun, transaksi transfer
tersebut gagal karena donat vanilla yang biasanya dijual di ORION berbeda dengan donat vanilla
QUANTA (jumlah ​ _choco chips_ ​ berbeda: 30 dan 35).


**Penjelasan Jawaban yang Dicetak sebagai Keluaran Program**
Hari Pertama
Jawaban: 0
Tidak ada toko yang buka. Tidak ada cara yang valid untuk Borman ★ mengumpulkan ​ _choco chips_ ​.
Hari Kedua
Jawaban: 1
Karena Borman ★ tidak memiliki target, terdapat satu cara yakni tidak mengumpulkan donat
manapun.
Hari Ketiga
Jawaban: 0
Borman ★ ingin mendapatkan 50 ​ _choco chips_ ​. Ada satu toko yang buka hari tersebut, yaitu
TARUNG. Namun, TARUNG sedang tidak memiliki stok donat apapun hari itu.
Hari Keempat
Jawaban: 14
Seluruh toko buka pada hari itu dan Borman ★ ingin mendapatkan total 3 ​ _choco chips_ ​.
Terdapat 11 kemungkinan:

1. 3 donat ​ **anggur** ​ (@1) toko ​ **QUANTA**
2. 3 donat ​ **tiramisu** ​ (@1) toko ​ **TARUNG**
3. 2 donat ​ **anggur** ​ (@1) toko ​ **QUANTA** ​ dan 1 donat ​ **tiramisu** ​ (@1) toko ​ **TARUNG**
4. 1 donat ​ **anggur** ​ (@1) toko ​ **QUANTA** ​ dan 2 donat ​ **tiramisu** ​ (@1) toko ​ **TARUNG**
5. 1 donat ​ **coklat** ​ (@2) toko ​ **ORION** ​ dan 1 donat ​ **anggur** ​ (@1) toko ​ **QUANTA**
6. 1 donat ​ **coklat** ​ (@2) toko ​ **OMEGA** ​ dan 1 donat ​ **anggur** ​ (@1) toko ​ **QUANTA**
7. 1 donat ​ **blueberry** ​ (@2) toko ​ **QUANTA** ​ dan 1 donat ​ **anggur** ​ (@1) toko ​ **QUANTA**
8. 1 donat ​ **blueberry** ​ (@2) toko ​ **ORION** ​ dan 1 donat ​ **anggur** ​ (@1) toko ​ **QUANTA**
9. 1 donat ​ **coklat** ​ (@2) toko ​ **ORION** ​ dan 1 donat ​ **tiramisu** ​ (@1) toko ​ **TARUNG**
10.1 donat ​ **coklat** ​ (@2) toko ​ **OMEGA** ​ dan 1 donat ​ **tiramisu** ​ (@1) toko ​ **TARUNG**
11.1 donat ​ **blueberry** ​ (@2) toko ​ **QUANTA** ​ dan 1 donat ​ **tiramisu** ​ (@1) toko ​ **TARUNG**
12.1 donat ​ **blueberry** ​ (@2) toko ​ **ORION** ​ dan 1 donat ​ **tiramisu** ​ (@1) toko ​ **TARUNG**
13.1 donat ​ **gula** ​ (@3) toko ​ **ORION**
14.1 donat ​ **gula** ​ (@3) toko ​ **TARUNG**
Hari Kelima
Jawaban: 41
Toko yang buka hanya 2: ​ **OMEGA** ​ dan ​ **TARUNG** ​. Borman ★ ingin mendapatkan total 20 ​ _choco chips_ ​.
Jenis Donat (Chips) Total Stok Jenis Donat (Chips) Total Stok
kopyor (20) 1 gula (3) 5
durian (5) 1 tiramisu (1) 10
coklat (2) 5


Terdapat 41 kemungkinan:

1. 1 donat ​ **kopyor** ​ (@20) toko ​ **OMEGA**
2. 1 donat ​ **durian** ​ (@5) toko ​ **OMEGA** ​, 2 donat ​ **coklat** ​ (@5) toko ​ **OMEGA** ​, dan 5 ​ **donat** ​ tiramisu
    (@1) toko ​ **TARUNG**
3. 1 donat ​ **durian** ​ (@5) toko ​ **OMEGA** ​, 1 donat ​ **coklat** ​ (@5) toko ​ **OMEGA** ​, dan 10 ​ **donat** ​ tiramisu
    (@1) toko ​ **TARUNG**
4. 10 donat ​ **tiramisu** ​ (@1) toko ​ **TARUNG** ​, 2 donat gula (@3) toko ​ **TARUNG** ​, 2 donat ​ **coklat** ​ (@2)
    toko ​ **OMEGA**
5. dan seterusnya...
Pemilihan 4 donat ​ **durian** (@5) toko ​ **OMEGA** ​ **BUKAN** merupakan salah satu kemungkinan yang valid
untuk Borman ★ mengumpulkan total 20 ​ _choco chips_ ​. Karena di hari tersebut, toko OMEGA hanya
memiliki stok 1 donat durian saja.
Hari Keenam
Jawaban: 48
Hari Ketujuh
Jawaban: 1
Borman ★ tidak memiliki target dan tidak ada toko yang buka. Terdapat satu cara yakni tidak
mengumpulkan donat manapun.


**Bonus Patrick**
Source: ​https://spongebob.fandom.com/wiki/Devil_donut


