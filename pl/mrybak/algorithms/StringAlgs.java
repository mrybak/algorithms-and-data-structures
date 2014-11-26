package pl.mrybak.algorithms;

import java.util.*;


// TODO: big refactoring
public class StringAlgs {

    public static Set<ArrayList<String>> splitToWords(String input, Set<String> dict) {
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
        Set<ArrayList<String>> results = getResultsRec(0, len, wordsAt);
        for (ArrayList<String> result : results) {
            System.out.println(result);
        }
    }

    private static Set<ArrayList<String>> getResultsRec(int from, int len, Map<Integer, List<String>> wordsAt) {
        Set<ArrayList<String>> result = new HashSet<>();

        if (from == len) {
            ArrayList<String> empty = new ArrayList<>();
            empty.add("");
            result.add(empty);
            return result;
        }

        List<String> words = wordsAt.get(from);

        for (String word : words) {
            Set<ArrayList<String>> suffixes = getResultsRec(from + word.length(), len, wordsAt);
            for (ArrayList<String> suffix : suffixes) {
                ArrayList<String> sentence = new ArrayList<>();
                sentence.add(word);
                sentence.addAll(suffix);
                result.add(sentence);
            }
        }

        return result;
    }
}
