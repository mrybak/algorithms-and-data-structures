package pl.mrybak.algorithms;

import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.*;

public class IntegerAlgs {
    private int n;
    private Map<Integer, Set<ArrayList<Integer>>> mem = new HashMap<>();   // this will be used or memoization

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

        for (ArrayList<Integer> sum : sums) {
            System.out.println(sum);
        }
    }

    private Set<ArrayList<Integer>> getSumsRec(int of) {
        if (mem.get(of) != null) {
            return mem.get(of);
        }

        Set<ArrayList<Integer>> result = new HashSet<>();

        ArrayList<Integer> self = new ArrayList<>();
        self.add(of);
        result.add(self);

        if (of != 1) {
            // take numbers from 1 to of/2
            for (int i = 1; i <= of / 2; i++) {
                Set<ArrayList<Integer>> sums = getSumsRec(of - i);
                // generate set consisting of this number and all sums forming the rest
                for (ArrayList<Integer> sum : sums) {
                    ArrayList<Integer> thisSum = new ArrayList<>();
                    thisSum.add(i);
                    thisSum.addAll(sum);
                    Collections.sort(thisSum);
                    result.add(thisSum);
                }
            }
        }

        mem.put(of, result);

        return result;
    }

    public static boolean has3SummingToZero(int[] a) {
        if (a.length < 3) {
            return false;
        }

        Map<Integer, Integer> arrElems = new HashMap<>(a.length);  // we'll use conjugates as keys and indices as values
        int sum;

        for (int i = 0; i < a.length; i++) {
            arrElems.put(-a[i], i);
        }

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                sum = a[i] + a[j];
                if (i != j &&
                    arrElems.containsKey(sum) &&
                    arrElems.get(sum) != i &&
                    arrElems.get(sum) != j
                )  {

                    return true;
                }
            }
        }

        return false;
    }

    public static int nthFib(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        if (n <= 2) {
            return 1;
        }

        int f1 = 1, f2 = 1, temp;

        for (int i = 3; i <= n; i++) {
            temp = f1 + f2;
            f1 = f2;
            f2 = temp;
        }

        return f2;
    }

}
