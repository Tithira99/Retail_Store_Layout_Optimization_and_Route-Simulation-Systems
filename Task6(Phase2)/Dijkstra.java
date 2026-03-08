import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Dijkstra {
    
    public  Map<Point, Integer> findDistancesToDesignatedPoints(int[][] grid, Point source, List<Point> designatedPoints) {
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

        Map<Point, Integer> result = new HashMap<>();

        distances[source.entY][source.entX] = 0;
        minHeap.offer(new Node(source, 0));

        while (!minHeap.isEmpty()) {
            Node currentNode = minHeap.poll();
            Point currentPoint = currentNode.point;

            if (visited[currentPoint.entY][currentPoint.entX]) {
                continue;
            }

            visited[currentPoint.entY][currentPoint.entX] = true;
            for(int i=0;i<designatedPoints.size();i++){
                if (designatedPoints.get(i).getentX()==currentPoint.getentX() && designatedPoints.get(i).getentY()==currentPoint.getentY()) {
                    result.put(currentPoint, distances[currentPoint.entY][currentPoint.entX]);
                }
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
        // System.out.println(result);

        return result;
    }

    
    public   Point[] findObstacles(int[][] grid, Point source, Point destination, Point[] allPoints, int noOfProducts) {
            int numRows = grid.length;
            int numCols = grid[0].length;
            int[][] distances = new int[numRows][numCols];
            boolean[][] visited = new boolean[numRows][numCols];
            PriorityQueue<Node> minHeap = new PriorityQueue<>();
            Point[][][] obstacles= new Point [numRows][numCols][100];
      

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
                    return obstacles[currentPoint.entY][currentPoint.entX];
                }
                
                for (int i = 0; i < 4; i++) {
                    int newRow = currentPoint.entY + rowMovements[i];
                    int newCol = currentPoint.entX + colMovements[i];

                    if (isValidMove(grid, visited, newRow, newCol)) {
                        int distance = distances[currentPoint.entY][currentPoint.entX] + 1;
                        
                        if (distance < distances[newRow][newCol]) {

                            for(int j=0;j<noOfProducts;j++){
                                if (allPoints[j].getentX()==currentPoint.getentX() && allPoints[j].getentY()==currentPoint.getentY()) {
                                    int count=0;
                                    for(int k=0;k<obstacles[currentPoint.entY][currentPoint.entX].length;k++){
                                        if(obstacles[currentPoint.entY][currentPoint.entX][k]!=null)
                                            count++;
                                    
                                    }
                                    for(int t=0;t<obstacles[currentPoint.entY][currentPoint.entX].length;t++){
                                        obstacles[newRow][newCol][t]=obstacles[currentPoint.entY][currentPoint.entX][t];
                                       
                                    }
                                     obstacles[newRow][newCol][count]=allPoints[j];
                                    
                                }
                            }
                            if(obstacles[newRow][newCol][0]==null){
                                for(int t=0;t<obstacles[currentPoint.entY][currentPoint.entX].length;t++){
                                        obstacles[newRow][newCol][t]=obstacles[currentPoint.entY][currentPoint.entX][t];
                                }
                               
                            }
                            distances[newRow][newCol] = distance;
                            minHeap.offer(new Node(new Point(newCol,newRow), distance));
                        }
                    }
                }
            }
            return null;
    }

    private static boolean isValidMove(int[][] grid, boolean[][] visited, int row, int col) {
        int numRows = grid.length;
        int numCols = grid[0].length;

        return 
        
        row >= 0 && row < numRows && col >= 0 && col < numCols && grid[row][col] != 1 && !visited[row][col];
    }

}
