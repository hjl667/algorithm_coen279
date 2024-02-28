package tt;

import java.util.*;

public class Algorithm {
    public static int[] solution(int[] data){
        int n = data.length;
        int[] ans = new int[n];

        HashMap<Integer, Integer> map01 = new HashMap<>();
        TreeMap<Integer, Integer> map02 = new TreeMap<>();

        for(int i = 0; i < data.length; i++){
            int curr = 0;
            if(map01.containsKey(data[i])){
                curr = i - map01.get(data[i]) + 1;
            }else{
                curr = i + 1;
            }
            map01.put(data[i], i);
            if(!map02.containsKey(data[i])){
                map02.put(data[i], curr);
            }else{
                map02.put(data[i], Math.max(curr, map02.get(data[i])));
            }
        }
        for(Integer num : map01.keySet()){
            int curr = n - map01.get(num);
            map02.put(num, Math.max(curr, map02.get(num)));
        }
        System.out.println(map02.get(3));
        int idx = n - 1;
        for(Integer num : map02.keySet()){
            int curr_idx = map02.get(num) - 1;
            while(idx >= curr_idx){
                ans[idx] = num;
                idx--;
            }
        }
        for(int i = 0; i <= idx; i++){
            ans[i] = -1;
        }
        return ans;
    }

    public static void main(String[] args){
        int[] ans = solution(new int[]{4,3,3,4,2});
        for(Integer num : ans){
            System.out.println( "the ans is: " + num);
        }

    }
}
