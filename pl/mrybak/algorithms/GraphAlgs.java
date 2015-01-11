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


    /** ======================================================================================== */

    /**
     * Given a board consisting of empty space, walls, and the starting positions of two players A and B,
     * determine the minimum number of turns it will take for players A and B to switch positions on the board.
     *
     * During a turn, one or both players may take a step. A step is defined as a unit movement up, down, left, right, or in any of the four diagonals.
     * Players may not step into walls or off the board. Players may never share the same square at the end of a turn.
     * Players may not cross paths during a turn. Crossing paths occurs when players A and B switch positions in a single turn.
     * For example, assume player A is in the upper left corner of the board, and player B is in the square immediately to his right.
     * Player A may not move right while player B moves left, since they would be passing each other.
     * Player A can, however, move right if player B moves in any other direction.
     *
     * You will be given a String[] board, representing the game board.
     * board will contain exactly one 'A' and exactly one 'B'; each other character will be either '.' (empty space), or 'X' (a wall).
     * Your method should return the minimum number of turns necessary for players A and B to switch positions, or -1 if this is impossible.
     *
     * @see: http://community.topcoder.com/stat?c=problem_statement&pm=1110
     *
     * TODO: refactor
     *
     * We will be using BFS on a graph of states, which are represented using GameState class
     */
    static class GameState {
        public int aX, aY, bX, bY; // A and B's positions
        public int turns;  // total turns to this state

        public GameState(int aX, int aY, int bX, int bY, int turns) {
            this.aX = aX;
            this.aY = aY;
            this.bX = bX;
            this.bY = bY;
            this.turns = turns;
        }
    }

    public static int minTurns(String[] board) {
        final int ROWS = board.length;
        final int COLS = board[0].length();

        boolean[][][][] visited = new boolean[ROWS][COLS][ROWS][COLS];

        Deque<GameState> q = new LinkedList<>();
        GameState start = findInitialGameState(board, ROWS, COLS);
        visited = pushToQueue(q, visited, start);

        while (!q.isEmpty()) {
            // shit, spent half an hour debugging this... so I've actually used .pop() and .push() instead of poll() and offer() which caused my "BFS" to turn into DFS...
            GameState top = q.poll();

            if (areOpposite(top, start)) {
                return top.turns;
            }

            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    for (int k = -1; k <= 1; k++) {
                        for (int l = -1; l <= 1; l++) {
                            GameState afterTurn = new GameState(
                                    top.aX + i,
                                    top.aY + j,
                                    top.bX + k,
                                    top.bY + l,
                                    top.turns + 1
                            );

                            if (ABinBounds(afterTurn, ROWS, COLS) && !AorBonX(afterTurn, board) && !ABinSamePlace(afterTurn) && !areOpposite(top, afterTurn)) {
                                visited = pushToQueue(q, visited, afterTurn);
                            }
                        }
                    }
                }
            }
        }
        return -1;  // no path found
    }

    private static boolean AorBonX(GameState s, String[] board) {
        return board[s.aX].charAt(s.aY) == 'X' || board[s.bX].charAt(s.bY) == 'X';
    }

    private static boolean ABinSamePlace(GameState s) {
        return s.aX == s.bX && s.aY == s.bY;
    }

    private static boolean ABinBounds(GameState s, int rows, int cols) {
        return !(s.aX < 0 || s.aX >= rows) && !(s.aY < 0 || s.aY >= cols) && !(s.bX < 0 || s.bX >= rows) && !(s.bY < 0 || s.bY >= cols);
    }



    private static boolean[][][][] pushToQueue(Deque<GameState> q, boolean[][][][] visited, GameState s) {
        if(!visited[s.aX][s.aY][s.bX][s.bY]) {
            q.offer(s);
            visited[s.aX][s.aY][s.bX][s.bY] = true;
        }
        return visited;
    }

    private static boolean areOpposite(GameState s1, GameState s2) {
        return s1.aX == s2.bX && s1.aY == s2.bY && s1.bX == s2.aX && s1.bY == s2.aY;
    }

    private static GameState findInitialGameState(String[] board, int ROWS, int COLS) {
        int aX = 0, aY = 0, bX = 0, bY = 0;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (board[i].charAt(j) == 'A') {
                    aX = i; aY = j;
                }
                if (board[i].charAt(j) == 'B') {
                    bX = i; bY = j;
                }
            }
        }

        return new GameState(aX, aY, bX, bY, 0);
    }

}
