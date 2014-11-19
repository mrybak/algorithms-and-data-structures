package pl.mrybak.datastructures;

import java.util.HashSet;
import java.util.List;
import java.util.OptionalInt;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Undirected graph
 */
public class WeightedDirectedGraph<T> {

    Set<Node<T>> nodes = new HashSet<>();
    Set<DirectedEdge> edges = new HashSet<>();

    private static class Node<T> {
        private int id;
        private T value;

        public Node(int id, T value) {
            this.id = id;
            this.value = value;
        }

        @Override
        public String toString() {
            return this.id + " (" + this.value + ") ";
        }
    }


    /**
     * Add node to this graph. If node with this id already exists, overwrite value
     */
    public void addNode(int id, T value) {
        nodes.add(new Node<T>(id, value));
    }


    /**
     * Add directed edge to this graph
     */
    public void addEdge(int from, int to, int weight) {
        Node<T> node1 = getNode(from);
        Node<T> node2 = getNode(to);
        if (node1 == null || node2 == null) {
            throw new NullPointerException("Attempted to add an edge between non-existing nodes");
        }
        edges.add(new DirectedEdge(from, to, weight));
    }


    /**
     * Utility methods
     */
    private Node<T> getNode(int id) {
        for (Node<T> node : nodes) {
            if (node.id == id) {
                return node;
            }
        }
        return null;
    }

    public T getValue(int nodeId) {
        return getNode(nodeId).value;
    }

    public OptionalInt maxNodeId() {
        return nodes.stream().mapToInt(node -> node.id).max();
    }

    public List<DirectedEdge> getEdgesFrom(int id) {
        List<DirectedEdge> result = edges.stream()
                .filter(e -> e.from() == id)
                .collect(Collectors.toList());   // yeah
        return result;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Node<T> node : nodes) {
            sb.append(node.id);
            sb.append(" (");
            sb.append(node.value);
            sb.append(") -> [");

            for (DirectedEdge edge : edges) {
                if (edge.from() == node.id) {
                    sb.append(edge.to());
                    sb.append("( w");
                    sb.append(edge.weight());
                    sb.append("),");
                }
            }
            sb.append("]\n");
        }
        return sb.toString();
    }
}
