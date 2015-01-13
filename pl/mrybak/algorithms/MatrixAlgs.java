package pl.mrybak.algorithms;

public class MatrixAlgs {
    public static int maxPath(int[][] m) {
        // List<String> result = new ArrayList<>();

        int rows = m.length;
        if (rows == 0) { return 0; }
        int cols = m[0].length;

        int[][] sums = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sums[i][j] = getSum(i, j, m, sums);
            }
        }

        return sums[rows-1][cols-1];
    }

    private static int getSum(int i, int j, int[][] m, int[][] sums) {
        if (i == 0 && j == 0) return m[i][j];
        if (i == 0) return m[i][j] + sums[i][j-1];
        if (j == 0) return m[i][j] + sums[i-1][j];
        return m[i][j] + Math.max(sums[i-1][j], sums[i][j-1]);
    }

    /**
     * Auxiliary function to understand Kadane's algorithm better
     */
    public static int maxContinuousSum(int[] arr) {
        int maxSumSoFar = 0, maxSumEndingHere = 0;

        for (int elem : arr) {
            maxSumEndingHere = Math.max(maxSumEndingHere + elem, 0);
            if (maxSumEndingHere > maxSumSoFar) {
                maxSumSoFar = maxSumEndingHere;
            }
        }

        return maxSumSoFar;
    }
}
