import java.util.*;

class PacificAtlanticOcean {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        //set up two queues to represent pacific ocean and atlantic ocean
        Queue<int[]> pacific = new LinkedList<>();
        Queue<int[]> atlantic = new LinkedList<>();
        
        for(int i = 0; i < heights.length; i++){
            pacific.offer(new int[]{i,0});
            atlantic.offer(new int[]{i,heights[0].length - 1});
        }
        
        for(int j = 0; j < heights[0].length; j++){
            pacific.offer(new int[]{0,j});
            atlantic.offer(new int[]{heights.length - 1, j});
        }

        //derive cells that reachable from both ocean
        boolean[][] pacificReachable = new boolean[heights.length][heights[0].length];
        boolean[][] atlanticReachable = new boolean[heights.length][heights[0].length];
        bfs(heights, pacific, pacificReachable);
        bfs(heights, atlantic, atlanticReachable);
        List<List<Integer>> ans = new ArrayList<>();
        for(int i = 0; i < heights.length;i++){
            for(int j = 0; j < heights[0].length;j++){
                if(pacificReachable[i][j] == true && atlanticReachable[i][j] == true){
                    ans.add(List.of(i, j));
                }
            }
        }
        return ans;         
    }

    public void bfs(int[][]heights, Queue<int[]> ocean, boolean[][] reacheable){
        int[][] dirs = new int[][] {{0,1},{1,0},{-1,0},{0,-1}};
        while(!ocean.isEmpty()){
            int[] curr = ocean.poll();
            reacheable[curr[0]][curr[1]] = true;
            for(int[] dir : dirs){
            int x = curr[0] + dir[0];
            int y = curr[1] + dir[1];
            if(x >= heights.length || y >= heights[0].length || x < 0 || y < 0){
                continue;
            }
            if(reacheable[x][y]){
                continue; 
            }
            if(heights[x][y] >= heights[curr[0]][curr[1]]){
                ocean.offer(new int[]{x,y});
            }
            }    
        }
    }
}