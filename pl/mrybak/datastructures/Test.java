package pl.mrybak.datastructures;

import pl.mrybak.algorithms.ShortestPath;

public class Test {
  public static void main(String[] args) {
    
    /**
      * LinkedList tests
      */
    SinglyLinkedList<Integer> l1 = new SinglyLinkedList<>();
  
    l1.append(2);
    l1.append(7);
    l1.append(4);
    l1.append(5);
    System.out.println(l1.empty());
    System.out.println(l1.size());
    System.out.println(l1.contains(4L));
    System.out.println(l1.contains(5));
    System.out.println(l1.contains("foo"));
    System.out.println(l1);
    l1.remove(7);
    System.out.println(l1);
    l1.remove(5);
    System.out.println(l1);
    System.out.println(l1.get(0));
    System.out.println(l1.get(1));
    l1.clear();
    System.out.println(l1.empty());
    System.out.println(l1.size());
    System.out.println(l1.contains(4));
    System.out.println(l1);
    
    // can be used as stack
    SinglyLinkedList<String> stack = new SinglyLinkedList<>();
    stack.push("foo");
    stack.push("bar");
    stack.push("baz");
    System.out.println(stack);
    System.out.println(stack.pop());
    System.out.println(stack.pop());
    System.out.println(stack.pop());    
    
    BST<Integer> bst = new BST<>();
    System.out.println(bst.contains(null));
    bst.insert(5);    
    System.out.println("h = " + bst.height());
    bst.insert(2);
    System.out.println("h = " + bst.height());
    bst.insert(1);
    System.out.println("h = " + bst.height());
    bst.insert(4);
    System.out.println("h = " + bst.height());
    bst.insert(3);
    System.out.println("h = " + bst.height());
    bst.insert(3);
    System.out.println("==/");
    System.out.println("MIN: " + bst.findMin());
    
    System.out.println(bst.contains(4));
    System.out.println(bst.contains(6));
    System.out.println("=== Pre ===");
    bst.enumeratePreOrder();
    System.out.println("=== In ===");
    bst.enumerateInOrder();
    System.out.println("=== Post ===");
    bst.enumeratePostOrder();
    System.out.println("=== BFS ===");
    bst.enumerateBFS();
    bst.delete(2);
    System.out.println("=== BFS ===");
    bst.enumerateBFS();
    System.out.println();
    
    /**
      * Graphs tests
      */
    UndirectedGraph<Integer> g = new UndirectedGraph<>();
    g.addNode(1, 10);
    g.addNode(2, 20);
    g.addNode(3, 30);
    g.addNode(4, 40);
    g.addNode(5, 50);
    g.addNode(6, 60);
    g.addNode(7, 70);
    
    g.addEdge(1,3);
    g.addEdge(2,3);
    g.addEdge(6,3);    
    g.addEdge(2,4);    
    g.addEdge(2,5); // comment out this lined to turn g into 2-tree forest
    g.addEdge(7,5);    
    g.addEdge(4,1);
    
    // will throw exception
    // g.addEdge(0,1);  
    
    
    System.out.println(g);
    
    System.out.println("DFS traversal: ");
    g.traverseDFS();    
    
    System.out.println("\nBFS traversal: ");
    g.traverseBFS();
    
    WeightedDirectedGraph<Integer> dg = new WeightedDirectedGraph<>();
    dg.addNode(1, 10);
    dg.addNode(2, 20);
    dg.addNode(3, 30);
    dg.addNode(4, 40);
    
    dg.addEdge(1,3,25);
    dg.addEdge(2,3,62);
    dg.addEdge(2,4,2);     
    dg.addEdge(4,1,9);
    dg.addEdge(1,4,28);
    
    System.out.println(dg);
    
    ShortestPath sp = new ShortestPath(2, dg);
    // TODO: more tests
    System.out.println(sp.getPathLength(1));
    System.out.println(sp.getPathLength(3));
    
  }
}