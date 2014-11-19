package pl.mrybak.algorithms;

import pl.mrybak.datastructures.DirectedEdge;
import pl.mrybak.datastructures.Pair;
import pl.mrybak.datastructures.WeightedDirectedGraph;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Shortest path API
 */
public class ShortestPath {

    WeightedDirectedGraph G;
    int s;   // id of node we start search from

    int[] distTo;     // distTo[n] == min. distance from s to n
    DirectedEdge[] edgeTo; // edgeTo[n] == last edge on shortest path from s to n

    PriorityQueue<Pair<Integer, Integer>> pq;

    public ShortestPath(int source, WeightedDirectedGraph graph) {
        this.G = graph;
        this.s = source;
        int numberOfVertices = G.maxNodeId().getAsInt();
        // those two below should actually check for max vertex id instead of number of vertices;
        this.distTo = new int[numberOfVertices + 1];
        this.edgeTo = new DirectedEdge[numberOfVertices + 1];

        pq = new PriorityQueue<>(numberOfVertices, new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o1.second.compareTo(o2.second);   // compare by distance
            }
        });
    }

    /**
     * Shortest paths API
     */
    public int getPathLength(int toNode) {
        int numberOfVertices = G.maxNodeId().getAsInt();


        for (int i = 0; i <= numberOfVertices; i++) {
            distTo[i] = Integer.MAX_VALUE;
        }
        distTo[s] = 0;

        pq.offer(new Pair<Integer, Integer>(s, 0));
        while (!pq.isEmpty()) {
            Pair<Integer, Integer> node = pq.poll();
            for (Object e : G.getEdgesFrom(node.first)) {
                relax((DirectedEdge) e);
            }
        }

        return distTo[toNode];
    }

    private void relax(DirectedEdge e) {
        int neighbour = e.to();
        int current = e.from();
        if (distTo[neighbour] > distTo[e.from()] + e.weight()) {
            distTo[neighbour] = distTo[e.from()] + e.weight();
            edgeTo[neighbour] = e;
            pq.offer(new Pair<Integer, Integer>(neighbour, distTo[neighbour]));
        }
    }

    boolean isConnected(int toNode) {
        return getPathLength(toNode) != Integer.MAX_VALUE;
    }
}