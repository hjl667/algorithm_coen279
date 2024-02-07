package sf;
import java.util.*;

public class string_formation_01 {
    public static int solution(String[] arr, String target){
        int max = 0;
        HashMap<Character, List<Integer>> map = new HashMap<>();
        for(String s : arr){
            max = Math.max(max, s.length());
            for(int i = 0; i < s.length(); i++){
                if(!map.containsKey(s.charAt(i))) map.put(s.charAt(i), new ArrayList<>());
                List<Integer> temp = map.get(s.charAt(i));
                temp.add(i);
                map.put(s.charAt(i), temp);
            }
        }
        //System.out.print();

        int[][] dp = new int[target.length()][max];
        //count how many  char x at each idx
        for(Integer idx : map.get(target.charAt(0))){
            dp[0][idx]++;
        }
        //sum up number of char no bigger than idx i
        int prefix_sum = 0;
        for(int i = 0; i < max; i++){
            dp[0][i] += prefix_sum;
            prefix_sum = dp[0][i];
        }

        for(int i = 1; i < target.length(); i++){
            for(Integer idx : map.get(target.charAt(i))){
                dp[i][idx]++;
            }
            //xy combinations by using y at idx j and x before idx j
            for(int j = 1; j < max; j++){
                dp[i][j] = dp[i-1][j-1]*dp[i][j];
            }
            prefix_sum = 0;
            //sum up number of xy combinations using y at idx no bigger than j
            for(int j = 1; j < max; j++) {
                dp[i][j] += prefix_sum;
                prefix_sum = dp[i][j];
            }
        }
        return dp[target.length()-1][max - 1];
    }

    public static void main(String[] args){
        String[] arr = new String[] {"adc", "aec", "efg"};
        int res = solution(arr, "ac");
        System.out.println("number of ways: " + res);
    }
}
