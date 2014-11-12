package pl.mrybak.algorithms;

public class Binsearch {

  /**
    * Binary search
    * @return index of elem in a or -1 in a does not contain elem
    */
  public static int search(int[] a, int elem) {
      return searchRec(a, elem, 0, a.length);
  }  
  
  private static int searchRec(int[] a, int elem, int from, int to) {
      if (from == to) {
	return -1;
      }
      int mid = from + (to - from) / 2;  // simple (from + to) / 2 may overflow
      if (a[mid] == elem) {
	return mid;
      }
      if (a[mid] < elem) {
	return searchRec(a, elem, mid + 1, to);
      } 
      else {
	return searchRec(a, elem, from, mid);
      }
  }  
}
