package main.java;
import java.util.*;

public class EdgeRemover {

    public static List<Edge> removeEdge(List<Edge> mst, Edge toRemove) {
        List<Edge> newMST = new ArrayList<>(mst);
        newMST.remove(toRemove);
        return newMST;
    }

    private static Map<Integer, List<Integer>> buildAdjacency(List<Edge> edges) {
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for (Edge e : edges) {
            adj.computeIfAbsent(e.src, k -> new ArrayList<>()).add(e.dest);
            adj.computeIfAbsent(e.dest, k -> new ArrayList<>()).add(e.src);
        }
        return adj;
    }

    public static Set<Integer> dfsComponent(int start, Map<Integer, List<Integer>> adj) {
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(start);

        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!visited.contains(node)) {
                visited.add(node);
                for (int neighbor : adj.getOrDefault(node, new ArrayList<>())) {
                    stack.push(neighbor);
                }
            }
        }
        return visited;
    }

    public static List<Set<Integer>> getComponents(List<Edge> edges, int totalVertices) {
        Map<Integer, List<Integer>> adj = buildAdjacency(edges);
        Set<Integer> visited = new HashSet<>();
        List<Set<Integer>> components = new ArrayList<>();

        for (int i = 0; i < totalVertices; i++) {
            if (!visited.contains(i) && adj.containsKey(i)) {
                Set<Integer> comp = dfsComponent(i, adj);
                components.add(comp);
                visited.addAll(comp);
            }
        }

        return components;
    }

    public static Edge findReplacementEdge(Graph graph, List<Set<Integer>> components) {

        if (components.size() != 2) {
            System.out.println("Expected exactly two components, but found: " + components.size());
            return null;
        }
        Set<Integer> compA = components.get(0);
        Set<Integer> compB = components.get(1);
        Edge bestEdge = null;
        int minWeight = Integer.MAX_VALUE;

        for (Edge e : graph.getEdges()) {
            boolean connectsDifferent = 
                (compA.contains(e.src) && compB.contains(e.dest)) ||
                (compB.contains(e.src) && compA.contains(e.dest));

            if (connectsDifferent && e.weight < minWeight) {
                minWeight = e.weight;
                bestEdge = e;
            }
        }

        return bestEdge;
    }
}