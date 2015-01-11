package pl.mrybak.algorithms;

import java.util.List;

public class Bitwise {
    private static void printBin(int i) {
        System.out.println(Integer.toBinaryString(i));
    }

    public static void main(String[] args) {
        Integer a = 21;
        Integer b = 29;
        Integer c = 0xa9;

        printBin(a);
        printBin(b);
        System.out.println(c);
        printBin(c);
        c = ~c;
        printBin(c);
        System.out.println(c);
        printBin(c << 1);
        System.out.println(c << 1);
        printBin(c >> 1);
        System.out.println(c >> 1);
        printBin(c >>> 1);  // fills with zeros from left
        System.out.println(c >>> 1);

        System.out.println();

        printBin(a & b);
        printBin(a ^ b);
        printBin(a ^ a);
        printBin(0 ^ b);
        printBin(a | b);




        int n = findNonDuplicatedIn(new int[]{1,2,3,4,5,6,7,8,9,1,2,3,4,5,6,8,9});
        System.out.println(n + " has no duplicate");
        n = findDifferenceBetween(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, new int[]{1, 2, 3, 4, 5, 6, 8, 9});
        System.out.println(n + " is the difference");
    }

    private static int findDifferenceBetween(int[] ints, int[] ints2) {
        int result = 0;
        for (int n : ints) {
            result ^= n;
        }
        for (int n : ints2) {
            result ^= n;
        }
        return result;
    }

    private static int findNonDuplicatedIn(int[] numbers) {
        int result = 0;
        for (int n : numbers) {
            result ^= n;
        }
        return result;
    }
}
