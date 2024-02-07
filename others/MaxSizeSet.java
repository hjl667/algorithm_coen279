package others;
import java.util.*;

// leetcode 3002 max size of a set after removals

public class MaxSizeSet {
    public int maximumSetSize(int[] nums1, int[] nums2) {
        int extra1 = 0;
        int extra2 = 0;
        int delete = nums1.length/2;

        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();

        for(int i = 0; i < nums1.length; i++){
            if(set1.contains(nums1[i])){
                extra1++;
            }else{
                set1.add(nums1[i]);
            }

            if(set2.contains(nums2[i])){
                extra2++;
            }else{
                set2.add(nums2[i]);
            }
        }

        int delete1 = Math.max(0, (delete - extra1));
        int delete2 = Math.max(0, (delete - extra2));

        // if(delete1 == 0 && delete2 == 0) return set1.size() + set2.size();

        HashSet<Integer> commonSet = new HashSet<>();

        for(Integer num: set1){
            if(set2.contains(num)){
                commonSet.add(num);
            }
        }

        int remaining1 = commonSet.size() - delete1 ;
        int remaining2 = commonSet.size() - delete2;
        int remaining = 0;

        if((remaining1 >= 0 && remaining2 >= 0)
                && (remaining1 + remaining2 < commonSet.size())){
            remaining = remaining1 + remaining2;
        }else if(remaining1 + remaining2 >= commonSet.size()){
            remaining = commonSet.size();
        }else{
            remaining = remaining1 + remaining2;
        }

        return set1.size() + set2.size() - 2 * commonSet.size() + remaining;

    }


}
