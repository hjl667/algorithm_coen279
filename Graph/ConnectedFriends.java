

//this is leetcode 1101. The Earliest Moment When Everyone Become Friends 
//this can be modeled as a graph problems
//each person is a vertex, becoming friends is forming an undirected edge
//by the definition of acquaintance, the problem is asking for the timestamp when every vertex is connected, i.e no isolated vertex

//to check connections after adding a new edge, we utilize union find 
//sort logs by timestamp 
//add a new connection to the graph with every new timestamp
//check if with the new connection, all the people can be connected
//return the timestamp if that's the case
//return -1 if that does not happen

import java.util.*;
public class ConnectedFriends {
    private int[] parent;

    public int earliestAcq(int[][] logs, int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        Arrays.sort(logs, (a, b) -> a[0] - b[0]);

        for (int[] log : logs) {
            union(log[1], log[2]);
            if (connected())
                return log[0];
        }

        return -1;
    }

    private void union(int a, int b) {
        parent[find(a)] = find(b);
    }

    private int find(int x) {
        if (x != parent[x])
            parent[x] = find(parent[x]);

        return parent[x];
    }

    private boolean connected() {
        int count = 0;
        for (int i = 0; i < parent.length; i++) {
            if (i == parent[i])
                count++;
            if (count > 1)
                return false;
        }

        return true;
    }
}