import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

/**
 * Tp1
 */
// Class for the donut
class Donut{
    private String namaDonut;
    private int jumlahStok;
    private int nilaiChocoChips;
    // Constructor
    public Donut(String namaDonut, int jumlahStok, int nilaiChocoChips){
        this.namaDonut = namaDonut;
        this.jumlahStok = jumlahStok;
        this.nilaiChocoChips = nilaiChocoChips;
    }

    int getStock(){
        return this.jumlahStok;
    }

    int getChocoChips(){
        return this.nilaiChocoChips;
    }

    void recieveStock(int stock){
        this.jumlahStok +=stock;
    }

    void transferStock(int stock){
        this.jumlahStok -= stock;
    }
}

//  Class for the store
class DonutStore {
    String nama = "";
    boolean sedangBuka = false;
    // Array of Donuts
    HashMap<String,Donut> stockDonut;

    // Constructor
    public DonutStore(String nama){
        stockDonut = new HashMap<String,Donut>();
        this.nama = nama;
    }

    String getNama(){
        return this.nama;
    }

    void bukaTutupToko(boolean status){
        this.sedangBuka = status;
    }

    // Method for adding donuts
    void addDonut(String namaDonut, Donut donut){
        this.stockDonut.put(namaDonut, donut);
    }

    // Restock donat
    void restockDonut(String jenisDonut,int jumlahRestock, int chipsRestock){
        // TODO
        // Kalo dia namanya gaada, tambahin
        if(this.stockDonut.get(jenisDonut) == null){
            // Tambahin donut baru
            Donut baru = new Donut(jenisDonut, jumlahRestock, chipsRestock);
            this.stockDonut.put(jenisDonut, baru);
        }
        // Kalo nama dia ada
        else{
            // Kalo chips nya sama, berhasil
            if(this.stockDonut.get(jenisDonut).getChocoChips() == chipsRestock){
                // Tambahin stock nya
                this.stockDonut.get(jenisDonut).transferStock(chipsRestock);
            }
        }
    }

    // Meledak
    void duarDonut(String jenisDonut, int jumlahMeledak){
        // Kurangin stocknya
        if(this.stockDonut.get(jenisDonut).getStock() > 0){
            // kurangin stock nya by 1
            this.stockDonut.get(jenisDonut).transferStock(1);
        }
    }

    // Transfer Donut
    void transferDonut(DonutStore tokoTujuan, String jenisDonut, int jumlahDonut){
        // If the name of the donut is NOT in the destination, transfer the donut
        if(tokoTujuan.stockDonut.get(jenisDonut) == null){
            // Transfer the donut
            tokoTujuan.stockDonut.get(jenisDonut).recieveStock(jumlahDonut);
            // Kurangin stock awalnya
            this.stockDonut.get(jenisDonut).transferStock(jumlahDonut);
        }
        // Kalo namanya ada
        else{
            // Kalo chips nya sama
            if(tokoTujuan.stockDonut.get(jenisDonut).getChocoChips() == this.stockDonut.get(jenisDonut).getChocoChips()){
                // Transfer the donut
                tokoTujuan.stockDonut.get(jenisDonut).recieveStock(jumlahDonut);
                // Kurangin stock awalnya
                this.stockDonut.get(jenisDonut).transferStock(jumlahDonut);
            }
        }
    }
}

public class Tp1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // Make an map of Donut Stores
        Map<String,DonutStore> donutStores = new HashMap<String,DonutStore>();
        // Takes the amount of donut stores
        // Baca input N
        int banyakToko = Integer.parseInt(br.readLine());
        // For loop for every donut store and store it in an array
        for(int i = 0;i<banyakToko;i++){
            // Baca Input Pi dan Di
            String[] storeDetail = br.readLine().split(" ");
            DonutStore donutStore = new DonutStore(storeDetail[0]);
            for(int j = 0; j<Integer.parseInt(storeDetail[1]);j++){
                // Baca input Si, Ji, dan Ci
                String[] donutDetail = br.readLine().split(" ");
                Donut donut = new Donut(donutDetail[0], Integer.parseInt(donutDetail[1]), Integer.parseInt(donutDetail[2]));
                donutStore.addDonut(donutDetail[0],donut);
            }
            // Add the donut store to the array of stores
            donutStores.put(donutStore.getNama(), donutStore);
        }
        // Baca Q
        int jumlahHari = Integer.parseInt(br.readLine());
        // Aktivitas per hari
        for(int i = 0; i<jumlahHari;i++){
            // Baca Xi
            int jumlahTokoBuka = Integer.parseInt(br.readLine());
            String[] namaTokoBuka = br.readLine().split(" ");
            // Iterate setiap tokobuka
            for(int j=0;j<namaTokoBuka.length;j++){
                // Set stores to open
                donutStores.get(namaTokoBuka[j]).bukaTutupToko(true);
            }
            // Baca Ti dari Target Ti
            int targetChocoChips = Integer.parseInt(br.readLine().split(" ")[1]);

            // ITUNG ALGO NYAA
            countPermutation(chipsList, target);

            // Baca Duar
            int jumlahDuar = Integer.parseInt(br.readLine().split(" ")[1]);
            // Iterate Pi Si Bi
            for(int k = 0; k<jumlahDuar;k++){
                // Nama toko, jenis donut, jumlah donut
                String[] detailLedakan = br.readLine().split(" ");
                // Ledakin
                donutStores.get(detailLedakan[0]).duarDonut(detailLedakan[1], Integer.parseInt(detailLedakan[2]));
            }
            // Baca Restock
            int jumlahRestock = Integer.parseInt(br.readLine().split(" ")[1]);
            // Iterate Pi Si Bi Ci
            for(int l=0;l<jumlahRestock;l++){
                // Nama Toko, jenis donut, jumlah restock, jumlah chips restock
                String[] detailRestock = br.readLine().split(" ");
                // Transferin
                donutStores.get(detailRestock[0]).restockDonut(detailRestock[1], Integer.parseInt(detailRestock[2]), Integer.parseInt(detailRestock[3]));
            }
            // Baca Transfer
            int jumlahTransfer = Integer.parseInt(br.readLine().split(" ")[1]);
            // Iterate Pi Zi Si Bi
            for(int m=0;m<jumlahTransfer;m++){
                // Toko asal, toko tujuan, jenis donut, jumlah donut
                String[] detailTransfer = br.readLine().split(" ");
                // Transfer
                donutStores.get(detailTransfer[0]).transferDonut(donutStores.get(detailTransfer[1]), detailTransfer[2], Integer.parseInt(detailTransfer[3]));
            }

            // Akhir hari. Set semua toko jadi tutup
            for(Map.Entry<String,DonutStore> entry : donutStores.entrySet()){
                entry.getValue().bukaTutupToko(false);
            }
        }
        // Outputs the result
        bw.write(Arrays.asList());
    }

    // Itung algonya
    public static void countPermutation(int[] chipsList, int target, int[] donutQuantity){
        // TODO
        bw.write("hello");
        bw.flush();
    }
}