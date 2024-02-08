package sf;
import java.util.*;

public class unequal_elements {
    public static int solution(int[] nums, int k){
        //dp[i][k] is the max length including num i having k adjacent different elements
        //this does not cover cases when the last element is not choosing
        //so we add an extra negative integer at the end of the array

        //dp[0][0] = 1; dp[i][k] == 0 meaning non-existent
        //dp[i][k] -> case01: nums[i] == nums[i-1], then dp[i][k] = dp[i-1][k] + 1
        //         -> case02: nums[i] != nums[i-1], then case01: increasing k, dp[i][k] = dp[i-1][k-1] + 1
        //                                               case02: find the nearest nums[j] == nums[i] to the left
        //                                                       dp[i][k] = dp[j][k] + 1
        //                                          dp[i][k] = Max(case01, case02)

        int[][] dp = new int[nums.length][k+1];
        for(int i = 0; i < nums.length; i++) dp[i][0] = 1;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(nums[0], 0);
        for(int i = 1; i < nums.length; i++){
            for(int j = 0; j <= k; j++){
                if(nums[i] == nums[i-1]){
                    dp[i][j] = dp[i-1][j] + 1;
                }else{
                    if(j != 0){
                        dp[i][j] = dp[i-1][j-1] == 0 ? 0 : dp[i-1][j-1] + 1;
                    }
                    if(map.containsKey(nums[i])){
                        if(dp[map.get(nums[i])][j] != 0) dp[i][j] = Math.max(dp[i][j], dp[map.get(nums[i])][j] + 1);
                    }
                }
                if(dp[i][j] != 0) System.out.println("i: " + i + " " + "j: " + j + " " + "max: " + dp[i][j]);
            }
            map.put(nums[i],i);
        }
        int max = 0;
        for(int i = 0; i < dp.length; i++) max = Math.max(max, dp[i][k]);
        return max;
    }

    public static void main(String[] args){
        int[] arr = new int[] {1,1,2,3,3,3,3,2,1};
        int res = solution(arr, 1);
        System.out.println("longest length " + res);
    }
}
