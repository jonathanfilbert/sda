
/**
 * Main
 */
import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge> {
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

    // TODO COMPARABLE WEIGHT
    public int compareTo(Edge otherEdge) {
        // TODO
        if (this.weight > otherEdge.getWeight()) {
            return 1;
        } else if (this.weight == otherEdge.getWeight()) {
            return 0;
        } else {
            return -1;
        }
    }
}

class Graph {
    private Integer numberOfVertices = 0;
    private HashMap<Integer, ArrayList<Edge>> edgeList;

    public Graph(int number) {
        this.numberOfVertices = number;
        this.edgeList = new HashMap<Integer, ArrayList<Edge>>();
    }

    // int djikstraHelper(int[] result, boolean[] vertexInShortestPath) {
    // // TODO
    // int min = Integer.MAX_VALUE;
    // int minIndex = -1;

    // for (int i = 0; i < this.numberOfVertices; i++) {
    // if (vertexInShortestPath[i] == false && result[i] <= min) {
    // min = result[i];
    // minIndex = i;
    // }
    // }
    // return minIndex;

    // }

    // void djikstra(int start, int end, HashMap<Integer, ArrayList<Edge>> adjMap) {
    // // Priotity Queue
    // PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
    // // result array
    // int[] result = new int[this.numberOfVertices + 1];
    // boolean[] vertexInShortestPath = new boolean[this.numberOfVertices + 1];
    // for (int i = 0; i < this.numberOfVertices; i++) {
    // result[i] = Integer.MAX_VALUE;
    // vertexInShortestPath[i] = false;
    // }

    // // distance source = 0
    // result[start] = 0;

    // // find shortest path to all vertex from start vertex
    // for (int i = 0; i < this.numberOfVertices - 1; i++) {
    // int initialVertex = djikstraHelper(result, vertexInShortestPath);
    // vertexInShortestPath[initialVertex] = true;
    // for (int j = 0; j < this.numberOfVertices; j++) {
    // if (!vertexInShortestPath[j] && adjMap.get(initialVertex).get(j).getTo() != 0
    // && vertexInShortestPath[initialVertex] != Integer.MAX_VALUE
    // && vertexInShortestPath[initialVertex]
    // + adjMap.get(initialVertex).get(j).getTo() < vertexInShortestPath[j]) {
    // // TODO
    // vertexInShortestPath[j] = vertexInShortestPath[initialVertex]
    // + adjMap.get(initialVertex).get(j).getTo();
    // }
    // }
    // }

    // }

    int[] dijkstra(int from, int to) {
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
        boolean[] visitedCities = new boolean[this.numberOfVertices + 1];
        int[] distance = new int[this.numberOfVertices + 1];
        boolean arrived = false;

        int current = from;
        visitedCities[current] = true;
        distance[current] = 0;
        // forloop start
        for (int i = 0; i < this.edgeList.get(current).size(); i++) {
            pq.add(this.edgeList.get(current).get(i));
            distance[this.edgeList.get(current).get(i).getTo()] = distance[current]
                    + this.edgeList.get(current).get(i).getWeight();
        }
        // BFS with Dijkstra
        while (!pq.isEmpty()) {
            Edge currentEdge = pq.poll();
            // for semua edge dari tujuan edge
            for (int i = 0; i < this.edgeList.get(currentEdge.getTo()).size(); i++) {
                // Kalo node di edge nya belom pernah dikunjungin
                if (!visitedCities[this.edgeList.get(currentEdge.getTo()).get(i).getTo()]) {
                    // set jadi pernah
                    visitedCities[this.edgeList.get(currentEdge.getTo()).get(i).getTo()] = true;
                    // masukin ke pq
                    pq.add(this.edgeList.get(currentEdge.getTo()).get(i));
                    // distance[node] = distance[previous] + weight
                    distance[this.edgeList.get(currentEdge.getTo()).get(i).getTo()] = distance[currentEdge.getTo()]
                            + currentEdge.getWeight();

                    // Kalo dia udah sampe to, set jadi true
                    if (this.edgeList.get(currentEdge.getTo()).get(i).getTo() == to) {
                        arrived = true;
                    }

                }
                // Kalo dia udah pernah
                else {
                    // Kalo distancenya node > distance current + weight
                    if (distance[this.edgeList.get(currentEdge.getTo()).get(i).getTo()] > distance[currentEdge.getTo()]
                            + currentEdge.getWeight()) {
                        // distance next = distance node + weight
                        distance[this.edgeList.get(currentEdge.getTo()).get(i).getTo()] = distance[currentEdge.getTo()]
                                + currentEdge.getWeight();
                        // add ke pq
                        pq.add(this.edgeList.get(currentEdge.getTo()).get(i));
                    }
                }
            }

        }
        // Kalo dia ga nyampe, return -1
        if (arrived == false) {
            distance[to] = -1;
        }
        System.out.println(Arrays.toString(distance));
        return distance;
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
                    System.out.println("ha");
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
        return result - 1;
    }

    int leastAmountOfRoad(int from, int to) {
        boolean[] visitedCities = new boolean[this.numberOfVertices + 1];
        int[] distanceArray = new int[this.numberOfVertices + 1];
        // Initialize array visitation
        for (int i = 0; i < visitedCities.length; i++) {
            visitedCities[i] = false;
        }
        // Initialize array distance
        for (int i = 0; i < distanceArray.length; i++) {
            distanceArray[i] = 0;
        }
        Queue<Integer> edgQueue = new LinkedList<Integer>();
        visitedCities[from] = true;
        edgQueue.add(from);
        if (this.edgeList.get(from) == null) {
            return -1;
        } else {
            while (!edgQueue.isEmpty()) {
                int current = edgQueue.poll();
                // System.out.println(current);
                visitedCities[current] = true;
                // Kalo gaada jalan return -1
                // get all the adjacent cities
                ArrayList<Edge> adjacentCities = this.edgeList.get(current);
                // Visit all the adjacent cities
                for (int i = 0; i < adjacentCities.size(); i++) {
                    // TODO
                    // Kalo tetangganya belom pernah dikunjungin, distance +=1
                    if (!visitedCities[adjacentCities.get(i).getTo()]) {
                        // Kunjungin dia
                        visitedCities[adjacentCities.get(i).getTo()] = true;
                        // Masukin ke queue
                        edgQueue.add(adjacentCities.get(i).getTo());
                        distanceArray[adjacentCities.get(i).getTo()] = distanceArray[current] + 1;
                        // Tambahin jumlah edgeCount
                    }
                }
            }
            return (distanceArray[to] == 0 ? -1 : distanceArray[to]);
        }
    }

    int leastAmountOfRoadCombination(int from, int to) {
        boolean[] visitedCities = new boolean[this.numberOfVertices + 1];
        int[] distanceArray = new int[this.numberOfVertices + 1];
        int[] countArray = new int[this.numberOfVertices + 1];
        // Initialize
        for (int i = 0; i < visitedCities.length; i++) {
            visitedCities[i] = false;
            distanceArray[i] = 0;
            countArray[i] = 0;
        }
        Queue<Integer> edgeQueue = new LinkedList<Integer>();
        // Bikin node pertama jadi true
        visitedCities[from] = true;
        // Add ke queue
        edgeQueue.add(from);
        // distanceArray[from] = 1;
        countArray[from] = 1;
        while (!edgeQueue.isEmpty()) {
            int current = edgeQueue.poll();
            visitedCities[current] = true;
            ArrayList<Edge> adjacentCities = this.edgeList.get(current);
            if (this.edgeList.get(current) == null) {
                return 0;
            }
            for (int i = 0; i < adjacentCities.size(); i++) {
                // Kalo dia belom pernah divisiit atau distancenya sama kek current+1
                if ((distanceArray[adjacentCities.get(i).getTo()] == distanceArray[current] + 1)
                        || !visitedCities[adjacentCities.get(i).getTo()]) {
                    // Set distance nya
                    if (!visitedCities[adjacentCities.get(i).getTo()]) {
                        // Jadiin true
                        visitedCities[adjacentCities.get(i).getTo()] = true;
                        edgeQueue.add(adjacentCities.get(i).getTo());
                    }
                    // Masukin ke queue
                    countArray[adjacentCities.get(i).getTo()] = (countArray[adjacentCities.get(i).getTo()] % 10001
                            + countArray[current] % 10001) % 10001;
                    distanceArray[adjacentCities.get(i).getTo()] = distanceArray[current] + 1;
                }
            }
        }
        return countArray[to];
    }
}

public class Alghi {

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] programDetails = br.readLine().split(" ");
        // Buat graph nya
        Graph graph = new Graph(Integer.parseInt(programDetails[0]));

        // Forloop sesuai jumlah jalan raya / edge
        for (int i = 0; i < Integer.parseInt(programDetails[1]); i++) {
            String[] jalanrayaArray = br.readLine().split(" ");
            // Tambah edge nya ke graph
            graph.addKota(Integer.parseInt(jalanrayaArray[0]), Integer.parseInt(jalanrayaArray[1]),
                    Integer.parseInt(jalanrayaArray[2]));
        }

        // Forloop perintah
        for (int i = 0; i < Integer.parseInt(programDetails[2]); i++) {
            String[] perintahDetail = br.readLine().split(" ");
            switch (perintahDetail[0]) {
            case "OS":
                String[] openStores = Arrays.copyOfRange(perintahDetail, 1, perintahDetail.length);
                System.out.print(Integer.toString(graph.openStore(openStores)) + "\n");
                break;
            case "CCWGD":
                System.out.print(Integer.toString(graph.countCityWithGivenDistance(Integer.parseInt(perintahDetail[1]),
                        Integer.parseInt(perintahDetail[2]))) + "\n");
                break;
            case "LAOR":
                // TODO
                System.out.print(Integer.toString(graph.leastAmountOfRoad(Integer.parseInt(perintahDetail[1]),
                        Integer.parseInt(perintahDetail[2]))) + "\n");
                break;
            case "LAORC":
                System.out
                        .print(Integer.toString(graph.leastAmountOfRoadCombination(Integer.parseInt(perintahDetail[1]),
                                Integer.parseInt(perintahDetail[2]))) + "\n");
                // TODO
                break;
            case "SR":
                // TODO
                System.out.print("-\n");
                // System.out.print(Integer.toString(graph.dijkstra(Integer.parseInt(perintahDetail[1]),
                // Integer.parseInt(perintahDetail[2]))[Integer.parseInt(perintahDetail[2])]) +
                // "\n");
                break;
            case "SM":
                // TODO
                System.out.print("-\n");
                break;
            }
        }
        // Main.bw.flush();
    }
}