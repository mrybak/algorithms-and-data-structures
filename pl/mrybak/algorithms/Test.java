package pl.mrybak.algorithms;

import java.util.Arrays;

public class Test {
  public static void main(String[] args) {
    System.out.println("unsorted: " + Arrays.toString(new int[]{3,1,6,4,8,0,2,4,5,6,12,3}));
    
    System.out.println("selection sort: " + Arrays.toString(Sort.selectionSort(new int[]{3,1,6,4,8,0,2,4,5,6,12,3})));
    System.out.println("insertion sort: " + Arrays.toString(Sort.insertionSort(new int[]{3,1,6,4,8,0,2,4,5,6,12,3})));
    System.out.println("merge sort: " + Arrays.toString(Sort.mergeSort(new int[]{3,1,6,4,8,0,2,4,5,6,12,3})));
    System.out.println("quick sort: " + Arrays.toString(Sort.quickSort(new int[]{3,1,6,4,8,0,2,4,5,6,12,3})));
    System.out.println("quick sort: " + Arrays.toString(Sort.quickSort(new int[]{12,1,6,4,8,0,2,4,5,6,3,3})));
    
    int[] sorted = Sort.mergeSort(new int[]{3,1,6,4,8,0,2,4,5,6,12,3});    
    System.out.println("\nbinsearch for 4: " + Binsearch.search(sorted, 4)); 
    System.out.println("binsearch for 12: " + Binsearch.search(sorted, 12)); 
    System.out.println("binsearch for 0: " + Binsearch.search(sorted, 0)); 
    System.out.println("binsearch for 6: " + Binsearch.search(sorted, 6));
    System.out.println("binsearch for 7 (missing): " + Binsearch.search(sorted, 7));      
    
    FindUnion fu = new FindUnion(10);
    fu.union(1,2); 
    fu.union(1,3); 
    fu.union(4,3); 
    fu.union(6,5); 
    System.out.println("1, 2: " + (fu.connected(1,2) ? "connected" : "not connnected"));
    System.out.println("4, 1: " + (fu.connected(4,1) ? "connected" : "not connnected"));
    System.out.println("3, 6: " + (fu.connected(3,6) ? "connected" : "not connnected"));
    fu.addNode(12);
    fu.union(12,5);
    System.out.println("12, 6: " + (fu.connected(12,6) ? "connected" : "not connnected"));
  }
}