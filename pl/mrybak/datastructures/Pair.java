package pl.mrybak.datastructures;

public class Pair<F, S extends Comparable<S>> {
    public F first;
    public S second;

    public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }
}