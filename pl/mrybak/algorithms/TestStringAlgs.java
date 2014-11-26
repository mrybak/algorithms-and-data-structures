package pl.mrybak.algorithms;

import java.util.ArrayList;
import java.util.HashSet;
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

        Set<ArrayList<String>> splitted = StringAlgs.splitToWords("domainblockedit", dict);

        //splitted.stream().forEach(System.out::println);
    }
}