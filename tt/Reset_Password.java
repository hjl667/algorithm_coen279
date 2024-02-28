package tt;
import java.util.*;


public class Reset_Password {
    public static String bf_solution(String s){
        return "";
    }

    public static String solution(String s){
        int[] arr = new int[26];
        for(int i = 0; i < s.length(); i++){
            arr[s.charAt(i) - 'a']++;
        }
        int left = 0, right = 25;
        while(left < right){
            while(arr[left] % 2 == 0){
                left++;
            }
            while(arr[right] % 2 == 0){
                right--;
            }
            arr[left]++;
            arr[right]--;
        }
        StringBuilder sb = new StringBuilder();
        int odd_idx = -1;
        for(int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 != 0) {
                odd_idx = i;
            }
            for (int j = 0; j < arr[i] / 2; j++) {
                sb.append((char) (i + 'a'));
            }
        }
        String first_half = sb.toString();
        String second_half = sb.reverse().toString();
        if(odd_idx != -1){
            return first_half + (char) (odd_idx + 'a') + second_half;
        }else{
            return first_half + second_half;
        }
    }

    public static void main(String[] args){
        String s = solution("aebcd");
        System.out.println( "the ans is: " + s);
    }

}
