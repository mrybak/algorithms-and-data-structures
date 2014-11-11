package pl.mrybak.algorithms;

import java.util.Arrays;

public class Test {
  public static void main(String[] args) {
    System.out.println("unsorted: " + Arrays.toString(new int[]{3,1,6,4,8,0,2,4,5,6,12,3}));
    
    System.out.println("selection sort: " + Arrays.toString(Sort.selectionSort(new int[]{3,1,6,4,8,0,2,4,5,6,12,3})));
    System.out.println("insertion sort: " + Arrays.toString(Sort.insertionSort(new int[]{3,1,6,4,8,0,2,4,5,6,12,3})));
    System.out.println("merge sort: " + Arrays.toString(Sort.mergeSort(new int[]{3,1,6,4,8,0,2,4,5,6,12,3})));
  }
}