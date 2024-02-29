package tt;

import java.util.*;

public class min_product_sum {

    public long solution(int[] nums){
        Arrays.sort(nums);
        int n = nums.length;
        int[] new_arr = new int[n];

        int i = 0, j = n-1, p1 = 0, p2 = n -1;
        //p1, p2 for the new array;
        while(p1 <= p2){
            new_arr[p1++] = nums[j--]; // left
            if(p1 <= p2) new_arr[p2--] = nums[j--]; //right
            if(p1 <= p2) new_arr[p1++] = nums[i++];
            if(p1 <= p2) new_arr[p2--] = nums[i++];
        }
        long ans = 0;
        for(int k = 0; k < n -1; k++){
           ans += (long)(new_arr[k]*new_arr[k+1]);
        }
        
        return ans;
    }

    public static void main(String[] args){
        min_product_sum concaveArray = new min_product_sum();
        long ans = concaveArray.solution(new int[]{1,10,2,7,10,6,6});
        System.out.println( "the ans is: " + ans);
    }
}
