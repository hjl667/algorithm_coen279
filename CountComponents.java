import java.util.*;

class CountComponents {
    private int count = 0;
    public int countComponents(int n, int[][] edges) {

        Map<Integer,List<Integer>> graph = new HashMap<>();
        for(int[] edge : edges){
            if(!graph.containsKey(edge[0])){
                graph.put(edge[0], new ArrayList<>());
            }
            if(!graph.containsKey(edge[1])){
                graph.put(edge[1], new ArrayList<>());
            }
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        } 

        HashSet<Integer> visited = new HashSet<>();

        for(int i = 0; i < n;i++){
            if(!visited.contains(i)){
            this.count++;
            dfs(graph, visited, i);
            }
        }
        return this.count;
    }

    public void dfs(Map<Integer,List<Integer>> graph, HashSet<Integer> visited, Integer start){
        visited.add(start);
        List<Integer> neighbors = graph.get(start);
        if(neighbors != null){
            for(int neighbor : neighbors){
            if(!visited.contains(neighbor)){
                dfs(graph, visited, neighbor);
            }
        }  
        }   
    }
}