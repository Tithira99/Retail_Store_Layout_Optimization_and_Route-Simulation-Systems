import java.util.Arrays;

public class HeldKarp {
    private  int[][] distanceMatrix;
    private  int numPoints;
    private  int[][] memo; 

    public  int findShortestPath(int source, int destination, int[][] distances) {
        distanceMatrix = distances;
        numPoints = distanceMatrix.length;
        memo = new int[numPoints][(1 << numPoints)];

        int allVisited = (1 << numPoints) - 1; // All points visited bitmask

        // Initialize memoization table
        for (int i = 0; i < numPoints; i++) {
            Arrays.fill(memo[i], -1);
        }
        return tsp(source, 1 << source, destination, allVisited);
    }

    private  int tsp(int current, int visited, int destination, int allVisited) {
        if (visited == allVisited) {
            return distanceMatrix[current][destination];
        }

        if (memo[current][visited] != -1) {
            return memo[current][visited];
        }

        int minDistance = Integer.MAX_VALUE;

        for (int next = 0; next < numPoints; next++) {
            if (next != current && (visited & (1 << next)) == 0) {
                int distance = distanceMatrix[current][next] + tsp(next, visited | (1 << next), destination, allVisited);
                minDistance = Math.min(minDistance, distance);
            }
        }

        memo[current][visited] = minDistance;
        return minDistance;
    }
}
