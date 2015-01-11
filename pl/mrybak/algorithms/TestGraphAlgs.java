package pl.mrybak.algorithms;

import java.util.Arrays;

public class TestGraphAlgs {
    public static void main(String [] algs) {
        int[] results = GraphAlgs.sortedAreas(new String[]{"0 192 399 207", "0 392 399 407", "120 0 135 599", "260 0 275 599"});
        System.out.println(Arrays.toString(results));

        int result = GraphAlgs.minTurns(new String[]{
                "...A.XXXXX.....",
                ".....XXXXX.....",
                "...............",
                ".....XXXXX.B...",
                ".....XXXXX....."
                });
        System.out.println(result);
    }
}
