package sf;

public class efficient_cost {
    public static int solution(int[] nums, int threshold){
       // dp[i] means smallest cost until i
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for(int i = 1; i < nums.length; i++){
            //curr i as one partition
            dp[i] = dp[i-1] + nums[i];
            int max = nums[i];
            //bigger size as current partition [1:threshold]
            for(int j = i-1; j >= Math.max(0, i - threshold + 1); j--){
                max = Math.max(max, nums[j]);
                if(j != 0){
                    dp[i] = Math.min(dp[i], dp[j-1] + max);
                }else{
                    dp[i] = Math.min(dp[i], max);
                }
            }
            System.out.println(dp[i]);
        }
        return dp[nums.length - 1];
    }

    public static void main(String[] args){
        int[] arr= new int[] {1,3,4,5,2,6};
        int ans = solution(arr, 3);
        System.out.println(ans);
    }
}
