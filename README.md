# EDGE REMOVAL FROM MST

Main idea:

This bonus task demonstrates how to build a Minimum Spanning Tree (MST) using Kruskal’s Algorithm, remove an edge from the MST,
find the resulting connected components and efficiently find a replacement edge to reconnect the graph
while maintaining the minimum total weight.

Methodology:

1. Graph Representation:

The graph is represented using an edge list, where each edge contains a source vertex, destination vertex, and weight.
The number of vertices V is stored in the Graph class to facilitate component detection.

2. Minimum Spanning Tree (MST):

Algorithm Used: Kruskal’s Algorithm
Sort all edges by weight in ascending order.
Use a Union-Find (Disjoint Set Union) structure to prevent cycles.
Add edges one by one until MST contains V-1 edges.

3. Edge Removal

A specific edge is removed from the MST.
The MST may split into two components, which are detected using DFS.

4. Replacement Edge Detection

Among all edges in the original graph (except the removed edge), find the edge with minimum weight that connects vertices from different components.
Add this edge to the MST to restore connectivity while keeping the total weight minimal.

How to run a program:

Open project folder > Open Main.java in src/main/java > "Run Java"
To choose the edge to remove, write down its index to the mstEdges.get method in Main.java


<img width="397" height="100" alt="image" src="https://github.com/user-attachments/assets/ee0f0188-b35b-47da-b7b2-ab91a92206f2" />

