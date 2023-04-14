import java.util.*;

public class TreeDiameter {
    private int diameter = 0;
    public int treeDiameter(int[][] edges) {
        if(edges.length == 0) return 0;

        //convert edges to an adjacency array for dfs
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Set<Integer> visited = new HashSet<>();

        for(int[] edge : edges){
            if(!graph.containsKey(edge[0])){
                graph.put(edge[0], new ArrayList<Integer>());
            }
            if(!graph.containsKey(edge[1])){
                graph.put(edge[1], new ArrayList<Integer>());
            }
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        //create a set for marking as visited, boolean array also works 
        //call dfs starting from node[0] to derive longest path
        
        dfs(graph, 0, visited);
        return diameter;
    }
    
    //given a node, the longest path is the sum of the longest path to the right and the longet path to the left, to use left and right loosely.
    public int dfs(Map<Integer, List<Integer>> graph, int start, Set<Integer> visited){
        int distance1 = 0, distance2 = 0;
        visited.add(start);

        for(int neighbor : graph.get(start)){
            int distance = 0;
            if(!visited.contains(neighbor)){
                distance = 1 + dfs(graph, neighbor, visited);
            }

            if(distance > distance1){
                distance2 = distance1;
                distance1 = distance;
            } else if(distance > distance2){
                distance2 = distance;
            }     
        }
        diameter = Math.max(diameter, distance1 + distance2);
        return distance1;
    }
}
