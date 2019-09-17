import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.*;

/**
 * Anda hanya ditugaskan untuk menyelesaikan implementasi processQuery.
 * Anda dapat menggunakan variable n dan q dari global variable yang
 * nilainya telah diisi pada method readInput. Anda bebas menambahkan maupun
 * merubah fungsi, variable, dll dalam template ini
 */

// Create the pengunjung
class Pengunjung{
    boolean punyaTiket = false;
    int value = 0;
    public Pengunjung(int nilai){
        value = nilai;
    }

    void kasihTiket(){
        punyaTiket = true;
    }
    int getValue(){
        return this.value;
    }
}

public class WS2Selasa {
    private static int n, q;
    // Buat antrian
    public static Queue<Pengunjung> antrian = new LinkedList<Pengunjung>();
    // Buat ArrayList
    public static ArrayList<Pengunjung> ruangTunggu = new ArrayList<Pengunjung>();
    
    private static InputReader in;
    private static PrintWriter out;
    public static void main(String[] args) throws IOException {
        InputStream inputStream = System.in;
        in = new InputReader(inputStream);
        OutputStream outputStream = System.out;
        out = new PrintWriter(outputStream);
        readInput();
        out.close();
    }

    // Borman 1st command
    private static void firstCommand(){
        // Check if the queue is empty or not
        if(WS2Selasa.antrian.size() != 0){
                    // Akses paling depan
        Pengunjung pengunjung = WS2Selasa.antrian.peek();
        // Kalo dia punya tiket
        if(pengunjung.punyaTiket){
            // Remove dari antrian
            System.out.println(WS2Selasa.antrian.poll().getValue());
        }
        // Kalo dia gapunya tiket
        else{
            // Kasih tiket
            pengunjung.kasihTiket();
            // Masukin ke ruang tunggu
            WS2Selasa.ruangTunggu.add(pengunjung);
            // Polls
            WS2Selasa.antrian.poll();
        }
        }
    }
    // Broman 2nd command
    private static void secondCommand(){
        if(WS2Selasa.antrian.size() != 0){
                    Pengunjung pengunjung = WS2Selasa.antrian.peek();
        // Kalo dia punya tiket
        if(pengunjung.punyaTiket){
            // Remove
            System.out.println(WS2Selasa.antrian.poll().getValue());
        }
        // Kalo dia gapunya tiket
        else{
            // Kasih tiket
            pengunjung.kasihTiket();
            // Masukin ke antrian belakang
            WS2Selasa.antrian.add(WS2Selasa.antrian.poll());
        }
        }
    }
    // Broman third command
    private static void thirdCommand(){
        // If the ruang tungggu is not empty
        if(WS2Selasa.ruangTunggu.size() != 0){
        // Reverse the array
        Collections.reverse(WS2Selasa.ruangTunggu);
        // Add the reversed array to the Queue
        for(int i = 0; i<WS2Selasa.ruangTunggu.size();i++){
            WS2Selasa.antrian.add(WS2Selasa.ruangTunggu.get(i));
        }
        // Clear the ruang tunggu
        WS2Selasa.ruangTunggu.clear();
        }
    }

        
    private static void processQuery(int query) throws IOException{
        // TODO: selesaikan fungsi ini agar dapat mensimulasikan perintah
        // dari Borman, jika ada pelanggan yang mendapatkan sate dan
        // ingin diprint maka menggunakan "printOutput(...)"

        // Decide which command to call
        switch(query){
            case 1:
                firstCommand();
                break;
            case 2:
                secondCommand();
                break;
            case 3:
                thirdCommand();
                break;
        }
    }
    
    private static void printOutput(int answer) throws IOException {
        out.println(answer);
    }
    
    private static void readInput() throws IOException {
        n = in.nextInt();
        q = in.nextInt();

        // Isi antrian dengan orang
        for(int i = 1; i<n+1; i++){
            // Make a new Pengunjung
            Pengunjung pengunjung = new Pengunjung(i);
            // Add the pengunjung to the queue
            WS2Selasa.antrian.add(pengunjung);
        }

        for (int i = 0; i < q; i++) {
            int query = in.nextInt();
            processQuery(query);
        }
    }

    static class InputReader {
        // taken from https://codeforces.com/submissions/Petr
        public BufferedReader reader;
        public StringTokenizer tokenizer;
 
        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }
 
        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }
 
        public int nextInt() {
            return Integer.parseInt(next());
        }
 
    }
}
