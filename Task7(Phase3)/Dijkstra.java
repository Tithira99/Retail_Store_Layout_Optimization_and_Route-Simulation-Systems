import java.util.Arrays;

import java.util.PriorityQueue;

public class Dijkstra {
    
    public  int findMinDistance(int[][] grid, Point source, Point destination) {
        int numRows = grid.length;
        int numCols = grid[0].length;
        int[][] distances = new int[numRows][numCols];
        boolean[][] visited = new boolean[numRows][numCols];
        PriorityQueue<Node> minHeap = new PriorityQueue<>();

        // Initialize distances to infinity
        for (int i = 0; i < numRows; i++) {
            Arrays.fill(distances[i], Integer.MAX_VALUE);
        }

        // Possible movements: Up, Down, Left, Right
        int[] rowMovements = {-1, 1, 0, 0};
        int[] colMovements = {0, 0, -1, 1};

        distances[source.entY][source.entX] = 0;
        minHeap.offer(new Node(source, 0));

        while (!minHeap.isEmpty()) {
            Node currentNode = minHeap.poll();
            Point currentPoint = currentNode.point;

            if (visited[currentPoint.entY][currentPoint.entX]) {
                continue;
            }

            visited[currentPoint.entY][currentPoint.entX] = true;
            
            if (destination.getentX()==currentPoint.getentX() && destination.getentY()==currentPoint.getentY()) {
                return distances[currentPoint.entY][currentPoint.entX];
            }
            

            

            for (int i = 0; i < 4; i++) {
                int newRow = currentPoint.entY + rowMovements[i];
                int newCol = currentPoint.entX + colMovements[i];

                if (isValidMove(grid, visited, newRow, newCol)) {
                    int distance = distances[currentPoint.entY][currentPoint.entX] + 1;

                    if (distance < distances[newRow][newCol]) {
                        distances[newRow][newCol] = distance;
                        minHeap.offer(new Node(new Point(newCol,newRow), distance));
                    }
                }
            }
        }
        return 0;
    }

    private  boolean isValidMove(int[][] grid, boolean[][] visited, int row, int col) {
        int numRows = grid.length;
        int numCols = grid[0].length;

        return row >= 0 && row < numRows && col >= 0 && col < numCols && grid[row][col] != 1 && grid[row][col] != 3 && !visited[row][col];
    }
}
