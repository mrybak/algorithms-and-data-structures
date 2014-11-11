package pl.mrybak.algorithms;

import java.util.Arrays;

public class Test {
  public static void main(String[] args) {
    int[] arr = {3,1,6,4,8,0,2,4,5,6,12,3};
        
    System.out.println("unsorted: " + Arrays.toString(arr));
    
    System.out.println("selection sort: " + Arrays.toString(Sort.selection(arr)));
  }
}