package tt;
import java.util.*;

public class CPU {
    public static int solution(int[] priority, int x, int y){
        int n = priority.length;
        Arrays.sort(priority);
        int[] arr = new int[x];
        for(int i = 0; i < n; i++){
            if(n - 1 - i >= 0){
                arr[i] = priority[n - 1 - i];
            }else{
                arr[i] = 0;
            }
        }
        int ans = 0;
        for(int i = 0; i < y; i++){
            ans += arr[i%x];
        }
        return ans;
    }

    public static void main(String[] args){
        int ans = solution(new int[]{3,1,2}, 5, 7);
        System.out.println( "the ans is: " + ans);
    }
}
