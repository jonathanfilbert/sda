import java.util.*;
import java.io.*;

// Implementation of Node
class Node implements Comparable<Node> {
    // A node has next, prev, and a value
    private Node next;
    private Node prev;
    private Integer value;

    // When initialized, a node has next and prev of null, and a value of an int
    public Node(Integer value) {
        this.next = null;
        this.prev = null;
        this.value = value;
    }

    // Set next sets the next pointer of a node
    public void setNext(Node next) {
        this.next = next;
    }

    // Setprev sets the prev pointer of a null
    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public Node getPrev() {
        return this.prev;
    }

    public Node getNext() {
        return this.next;
    }

    // getValue gets the value of a node
    public int getValue() {
        return this.value;
    }

    // To string
    public String toString() {
        return Integer.toString(this.value);
    }

    // Compare to
    @Override
    public int compareTo(Node lain) {
        if (this.value > lain.getValue()) {
            return 1;
        } else if (this.value < lain.getValue()) {
            return -1;
        } else {
            return 0;
        }
    }
}

// Untuk barisan
class LinkedList implements Comparable<LinkedList> {
    // A linkedList has a head node, tail node, and an int size
    private Node head;
    private Node tail;
    private Node current;
    private int size;

    // Constructor
    public LinkedList() {
        head = new Node(null);
        tail = new Node(null);
        size = 0;
        current = head;
    }

    boolean isEmpty() {
        if (head.getNext() == null && tail.getPrev() == null) {
            return true;
        } else {
            return false;
        }
    }

    // Sets heada
    void setHead(Node newHead) {
        this.head = newHead;
    }

    // Sets tail
    void setTail(Node newTail) {
        this.tail = newTail;
    }

    // Gets the head
    Node getHead() {
        return this.head;
    }

    // Gets the tail
    Node getTail() {
        return this.tail;
    }

    // Get a node
    Node getNode(int index) {
        this.current = this.head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current;
    }

    // Gets the size of the linkedList
    int getSize() {
        return this.size;
    }

    // Sets the size
    void addSize(int add) {
        this.size += add;
    }

    // Add a node in front of a linkedList
    void inFront(Node newNode) {
        // If the barisan donat is empty,
        if (this.isEmpty()) {
            this.head.setNext(newNode);
            this.tail.setPrev(newNode);
        }
        // If not empty
        else {
            // Set previous of the node to the head
            newNode.setPrev(this.head);
            // Set the next of the node to the 1st element
            newNode.setNext(this.head.getNext());
            // Set the head's next to the new node
            this.head.setNext(newNode);
            // Set the 1st element's prev to the new node
            newNode.getNext().setPrev(newNode);
        }
        // Increment the size
        this.size += 1;
    }

    void outFront() {
        // if empty
        if (this.size > 0) {
            // Set the 2nd element's prev to head
            this.head.getNext().getNext().setPrev(this.head);
            // Set the head's next to the 2nd element
            this.head.setNext(this.head.getNext().getNext());
        }
        this.size -= 1;
    }

    void inBack(Node newNode) {
        if (this.size > 0) {
            // Set the newNode's next to the tail
            newNode.setNext(this.tail);
            // Set the newNode's prev to the last element
            newNode.setPrev(this.tail.getPrev());
            // Set the last element's next to the new node
            newNode.getPrev().setNext(newNode);
            // Set the tail's prev to the newNode
            this.tail.setPrev(newNode);
        }
        // Kalo dia kosong
        else {
            // Tembah next ke tail
            newNode.setNext(this.tail);
            // Tembak prev ke head
            newNode.setPrev(this.head);
            // Tail's prev tembak ke node baru
            this.tail.setPrev(newNode);
            // Head's next tembak ke node baru
            this.head.setNext(newNode);
        }
        this.size += 1;
    }

    void outBack() {
        if (this.size > 0) {
            // Set the tail's prev to the 2nd last element
            this.tail.setPrev(this.tail.getPrev().getPrev());
            // Set the 2nd last element's next to the tail
            this.tail.getPrev().setNext(this.tail);
        }
        this.size -= 1;
    }

    void moveFront(LinkedList tujuan) {
        // Set the dari last element tail's next to tujuan's 1st element
        this.getTail().getPrev().setNext(tujuan.getHead().getNext());
        // Set the 1st element from tujuan's prev to the dari last element
        tujuan.getHead().getNext().setPrev(this.getTail().getPrev());
        // jaga jaga
        this.getTail().setNext(null);
        this.getTail().setPrev(null);
        tujuan.getHead().setNext(null);
        tujuan.getHead().setPrev(null);
        // Set the dari's head to be the new head
        tujuan.setHead(this.getHead());
        // Jadiin dari length jadi 0, adjust sizenya
        tujuan.addSize(this.size);
        this.size = 0;
    }

    void moveBack(LinkedList tujuan) {
        // Set the dari's first element's prev to the tujuan's last element
        this.head.getNext().setPrev(tujuan.getTail().getPrev());
        // Set the tujuan's last element's next to the dari's first element
        tujuan.getTail().getPrev().setNext(this.head.getNext());
        // jaga jaga
        this.getHead().setPrev(null);
        this.getHead().setNext(null);
        tujuan.getTail().setNext(null);
        tujuan.getTail().setPrev(null);
        // Set the dari's tail to be the new tail
        tujuan.setTail(this.getTail());
        // Adjust the size
        tujuan.addSize(this.size);
        this.size = 0;
    }

    // CompareTo Method››
    @Override
    public int compareTo(LinkedList barisLain) {
        // Cari length yang lebih kecil, forloop sesuai terkecil
        int length = 0;
        int answer = 2;
        if (this.size < barisLain.getSize()) {
            length = this.size;
        } else {
            length = barisLain.getSize();
        }
        Node currentOne = this.getHead().getNext();
        Node currentTwo = barisLain.getHead().getNext();
        // Forloop sesuai terkecil
        for (int i = 0; i < length; i++) {
            // Kalo elemen sekarang ini lebih besar, return 1
            if (currentOne.getValue() > currentTwo.getValue()) {
                answer = 1;
                break;
            } else if (currentOne.getValue() < currentTwo.getValue()) {
                answer = -1;
                break;
            }
            // Kalo sama elemennya
            else {
                currentOne = currentOne.getNext();
                currentTwo = currentTwo.getNext();
            }
        }
        // Kalo belom dapet hasil, cek length
        if(answer == 2){
            if(this.size < barisLain.getSize()){
                answer=-1;
            }
            else if(this.size > barisLain.getSize()){
                answer=1;
            }
            else{
                answer=0;
            }
        }
        return answer;
    }

    // toString method
    public String toString() {
        String ans = "";
        if (this.size == 0) {
            return "Kosong";
        } else {
            Node current = this.head.getNext();
            for (int i = 0; i < this.size; i++) {
                if(i != this.size-1){
                    ans = ans + Integer.toString(current.getValue()) + " ";
                }
                else{
                    ans = ans+ Integer.toString(current.getValue());
                }
                current = current.getNext();
            }
            return ans;
        }
    }
}

// Untuk per rak donut
class RakDonat {
    private ArrayList<LinkedList> rak;

    // Constructor
    public RakDonat() {
        // Bikin arrayList
        rak = new ArrayList<LinkedList>();
    }

    // Method bikin 1 barisan baru yang isinya 1 donat chips N
    void newBaris(int chips) {
        // Bikin baris baru
        LinkedList newBaris = new LinkedList();
        // Bikin donat baru
        Node newDonut = new Node(chips);
        // Masukin donat baru ke belakang barisan baru
        newBaris.inBack(newDonut);
        // Masukin barisan baru ke rak
        this.rak.add(newBaris);
    }

    // Gets the baris donut fr9om the index baris
    LinkedList getBaris(int baris) {
        return this.rak.get(baris);
    }

    // Adds a new baris to te rak
    void addBaris(LinkedList newBaris) {
        this.rak.add(newBaris);
    }

    // Make a method scan, which will scan the rak and delete the ones with size 0
    void scanAndDelete() {
        for (int i = 0; i < this.rak.size(); i++) {
            // Iterate the whole array
            if (rak.get(i).getSize() == 0) {
                // If the size of the LL is 0, delete
                this.rak.remove(i);
            }
        }
    }

    // Helper
    int getNewGap(int currentGap) {
        currentGap = (currentGap * 10) / 13;
        if (currentGap < 1) {
            return 1;
        } else {
            return currentGap;
        }
    }

    // Sorting algo
    void sort() {
        // TODO
        // Implement comb sort
        int size = this.rak.size();
        int gap = size;
        boolean swapped = true;
        while (gap != 1 || swapped) {
            gap = getNewGap(gap);
            swapped = false;
            // Comparations
            for (int i = 0; i < size - gap; i++) {
                if (this.rak.get(i).compareTo(this.rak.get(gap + i)) == 1) {
                    // Swapping
                    LinkedList temp = this.rak.get(i);
                    this.rak.set(i, this.rak.get(gap + i));
                    this.rak.set(gap + i, temp);
                    swapped = true;
                }
            }
        }
    }

    // getSize
    int getSize() {
        return rak.size();
    }

    // toString
    public String toString() {
        String answer = "";
        for (int i = 0; i < rak.size(); i++) {
            answer += rak.get(i).toString() + "\n";
        }
        return answer;
    }
}

public class Answer {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // Bikin raknyaa
        RakDonat rakDonat = new RakDonat();
        // Baca jumlah baris donat
        int jumlahBarisan = Integer.parseInt(br.readLine());
        // Forloop berapa barisan dalam rak
        for (int i = 0; i < jumlahBarisan; i++) {
            // Format: 3 10 7 14
            String[] inputDetails = br.readLine().split(" ");
            LinkedList barisanBaru = new LinkedList();
            // Forloop sesuai jumlah donut di barisan
            for (int j = 1; j < Integer.parseInt(inputDetails[0]) + 1; j++) {
                // Bikin node donut baru dengan value sesuai input
                Node newDonut = new Node(Integer.parseInt(inputDetails[j]));
                // Add donut nya ke belakang barisan
                barisanBaru.inBack(newDonut);
            }
            // Setelah selesai isi semua ke barisan, add barisan ke rak
            rakDonat.addBaris(barisanBaru);
        }
        // PRINT SEBELUM
        // bw.write(rakDonat.toString());
        // bw.write("----------------------\n");
        // Forloop jumlah perintah
        int jumlahPerintah = Integer.parseInt(br.readLine());
        for (int i = 0; i < jumlahPerintah; i++) {
            // Make a new donut for the operation
            Node newDonut;
            // Baca perintah
            String[] perintah = br.readLine().split(" ");
            switch (perintah[0]) {
            case "IN_FRONT":
                newDonut = new Node(Integer.parseInt(perintah[1]));
                rakDonat.getBaris(Integer.parseInt(perintah[2]) - 1).inFront(newDonut);
                // IN_FRONT 0 2
                break;
            case "OUT_FRONT":
                rakDonat.getBaris(Integer.parseInt(perintah[1]) - 1).outFront();
                // OUT_FRONT 1
                break;
            case "IN_BACK":
                newDonut = new Node(Integer.parseInt(perintah[1]));
                rakDonat.getBaris(Integer.parseInt(perintah[2]) - 1).inBack(newDonut);
                // IN_BACK 11 3
                break;
            case "OUT_BACK":
                rakDonat.getBaris(Integer.parseInt(perintah[1]) - 1).outBack();
                // OUT_BACK 2
                break;
            case "MOVE_FRONT":
                rakDonat.getBaris(Integer.parseInt(perintah[1]) - 1)
                        .moveFront(rakDonat.getBaris(Integer.parseInt(perintah[2]) - 1));
                // MOVE_FRONT 4 3
                break;
            case "MOVE_BACK":
                rakDonat.getBaris(Integer.parseInt(perintah[1]) - 1)
                        .moveBack(rakDonat.getBaris(Integer.parseInt(perintah[2]) - 1));
                // MOVE_BACK 4 3
                break;
            case "NEW":
                rakDonat.newBaris(Integer.parseInt(perintah[1]));
                // NEW 8
                break;
            }
            // Scan and delete yang kosong kosong
            rakDonat.scanAndDelete();
            // Sort the rak
            rakDonat.sort();
            // Print the rak
            // bw.write("----------------------\n");
        }
        bw.write(rakDonat.toString());
        bw.flush();
    }
}