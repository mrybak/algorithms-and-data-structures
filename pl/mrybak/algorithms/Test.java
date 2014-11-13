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
  }
}