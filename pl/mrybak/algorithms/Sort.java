package pl.mrybak.algorithms;

import java.util.Arrays;

/**
  * There are two approaches for implementing a tree, either using nested Node class or not.
  * Code below now uses Node class. This is for learning purposes (i.e discovering differences b'ween two approaches)
  */
public class Sort {

  /**
    * Selection sort
    */
  public static int[] selection(int[] a) {
      int minIndex = 0;
      int len = a.length;
      for (int i = 0; i < len; i++) {
	minIndex = getMinIndex(a, i, len);
	swap(a, i, minIndex);
      }
      return a;
  }
  
  private static int getMinIndex(int[] arr, int from, int to) {
    int minIndex = from;
    for (int i = from + 1; i < to; i++) {
      if (arr[i] < arr[minIndex]) {
	minIndex = i;
      }
    }
    return minIndex;
  } 
  
  /**
    * Insertion sort
    */
  public static int[] insertion(int[] a) {
    int len = a.length;
    for (int i = 1; i < len; i++) {
      for (int j = i-1; (j >= 0) && (a[j+1] < a[j]); j--) {
	swap(a, j+1, j); 
      }
    }
    
    return a;
  }
  
  
  /**
    * Merge sort
    */
  public static int[] merge(int[] a) {
    // TODO
    
    return a;
  }
  
  
  /**
    * Utility functions
    */
  private static void swap(int[] arr, int i1, int i2) {
    int temp = arr[i1];
    arr[i1] = arr[i2];
    arr[i2] = temp;
  }
}
