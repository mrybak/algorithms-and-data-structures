package pl.mrybak.algorithms;

import java.util.*;


// TODO: big refactoring
public class StringAlgs {


    /*
        classical LIS problem solved with dynamic programming
     */
    public static List<Integer> longestIncreasingSequence(int[] a) {
        List<Integer> result = new ArrayList<Integer>();
        if (a.length == 0) { return result; }

        int[] lisLengthHere = new int[a.length];  // LIS ending at given index of a
        int[] lisPrevElem = new int[a.length];    // index of previous element of LIS ending here
        int maxLenHere;                    // temp variable for computing the above
        int prevElemHere;                    // temp variable for computing the above
        lisLengthHere[0] = 1;   // initialization


        for (int i = 1; i < a.length; i++) {
            maxLenHere = 1;     // just this element
            prevElemHere = -1;  // no previous element right now
            for (int j = 0; j < i; j++) {
                if (a[i] > a[j]) {
                    if (lisLengthHere[j]+1 > maxLenHere) {
                        maxLenHere = lisLengthHere[j]+1;  // update max
                        prevElemHere = j;                 // update prev
                    }
                }
            }
            lisLengthHere[i] = maxLenHere;
            lisPrevElem[i] = prevElemHere;
        }

        int maxElem = 0;
        int maxElemIndex = -1;
        for (int i = 0; i < lisLengthHere.length; i++) {
            if (lisLengthHere[i] > maxElem) {
                maxElem = lisLengthHere[i];
                maxElemIndex = i;
            }
        }
        while (maxElemIndex != -1) {
            result.add(a[maxElemIndex]);
            maxElemIndex = lisPrevElem[maxElemIndex];
        }

        Collections.reverse(result);

        return result;
    }


    public static int longestIncreasingSequenceLength(int[] a) {
        return longestIncreasingSequence(a).size();
    }

    public static Set<List<String>> splitToWords(String input, Set<String> dict) {
        System.out.println("Splitting: " + input);
        System.out.println("==========================================");
        int len = input.length();

        boolean[] wordStart = new boolean[len+1];
        wordStart[len] = true;   // set character right after this string to true

        Map<Integer, List<String>> wordsAt = new HashMap<>();

        // iterate from end of string,
        for (int i = len; i >= 0; i--) {
            // for each position, check all words starting from this position
            for (int j = i + 1; j <= len; j++) {
                // if word in dict
                String word = input.substring(i, j);
                if(dict.contains(word)) {
                    //  if there is already a word or end of input right behind this word
                    wordStart[i] = wordStart[i] || wordStart[j];
                    if (wordStart[i]) {
                        List<String> words = wordsAt.get(i);
                        if (words == null) {
                            words = new ArrayList<>();
                        }
                        words.add(word);
                        wordsAt.put(i, words);
                    }
                }
            }
        }
        // after this loop, we have all positions of valid words contained in wordStart
        // TODO: if instead of binary info we store list of lengths, it should be available later

        for (int i = 0; i < len; i++) {
        //    System.out.println(i + ": " + wordStart[i]);
        //    System.out.println(wordsAt.get(i));
        }

        printResults(len, wordsAt);


        return null;
    }

    private static void printResults(int len, Map<Integer, List<String>> wordsAt) {
        Set<List<String>> results = getResultsRec(0, len, wordsAt);
        for (List<String> result : results) {
            System.out.println(result);
        }
    }

    private static Set<List<String>> getResultsRec(int from, int len, Map<Integer, List<String>> wordsAt) {
        Set<List<String>> result = new HashSet<>();

        if (from == len) {
            ArrayList<String> empty = new ArrayList<>();
            empty.add("");
            result.add(empty);
            return result;
        }

        List<String> words = wordsAt.get(from);

        for (String word : words) {
            Set<List<String>> suffixes = getResultsRec(from + word.length(), len, wordsAt);
            for (List<String> suffix : suffixes) {
                ArrayList<String> sentence = new ArrayList<>();
                sentence.add(word);
                sentence.addAll(suffix);
                result.add(sentence);
            }
        }

        return result;
    }
}
