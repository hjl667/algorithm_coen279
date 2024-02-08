package sf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

//
// 状态转移方程
// dp[i][j] means forming target string until index i and only using source string until index j
// dp[0][0] = 0;
// dp[i][j] = dp[i][j-1] + count[target[i]][j] * dp[i-1][j-1];
// case 01 : not using j column;
// case 02 : using j column * dp[i -1][j - 1]


public class string_formation_02 {
    public static int solution(String[] arr, String target){
        int max_length = 0;
        for(int i = 0; i < arr.length; i++) max_length = Math.max(max_length, arr[i].length());
        int[][] count = new int[126][max_length];
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[i].length(); j++){
                count[arr[i].charAt(j) - '0'][j]++;
            }
        }
        int[][] dp = new int[target.length()+1][max_length+1];
        Arrays.fill(dp[0], 1);
        for(int i = 1; i < dp.length; i++){
            for(int j = 1; j < dp[0].length; j++){
                dp[i][j] = dp[i][j-1] + (dp[i-1][j-1] * count[target.charAt(i-1) - '0'][j-1]);
            }
        }
        return dp[target.length()][max_length];
    }

    public static void main(String[] args){
        String[] arr = new String[] {"adc", "aec", "efg"};
        int res = solution(arr, "ac");
        System.out.println("number of ways: " + res);
    }
}
