package tt;

import java.util.*;

public class min_product_sum {

    public long solution(int[] nums){
        Arrays.sort(nums);
        int n = nums.length;
        int left = 0;
        int right = n-1;
        long ans = 0;
        List<Integer> left_list = new ArrayList<>();
        List<Integer> right_list = new ArrayList<>();
        while(left < right){
            if(right - left + 1 >= 4){
                left_list.add(nums[right]);
                left_list.add(nums[left]);
                left++;
                right--;
                right_list.add(nums[right]);
                right_list.add(nums[left]);
                left++;
                right--;
            }else if(right - left + 1 == 3 || right - left + 1 == 2){
                left_list.add(nums[right]);
                right--;
                right_list.add(nums[right]);
                left++;
            }
        }

        if(left == right){
            left_list.add(nums[right]);
        }

        int len1 = left_list.size();
        int len2 = right_list.size();
        int[] new_arr = new int[n];
        for(int i = 0; i < len1; i++){
            new_arr[i] = left_list.get(i);
            System.out.println(new_arr[i]);
        }

        for(int i = 0; i < len2; i++){
            new_arr[i + len1] = right_list.get(len2 - 1 - i);
            System.out.println(new_arr[i + len1]);
        }

        for(int i = 0; i < new_arr.length - 1; i++){
            ans += (long)new_arr[i]*new_arr[i+1];
        }
        return ans;
    }

    public static void main(String[] args){
        min_product_sum concaveArray = new min_product_sum();
        long ans = concaveArray.solution(new int[]{1,10,2,7,10,6,6});
        System.out.println( "the ans is: " + ans);
    }
}
