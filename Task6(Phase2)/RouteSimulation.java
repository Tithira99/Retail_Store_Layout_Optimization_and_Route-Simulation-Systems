import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class RouteSimulation{

    private static HeldKarp calc;
    private static boolean[][]  visited1;
    private static int numRows;
    private static int numCols;
    private static Dijkstra dijkstra=new Dijkstra();
    
	
    public static void main(String []args) throws FileNotFoundException{
        run(args[0]);
       
    }

    public static void run(String args) throws FileNotFoundException{
        File file=new File(args);

        Scanner input=new Scanner(file);

        Point[] prodList=new Point[100];
       
        int [][] graph;
        int distance;
       
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
                // graph[prodList[i].entY][prodList[i].entX]=2;
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
                
                // int currentX=1;
                // int currentY=0;
                distance=0;
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
                       
                        Map<Point,Integer> dist=dijkstra.findDistancesToDesignatedPoints(graph,destinations.get(k),destinations);
                        
                        
                        for(int p=0;p<destinations.size();p++){
                            for(Point point:dist.keySet()){
                                    
                                    if(destinations.get(p).getentX()==point.getentX() && destinations.get(p).getentY()==point.getentY()){
                                        distances[k][p]=dist.get(point);
                                    } 
                            }
                        }
                    }

                    
                List<Integer> list=standardRun(distances, 0, distances.length-1);
               
                List<Point> points=new ArrayList<>();
                for(int x=0;x<list.size();x++){
                    points.add(destinations.get(list.get(x)));
                }
               

                List<Point> drfList=new ArrayList<>();
                List<Point> finalList=new ArrayList<>();
                visited1= new boolean[numRows][numCols];
                for(int x=0;x<points.size()-1;x++){
                    Point[] r;
                    if(dijkstra.findObstacles(graph,points.get(x),points.get(x+1), prodList,noOfProducts)[0]!=null){
                        r=dijkstra.findObstacles(graph,points.get(x),points.get(x+1), prodList,noOfProducts);
                        for(Point p:r){
                            if(p!=null){
                                drfList.add(p);
                                
                            }
                        }
                    }
                }


                for(int x=0;x<drfList.size();x++){
                    boolean contains=false;
                    for(int y=0;y<finalList.size();y++){
                        if(finalList.get(y)==drfList.get(x)){
                            contains=true;
                        }
                    }

                    if(contains==false){
                        finalList.add(drfList.get(x));
                    }
                }

                List<String> nameList=new ArrayList<>();;

                for(int x=0;x<finalList.size();x++){
                    
                    nameList.add(finalList.get(x).getName());
                    
                }
                

                Collections.sort(nameList);
                for(int t=0;t<nameList.size();t++){
                    System.out.print(nameList.get(t)+" ");
                }

                System.out.println();

            }
        }
    }
    

    public static List<Integer> standardRun(int [][] distances, int start, int end) {
		
		calc = new HeldKarp(distances, start,end);
		List<Integer> result = calc.calculateHeldKarp();
        System.out.print(calc.getOpt()+" ");
		return result;
		
	}

    public static int constrain(int min, int max, int number) {
		if(number < min) {
			return min;
		} else if (number > max) {
			return max;
		} else {
			return number;
		}
	}
}