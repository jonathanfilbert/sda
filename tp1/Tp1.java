import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

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

    public String toString(){
        return(String.format("{Nama: %s, Jumlah Stock: %d, Chips: %d  }", this.namaDonut,this.jumlahStok, this.nilaiChocoChips));
    }

    int getStock(){
        return this.jumlahStok;
    }

    int getChocoChips(){
        return this.nilaiChocoChips;
    }

    void recieveStock(int stock){
        this.jumlahStok += stock;
    }

    void transferStock(int stock){
        if(this.jumlahStok > 0){
            this.jumlahStok -= stock;
        }
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

    public String toString(){
        return this.stockDonut.toString();
    }

    String getNama(){
        return this.nama;
    }

    boolean getStatus(){
        return this.sedangBuka;
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
                this.stockDonut.get(jenisDonut).recieveStock(chipsRestock);
            }
        }
    }

    // Meledak
    void duarDonut(String jenisDonut, int jumlahMeledak){
        // Kurangin stocknya
            // kurangin stock nya
            this.stockDonut.get(jenisDonut).transferStock(jumlahMeledak);
        }

    // Transfer Donut
    void transferDonut(DonutStore tokoTujuan, String jenisDonut, int jumlahDonut){
        // If the name of the donut is NOT in the destination, transfer the donut
        if(tokoTujuan.stockDonut.get(jenisDonut) == null){
            // Transfer the donut
            tokoTujuan.addDonut(jenisDonut, new Donut(jenisDonut, jumlahDonut, this.stockDonut.get(jenisDonut).getChocoChips()));
            // Kurangin stock awalnya
            this.stockDonut.get(jenisDonut).transferStock(jumlahDonut);
        }
        // Kalo namanya ada
        else{
            // Kalo stock nya nol
            if(this.stockDonut.get(jenisDonut).getStock() == 0){
                tokoTujuan.stockDonut.get(jenisDonut).recieveStock(jumlahDonut);
                this.stockDonut.get(jenisDonut).transferStock(jumlahDonut);
            }
            // Kalo stock nya ga nol, check chips
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
}

public class Tp1 {

    // Bikin bank nya sesuai max kemungkinan
    static long[][] bank = new long[600][600];
    static boolean[][] checkBank = new boolean[600][600];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ArrayList<Long> jawaban = new ArrayList<Long>();
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
            if(jumlahTokoBuka > 0){
                String[] namaTokoBuka = br.readLine().split(" ");
                // Iterate setiap tokobuka
                for(int j=0;j<namaTokoBuka.length;j++){
                    // Set stores to open
                    donutStores.get(namaTokoBuka[j]).bukaTutupToko(true);
                }
            }
            // Baca Ti dari Target Ti
            int targetChocoChips = Integer.parseInt(br.readLine().split(" ")[1]);

            // Bikin array semua quantity dan chips donut
            ArrayList<Integer> quantityArray = new ArrayList<Integer>();
            ArrayList<Integer> chipArray = new ArrayList<Integer>();

            // Iterate semua toko
            for(Map.Entry<String, DonutStore> toko : donutStores.entrySet()){
                // Kalo tokonya buka
                if(toko.getValue().getStatus() == true){
                    // Iterate semua donat di toko itu
                    for(Map.Entry<String, Donut> donat : toko.getValue().stockDonut.entrySet()){
                        // Add ke quantity array
                        quantityArray.add(donat.getValue().getStock());
                        // Add semua chips ke chipArray
                        chipArray.add(donat.getValue().getChocoChips());
                    }
                }
            }

            // Itung
            long result = countPermutation(chipArray, targetChocoChips, quantityArray, 0) % 1000000007;
            jawaban.add(result);

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
        // Loop through the answer dan print
        for(int i = 0; i< jawaban.size();i++){
            bw.write(Long.toString(jawaban.get(i)));
            bw.write("\n");
            bw.flush();
        }
    }
    // Itung algonya
    public static long countPermutation(ArrayList<Integer> chipsList, int target, ArrayList<Integer> donutQuantity, int counter){
        // TODO
        // If (counter < donutQuantity.length):
            // base case if(target == 0): return 1
            // else if (target < 0): return 0
            // else
                // iterate donut quantity
                // recursive countPermutation(chipList,target-(chipList[counter]*i),donutQuantity,counter+1)
        long result = 0;
        // Kalo target < 0 return 0
        if(target < 0){
            return 0;
        }
        // Another target 0 return 1
        else if(target == 0){
            return 1;
        }
        // Recursive case
        // Kalo target masih > 0
        else{
            if(counter < donutQuantity.size()){
            // Iterate semua donat
            for(int i = 0; i<=donutQuantity.get(counter);i++){
                // Kalo counter udah diatas length, break
                if(counter == donutQuantity.size()){
                    break;
                }
                else if(target < 0){
                    return 1;
                }
                // Kalo belom, recursive case
                else{
                    // If biar gaada overflow
                    if(counter+1 == donutQuantity.size()){
                        break;
                    }
                    else{
                        // Menghindari overflow lagi untuk target-chips*i
                        if(target-chipsList.get(counter)*i < 0){
                            break;
                        }
                        else{
                    // Kalo dia belom ada
                    if(checkBank[counter+1][target-(chipsList.get(counter)*i)] == false){
                        // Jadiin value di checkBank true
                        checkBank[counter+1][target-(chipsList.get(counter)*i)] = true;
                        // Tambahin ke resultnya
                        long temp = countPermutation(chipsList, target - (chipsList.get(counter)*i), donutQuantity, counter+1);
                        // Store valuenya ke bank
                        bank[counter+1][target-(chipsList.get(counter)*i)] = temp;
                        // Accumulate
                        result+=temp;
                    }
                    // Kalo dia udah ada
                    else{
                        result+= bank[counter+1][target-(chipsList.get(counter)*i)];
                    }
                        }

                    }
            }
        }
            }
        }
        return result;
    }
}