// this is leetcode 542. 01 Matrix
// initialize the distance matrix with infinity
// start BFS from cells which contain 0's
//during BFS, update distance when a shorter distance is found

import java.util.*;

class matrix {
	public int[][] updateMatrix(int[][] mat) {
		int m = mat.length;
		int n = mat[0].length;
		
		int[][] dist = new int[m][n];
		Queue<int[]> queue = new LinkedList<>();
		
		// initialize the distance matrix with infinity
		for (int i = 0; i < m; i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}
		
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (mat[i][j] == 0) {
					dist[i][j] = 0;
					queue.add(new int[]{i, j}); 
				}
			}
		}
		
		int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; 		
		
		while (!queue.isEmpty()) {
			int[] cell = queue.poll();
			
			for (int[] dir : dirs) {
				int r = cell[0] + dir[0];
				int c = cell[1] + dir[1];
				
				if (r < 0 || r >= m || c < 0 || c >= n || dist[r][c] <= dist[cell[0]][cell[1]] + 1) {
					continue;
				}
				dist[r][c] = dist[cell[0]][cell[1]] + 1;
				queue.add(new int[]{r, c});
			}
		}
		
		return dist;
	}
}


