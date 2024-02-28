package tt;
import java.util.*;

public class Channel_Rating {
    // a n^2 solution using prefix
    public static int solution(int[] nums){
        int ans = 0;
        int n = nums.length;
        int[][] prefix_sum = new int[32][n];
        for(int i = 0; i < 32; i++){
            if((nums[0] & 1<<i) > 0) prefix_sum[i][0] = 1;
        }

        for(int i = 0; i < 32; i++){
            for(int j = 1; j < nums.length; j++){
                if((nums[j] & 1 <<j) > 0){
                    prefix_sum[i][j] = prefix_sum[i][j-1] + 1;
                }else{
                    prefix_sum[i][j] = prefix_sum[i][j-1];
                }
            }
        }

        for(int i = 2; i < nums.length; i++){
            for(int j = i - 2; j >= 0; j--){
                boolean valid = true;
                for(int k = 0; k < 32; k++){
                    int cnt_j = j == 0? prefix_sum[k][j] : prefix_sum[k][j] - prefix_sum[k][j-1];
                    int left_check = cnt_j%2;
                    int middle_check = (prefix_sum[k][i-1] - prefix_sum[k][j])%2;
                    if(left_check != middle_check) valid = false;
                }
                if(valid) ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args){
        int ans = solution(new int[]{0,3,6,5});
        System.out.println( "the ans is: " + ans);
    }

}
