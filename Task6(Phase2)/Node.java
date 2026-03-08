public class Node implements Comparable<Node> {
    Point point;
    int distance;
    public int obstacles;
 

    public Node(Point point, int distance) {
        this.point = point;
        this.distance = distance;
    }

    
    public Node(Point point2, int distance2, int obstacles2) {
        this.point=point2;
        this.distance = distance2;
        this.obstacles=obstacles2;
    }


    @Override
    public int compareTo(Node other) {
        return Integer.compare(distance, other.distance);
    }
}
