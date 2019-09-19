## CSGE602040 - Struktur Data dan Algoritma

## Semester Gasal - 2019/

## WS 2 - Selasa

## Deadline: Selasa, 17-Sep-2019, 18.00 WIB

## Sate Star

## Deskripsi

Borman ★ mencoba peruntungan sebagai reseller sate Pacil yang legendaris itu. Sebulan pun sudah
terlewati dan Borman ★ kewalahan untuk melayani semua pelanggannya, hal ini dikarenakan
pelanggannya sudah membludak. Padahal Borman hanya menjual 1 jenis menu tanpa ada tambahan
topping (sambal, bawang goreng, kecap, dll). Borman ★ hanya bekerja seorang diri, pada akhirnya ia
pun membuat suatu sistem antrian.
Semua pelanggan Borman ★ berbaris sedemikian rupa. Semua pelanggan Borman harus memiliki
tiket ​ **(mulanya mereka tidak memiliki tiket)** dan berada pada ​ **barisan terdepan saat dipanggil**
Borman. Karena Borman hanya bekerja seorang diri, maka hanya terdapat ​ **satu antrian saja** untuk
pelanggannya (baik yang sudah memiliki tiket maupun yang belum), dan Borman juga menyediakan
**ruang tunggu** yang akan menampung beberapa pelanggannya. Karena Borman sangat sibuk, bisa
saja antrian ​ **sedang kosong** ketika Borman memanggil seorang pelanggan. Tentu saja antrian dan
ruang tunggu t​ **idak akan berubah** kalau Borman melakukan hal ini. Terdapat 3 macam perintah yang
akan diberikan Borman ★ kepada pelanggan - pelanggannya.
Perintah tipe 1 : ​ **Pelanggan paling depan** dipanggil Borman. Jika dia tidak punya tiket, maka Borman
memberikannya tiket dan menyuruhnya ​ **antre di ruang tunggu** ​. Jika pelanggan tersebut sudah
memiliki tiket maka Borman memberikan sate dan pelanggan tersebut keluar dari antrian.
Perintah tipe 2 : ​ **Pelanggan paling depan** dipanggil Borman. Jika dia tidak punya tiket, maka Borman
memberikannya tiket dan menyuruhnya ​ **mengantre lagi ke belakang antrian** ​. Jika pelanggan
tersebut sudah memiliki tiket maka Borman memberikan sate dan pelanggan tersebut keluar dari
antrian.
Perintah tipe 3 : ​ **Semua pelanggan** yang ada di ruang tunggu diminta ​ **baris kembali ke antrean** ​, tapi
**urutannya terbalik** ​. Misal di ruang tunggu urutannya "1 2 3" (1 paling depan), saat masuk ke kembali
ke barisan awal menjadi "3 2 1” (1 paling belakang). Karena Borman sangat sibuk, bisa saja ruang
tunggu ​ **sedang kosong** ​ ketika Borman melakukan perintah ini.
Borman ★ akan memberikan Q buah perintah kepada para pelanggannya. Setelah memberikan
semua perintah, Borman ★ ingin mengetahui urutan pelanggan sesuai urutan mereka menerima
sate. Bantulah Borman ★ agar ia tidak bingung.

## Masukan

Masukan terdiri dari dua buah angka N dan Q, secara berturut-turut menyatakan jumlah pelanggan
Borman ★ (misal N = 5, maka antrian akan membentuk : 1 2 3 4 5 (1 paling depan) ) dan jumlah
perintah yang akan diberikan. Baris berikutnya berisi Q buah angka 1, 2, atau 3 menyatakan urutan
jenis perintah yang diberikan.

## UCI RDO | WS 2 SDA Gasal 2019/


## Keluaran

Keluaran terdiri dari beberapa baris, sesuai dengan urutan pelanggan Borman ★ yang telah
menerima sate.

## Batasan

### 1 ≤ N, Q ≤ 100.

Dijamin jika seluruh pelanggan sudah mendapatkan sate, maka tidak akan ada perintah lagi.

## Contoh Masukan 1

### 5 12

### 1 1 2 3 1 2 1 2 3 1 1 1

## Contoh Keluaran 1

### 3 

### 2 

### 1 

### 5 

### 4 

## Contoh Masukan 2

### 4 6

### 2 3 1 2 1 2

## Contoh Keluaran 2

### 1 

## Penjelasan Masukan dan Keluaran 1

Tanda “-” memisahkan antar antrian dengan ruang tunggu. Dan pelanggan yang sudah mendapat
tiket akan di-​ **Bold.** ​Masukan di atas kita mengetahui kalau ada 5 pelanggan yang antri dengan
urutan:
5 4 3 2 1 (depan)
>>> 1
5 4 3 2 - ​ **1**
>>> 1
5 4 3 - ​ **2 1**
>>> 2
**3** ​5 4 - ​ **2 1**
>>> 3
**1 2 3** ​5 4
>>> 1
**1 2 3** ​5 - ​ **4**
>>> 2

## UCI | WS 2 SDA Gasal 2019/


### 5 1 2 3 ​- ​ 4

### >>> 1

**5 1 2** ​- ​ **4** ​(print 3)
>>> 2
**5 1** ​-​ **4** ​(print 2)
>>> 3
**4 5 1**
>>> 1
**4 5** ​(print 1)
>>> 1
**4** ​(print 5)
>>> 1
(print 4)

## UCI | WS 2 SDA Gasal 2019/


