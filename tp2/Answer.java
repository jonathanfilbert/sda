import java.util.*;
import java.io.*;

// Implementation of Node
class Node{
    // A node has next, prev, and a value
    private Node next;
    private Node prev;
    private Object value;

    // When initialized, a node has next and prev of null, and a value of an int
    public Node(Integer value){
        this.next = null;
        this.prev = null;
        this.value = value;
    }
    // Constructor for LinkedList (overloading)
    public Node(LinkedList value){
        this.next = null;
        this.prev = null;
        this.value = value;
    }
    // Constructor for initialization null value
    public Node(){
        this.next = null;
        this.prev = null;
        this.value = null;
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
        head = new Node();
        tail = new Node();
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
    }
    void moveBack(LinkedList tujuan){
        // Set the current head's prev to the tujuan's tail
        this.head.setPrev(tujuan.getTail());
        // Set the tujuan's tail's next to the current head's
        tujuan.getTail().setNext(this.head);
    }
}

// Untuk per rak donut
public class RakDonat{
    // Paling kiri
    private Node head;
    // Paling kanan
    private Node tail;
    // size of the rak
    private int size;

    // Constructor
    public RakDonat(){
        // Bikin pointer untuk head dan tail
        head = new Node();
        tail = new Node();
        size = 0;
    }
    void addBaris(LinkedList newBaris){
        // If the size is empty
        if(this.size == 0){
            // Set the head's next to newBaris' head's prev
            newBaris.getHead().setPrev(this.head);
            this.head.setNext(newBaris.getHead());
            // Set the newBaris' tail's next to tail's prev
            newBaris.getTail().setNext(this.tail);
            this.tail.setPrev(newBaris.getTail());
        }
        // If the size is not empty
        else{
            // Add ke paling belakang
            // head.prev baru nunjuk ke tail.next yang lama
            newBaris.getHead().setPrev(this.tail.getPrev());
            newBaris.getTail().setNext(this.tail);
            newBaris.getHead().getPrev().setNext(newBaris.getHead());
            newBaris.getTail().getNext().setPrev(newBaris.getTail());
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