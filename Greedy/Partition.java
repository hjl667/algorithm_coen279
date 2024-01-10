package Greedy;
import java.util.*;

// leetcode 3003
//this greedy approach does not solve all cases

public class Partition {
    public int maxPartitionsAfterOperations(String s, int k) {
        // greedy + sliding windows
        boolean change = true;
        int partition = 0;
        int right = 0;
        HashSet<Character> set = new HashSet<>();
        int idx = -1;

        while(right < s.length()){

            while(right < s.length() && set.size() <= k ){

                if(set.size() == k){
                    if(set.contains(s.charAt(right)) && change){
                        change = false;
                        idx = right;
                        break;
                    }else if(!set.contains(s.charAt(right))){
                        break;
                    }else{
                        set.add(s.charAt(right));
                    }
                }else{
                    if(right == idx){
                        set.add('?');
                    }else{
                        set.add(s.charAt(right));
                    }
                }
                right++;
            }

            partition += 1;
            set.clear();
        }
        return partition;
    }

    public static void main(String[] args){
        String s = "xxyz";
        int k = 1;
        Partition p = new Partition();
        int output = p.maxPartitionsAfterOperations(s,k);
        System.out.println(output);
    }
}
