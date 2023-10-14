import java.util.Arrays;

public class TravelingSalesmanProblem {
    public static int tsp(int[][] graph, int mask, int pos, int n, int[][] dp) {
        if (mask == (1 << n) - 1) {
            return graph[pos][0];
        }
        if (dp[mask][pos] != -1) {
            return dp[mask][pos];
        }

        int minCost = Integer.MAX_VALUE;

        for (int next = 0; next < n; next++) {
            if ((mask & (1 << next)) == 0) {
                int newMask = mask | (1 << next);
                int cost = graph[pos][next] + tsp(graph, newMask, next, n, dp);
                minCost = Math.min(minCost, cost);
            }
        }

        dp[mask][pos] = minCost;
        return minCost;
    }

    public static int solveTSP(int[][] graph) {
        int n = graph.length;
        int[][] dp = new int[1 << n][n];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return tsp(graph, 1, 0, n, dp);
    }

    public static void main(String[] args) {
        int[][] graph = {
            {0, 29, 20, 21},
            {29, 0, 15, 17},
            {20, 15, 0, 28},
            {21, 17, 28, 0}
        };

        int shortestTourLength = solveTSP(graph);
        System.out.println("Shortest tour length: " + shortestTourLength);
    }
}
