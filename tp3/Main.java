
/**
 * Main
 */
import java.io.*;
import java.util.*;

class Node {
    private String name;

    private Long suaraDonat1 = 0L;
    private Long suaraDonat2 = 0L;
    private Node parent;

    // Constructor dengan namanya, set parent sbg null unless di setParent
    public Node(String nama) {
        this.name = nama;
        this.parent = null;
    }

    // get suara donat1
    Long getDonat1() {
        return this.suaraDonat1;
    }

    // get suara donat2
    Long getDonat2() {
        return this.suaraDonat2;
    }

    // get suara donat2
    public String toString() {
        return String.format("Node %s (1: %d, 2: %d)", this.name, this.suaraDonat1, this.suaraDonat2);
    }

    // get persen donat1
    Integer getPersen1() {
        if (this.suaraDonat1 + this.suaraDonat2 == 0) {
            return 50;
        } else {
            return (int) ((this.suaraDonat1 * 100) / (this.suaraDonat1 + this.suaraDonat2));
        }
    }

    // get persen donat2
    Integer getPersen2() {
        if (this.suaraDonat1 + this.suaraDonat2 == 0) {
            return 50;
        } else {
            return (int) ((this.suaraDonat2 * 100) / (this.suaraDonat1 + this.suaraDonat2));
        }
    }

    // get suara provinsi
    String suaraProvinsi() {
        return (String.format("%s %d %d", this.name, this.suaraDonat1, this.suaraDonat2));
    }

    // return abs selisihnya
    Long selisih() {
        return Math.abs(this.suaraDonat1 - this.suaraDonat2);
    }

    // Set parent nya
    void setParent(Node baru) {
        this.parent = baru;
    }

    // Get parentnya
    Node getParent() {
        return this.parent;
    }

    // Returns the name of the Node
    String getName() {
        return this.name;
    }

    // Nambahin suara ke 1 dan 2
    void tambahSuara(Long suara1, Long suara2) {
        this.suaraDonat1 += suara1;
        this.suaraDonat2 += suara2;
    }

    // Anulir suaranya
    void anulirSuara(Long suara1, Long suara2) {
        this.suaraDonat1 -= suara1;
        this.suaraDonat2 -= suara2;
    }

    // Return suara di node ini
    String cekSuara() {
        return (Long.toString(this.suaraDonat1) + " " + Long.toString(this.suaraDonat2));
    }

    // Method cek yang menang
    Integer cekMenang() {
        // Kalo donat1 lebih banyak, return 0
        if (this.suaraDonat1 > this.suaraDonat2) {
            return 0;
            // Kalo donat2 lebih banyak, return 1
        } else if (this.suaraDonat2 > this.suaraDonat1) {
            return 1;
            // Kalo seri return -1
        } else {
            return -1;
        }
    }
}

class Tree {
    // Bikin map untuk semua nodenya
    private TreeMap<String, Node> allDaerah = new TreeMap<String, Node>();
    // Bikin map hubungna node dan parentnya #1 node pasti #1 parent
    private TreeMap<String, Node> parent = new TreeMap<String, Node>();
    // Array menang 0-100
    private Integer[] arrayMenang1 = new Integer[101];
    private Integer[] arrayMenang2 = new Integer[101];
    // Bikin map yang key nya 0,1 valuenya ArrayList of String yang nandain menang
    // dimana aja
    private TreeMap<Integer, ArrayList<String>> kamusMenang = new TreeMap<Integer, ArrayList<String>>();

    public Tree() {
        // Bikin array kamus menang
        this.kamusMenang.put(0, new ArrayList<String>());
        this.kamusMenang.put(1, new ArrayList<String>());
        // initialize arrayMenang
        for (int i = 0; i < 101; i++) {
            arrayMenang1[i] = 0;
            arrayMenang2[i] = 0;
        }
    }

    // Method getNode
    Node getNode(String id) {
        return this.allDaerah.get(id);
    }

    void insert(Node baru) {
        // Cek apakah node nya udah ada
        if (allDaerah.get(baru.getName()) == null) {
            this.allDaerah.put(baru.getName(), baru);
            // Set parent nya jadi null
            parent.put(baru.getName(), null);
            // karena awalnya 0-0, jadiin 50%
            this.arrayMenang1[50] += 1;
            this.arrayMenang2[50] += 1;
        }
    }

    void setParent(Node anak, String parent) {
        // Panggil node ortu dari allDaerah
        Node ortu = allDaerah.get(parent);
        // Set parent anak jadi ortu
        anak.setParent(ortu);
        // Patch parent Treemap valuenya jadi node oertu
        this.parent.replace(anak.getName(), ortu);
    }

    String printAllDaerah() {
        String res = "";
        for (String daerah : this.allDaerah.keySet()) {
            res += String.format("%s: %s", daerah, this.allDaerah.get(daerah).toString());
            res += "\n";
        }
        return res;
    }

    String printAllParentRelationships() {
        String res = "";
        for (String child : this.parent.keySet()) {
            res += String.format("Child: %s | Parent: %s", child, this.parent.get(child));
            res += "\n";
        }
        return res;
    }

    // Tambahin suara ke node
    void tambah(String daerahTujuan, Long donat1, Long donat2) {
        if (this.allDaerah.get(daerahTujuan) != null) {
            Node tujuan = this.allDaerah.get(daerahTujuan);
            // Tambahin value donat1 dan donat2 di tujuan secara rekursif
            tambahHelper(tujuan, donat1, donat2);
        }
    }

    // Helper method nambahin secara rekursif
    void tambahHelper(Node tujuan, Long donat1, Long donat2) {
        if (tujuan == null) {
            return;
        } else {
            catetMenang(tujuan);
            // Alteration
            persenBefore(tujuan);
            tujuan.tambahSuara(donat1, donat2);
            persenAfter(tujuan);
            catetMenang(tujuan);
            // Recursive call
            tambahHelper(tujuan.getParent(), donat1, donat2);
        }
    }

    void anulir(String daerahTujuan, Long donat1, Long donat2) {
        if (this.allDaerah.get(daerahTujuan) != null) {
            Node tujuan = this.allDaerah.get(daerahTujuan);
            // Tambahin value donat1 dan donat2 di tujuan secara rekursif
            anulirHelper(tujuan, donat1, donat2);
        }
    }

    // Helper method nambahin secara rekursif
    void anulirHelper(Node tujuan, Long donat1, Long donat2) {
        if (tujuan == null) {
            return;
        } else {
            catetMenang(tujuan);
            persenBefore(tujuan);
            // ALteration
            tujuan.anulirSuara(donat1, donat2);
            persenAfter(tujuan);
            catetMenang(tujuan);
            // Recursive Call
            anulirHelper(tujuan.getParent(), donat1, donat2);
        }
    }

    String cekSuara(String daerah) {
        Node daerahNode = this.allDaerah.get(daerah);
        return daerahNode.cekSuara();
    }

    void persenBefore(Node daerah) {
        // get persen awal donat1 dan donat2
        int persenDonat1 = daerah.getPersen1();
        int persenDonat2 = daerah.getPersen2();
        // Kalo dia ga 0-0
        if (this.arrayMenang1[persenDonat1] > 0 && this.arrayMenang2[persenDonat2] > 0) {
            // kurangin dari array
            this.arrayMenang1[persenDonat1] -= 1;
            this.arrayMenang2[persenDonat2] -= 1;
        }
    }

    void persenAfter(Node daerah) {
        // cek persen donat1 dan donat2
        int persenDonat1 = daerah.getPersen1();
        int persenDonat2 = daerah.getPersen2();
        // tambahin di array
        this.arrayMenang1[persenDonat1] += 1;
        this.arrayMenang2[persenDonat2] += 1;
    }

    void catetMenang(Node daerah) {
        int kodeMenang = daerah.cekMenang();
        // Kalo dia ga seri
        if (kodeMenang != -1) {
            // Kalo yang pertama menang
            if (kodeMenang == 0) {
                // Cek apakah daerah ini udah ada di menang donat lain
                if (this.kamusMenang.get(1).contains(daerah.getName())) {
                    // Kalo ada remove dari array
                    this.kamusMenang.get(1).remove(daerah.getName());
                    // Tambahin ke 0
                    this.kamusMenang.get(0).add(daerah.getName());
                } else {
                    // Kalo di yg seharusnya udah ada, gausah ngapa ngapain
                    if (this.kamusMenang.get(0).contains(daerah.getName())) {
                        return;
                        // Kalo blm ada, tambahin
                    } else {
                        this.kamusMenang.get(0).add(daerah.getName());
                    }
                }
                // Kalo yang kedua menang
            } else if (kodeMenang == 1) {
                // Kalo dia ada di lawannya
                if (this.kamusMenang.get(0).contains(daerah.getName())) {
                    // Remove dari lawannya
                    this.kamusMenang.get(0).remove(daerah.getName());
                    // Tambahin di 1
                    this.kamusMenang.get(1).add(daerah.getName());
                } else {
                    // Kalo yg seharusnya udah ada, return
                    if (this.kamusMenang.get(1).contains(daerah.getName())) {
                        return;
                        // Kalo belom tambahin
                    } else {
                        this.kamusMenang.get(1).add(daerah.getName());
                    }
                }
            }
        }
        // Kalo dia seri
        else {
            this.kamusMenang.get(0).remove(daerah.getName());
            this.kamusMenang.get(1).remove(daerah.getName());
        }
    }

    // Cek menang
    Integer cekMenang(int nomor) {
        // Size array dari donat tsb
        // j
        return this.kamusMenang.get(nomor).size();
    }

    // Count semua daerah yang selisihnya sama kek selisih
    Integer jumlahSelisih(Long selisih) {
        int counter = 0;
        for (String nama : this.allDaerah.keySet()) {
            if (this.allDaerah.get(nama).selisih() >= selisih) {
                counter += 1;
            }
        }
        return counter;
    }

    Integer wilayahMinimal(int kodeDonat, int persenMinimal) {
        Integer[] arrayWilayah;
        // Pilih array donat sesuai dengan yang diminta
        if (kodeDonat == 0) {
            arrayWilayah = this.arrayMenang1;
        } else {
            arrayWilayah = this.arrayMenang2;
        }
        int sum = 0;
        // forloop dari persenDiminta sampe 100 tambah tambahin
        for (int i = persenMinimal; i < 101; i++) {
            sum += arrayWilayah[i];
        }
        return sum;
    }

}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // ArrayList provinsi
        ArrayList<String> provinsi = new ArrayList<String>();
        // Input nama donat
        String[] namaDonat = br.readLine().split(" ");
        // Bikin treeMap khusus buat donat
        TreeMap<String, Integer> mapDonat = new TreeMap<String, Integer>();
        mapDonat.put(namaDonat[0], 0);
        mapDonat.put(namaDonat[1], 1);
        // Jumlah selain kelurahan yang mengadakan pemilu
        int jumlahSelainKelurahan = Integer.parseInt(br.readLine());
        // Construct tree nya
        Tree treeDaerah = new Tree();
        // Forloop tiap daerah
        for (int i = 0; i < jumlahSelainKelurahan; i++) {
            // Terima input terus pecah
            String[] detailDaerah = br.readLine().split(" ");
            // Jakarta 2 Jaksel Jakbar
            // Bikin node untuk daerahnya
            Node daerah1 = new Node(detailDaerah[0]);
            treeDaerah.insert(daerah1);
            // Forloop anak daerahnya
            for (int j = 2; j < detailDaerah.length; j++) {
                // Kalo dia yang pertama
                if (i == 0) {
                    // Masukin ke provinsi
                    provinsi.add(detailDaerah[j]);
                }
                // Bikin node baru buat anaknya
                Node daerahAnak = new Node(detailDaerah[j]);
                // Masukan anak baru ke tree pusat
                treeDaerah.insert(daerahAnak);
                // Set parent anak baru jadi yang index 0
                treeDaerah.setParent(daerahAnak, detailDaerah[0]);
            }
        }
        int jumlahOperasi = Integer.parseInt(br.readLine());
        // Forloop operasi
        for (int i = 0; i < jumlahOperasi; i++) {
            String[] operasi = br.readLine().split(" ");
            switch (operasi[0]) {
            // Switch case untuk semua operasi
            case "TAMBAH":
                treeDaerah.tambah(operasi[1], Long.parseLong(operasi[2]), Long.parseLong(operasi[3]));
                break;
            case "ANULIR":
                treeDaerah.anulir(operasi[1], Long.parseLong(operasi[2]), Long.parseLong(operasi[3]));
                break;
            case "CEK_SUARA":
                bw.write(treeDaerah.cekSuara(operasi[1]) + "\n");
                break;
            case "WILAYAH_MENANG":
                bw.write(treeDaerah.cekMenang(mapDonat.get(operasi[1])) + "\n");
                break;
            case "CEK_SUARA_PROVINSI":
                for (int j = 0; j < provinsi.size(); j++) {
                    Node nodeProvinsi = treeDaerah.getNode(provinsi.get(j));
                    bw.write(nodeProvinsi.suaraProvinsi() + "\n");
                }
                break;
            case "WILAYAH_SELISIH":
                bw.write(treeDaerah.jumlahSelisih(Long.parseLong(operasi[1])) + "\n");
                break;
            case "WILAYAH_MINIMAL":
                bw.write(treeDaerah.wilayahMinimal(mapDonat.get(operasi[1]), Integer.parseInt(operasi[2])) + "\n");
            }
        }
        // End
        bw.flush();
    }
}