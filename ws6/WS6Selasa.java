import java.util.*;
import java.io.*;

interface Hashable {
    boolean equals(Hashable other);
    int getHash(int mod);
}

class Book {
    public String title, author, year;
    public Book(String title, String author, String year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }
}

class MyMap<K extends Hashable, V> {
    int size;
    K[] keys;
    V[] table;

    public MyMap(int size) {
        this.size = size;
        this.table = (V[]) new Object[size];
        this.keys = (K[]) new Hashable[size];
    }

    int put(K key, V value) {
        int hashValue = key.getHash(size);
        // TODO: Lengkapi dengan implementasi meletakkan buku berjudul key pada rak
        // Linear Probing
        while(table[hashValue] != null){
            hashValue = (hashValue + 1)%size;
        }
        // udah ad yg kosong
        table[hashValue] = value;
        keys[hashValue] = key;
        return hashValue % size;
    }

    V get(K key) {
        int hashValue = key.getHash(size);
        // TODO: Lengkapi dengan implementasi mendapatkan informasi mengenai buku berjudul key
        while(table[hashValue] == null || !key.equals(keys[hashValue])){
            hashValue = (hashValue + 1)%size;
        }
        return table[hashValue];
    }

    int remove(K key) {
        int hashValue = key.getHash(size);
        // probing
        while(table[hashValue] == null || !key.equals(keys[hashValue])){
            hashValue = (hashValue + 1)%size;
        }
        // TODO: Lengkapi dengan implementasi mengeluarkan buku berjudul key dari rak
        table[hashValue] = null;
        keys[hashValue] = null;
        return hashValue;
    }
}

class MyString implements Hashable {
    private final int BASE = 29;
    public String s;

    public MyString(String s) {
        this.s = s;
    }

    public int getHash(int mod) {
        // TODO: Lengkapi dengan fungsi f(title, N)
        // mod = N
        // s = judul
        int length = s.length();
        int hasil = 0;
        int pangkat = 1;
        for(int i = 0; i<length;i++){
            int letterNumber = s.charAt(i) - 96;
            hasil = (hasil % mod + ((pangkat % mod) * (letterNumber % mod)) % mod) % mod;
            pangkat = (pangkat % mod * BASE % mod) % mod;
        }
        return hasil % mod;
    }

    public boolean equals(Hashable other) {
        return s.equals(((MyString) other).s);
    }
}

public class WS6Selasa {
    public static void main(String[] args) throws IOException {
        InputReader in = new InputReader(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] angkaa = br.readLine().split(" ");
        int N = Integer.parseInt(angkaa[0]);
        int Q = Integer.parseInt(angkaa[1]);

        MyMap<MyString, Book> myMap = new MyMap<>(N);

        for (int i = 0; i < Q; i++) {
            String command = br.readLine();
            String[] commandArray = command.split(" ");
            switch(commandArray[0]){
                case "RECEIVE":
                Book newBook = new Book(commandArray[1],commandArray[2],commandArray[3]);
                MyString judulBuku = new MyString(commandArray[1]);
               bw.write(Integer.toString(myMap.put(judulBuku,newBook)) + "\n");
                break;
                case "GET":
                MyString judul = new MyString(commandArray[1]);
                Book buku = myMap.get(judul);
               bw.write(buku.author + " " + buku.year + "\n");
                break;
                case "SEND":
                MyString title = new MyString(commandArray[1]);
               bw.write(myMap.remove(title) + "\n");
                break;
            }
            }
           // TODO: Lengkapi dengan memproses jenis command yang diterima
           bw.flush();
        }
    }
    
    class InputReader {
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
