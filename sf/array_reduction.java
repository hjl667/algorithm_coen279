package sf;

import java.util.HashMap;
import java.util.*;

public class array_reduction {

    public static String solution(int[] nums){
        String ans = "";
        TreeSet<Integer> tr = new TreeSet<>();
        for(int i = 0; i < 100; i++) tr.add(i);
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++) map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);

        int left = 0, right = 0;
        tr.remove(nums[0]);
        map.put(nums[0],map.get(nums[0]) - 1);
        while(right < nums.length) {
            while (right < nums.length && map.containsKey(tr.first())) {
                map.put(nums[right], map.get(nums[right] - 1));
                tr.remove(nums[right]);
                right++;
            }
            ans += ("" + tr.first());
            if(right < nums.length){
                map.put(nums[right], map.get(nums[right] - 1));
                while(left != right) {
                    tr.add(nums[left]);
                    left++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args){
        String s = solution(new int[]{0,1,1,0});
        System.out.println( "the max string is: " + s);
    }
}
