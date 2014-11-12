package pl.mrybak.datastructures;

import java.util.Set;
import java.util.HashSet;
import java.util.LinkedList;

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
    Set<Node<T>> visited = new HashSet<>();
    LinkedList<Node<T>> stack = new LinkedList<>();
    
    for(Node<T> node : nodes) {  // this loop ensures that *all* connected components are traversed
      if (!visited.contains(node)) {	
	visited.add(node);
	stack.push(node);
      }
      while(!stack.isEmpty()) {
	Node<T> currentNode = stack.pop();
	
	System.out.println(currentNode);  // arbitrary 'process' function can be used, here we print
	
	for(Node<T> neighbour : currentNode.neighbours) {
	  if(!visited.contains(neighbour)) {
	    visited.add(neighbour);
	    stack.push(neighbour);
	  }
	}
      }
    }
  }
  
  
  public void traverseBFS() {
    // Traverse.BFS(this);
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
      if (node.neighbours.size() > 0) { 
	// remove trailing comma
	sb.deleteCharAt(sb.length() - 1);
      }
      sb.append("]\n");
    }
    return sb.toString();
  }
}
