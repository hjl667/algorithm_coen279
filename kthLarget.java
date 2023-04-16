import java.util.Random;

//String[] array contains strings representing integer
//the only difference is to create a customized comparator

class kthLargest{
    String [] nums;
    public void swap(int a, int b){
        String temp = this.nums[a];
        this.nums[a] = this.nums[b];
        this.nums[b] = temp;
    }

    public int compareTwoStrings(String s1, String s2) {
        return s1.length() == s2.length() ? s1.compareTo(s2) : Integer.compare(s1.length(), s2.length());
    }

    public int partition(int left, int right, int pivot_index){
        String pivot = this.nums[pivot_index];
        swap(pivot_index, right);
        int store_index = left;

        for(int i = left; i <= right; i++){
            if(compareTwoStrings(this.nums[i], pivot) < 0){
                swap(store_index, i);
                store_index++;
            }
        }
        swap(store_index, right);
        return store_index;
    }

    public String quickselect(int left, int right, int k_smallest){
        if(left == right){
            return this.nums[left];
        }
        Random random_num = new Random();
        int pivot_index = left + random_num.nextInt(right - left);
        pivot_index = partition(left, right, pivot_index);
        if(k_smallest == pivot_index) return this.nums[k_smallest];
        else if(k_smallest < pivot_index)
            return quickselect(left, pivot_index - 1, k_smallest);
        return quickselect(pivot_index + 1, right, k_smallest);
    }

    public String kthLargestNumber(String[] nums, int k) {
        this.nums = nums;
        int size = nums.length;
        return quickselect(0, size - 1, size - k);
    }
}