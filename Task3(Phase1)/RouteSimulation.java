import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RouteSimulation{
    public static void main(String []args) throws FileNotFoundException{
        
        // Scanner sc= new Scanner(System.in);
        File file=new File(args[0]);

        Scanner input=new Scanner(file);

        Point[] prodList=new Point[100];
        int [][] graph;
        while (input.hasNext()) {
            int width=Integer.parseInt(input.next());
            int height=Integer.parseInt(input.next());
            graph=new int[height][width];
            int noOfProducts=Integer.parseInt(input.next());

            for(int i=0;i<noOfProducts;i++){
                int x=Integer.parseInt(input.next());
                int y=Integer.parseInt(input.next());
                String name=input.next();
                String direction= input.next();
                
                prodList[i]=new Point(x, y, name, direction);
                graph[y][x]=1;
            }
           
            int noOfOrders=Integer.parseInt(input.next());
            for(int i=0;i<noOfOrders;i++){
                int qty=Integer.parseInt(input.next());
                String name=input.next();
            
               
                int distance=0;

                for(int j=0;j<noOfProducts;j++){
                    if(prodList[j].getName().equals(name)){
                        distance=new Dijkstra().findMinDistance(graph, new Point(1,0), prodList[j]);
                        distance+=new Dijkstra().findMinDistance(graph, prodList[j], new Point(width-2,0));
                        System.out.println(distance);
                    }
                }
            }
        }
    }

}