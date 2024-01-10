package DP;

import java.util.HashMap;
import java.util.HashSet;


// this solution is from tojuna's post
// bitmask + recursive DP

public class MaxPartition {

    private HashMap<Long, Integer> cache;
    private String s;
    private int k;

    public int maxPartitionsAfterOperations(String s, int k) {
        this.cache = new HashMap<>();
        this.s = s;
        this.k = k;
        return dp(0, 0, true) + 1;
    }

    private int dp(int index, int currentSet, boolean canChange) {
        long key = ((long) index << 27)
                | ((long) currentSet << 1)
                | (canChange ? 1 : 0);

        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        if (index == s.length()) {
            return 0;
        }

        int characterIndex = s.charAt(index) - 'a';
        int currentSetUpdated = currentSet | (1 << characterIndex);
        int distinctCount = Integer.bitCount(currentSetUpdated);

        int res;
        if (distinctCount > k) {
            res = 1 + dp(index + 1, 1 << characterIndex, canChange);
        } else {
            res = dp(index + 1, currentSetUpdated, canChange);
        }

        if (canChange) {
            for (int newCharIndex = 0; newCharIndex < 26; newCharIndex++) {
                int newSet = currentSet | (1 << newCharIndex);
                int newDistinctCount = Integer.bitCount(newSet);

                if (newDistinctCount > k) {
                    res = Math.max(res, 1 + dp(index + 1, 1 << newCharIndex, false));
                } else {
                    res = Math.max(res, dp(index + 1, newSet, false));
                }
            }
        }

        cache.put(key, res);
        return res;
    }


    public static void main(String[] args){
        String s = "xxyz";
        int k = 1;
        Greedy.Partition p = new Greedy.Partition();
        int output = p.maxPartitionsAfterOperations(s,k);
        System.out.println(output);
    }
}
