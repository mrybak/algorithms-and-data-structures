package pl.mrybak.algorithms;

import java.util.HashMap;
import java.util.Map;

/**
 * Data structure & algorithms for representing dynamic connectivity.
 * Nodes are counted from 1.
 * The structure is backed by HashMap so we can not only union, but also add nodes dynamically.
 */
public class FindUnion {
    private Map<Integer, Integer> elems; // key: elemId, value: setId

    public FindUnion(int n) {
        elems = new HashMap<>(n);
        for (int i = 0; i < n; i++) {
            elems.put(i, i);  // initialize: each elem is in its own set
        }
    }

    public void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        // TODO: for weighted union, override line below to determine which tree has lower height
        elems.put(rootA, rootB);
    }

    public boolean connected(int a, int b) {
        if (elems.get(a) == null || elems.get(b) == null) {
            return false;
        }

        return find(a) == find(b);
    }

    public int find(int n) {
        // TODO: for path compression, overwrite rootN during while loop below
        while (elems.get(n) != n) {
            n = elems.get(n);
        }
        return n;
    }

    public void addNode(int nodeNr) {
        if (elems.get(nodeNr) == null) {
            elems.put(nodeNr, nodeNr);
        }
    }
}
