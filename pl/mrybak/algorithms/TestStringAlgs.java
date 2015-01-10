package pl.mrybak.algorithms;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestStringAlgs {
    public static void main(String[] args) {
        Set<String> dict = new HashSet<>();
        dict.add("do");
        dict.add("main");
        dict.add("domain");
        dict.add("lock");
        dict.add("block");
        dict.add("edit");
        dict.add("it");
        dict.add("blocked");
        dict.add("in");
        dict.add("locked");

        Set<List<String>> splitted = StringAlgs.splitToWords("domainblockedit", dict);

        //splitted.stream().forEach(System.out::println);


        System.out.println(StringAlgs.longestIncreasingSequence(new int[]{5, 3, 4, 7, 1, 8, 9}));
        System.out.println(StringAlgs.longestIncreasingSequence(new int[]{}));
        System.out.println(StringAlgs.longestIncreasingSequence(new int[]{6, 3, 8, 5, 2, 5, 78, 34, 2, 2, 1, 4, 7, 8, 4, 2}));
        System.out.println(StringAlgs.longestIncreasingSequenceLength(new int[]{5,3,4,7,1,8,9}));
        System.out.println(StringAlgs.longestIncreasingSequenceLength(new int[]{}));

        System.out.println(StringAlgs.toLowerCase("ASDasd"));
        System.out.println(StringAlgs.toLowerCase("asdDDEd"));
    }
}