import java.util.*;
import java.io.*;

// Hasil diskusi dengan Christopher Samuel

class MaxHeap {
    List<Integer> heap;

    public MaxHeap(){
        // constructor
        heap = new ArrayList<Integer>();
    }

    public int size(){
        return heap.size();
    }
    
    public void add(int val) {
        // add ke akhir
        heap.add(val);
        // ambil size
        int curr = heap.size()-1;
        // Kalo dia masih lebih gede dari parent, swap keatas dengan parentnya
        while(heap.get(curr) > heap.get(parent(curr)) ){
            swap(curr,parent(curr));
            curr = parent(curr);
        }
        // TODO: lengkapi dengan cara menambah elemen ke maximum binary heap.
    }
    
    public int poll() {
        // hapus elemen teratas, tukar dengan elemen terakhir
        int result = heap.get(0);
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        
        // percolate down
        int now = 0;
        while (2*now + 1 < heap.size()) {
            int maxIdx = 2*now + 1;
            if (2*now + 2 < heap.size() && heap.get(2*now + 2) > heap.get(2*now + 1)) {
                maxIdx = 2*now + 2;
            }
            if (heap.get(now) < heap.get(maxIdx)) {
                // swap elemen
                int temp = heap.get(maxIdx);
                heap.set(maxIdx, heap.get(now));
                heap.set(now, temp);
                
                // lanjut ke bawah
                now = maxIdx;
            } else {
                break;
            }
        }
        
        return result;
    }
    
    // TODO: lengkapi dengan method lain jika dibutuhkan.
    
    // get index parrent nya
    public int parent(int current){
        return (current-1)/2;
    }

    // Swap the current pos with another pos
    public void swap(int apos, int bpos){
        int temp = heap.get(apos);
        heap.set(apos,heap.get(bpos));
        heap.set(bpos,temp);
    }
}

public class Main {
    static InputReader in = new InputReader(System.in);
    static PrintWriter out = new PrintWriter(System.out);
    
    public static void main(String[] args) {
        int N = in.nextInt();
        MaxHeap heap = new MaxHeap();
        // Biaya hidup
        List<Integer> B = new ArrayList<>();
        // Gaji
        List<Integer> G = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            B.add(in.nextInt());
        }
        for (int i = 0; i < N; i++) {
            G.add(in.nextInt());
        }
        
        // TODO: lengkapi dengan cara mencari banyak hari bekerja minimal

        
        // TODO: lengkapi dengan memasukkan elemen-elemen G dan cetak representasi array heap-nya.

        // soal 1
        // bikin heap
        MaxHeap heap1 = new MaxHeap();
        // masukin ke heap untuk soal 2
        for(int i = 0;i<G.size();i++){
            heap.add(G.get(i));
        }

        int gajiTotal = 0;
        int counter = 0;
        // iterate soal 1 dan poll
        for(int i = 0;i<B.size();i++){
            int gajiHariIni = G.get(i);
            heap1.add(gajiHariIni);
            // Kalo hari ini cukup livingcost nya, mager
            if(B.get(i) <= gajiTotal ){
                // mager
                gajiTotal -= B.get(i);
                continue;
            }
            // living cost > gaji total
            else if(B.get(i) > gajiTotal){
                // Kalo living cost lebih gede, kerja selama dia lebih kecil
                while(gajiTotal < B.get(i)){
                    // COunter kerja ditambahin
                    counter+=1;
                    // Poll untuk kerja hari ini
                    int kerjaHariIni = heap1.poll();
                    // Duit hasil kerja tambahin ke kerjaHari ini
                    gajiTotal += kerjaHariIni;
                    // Kalo udah cukup, break
                    if(gajiTotal >= B.get(i)){
                        break;
                    }
                }
            }
            // Kurangin gaji total dengan living cost hari ini
            gajiTotal -= B.get(i);
        }
        // print berapa kali kerja
        out.write(Integer.toString(counter));
        out.write("\n");

        // printing soal 2
        for(int i = 0; i<heap.size();i++){
            out.write(heap.heap.get(i) + " ");
        }
        out.write("\n");
        
        out.close();
    }
    
    static class InputReader {
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

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

    }
}
