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

        Edge removed = mst.get(1);
        System.out.println("\nRemoving edge: " + removed);

        List<Edge> afterRemoval = EdgeRemover.removeEdge(mst, removed);

        List<Set<Integer>> components = EdgeRemover.getComponents(afterRemoval, graph.vertices);

        System.out.println("\nComponents after edge removal:");
        int idx = 1;
        for (Set<Integer> comp : components) {
            System.out.println("Component " + idx++ + ": " + comp);
        }
    }
}
