package pl.mrybak.algorithms;

import pl.mrybak.datastructures.Pair;

import java.util.*;

/* covers most important graph algorithms implemented using standard java libraries */
public class GraphAlgs {

    /**
     * In one mode of the grafix software package, the user blocks off portions of a masking layer using opaque rectangles.
     * The bitmap used as the masking layer is 400 pixels tall and 600 pixels wide.
     * Once the rectangles have been blocked off, the user can perform painting actions through the remaining areas of the masking layer, known as holes.
     * To be precise, each hole is a maximal collection of contiguous pixels that are not covered by any of the opaque rectangles.
     * Two pixels are contiguous if they share an edge, and contiguity is transitive.
     *
     * You are given a String[] named rectangles, the elements of which specify the rectangles that have been blocked off in the masking layer.
     * Each String in rectangles consists of four integers separated by single spaces, with no additional spaces in the string.
     * The first two integers are the window coordinates of the top left pixel in the given rectangle, and the last two integers are the window coordinates of its bottom right pixel.
     * The window coordinates of a pixel are a pair of integers specifying the row number and column number of the pixel, in that order.
     * Rows are numbered from top to bottom, starting with 0 and ending with 399.
     * Columns are numbered from left to right, starting with 0 and ending with 599.
     * Every pixel within and along the border of the rectangle defined by these opposing corners is blocked off.
     *
     * Return a int[] containing the area, in pixels, of every hole in the resulting masking area, sorted from smallest area to greatest.
     *
     * @see: http://community.topcoder.com/stat?c=problem_statement&pm=2998&rd=5857
     *
     *
     * We will be using DFS to find all connected components of resulting graph.
     */
    public static final int ROWS = 400;
    public static final int COLS = 600;

    public static int[] sortedAreas(String[] rectangles) {

        List<Integer> result = new ArrayList<>();

        boolean[][] filled = new boolean[ROWS][COLS];

        // mark rectangles
        for (String rectangle : rectangles) {
            String[] tokens = rectangle.split(" ");
            int x1 = Integer.parseInt(tokens[0]);
            int y1 = Integer.parseInt(tokens[1]);
            int x2 = Integer.parseInt(tokens[2]);
            int y2 = Integer.parseInt(tokens[3]);

            for (int i = x1; i <= x2; i++) {
                for (int j = y1; j <= y2; j++) {
                    filled[i][j] = true;
                }
            }
        }

        // perform DFS for each connected area of falsey values of filled
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                int size = componentSizeAt(i, j, filled);
                if (size > 0) { result.add(size); }
            }
        }

        // copy sorted results to int array (meh)
        Collections.sort(result);
        int[] resultArray = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            resultArray[i] = result.get(i);
        }

        return resultArray;
    }

    private static int componentSizeAt(int i, int j, boolean[][] filled) {
        int result = 0;
        Deque<Pair<Integer, Integer>> stack = new ArrayDeque<>();

        stack.push(new Pair<>(i, j));

        while (!stack.isEmpty()) {
            Pair<Integer, Integer> top = stack.pop();
            int x = top.first, y = top.second;
            if (inBounds(x,y) && !filled[x][y]) {
                filled[x][y] = true;
                result += 1;
                stack.push(new Pair<>(x-1, y));
                stack.push(new Pair<>(x+1, y));
                stack.push(new Pair<>(x, y-1));
                stack.push(new Pair<>(x, y+1));
            }
        }

        return result;
    }

    private static boolean inBounds(int x, int y) {
        return x >= 0 && x < ROWS && y >= 0 && y < COLS;
    }
}
