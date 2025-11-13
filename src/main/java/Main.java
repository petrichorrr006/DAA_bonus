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

        List<Edge> mstEdges = Kruskal.buildMST(graph);
        Edge removed = mstEdges.get(0);
        System.out.println("\nRemoving edge: " + removed.src + " - " + removed.dest + " (" + removed.weight + ")");
        mstEdges.remove(removed);

        Graph mstGraph = new Graph(graph.vertices);
        for (Edge e : mstEdges) {
            mstGraph.addEdge(e.src, e.dest, e.weight);
        }

        boolean[] visited = new boolean[graph.vertices];
        List<Integer> component1 = new ArrayList<>();
        List<Integer> component2 = new ArrayList<>();

        dfs(removed.src, mstGraph, visited, component1);

        for (int i = 0; i < graph.vertices; i++) {
            if (!visited[i]) component2.add(i);
        }

        if (component1.isEmpty() || component2.isEmpty()) {
            System.out.println("\nExpected exactly two components, but found: " +
                (component1.isEmpty() ? 0 : component2.isEmpty() ? 0 : 1));
            return;
        }

        System.out.println("\nComponents after removal:");
        System.out.println("Component 1: " + component1);
        System.out.println("Component 2: " + component2);

        Edge replacement = null;
        int minWeight = Integer.MAX_VALUE;

        for (Edge e : graph.edges) {
            if (e.equals(removed)) continue;
            boolean in1 = component1.contains(e.src);
            boolean in2 = component1.contains(e.dest);
            if (in1 ^ in2) {
                if (e.weight < minWeight) {
                    minWeight = e.weight;
                    replacement = e;
                }
            }
        }

        if (replacement == null) {
            System.out.println("\nNo replacement edge found to reconnect components.");
        } else {
            System.out.println("\nReplacement edge found: " + replacement.src + " - " + replacement.dest + " (" + replacement.weight + ")");
            mstEdges.add(replacement);
            System.out.println("\nNew MST edges:");
            for (Edge e : mstEdges) {
                System.out.println("  " + e.src + " - " + e.dest + " (" + e.weight + ")");
            }
        }
    }

    static void dfs(int v, Graph g, boolean[] visited, List<Integer> component) {
        visited[v] = true;
        component.add(v);
        for (Edge e : g.edges) {
            if (e.src == v && !visited[e.dest]) dfs(e.dest, g, visited, component);
            if (e.dest == v && !visited[e.src]) dfs(e.src, g, visited, component);
        }
    }
}
