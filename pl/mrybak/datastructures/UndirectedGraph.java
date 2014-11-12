package pl.mrybak.datastructures;

import pl.mrybak.algorithms.Traverse;
import java.util.Set;
import java.util.HashSet;

/**
  * Undirected graph
  */
public class UndirectedGraph<T> {
  
  Set<Node<T>> nodes = new HashSet<>();
  
  private static class Node<T> {
    private int id;
    private T value;
    private Set<Node<T>> neighbours = new HashSet<>();
    
    public Node(int id, T value) {
      this.id = id;
      this.value = value;
    }
  }  
  
  
  /**
    * Add node to this graph. If node with this id already exists, overwrite value
    */
  public void addNode(int id, T value) {
    nodes.add(new Node<T>(id, value));    
  }
  
  
  /**
    * Add undirected edge to this graph
    */
  public void addEdge(int from, int to) {
    Node<T> node1 = getNode(from);
    Node<T> node2 = getNode(to);
    if (node1 == null || node2 == null) {
      throw new NullPointerException("Attempted to add an edge between non-existing nodes");
    }
    node1.neighbours.add(node2);
    node2.neighbours.add(node1);
  }
    
  
  /**
    * Utility methods
    */
  private Node<T> getNode(int id) {
    for(Node<T> node : nodes) {
      if (node.id == id) { 
	return node;
      }
    }
    return null;
  }    
    
  /**
    * Traversal methods
    */
  public void traverseDFS() {
    Traverse.DFS(this);
  }
  
  
  public void traverseBFS() {
    Traverse.BFS(this);
  }
  
  
  @Override 
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for(Node<T> node : nodes) {
      sb.append(node.id);
      sb.append(" (");
      sb.append(node.value);
      sb.append(") -> [");
      
      for(Node<T> neighbour : node.neighbours) {
	sb.append(neighbour.id);
	sb.append(",");
      }	
      sb.deleteCharAt(sb.length() - 1);
      sb.append("]\n");
    }
    return sb.toString();
  }
}
