        //this is leetcode 1202. Smallest String With Swaps

        //algorithm:
        //we model this as a graph problem
        //each character is a vertex and each pair is an undirected edge
        //by graph theory, we can assign characters to any  in each connected component
        //so we sort each connected component lexicographically
        //and rebuild the graph

        //implementation
        //we use union find to build connected components by iterate over the string and recursively finding each character's root.
        //initiate a map to match root with its priorityqueue
        //initiate a priorityqueue for every unique root and to have the lexicographically smallest character at the front
        //to rebuild the graph, we iterate over the string again to find the root of each index add the front element of its corresponding priorityqueue.  

import java.util.*;

public class smallestString {
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int n = s.length();
        UnionFind uf = new UnionFind(n);

        // Union operations for each pair
        for (List<Integer> pair : pairs) {
            uf.union(pair.get(0), pair.get(1));
        }

        // Group characters by parent
        Map<Integer, PriorityQueue<Character>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int root = uf.find(i);
            map.putIfAbsent(root, new PriorityQueue<>());
            map.get(root).add(s.charAt(i));
        }

        // Rebuild the string
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int root = uf.find(i);
            sb.append(map.get(root).poll());
        }

        return sb.toString();
    }

    class UnionFind {
        private int[] parent;

        public UnionFind(int size) {
            parent = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                parent[rootX] = rootY;
            }
        }
    }
}