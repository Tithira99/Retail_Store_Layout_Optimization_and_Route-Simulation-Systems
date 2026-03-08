import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class RouteSimulation{
    
    public static void main(String []args) throws FileNotFoundException{
        File file=new File(args[0]);
        Scanner input=new Scanner(file);
        Point[] prodList=new Point[100];
        int [][] graph;
       
        while (input.hasNext()) {
            int width=Integer.parseInt(input.next());
            int height=Integer.parseInt(input.next());

            graph=new int[height][width];
            int noOfProducts;
            noOfProducts=Integer.parseInt(input.next());

            for(int i=0;i<noOfProducts;i++){
                int x=Integer.parseInt(input.next());
                int y=Integer.parseInt(input.next());
                String name=input.next();
                String direction= input.next();
                
                prodList[i]=new Point(x, y, name, direction);
                graph[y][x]=1;
            }
            
            for(int i=0;i<width;i++){
                if(i==1 || i==width-2){
                    continue;
                } else{
                    graph[0][i]=1;
                }
            }

            graph[height-1][0]=1;
            graph[height-1][width-1]=1;
           
            int noOfOrders=Integer.parseInt(input.next());
            for(int i=0;i<noOfOrders;i++){
                int qty=Integer.parseInt(input.next());
                List<Point> destinations=new ArrayList<Point>();
                destinations.add(new Point(1,0));
                for(int j=0;j<qty;j++){
                    String name=input.next();
                   
                    for(int k=0;k<noOfProducts;k++){
                        if(prodList[k].getName().equals(name)){
                            destinations.add(prodList[k]);
                        }
                    }
                }
                
                    int [][]distances=new int[qty+2][qty+2];
                    destinations.add(new Point(width-2,0));
                     
                    for(int k=0;k<destinations.size();k++){
                        Map<Point,Integer> dist=new Dijkstra().findDistancesToDesignatedPoints(graph,destinations.get(k),destinations);
                        for(int p=0;p<destinations.size();p++){
                            for(Point point:dist.keySet()){
                                for(int m=p;m<destinations.size();m++){
                                    if(destinations.get(p).getentX()==point.getentX() && destinations.get(p).getentY()==point.getentY()){
                                        distances[k][p]=dist.get(point);
                                    } 
                                }
                            }
                        }
                    }
                    
                int shortestDistance = new HeldKarp().findShortestPath(0, distances.length-1, distances);
                System.out.println(shortestDistance);
            }
        }
    }
}