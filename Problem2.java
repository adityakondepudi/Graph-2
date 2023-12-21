//Time Complexity : O(n^2)
//Space Complexity: O(n^2)

class Solution {
    int [] colors;
    public int minMalwareSpread(int[][] graph, int[] initial) {
        int n = graph.length;
        this.colors = new int[n];
        Arrays.fill(this.colors,-1);
        int cl = 0;
        for(int i=0; i<n; i++){
            if(colors[i] == -1){
                dfs(graph, i, cl);
            }
            cl++;
        }
        int [] groups = new int[cl];
        int [] initGroups = new int[cl];
        for(int c: colors){
            groups[c]++;
        }
        for(int node: initial){
            int c = colors[node];
            initGroups[c]++;
        }
        int result = Integer.MAX_VALUE;
        for(int node : initial){
            int c = colors[node];
            if(initGroups[c] == 1){
                if(result == Integer.MAX_VALUE){
                    result = node;
                }
                else if(groups[c] > groups[colors[result]]){
                    result = node;
                }
                else if(groups[c] == groups[colors[result]] && node < result){
                    result = node;
                }
            }
        }
        if(result == Integer.MAX_VALUE){
        for(int node: initial){
            result = Math.min(result, node);
        }
        }
        return result;
    }
    private void dfs(int[][] graph, int i, int cl){
        if(colors[i] != -1) return;
        colors[i] = cl;
        for(int j=0;j<graph.length;j++){
            if(graph[i][j] == 1){
                dfs(graph, j, cl);
            }
        }
    }
}
