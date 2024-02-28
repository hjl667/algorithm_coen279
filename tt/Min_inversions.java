package tt;
import com.sun.source.tree.Tree;

import java.util.*;

public class Min_inversions {
    private int max_bit;
    //x_table[i][0]: number of inversion without flip
    private int[][] x_table;

    public static int bs(TreeMap<Integer, Integer> tree, int target){
        Integer ceilingKey = tree.ceilingKey(target);

        if(ceilingKey == null){
            return 0;
        }
        TreeMap<Integer, Integer> subMap = new TreeMap<>(tree.tailMap(ceilingKey));
        return subMap.size();
    }

    public void dfs(TreeMap<Integer, Integer> tree, int digit){
        if(digit < 0) return;
        TreeMap<Integer, Integer> left_tree = new TreeMap<>();
        TreeMap<Integer, Integer> right_tree = new TreeMap<>();
        for(Integer key : tree.keySet()){
            int num = tree.get(key);
            if(((1 << digit)&num) > 0){
                right_tree.put(key, num);
            }else{
                left_tree.put(key, num);
            }
        }
        int total_pairs = left_tree.size()*right_tree.size();
        int inversion_pairs = 0;
        //for each index in the right_tree, find the number of index in the left tree smaller or equal to the index
        //i.e. calculate the number inversion paris at the current segment
        for(Integer key : right_tree.keySet()){
            inversion_pairs += bs(left_tree, key);
        }
        x_table[digit][0] += inversion_pairs;
        x_table[digit][1] += (total_pairs - inversion_pairs);
        dfs(left_tree, digit-1);
        dfs(right_tree, digit-1);

    }

    public int solution(int[] nums){
        int max = 0;
        TreeMap<Integer, Integer> tree = new TreeMap<>();
        for(int i = 0; i < nums.length;i++){
            max = Math.max(max, nums[i]);
            tree.put(i, nums[i]);
        }
        int msb = Integer.highestOneBit(max);
        while(((1 << this.max_bit)&msb) == 0){
            this.max_bit += 1;
        }
        x_table = new int[max_bit + 1][2];
        dfs(tree, max_bit);

        int x = 0;
        for(int i = max_bit; i >= 0; i--){
            if(x_table[i][0] > x_table[i][1]) x += 1;
            x = x << 1;
        }
        x = x >> 1;
        return x;
    }

    public static void main(String[] args){
        Min_inversions min = new Min_inversions();
        int ans = min.solution(new int[]{8,5,2});
        System.out.println( "the ans is: " + ans);
    }

}
