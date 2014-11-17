package pl.mrybak.algorithms;

import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collections;

public class IntegerAlgs {
  private int n;

  public IntegerAlgs(int n) {
    this.n = n;
  }

  public void setN(int n) {
    this.n = n;
  }
  
  /**
    * Print all sequences of integers that sum to n
    */
  public void printAsSums() {
    Set<ArrayList<Integer>> sums = getSumsRec(n);
    
    for(ArrayList<Integer> sum : sums) {
      System.out.println(sum);
    }
  }  
  
  private Set<ArrayList<Integer>> getSumsRec(int of) {
    Set<ArrayList<Integer>> result = new HashSet<>();
    
    ArrayList<Integer> self = new ArrayList<>();
    self.add(of);
    result.add(self);
    
    if (of != 1) {
      // take numbers from 1 to of/2
      for(int i = 1; i <= of/2; i++) {
	Set<ArrayList<Integer>> sums = getSumsRec(of-i);
	// generate set consisting of this number and all sums forming the rest
	for(ArrayList<Integer> sum : sums) {
	  ArrayList<Integer> thisSum = new ArrayList<>();
	  thisSum.add(i);
	  thisSum.addAll(sum);
	  Collections.sort(thisSum);
	  result.add(thisSum);
	}
      }
    }
    return result;
  }
}
