package pl.mrybak.algorithms;

import java.util.Arrays;
import pl.mrybak.datastructures.UndirectedGraph;

public class Test {
  public static void main(String[] args) {
    System.out.println("unsorted: " + Arrays.toString(new int[]{3,1,6,4,8,0,2,4,5,6,12,3}));
    
    System.out.println("selection sort: " + Arrays.toString(Sort.selectionSort(new int[]{3,1,6,4,8,0,2,4,5,6,12,3})));
    System.out.println("insertion sort: " + Arrays.toString(Sort.insertionSort(new int[]{3,1,6,4,8,0,2,4,5,6,12,3})));
    System.out.println("merge sort: " + Arrays.toString(Sort.mergeSort(new int[]{3,1,6,4,8,0,2,4,5,6,12,3})));
    
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
  }
}