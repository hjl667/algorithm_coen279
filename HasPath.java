class HasPath {
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {    
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        return dfs(maze, start, destination, visited);
    }
    
    public boolean dfs(int[][] maze, int[]start, int[] destination, boolean[][] visited){
        //check if desination is reached and it's rollback
        if(visited[start[0]][start[1]]) return false;
        if(start[0] == destination[0] && start[1] == destination[1]) return true;
        visited[start[0]][start[1]] = true;
        //call dfs to traverse in four directions;
        int up = start[0] - 1, down = start[0] + 1, left = start[1] - 1, right = start[1] + 1;
        while(up >= 0 && maze[up][start[1]] == 0) up--;
        if(dfs(maze, new int[] {up + 1, start[1]}, destination, visited)) return true;
        
        while(down < maze.length && maze[down][start[1]] == 0) down++;
        if(dfs(maze, new int[] {down - 1, start[1]}, destination, visited)) return true;
        
        while(left >= 0 && maze[start[0]][left] == 0) left--;
        if(dfs(maze, new int[] {start[0],left + 1}, destination, visited)) return true;
        
        while(right < maze[0].length && maze[start[0]][right] == 0) right++;
        if(dfs(maze, new int[] {start[0],right - 1}, destination, visited)) return true;
        
        return false;
    }
    
    public boolean dfs2(int[][] maze, int[]start, int[] destination, boolean[][] visited){
            
            if(visited[start[0]][start[1]]) return false;
            if(start[0] == destination[0] && start[1] == destination[1]) return true;
            visited[start[0]][start[1]] = true;
        
            int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
            for(int[]d : dirs){
                int x = start[0] + d[0];
                int y = start[1] + d[1];
                while(x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == 0){
                    x += d[0];
                    y += d[1];
                } 
                if(dfs(maze, new int[] {x - d[0], y - d[1]}, destination, visited)){
                    return true; 
                }
            }
            return false;
        }
}