package pl.mrybak.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

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

    /**
     * Return subarray with maximum sum;
     * We assume matrix is nonempty and no column and no row consists of negative integers only
     *
     * TODO: comments & clarification
     */
    public static int[][] maxSumSubarray(int[][] m) {
        int rows = m.length;
        int cols = m[0].length;

        int[][] maximums = new int[cols][cols];
        int[][] topRow = new int[cols][cols];
        int[][] bottomRow = new int[cols][cols];


        for (int left = 0; left < cols; left++) {
            int[] rowSum = new int[rows];
            for (int right = left; right < cols; right++) {
                // for every possible left-right column combination, compute row sum for every row:
                for (int k = 0; k < rows; k++) {
                     rowSum[k] += m[k][right];
                }
                // and apply kadane's algorithm to it, saving top/bottom row index in the meantime:
                int maxSumEndingHere = 0, maxSumSoFar = 0;

                for (int i = 0; i < rowSum.length; i++) {
                    int elem = rowSum[i];
                    if (maxSumEndingHere + elem < 0) {
                        topRow[left][right] = i+1;
                    }
                    maxSumEndingHere = Math.max(maxSumEndingHere + elem, 0);
                    if (maxSumEndingHere > maxSumSoFar) {
                        bottomRow[left][right] = i;
                        maxSumSoFar = maxSumEndingHere;
                    }
                }
                maximums[left][right] = maxSumSoFar;
            }
        }

        int maxSum = 0, fromX = 0, fromY = 0, toX = 0, toY = 0;
        for (int i = 0; i < cols; i++) {
            for (int j = i; j < cols; j++) {
                int sum = maximums[i][j];
                if (sum > maxSum) {
                    maxSum = sum;
                    fromX = i;
                    fromY = topRow[i][j];
                    toX = j;
                    toY = bottomRow[i][j];
                }
            }
        }

        int[][] result = new int[toY - fromY + 1][toX - fromX + 1];
        for (int i = fromY; i <= toY ; i++) {
            for (int j = fromX; j < toX; j++) {
                result[i-fromY][j-fromX] = m[i][j];
            }
        }

        return result;
    }
}
