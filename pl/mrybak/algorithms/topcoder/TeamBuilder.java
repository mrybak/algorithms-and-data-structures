package pl.mrybak.algorithms.topcoder;

import java.util.Arrays;

/**
 * You are arranging a weird game for a team building exercise.
 * In this game there are certain locations that people can stand at,
 * and from each location there are paths that lead to other locations,
 * but there are not necessarily paths that lead directly back.
 * <p>
 * You have everything set up, but you need to know two important numbers.
 * There might be some locations from which every other location can be reached.
 * There might also be locations that can be reached from every other location.
 * You need to know how many of each of these there are.
 * <p>
 * Create a class TeamBuilder with a method specialLocations
 * that takes a String[] paths that describes the way the locations have been connected,
 * and returns a int[] with exactly two elements,
 * the first one is the number of locations that can reach all other locations,
 * and the second one is the number of locations that are reachable by all other locations.
 * <p>
 * Each element of paths will be a String containing as many characters as there are elements in paths.
 * The i-th element of paths (beginning with the 0-th element) will contain a '1' (all quotes are for clarity only)
 * in position j if there is a path that leads directly from i to j, and a '0' if there is not a path that leads directly from i to j.
 *
 * @see: http://community.topcoder.com/stat?c=problem_statement&pm=2356&rd=4740
 * <p>
 * We will use Floyd-Warshall all pairs shortest paths algorithm to find nodes connected to all other/no other nodes
 */
public class TeamBuilder {

    public int[] specialLocations(String[] paths) {
        boolean[][] adj = constructAdjacencyMatrix(paths);
        int[] ret = new int[2];

        for (boolean[] anAdj : adj) {
            System.out.println(Arrays.toString(anAdj));
        }

        for (int k = 0; k < paths.length; k++) {
            for (int i = 0; i < paths.length; i++) {
                for (int j = 0; j < paths.length; j++) {
                    // either its possible to go via k (if both i,k and k,j are connected) or directly
                    adj[i][j] = adj[i][j] || (adj[i][k] && adj[k][j]);
                }
            }
        }
        System.out.println();
        for (boolean[] anAdj : adj) {
            System.out.println(Arrays.toString(anAdj));
        }

        for (boolean[] anAdj : adj) {
            boolean good = true;
            for (int j = 0; j < adj.length && good; j++) if (!anAdj[j]) good = false;
            if (good) ret[0]++;
        }
        for( int i = 0; i < adj.length; i++ ){
            boolean good = true;
            for( int j = 0; j < adj.length && good; j++ ) if ( !adj[j][i] ) good = false;
            if( good ) ret[1]++;
        }

        return ret;
    }

    private boolean[][] constructAdjacencyMatrix(String[] paths) {
        boolean[][] matrix = new boolean[paths.length][paths.length];

        for (int i = 0; i < paths.length; i++) {
            for (int j = 0; j < paths.length; j++) {
                int pathLen = paths[i].charAt(j) - '0';
                matrix[i][j] = i == j || (pathLen != 0);
            }
        }

        return matrix;
    }

    public static void main(String[] args) {
        TeamBuilder tb = new TeamBuilder();
        int[] results = tb.specialLocations(new String[]{"0010", "1000", "1100", "1000"});
        System.out.println(Arrays.toString(results));
    }
}
