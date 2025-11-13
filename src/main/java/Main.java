package main.java;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph(6);

        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 4);
        graph.addEdge(1, 2, 2);
        graph.addEdge(1, 0, 4);
        graph.addEdge(2, 3, 3);
        graph.addEdge(2, 5, 2);
        graph.addEdge(2, 4, 4);
        graph.addEdge(3, 4, 3);
        graph.addEdge(5, 4, 3);

        List<Edge> mst = Kruskal.buildMST(graph);

        System.out.println("MST edges:");
        for (Edge e : mst) {
            System.out.println(e);
        }
    }
}
