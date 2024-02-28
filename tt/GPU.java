package tt;
import java.util.*;

public class GPU {
    public static int solution(int[] cost, int[] compatible1, int[] compatible2, int min_compatible){
        PriorityQueue<Integer> pq01 = new PriorityQueue<>();
        PriorityQueue<Integer> pq02 = new PriorityQueue<>();
        PriorityQueue<Integer> pq03 = new PriorityQueue<>();
        for(int i = 0; i < cost.length; i++){
            if(compatible1[i] == 1 && compatible2[i] == 1) pq03.add(cost[i]);
            if(compatible1[i] == 1 && compatible2[i] != 1) pq01.add(cost[i]);
            if(compatible1[i] != 1 && compatible2[i] == 1) pq02.add(cost[i]);
        }
        int ans = 0;
        int cnt = 0;
        while(cnt < min_compatible){
            if(!pq01.isEmpty() || !pq02.isEmpty() || !pq03.isEmpty()){
                if(!pq01.isEmpty() && !pq02.isEmpty() && !pq03.isEmpty()) {
                    if(pq03.peek() <= (pq01.peek() + pq02.peek())){
                        ans += pq03.poll();
                    }else{
                        ans += pq01.poll();
                        ans += pq02.poll();
                    }
                    cnt++;
                }else if(!pq03.isEmpty() && (pq01.isEmpty()||pq02.isEmpty())){
                    ans += pq03.poll();
                    cnt++;
                }else if(pq03.isEmpty() && !pq01.isEmpty() && !pq02.isEmpty()){
                    ans += pq01.poll();
                    ans += pq02.poll();
                    cnt++;
                }else{
                    return -1;
                }
            }else {
                return -1;
            }
        }
        return ans;
    }
    public static void main(String[] args){
        int ans = solution(new int[]{2,4,6,5}, new int[]{1,1,1,0}, new int[]{0,0,1,1}, 2);
        System.out.println( "the ans is: " + ans);
    }
}
