package tt;
import java.util.*;

public class message {
    public static String solution(List<String> message){
        int n = message.size();
        int k = message.get(0).length();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < k/2; i++){
            List<Character> list = new ArrayList<>();
            for(int j = 0; j < n; j++){
                list.add(message.get(j).charAt(i));
                list.add(message.get(j).charAt(k-1-i));
            }
            Collections.sort(list);
            sb.append(list.get((k + 1)/2 - 1));
        }
        String first_half = sb.toString();
        String second_half = sb.reverse().toString();
        if(k%2 != 0){
            List<Character> list = new ArrayList<>();
            for(int j = 0; j < n; j++){
                list.add(message.get(j).charAt(k/2));
            }
            Collections.sort(list);
            return first_half + list.get(k/2) + second_half;
        }else{
             return first_half + second_half;
        }
    }

    public static void main(String[] args){
        ArrayList<String> list = new ArrayList<>();
        list.add("aba");
        list.add("cbd");
        String ans = solution(list);
        System.out.println( "the ans is: " + ans);
    }
}
