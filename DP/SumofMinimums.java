//1D dp approach
//subproblem: dp[i] is the sum of minimums of subarrays ending at i
//base cases: dp[0] = nums[0]
//recurrence: dp[i] = dp[i-1] + nums[i] if nums[i] >= nums[i-1]
//                  = dp[j] +  (i-j)*nums[i], if nums[i] < nums[i-1], j is the index of the first smaller element to the left.
//finall sum up all dp[i] and return the sum

//we could also use a monotonic stack to find the first smaller element to the left, which will be faster, but simply traversing the array works just fine

//the following is similar subarray problems that can be solved with DP
//53. Maximum Subarray
//152. Maximum Product Subarray
//2104. Sum of Subarray Ranges

class Solution {
	public int sumSubarrayMins(int[] arr) {
		int MOD = 1000000007;
		int n = arr.length;
		
		int[] dp = new int[n];
		dp[0] = arr[0];
		long sumOfMinimums = dp[0];
		
		for (int i = 1; i < n; i++) {
			if (arr[i] >= arr[i - 1]) {
				dp[i] = dp[i - 1] + arr[i];
			} else {
				int j = i - 1;
				while (j >= 0 && arr[j] > arr[i]) {
					j--;
				}
				dp[i] = (j >= 0) ? dp[j] + (i - j) * arr[i] : (i + 1) * arr[i];
			}
			
			dp[i] %= MOD;
			sumOfMinimums += dp[i];
			sumOfMinimums %= MOD;
		}
		
		return (int) sumOfMinimums;
	}
}

