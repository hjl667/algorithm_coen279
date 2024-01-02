//the subproblem is for a given i, j, the number of ways the first i elements can evaluate to j
//base cases: dp[0][nums[0]] = 1 dp[0][-nums[0]]
//recurrence: dp[i][j] = dp[i-1][j-nums[i]] + 1 if dp[i-1][j-nums[i]] != 0
//              dp[i][j] += dp[i-1][j + nums[i]] + 1 if dp[i-1][j+nums[i]] !=0;
//return dp[nums.length - 1][target]
//add an offset to avoid negative indices 

public class TargetSum {
    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for (int num : nums) sum += num;
        if (S > sum || S < -sum) return 0;
        
        int offset = sum;  
        int[][] dp = new int[nums.length][2 * sum + 1];
        
        //base cases
        dp[0][nums[0] + offset] = 1;
        dp[0][-nums[0] + offset] += 1; 
        
        //recurrence
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < 2 * sum + 1; j++) {
                if (dp[i - 1][j] != 0) {
                    dp[i][j + nums[i]] += dp[i - 1][j];
                    dp[i][j - nums[i]] += dp[i - 1][j];
                }
            }
        }
        return dp[nums.length - 1][S + offset];
    }
}
    
    