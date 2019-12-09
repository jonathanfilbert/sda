
/**
 * Main
 */
import java.io.*;
import java.util.*;

class Edge {
    private int from;
    private int to;
    private int weight;

    public Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    int getWeight() {
        return this.weight;
    }

    int getFrom() {
        return this.from;
    }

    int getTo() {
        return this.to;
    }
}

class Graph {
    private Integer numberOfVertices = 0;
    private HashMap<Integer, ArrayList<Edge>> edgeList;

    public Graph(int number) {
        this.numberOfVertices = number;
        this.edgeList = new HashMap<Integer, ArrayList<Edge>>();
    }

    void print() {
        String result = "";
        for (Integer key : edgeList.keySet()) {
            result += Integer.toString(key) + "\n";
            result += "- - - \n";
            for (int i = 0; i < edgeList.get(key).size(); i++) {
                result += String.format("%s - %s Weight: %s \n", Integer.toString(edgeList.get(key).get(i).getFrom()),
                        Integer.toString(edgeList.get(key).get(i).getTo()),
                        Integer.toString(edgeList.get(key).get(i).getWeight()));
            }
            result += "\n";
        }
        System.out.println(result);
    }

    void addKota(int from, int to, int weight) {
        // Kalo dia belom ada di edgeList, buat baru, trus masukin
        Edge edge = new Edge(from, to, weight);
        if (this.edgeList.get(from) == null) {
            ArrayList<Edge> tempEdgeList = new ArrayList<Edge>();
            tempEdgeList.add(edge);
            this.edgeList.put(from, tempEdgeList);
            // Kalo dia udah ada, append
        } else {
            this.edgeList.get(from).add(edge);
        }
        Edge edgeReverse = new Edge(to, from, weight);
        if (this.edgeList.get(to) == null) {
            ArrayList<Edge> tempEdgeList = new ArrayList<Edge>();
            tempEdgeList.add(edgeReverse);
            this.edgeList.put(to, tempEdgeList);
            // Kalo dia udah ada, append
        } else {
            this.edgeList.get(to).add(edgeReverse);
        }
    }

    int openStore(String[] openStores) {
        // Forloop semua toko yang buka
        boolean[] visitedCities = new boolean[this.numberOfVertices + 1];
        // Initialize menjadi false semua
        for (int j = 0; j < visitedCities.length; j++) {
            visitedCities[j] = false;
        }
        // BFS START DARI TIAP KOTA YANG OPEN
        for (int i = 0; i < openStores.length; i++) {
            int initial = Integer.parseInt(openStores[i]);
            Queue<Integer> edgeQueue = new LinkedList<Integer>();
            // Bikin node pertama jadi true
            visitedCities[initial] = true;
            // Add ke queue
            edgeQueue.add(initial);
            // Mulai BFS
            while (!edgeQueue.isEmpty()) {
                // Poll a city from queue
                int current = edgeQueue.poll();
                // Set the current jadi visited
                visitedCities[current] = true;
                // KAlo current nya itu gapunya anak return 1
                if (edgeList.get(current) == null) {
                    return 1;
                } else {
                    // Cari adjacent edges dari kota skrg
                    ArrayList<Edge> adjacentCities = this.edgeList.get(current);
                    // Visit semua yang ada disana
                    for (int k = 0; k < adjacentCities.size(); k++) {
                        // Kalo dia belom pernah divisit
                        if (!visitedCities[adjacentCities.get(k).getTo()]) {
                            visitedCities[adjacentCities.get(k).getTo()] = true;
                            // Masukin ke queue
                            edgeQueue.add(adjacentCities.get(k).getTo());
                        }
                    }
                }
            }
        }
        int result = 0;
        for (int i = 0; i < visitedCities.length; i++) {
            if (visitedCities[i] == true) {
                result += 1;
            }
        }
        // return
        return result;
    }

    int countCityWithGivenDistance(int initial, int distance) {
        boolean[] visitedCities = new boolean[this.numberOfVertices + 1];
        // Initialize jadi false semua
        for (int i = 0; i < visitedCities.length; i++) {
            visitedCities[i] = false;
        }
        Queue<Integer> edgeQueue = new LinkedList<Integer>();
        // Bikin node pertama jadi true
        visitedCities[initial] = true;
        // Add ke queue
        edgeQueue.add(initial);
        while (!edgeQueue.isEmpty()) {
            // Poll a city from queue
            int current = edgeQueue.poll();
            // System.out.println(current);
            // Set the current jadi visited
            visitedCities[current] = true;
            if (edgeList.get(current) == null) {
                return 0;
            } else {
                // Cari adjacent edges dari kota skrg
                ArrayList<Edge> adjacentCities = this.edgeList.get(current);
                // Visit semua yang ada disana
                // Visit semua yang ada disana
                for (int k = 0; k < adjacentCities.size(); k++) {
                    // Kalo dia belom pernah divisit
                    // System.out.printf("Check (%d)\n", adjacentCities.get(k).getTo());
                    if (adjacentCities.get(k).getWeight() <= distance) {
                        if (!visitedCities[adjacentCities.get(k).getTo()]) {
                            visitedCities[adjacentCities.get(k).getTo()] = true;
                            // Masukin ke queue
                            edgeQueue.add(adjacentCities.get(k).getTo());
                        }
                    }
                }
            }
        }

        int result = 0;
        for (int i = 0; i < visitedCities.length; i++) {
            if (visitedCities[i] == true) {
                result += 1;
            }
        }
        // TODO
        return result - 1;

    }

}

public class Main {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String[] programDetails = Main.br.readLine().split(" ");
        // Buat graph nya
        Graph graph = new Graph(Integer.parseInt(programDetails[0]));

        // Forloop sesuai jumlah jalan raya / edge
        for (int i = 0; i < Integer.parseInt(programDetails[1]); i++) {
            String[] jalanrayaArray = Main.br.readLine().split(" ");
            // Tambah edge nya ke graph
            graph.addKota(Integer.parseInt(jalanrayaArray[0]), Integer.parseInt(jalanrayaArray[1]),
                    Integer.parseInt(jalanrayaArray[2]));
        }

        // Forloop perintah
        for (int i = 0; i < Integer.parseInt(programDetails[2]); i++) {
            String[] perintahDetail = Main.br.readLine().split(" ");
            switch (perintahDetail[0]) {
            case "OS":
                String[] openStores = Arrays.copyOfRange(perintahDetail, 1, perintahDetail.length);
                Main.bw.write(Integer.toString(graph.openStore(openStores)) + "\n");
                break;
            case "CCWGD":
                // graph.print();
                // TODO
                Main.bw.write(Integer.toString(graph.countCityWithGivenDistance(Integer.parseInt(perintahDetail[1]),
                        Integer.parseInt(perintahDetail[2]))) + "\n");
                break;
            case "LAOR":
                // TODO
            case "LAORC":
                // TODO
            case "SR":
                // TODO
            case "SM":
                // TODO
            }
        }
        Main.bw.flush();
    }
}