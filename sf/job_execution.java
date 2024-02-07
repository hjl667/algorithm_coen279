package sf;

public class job_execution {
    public static int solution(int[] arr, int x, int y) {
        int l = 0;
        int r = 0;
        for (Integer num : arr) r = Math.max(r, (num + 1)/ y );

        while (l < r) {
            int mid = (l + r) / 2;
            if (valid(mid, arr, x, y)){
                r = mid;
            }else{
                l = mid + 1;
            }
        }
        return l;
    }

    public static boolean valid(int t, int[] arr, int x, int y){
        int xTime = 0;
        int extra = x - y;
        for(int i = 0; i < arr.length; i++){
            int temp = arr[i] - t*y;
            if(temp > 0) xTime += ((temp + 1)/extra);
        }

        return xTime <= t;
    }


    public static void main(String[] args){
        int[] arr= new int[] {3, 4, 1, 7, 6};
        int ans = solution(arr, 4, 2);
        System.out.println(ans);

    }
}
