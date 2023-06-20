
//1D tabulation
//subproblem: dp[i] is the number of ways to decode for s[0,i-1],i.e, length of i
//base cases: dp[0] = 0
//            dp[1] = 1 if s[0] ≠ 0, else dp[0] = 0
//recurrence: dp[i] += dp[i-1] if the new digit ≠ 0
//            dp[i] += dp[i-2] if the last two digits ≥ 10 and ≤ 26
//return dp[s.length()]

public class decodeWays {
    public int numDecodings(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        
        int n = s.length();
        int[] dp = new int[n + 1]; 
        dp[0] = 1;
        dp[1] = s.charAt(0) != '0' ? 1 : 0; 
        
        for(int i = 2; i <= n; i++) {
            int oneDigit = Integer.parseInt(s.substring(i-1, i)); 
            int twoDigits = Integer.parseInt(s.substring(i-2, i)); 
            
            if(oneDigit >= 1) {
                dp[i] += dp[i-1];  
            }
            if(twoDigits >= 10 && twoDigits <= 26) {
                dp[i] += dp[i-2];
            }
        }
        return dp[n];
    }
}
