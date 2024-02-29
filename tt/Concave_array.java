package tt;

import java.util.*;

public class Concave_array {

    public int solution(int[] nums){
        int n = nums.length;
        int[] new_arr = new int[n];
        for(int i = 0; i < nums.length; i++){
            new_arr[nums[i] - 1] = i;
        }
        Arrays.sort(nums);
        int left = 0, right = n - 1, ans = 0, added = 0;
        for(int i = 0; i < nums.length;i++){
            int curr_idx = new_arr[nums[i] - 1];
            added++;
            if(curr_idx == left) left++;
            if(curr_idx == right) right--;
            if(left == right) break;
            int curr_max = added + 2 - left - (n-1-right);
            ans = Math.max(ans, curr_max);
        }
        return ans;
    }

    public static void main(String[] args){
        Concave_array concaveArray = new Concave_array();
        int ans = concaveArray.solution(new int[]{4,2,6,5,3,1});
        System.out.println( "the ans is: " + ans);
    }
}
