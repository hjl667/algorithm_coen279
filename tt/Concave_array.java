package tt;

public class Concave_array {
    class Segment_tree{
        int n;
        int[] tree;

        public Segment_tree(int n){
            n = n;
            tree = new int[4*n];
        }

        void update(int index, int value, int node, int left, int right){
            if(left == right){
                tree[node] = value;
                return;
            }
            int mid = (left + right)/2;
            if(index <= mid){
                update(index, value, 2*node, left, mid);
            }else {
                update(index, value, 2*node + 1, mid + 1, right);
            }
            tree[node] = Math.max(tree[2*node], tree[2*node + 1]);
        }

        int query(int qleft, int qright, int node, int left, int right){
            if(qleft > right || qright < left) return 0;

            if(qleft <= left && qright >= right){
                return tree[node];
            }
            int mid = (left + right)/2;
            int left_max = query(qleft, qright, 2*node, left, mid);
            int right_max = query(qleft, qright, 2*node + 1, mid + 1, right);
            return Math.max(left_max, right_max);
        }
    }

    public int solution(int[] nums){
//        n^2 dp solution
//        int ans = 0;
//        int n = nums.length;
//        if(n < 3) return 0;
//        int[] dp = new int[n];
//        dp[0] = 1;
//        dp[1] = 2;
//        for(int i = 2; i < n; i++){
//            if(nums[i] > nums[i-1] && nums[i-2] > nums[i-1]){
//                dp[i] = 3;
//            }
//        }
//
//        for(int i = 2; i < n; i++){
//            int max = 0;
//            for(int j = i -1; j >= 0; j--){
//                if(nums[j] < nums[i]) max = Math.max(max, dp[j]);
//            }
//            dp[i] = Math.max(dp[i], max + 1);
//            ans = Math.max(ans, dp[i]);
//        }
        int n = nums.length;
        int ans = 0;
        Segment_tree tr = new Segment_tree(n);
        tr.update(nums[0], 1, 1,1, n);
        tr.update(nums[1], 2, 1,1, n);
        for(int i = 2; i < n; i++){

            int max = tr.query(1, nums[i] - 1, 1, 1, n);
            int curr_len = max + 1;
            ans = Math.max(ans, curr_len);
            tr.update(nums[i], curr_len, 1, 1, n);
        }
        return ans;
    }

    public static void main(String[] args){
        Concave_array concaveArray = new Concave_array();
        int ans = concaveArray.solution(new int[]{4,2,6,5,3,1});
        System.out.println( "the ans is: " + ans);
    }
}
