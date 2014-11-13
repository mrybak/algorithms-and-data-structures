package pl.mrybak.algorithms;

/**
  * Selection, insertion and quick sort are sorting passed array in-place, but they do return it for consistency
  */
public class Sort {

  /**
    * Selection sort
    */
  public static int[] selectionSort(int[] a) {
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
  public static int[] insertionSort(int[] a) {
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
  public static int[] mergeSort(int[] a) {
    return mergeSortRec(a, 0, a.length);
  }
  
  private static int[] mergeSortRec(int[] a, int from, int to) {
    if (from + 1 == to) return new int[]{a[from]};
    int middle = (from + to) / 2;
    int[] a1 = mergeSortRec(a, from, middle);
    int[] a2 = mergeSortRec(a, middle, to);
    
    return merge(a1, a2);
  }
  
  private static int[] merge(int[] a, int[] b) {
    int lenA = a.length;
    int lenB = b.length;
    int[] result = new int[lenA + lenB];
    
    for(int i = 0, j = 0; i < lenA || j < lenB;) {
      if (i == lenA) { 
	result[i+j] = b[j++];
      }
      else if (j == lenB) { 
	result[i+j] = a[i++]; 
      }
      else { 
	result[i+j] = (a[i] < b[j]) ? a[i++] : b[j++]; 
      }
    }    

    return result;
  }
  
  
  /**
    * Quicksort
    * @return a sorted in-place; we do return it for Sort class consistency only
    */
  public static int[] quickSort(int[] a) {  
    quickSortRec(a, 0, a.length);
    return a;  
  }
  
  private static void quickSortRec(int[] a, int from, int to) {
    if (to - from > 1) {  // do not sort if only one element left
      int pivot = a[from], pivotIndex;
      
      pivotIndex = partition(a, pivot, from, to);
      
      quickSortRec(a, from, pivotIndex);
      quickSortRec(a, pivotIndex + 1, to);
    }
  }
  
  /**
    * Rearrange a in such way that it consists of three parts:
    * A, pivot, B where:
    * A = { e : a contains e && e <= pivot }
    * B = { e : a contains e && e >  pivot } 
    * @return index of pivot after rearrangement
    */
  private static int partition(int[] a, int pivot, int from, int to) {
    int i = from, j = to - 1;
    while(i < j) { 
      // TODO: refactor
      while(a[i] < pivot && i < to-1) { i++; } 
      while(a[j] >=  pivot && j > from) { j--; }
      
      if (i < j) { 
	swap(a, i, j); 	
      }      
    }
    // because pivot index is initially equal to parameter from, we swap it with the last element of A 
    swap(a, from, j); 
    return j;  // new pivot index
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
