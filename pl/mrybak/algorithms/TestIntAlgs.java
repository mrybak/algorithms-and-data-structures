package pl.mrybak.algorithms;

public class TestIntAlgs {
    public static void main(String[] args) {
        IntegerAlgs ia = new IntegerAlgs(25);
        ia.printAsSums();

        System.out.println(IntegerAlgs.has3SummingToZero(new int[]{-2,4,1,1}));
        System.out.println(IntegerAlgs.has3SummingToZero(new int[]{-2,4,1,2}));
        System.out.println(IntegerAlgs.has3SummingToZero(new int[]{}));
        System.out.println(IntegerAlgs.has3SummingToZero(new int[]{1,5,3,7,-3,-3,-7,5,32,7,2,-4,-5,-2}));
    }
}