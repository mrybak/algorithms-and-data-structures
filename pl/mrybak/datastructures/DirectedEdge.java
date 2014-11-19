package pl.mrybak.datastructures;

public class DirectedEdge {
    private int weight;
    private int from;
    private int to;

    public DirectedEdge(int from, int to, int weight) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }

    public int from() {
        return from;
    }

    ;

    public int to() {
        return to;
    }

    ;

    public int weight() {
        return weight;
    }

    ;
}