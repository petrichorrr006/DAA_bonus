package main.java;
import java.util.*;

public class Kruskal {
    public static List<Edge> buildMST(Graph graph) {
        List<Edge> result = new ArrayList<>();
        Collections.sort(graph.edges);

        DSU ds = new DSU(graph.vertices);

        for (Edge edge : graph.edges) {
            int root1 = ds.find(edge.src);
            int root2 = ds.find(edge.dest);

            if (root1 != root2) {
                result.add(edge);
                ds.union(root1, root2);
            }
        }

        return result;
    }
}