package pl.mrybak.algorithms;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestMatrixAlgs {
    public static void main(String[] args) {

        System.out.println(MatrixAlgs.maxPath(
                new int[][]{
                        {6,2,3,5,7,8,1,2},
                        {5,4,9,1,4,2,6,5},
                        {4,5,6,3,7,5,0,1},
                        {1,0,2,7,4,6,3,9},
                        {5,1,9,8,7,0,0,3}
                }
        ));

        System.out.println(MatrixAlgs.maxContinuousSum(new int[]{-2,-3,3,4,1,-4,5,-2,0,0,-7,3,8}));
    }
}