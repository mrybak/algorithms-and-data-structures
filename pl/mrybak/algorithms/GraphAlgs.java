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

    /** ======================================================================================== */

    /**
     * The KiloMan series has always had a consistent pattern:
     * you start off with one (rather weak) default weapon, and then defeat some number of bosses.
     * When you defeat a boss, you then acquire his weapon, which can then be used against other bosses, and so on.
     * Usually, each boss is weak against some weapon acquired from another boss, so there is a recommended order in which to tackle the bosses.
     * You have been playing for a while and wonder exactly how efficient you can get at playing the game.
     * Your metric for efficiency is the total number of weapon shots fired to defeat all of the bosses.
     *
     * You have a chart in front of you detailing how much damage each weapon does to each boss per shot, and you know how much health each boss has.
     * When a boss's health is reduced to 0 or less, he is defeated.
     * You start off only with the Kilo Buster, which does 1 damage per shot to any boss.
     * The chart is represented as a String[],
     * with the ith element containing N one-digit numbers ('0'-'9'),
     * detailing the damage done to bosses 0, 1, 2, ..., N-1 by the weapon obtained from boss i,
     * and the health is represented as a int[], with the ith element representing the amount of health that boss i has.
     *
     * Given a String[] damageChart representing all the weapon damages,
     * and a int[] bossHealth showing how much health each boss has,
     * your method should return an int which is the least number of shots that need to be fired to defeat all of the bosses.
     *
     *
     * @see: http://community.topcoder.com/stat?c=problem_statement&pm=2288&rd=4725
     *
     * TODO: refactor all algorithms here into separate classes as this is becoming a total mess
     *
     * We will find shortest path through a graph of game states, which are represented by the State class below.
     * We start without any weapons and finish when we collect all weapons, ie. defeat all bosses
     */
    static class State implements Comparable<State> {
        public int weapons;  // in range 0-2^15 (as there is a maximum of 15 weapons)
        public int shots;    // shots fired until now

        public State(int weapons, int shots) {
            this.weapons = weapons;
            this.shots = shots;
        }

        @Override
        public int compareTo(State o) {
            if (this.shots < o.shots) return -1;
            if (this.shots > o.shots) return  1;
            return 0;
        }
    }

    public static int leastShots(String[] damageChart, int[] bossHealth) {
        boolean[] visited = new boolean[1 << 15];
        int maxWeapons = damageChart.length;

        PriorityQueue<State> pq = new PriorityQueue<>();
        pq.offer(new State(0,0));  // push start node into queue

        while (!pq.isEmpty()) {
            State top = pq.poll();

            // check if this state is our finish state, ie. all ones
            if (top.weapons == (1 << maxWeapons) - 1) {
                return top.shots;
            }

            // if this state was already visited, skip
            if (visited[top.weapons]) {
                continue;
            }

            visited[top.weapons] = true;

            // now, iterate over all weapons that we have and all the bosses in order to find next most efective kill
            for (int i = 0; i <  maxWeapons; i++) {
                // if we have visited this boss already (ie. ith bit is set to 1), skip
                if ((top.weapons >> i & 1) != 0) {
                    continue;
                }
                // boss is not visited; let's check which weapon kills him with least shots
                int minShots = bossHealth[i];  // initialize to boss health, as we always have at least Kilo Buster
                for (int j = 0; j < damageChart.length; j++) {
                    int damageToThisBoss = damageChart[j].charAt(i) - '0';  // convert char to int
                    // if we have this weapon and it does a non-zero damage to given boss...
                    if (((top.weapons >> j) & 1) == 1 && damageToThisBoss != 0) {
                        // compute minimum numbers of shot with this weapon
                        int shotsNeeded = bossHealth[i] / damageToThisBoss;
                        if (bossHealth[i] % damageToThisBoss != 0) shotsNeeded++;   // padding
                        if (shotsNeeded < minShots) minShots = shotsNeeded;
                    }
                }
                // add state after this boss is defeated
                pq.offer(new State(top.weapons | (1 << i), top.shots + minShots));
            }
        }

        return -1;  // we should never arrive here; could throw exception too
    }

    public static void topoSort(int[][] adjMatrix) {
        Deque<Integer> stack = new ArrayDeque<>();
        boolean[] visited = new boolean[adjMatrix.length];
        for (int i = 0; i < adjMatrix.length; i++) {
            topoSortRec(adjMatrix, visited, stack, i);
        }
        System.out.println(stack.toString());
    }

    private static void topoSortRec(int[][] adjMatrix, boolean[] visited, Deque<Integer> stack, int startNode) {
        if (visited[startNode]) {
            return;
        }
        visited[startNode] = true;
        for (int neighbour : adjMatrix[startNode]) {
                topoSortRec(adjMatrix, visited, stack, neighbour);
        }
        stack.push(startNode);
    }
}
