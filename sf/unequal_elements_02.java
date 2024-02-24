package sf;
import java.util.*;

public class unequal_elements_02 {
    public static int solution(int[] nums, int k){
        // prefix[i][j] meaning the count of i from index 0 to j inclusive
        int[][] prefix = new int[120][nums.length];

        for(int i = 0; i < nums.length; i++) prefix[nums[i]][i]++;
        int[][] dp = new int[nums.length][k+1];
        int[][] gx = new int[120][k+1];

        for(int i = 0; i <= k; i++) dp[0][i] = 1;
        int max_len = 1;
        int curr_len = 1;

        for(int i = 1; i < dp.length; i++){
            if(nums[i] == nums[i-1]){
                curr_len++;
            }else{
                curr_len = 1;
            }
            max_len = Math.max(max_len, curr_len);
            dp[i][0] = max_len;
            gx[nums[i]][0] =  Math.max(gx[nums[i]][0], curr_len);
        }

        for(int i = 1; i < dp.length; i++){
            for(int k_idx = 1; k_idx <= k; k_idx++){

                dp[i][k_idx] = dp[i-1][k_idx];
                gx[nums[i]][k_idx] = Math.max(gx[nums[i]][k_idx], dp[i][k_idx - 1] - prefix[nums[i]][i]);
                dp[i][k_idx] = Math.max(gx[nums[i]][k_idx-1] + prefix[nums[i]][i], dp[i][k_idx]);

                //3D dp
                //for(int j = 1; j < i; j++){
                //    int count = prefix[nums[i]][i] - prefix[nums[i]][j-1];
                //    dp[i][k_idx] = Math.max(dp[i][k_idx], dp[j][k_idx - 1] + count);
                //}
            }
        }

        return dp[nums.length -1][k];
    }

    public static void main(String[] args){

        int[] arr = new int[] {1,1,2,3,3,2,2,2,2,1};
        int res = solution(arr, 1);
        System.out.println("longest length " + res);

    }
}
