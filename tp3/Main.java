
/**
 * Main
 */
import java.io.*;
import java.util.*;

class Node {
    private String name;

    private Long suaraDonat1;
    private Long suaraDonat2;
    private Node parent;

    // Constructor dengan namanya, set parent sbg null unless di setParent
    public Node(String nama) {
        this.name = nama;
        this.parent = null;
    }

    public String toString() {
        return String.format("Node %s", this.name);
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
    void anulirSuara(Integer suara1, Integer suara2) {
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
    private Node root;
    // Bikin map untuk semua nodenya
    private TreeMap<String, Node> allDaerah = new TreeMap<String, Node>();
    // Bikin map hubungna node dan parentnya #1 node pasti #1 parent
    private TreeMap<String, Node> parent = new TreeMap<String, Node>();
    // Bikin map yang key nya 0,1 valuenya ArrayList of String yang nandain menang
    // dimana aja
    private TreeMap<Integer, ArrayList<String>> kamusMenang = new TreeMap<Integer, ArrayList<String>>();

    public Tree() {
        this.kamusMenang.put(0, new ArrayList<String>());
        this.kamusMenang.put(1, new ArrayList<String>());
    }

    void insert(Node baru) {
        // Cek apakah node nya udah ada
        if (allDaerah.get(baru.getName()) == null) {
            this.allDaerah.put(baru.getName(), baru);
            // Set parent nya jadi null
            parent.put(baru.getName(), null);
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
        Node tujuan = this.allDaerah.get(daerahTujuan);
        // Tambahin value donat1 dan donat2 di tujuan secara rekursif
        tambahHelper(tujuan, donat1, donat2);
    }

    // Helper method nambahin secara rekursif
    void tambahHelper(Node tujuan, Long donat1, Long donat2) {
        if (tujuan.getParent() == null) {
            return;
        } else {
            tujuan.tambahSuara(donat1, donat2);
            tambahHelper(tujuan.getParent(), donat1, donat2);
        }
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
                }
                // Kalo yang kedua menang
            } else if (kodeMenang == 1) {
                // Kalo dia ada di lawannya
                if (this.kamusMenang.get(0).contains(daerah.getName())) {
                    // Remove dari lawannya
                    this.kamusMenang.get(0).remove(daerah.getName());
                    // Tambahin di 1
                    this.kamusMenang.get(1).add(daerah.getName());
                }
            }
        }
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // Input nama donat
        String[] namaDonat = br.readLine().split(" ");
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
                // Bikin node baru buat anaknya
                Node daerahAnak = new Node(detailDaerah[j]);
                // Masukan anak baru ke tree pusat
                treeDaerah.insert(daerahAnak);
                // Set parent anak baru jadi yang index 0
                treeDaerah.setParent(daerahAnak, detailDaerah[0]);
            }
        }
        bw.write(treeDaerah.printAllParentRelationships());

        bw.flush();
    }
}