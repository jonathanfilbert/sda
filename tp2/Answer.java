import java.util.*;
import java.io.*;

// Implementation of Node
class Node{
    // A node has next, prev, and a value
    private Node next;
    private Node prev;
    private Integer value;

    // When initialized, a node has next and prev of null, and a value of an int
    public Node(Integer value){
        this.next = null;
        this.prev = null;
        this.value = value;
    }
    // Set next sets the next pointer of a node
    public void setNext(Node next){
        this.next = next;
    }
    // Setprev sets the prev pointer of a null
    public void setPrev(Node prev){
        this.prev = prev;
    }
    public Node getPrev(){
        return this.prev;
    }
    public Node getNext(){
        return this.next;
    }
    // getValue gets the value of a node
    public Object getValue(){
        return this.value;
    }
}

// Untuk barisan
class LinkedList{
    // A linkedList has a head node, tail node, and an int size
    private Node head;
    private Node tail;
    private Node current;
    private int size;
    // Constructor
    public LinkedList(){
        head = new Node(null);
        tail = new Node(null);
        size=0;
        current = head;
    }
    boolean isEmpty(){
        if(head.getNext() == null && tail.getPrev() == null){
            return true;
        }
        else{
            return false;
        }
    }
    // Sets heada
    void setHead(Node newHead){
        this.head = newHead;
    }
    // Sets tail
    void setTail(Node newTail){
        this.tail = newTail;
    }
    // Gets the head
    Node getHead(){
        return this.head;
    }
    // Gets the tail
    Node getTail(){
        return this.tail;
    }
    // Gets the size of the linkedList
    int getSize(){
        return this.size;
    }
    // Sets the size
    void addSize(int add){
        this.size+=add;
    }
    // Add a node in front of a linkedList
    void inFront(Node newNode){
        // If the barisan donat is empty,
        if(this.isEmpty()){
            this.head.setNext(newNode);
            this.tail.setPrev(newNode);
        }
        // If not empty
        else{
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
        this.size+=1;
    }
    void outFront(){
        // if empty
        if(this.size > 0){
        // Set the 2nd element's prev to head
        this.head.getNext().getNext().setPrev(this.head);
        // Set the head's next to the 2nd element
        this.head.setNext(this.head.getNext().getNext());
        }
        this.size-=1;
    }
    void inBack(Node newNode){
        if(this.size > 0){
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
        else{
            // Tembah next ke tail
            newNode.setNext(this.tail);
            // Tembak prev ke head
            newNode.setPrev(this.head);
            // Tail's prev tembak ke node baru
            this.tail.setPrev(newNode);
            // Head's next tembak ke node baru
            this.head.setNext(newNode);
        }
        this.size+=1;
    }
    void outBack(Node newNode){
        if(this.size>0){
        // Set the tail's prev to the 2nd last element
        this.tail.setPrev(this.tail.getPrev().getPrev());
        // Set the 2nd last element's next to the tail
        this.tail.getPrev().setNext(this.tail);
        }
        this.size-=1;
    }
    void moveFront(LinkedList tujuan){
        // Set the current tail's next to the tujuan's head
        this.tail.setNext(tujuan.getHead());
        // Set the tujuan's head's prev to the current tail
        tujuan.getHead().setPrev(this.tail);
        // Jadiin head asal, head baru, tail dah bener
        tujuan.setHead(this.head);
        // Tambahin size tujuan dengan size asal
        tujuan.addSize(this.size);
        // Jadiin yang sekarang nol
        this.size = 0;
    }
    void moveBack(LinkedList tujuan){
        // Set the current head's prev to the tujuan's tail
        this.head.setPrev(tujuan.getTail());
        // Set the tujuan's tail's next to the current head's
        tujuan.getTail().setNext(this.head);
        // Ganti tail tujuan, jadi tail awal, head dah bener
        tujuan.setTail(this.tail);
        // Adds the tujuan's size
        tujuan.addSize(this.size);
        // Jadiin skrg size nya 0
        this.size=0;
    }
}

// Untuk per rak donut
public class RakDonat{
    private ArrayList<LinkedList> rak;
    // Constructor
    public RakDonat(){
        // Bikin arrayList
        rak = new ArrayList<LinkedList>();
    }
    // Gets the baris donut fr9om the index baris
    LinkedList getBaris(int baris){
        return this.rak.get(baris);
    }
    // Adds a new baris to te rak
    void addBaris(LinkedList newBaris){
        this.rak.add(newBaris);
    }
    // Make a method scan, which will scan the rak and delete the ones with size 0
    void scanAndDelete(){
        for(int i=0; i<this.rak.size();i++){
            // Iterate the whole array
            if(rak.get(i).getSize() == 0){
                // If the size of the LL is 0, delete
                this.rak.remove(i);
            }
        }
    }
}

public class Answer{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write("Hello World");
        // Bikin raknyaa
        RakDonat rakDonat = new RakDonat();
        int jumlahBarisan = Integer.parseInt(br.readLine());
        // Forloop berapa barisan dalam rak
        for(int i = 0; i<jumlahBarisan;i++){
            // Format: 3 10 7 14
            String[] inputDetails = br.readLine().split(" ");
            LinkedList barisanBaru = new LinkedList();
            for(int j = 1; j< Integer.parseInt(inputDetails[0]);j++){
                // Bikin node donut baru dengan value sesuai input
                Node newDonut = new Node(Integer.parseInt(inputDetails[j]));
                // Add donut nya ke belakang barisan
                barisanBaru.inBack(newDonut);
            }
            // Setelah selesai isi semua ke barisan, add barisan ke rak
            rakDonat.addBaris(barisanBaru);
        }
        // Forloop jumlah perintah
        int jumlahPerintah = Integer.parseInt(br.readLine());
        for(int i = 0; i<jumlahPerintah;i++){

        }
        bw.flush();
    }
}