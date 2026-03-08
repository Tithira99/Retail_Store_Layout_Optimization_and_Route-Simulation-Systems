public class Node implements Comparable<Node> {
    Point point;
    int distance;

    public Node(Point point, int distance) {
        this.point = point;
        this.distance = distance;
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(distance, other.distance);
    }
}
