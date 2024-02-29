package tt;
import com.sun.source.tree.Tree;

import java.util.*;

public class Min_inversions {
    private int max_bit;
    private int[][] x_table;

    public int findInversionPairs(List<int[]> left_list, List<int[]> right_list){

        int left = 0, right = 0, cnt = 0;
        int l_size = left_list.size(), r_size = right_list.size();
        while(left < l_size && right < r_size){
            while(right_list.get(right)[0] > left_list.get(left)[0]){
                left++;
            }
            cnt += (l_size - left);
            right++;
        }
        return cnt;

    }

    public void dfs(List<int[]> list, int digit){
        if(digit < 0) return;
        List<int[]> left_list = new ArrayList<>();
        List<int[]> right_list = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            int key = list.get(i)[0];
            int num = list.get(i)[1];
            if(((1 << digit)&num) > 0){
                right_list.add(new int[]{key, num});
            }else{
                left_list.add(new int[]{key, num});
            }
        }
        int total_pairs = left_list.size()*right_list.size();
        int inversion_pairs = findInversionPairs(left_list, right_list);

        x_table[digit][0] += inversion_pairs;
        x_table[digit][1] += (total_pairs - inversion_pairs);
        dfs(left_list, digit-1);
        dfs(right_list, digit-1);

    }

    public int solution(int[] nums){
        int max = 0;
        TreeMap<Integer, Integer> tree = new TreeMap<>();
        for(int i = 0; i < nums.length;i++){
            max = Math.max(max, nums[i]);
            tree.put(i, nums[i]);
        }
        List<int[]> list = new ArrayList<>();
        for(Integer key: tree.keySet()){
            list.add(new int[]{key, tree.get(key)});
        }
        int msb = Integer.highestOneBit(max);
        while(((1 << this.max_bit)&msb) == 0){
            this.max_bit += 1;
        }
        x_table = new int[max_bit + 1][2];
        dfs(list, max_bit);

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
