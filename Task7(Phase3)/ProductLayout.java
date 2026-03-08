import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.SwingUtilities;

class ProductLayout{
    public static void main(String []args) throws IOException {
        long startTime = System.currentTimeMillis();
        long endTime;
        run(args);
        endTime = System.currentTimeMillis();
        System.out.println();
        System.out.println("Execution Time: "+ (endTime-startTime));
    }


    public static void run(String [] args)throws IOException{
       
        Map<ArrayList<String>, Integer> sortedList=new HashMap<>();
        
        int noOfProducts;

        IdentifyPairs id=new IdentifyPairs(args);

        sortedList=id.getSortedList();

        noOfProducts=id.getNoOfProducts();

        int [][] graph;

        int width=12;
        int height=12;
        
        graph=new int[height][width];
        

        //adding maximum possible number of shelves in the horizontal corners of the store 
        for(int i=0;i<width;i++){
            if(i==1 || i==width-2){
                graph[height-1][i]=2;
            }else if(i==0 || i==width-1){
                graph[0][i]=1;
                graph[height-1][i]=1;
            } 
            else{
                graph[0][i]=1;
                graph[height-1][i]=2;
            }
        }

        //adding maximum possible number of shelves in the vertical corners of the store 
        for(int j=0;j<height;j++){
            if(j==0 || j==height-1 || j==height-2){
                graph[j][0]=1;
                graph[j][width-1]=1;
            } else{
                graph[j][0]=2;
            }
        }

        //adding maximum possible number of shelves in the middle part of the store 
        int k=0;
        while(width-k>2){
            if(k==0){
                if((k+3)<=width-1){
                    k=3;
                    if(k+1==width-1){
                        k=width-1;
                    }
                    // System.out.println(k);
                    if(k!=width-1){
                        for(int m=2;m<height-2;m++){
                            graph[m][k]=2;
                        }
                    }else{
                        for(int m=1;m<height-2;m++){
                            graph[m][k]=2;
                        }
                    }
                }
            }else if(k>0 && graph[3][k-1]!=2){
                k=k+1;
                if(k+1==width-1){
                    k=width-1;
                    
                }
                
                if(k!=width-1){
                    for(int m=2;m<height-2;m++){
                            graph[m][k]=2;
                    }
                }else{
                    for(int m=1;m<height-2;m++){
                        graph[m][k]=2;
                    }
                }
                
            }else if(k>0 && graph[3][k-1]==2){
                if((k+3)<=width-1){
                    k=k+3;
                    if(k!=width-1){
                        for(int m=2;m<height-2;m++){
                                graph[m][k]=2;
                          
                        }
                    }else{
                        for(int m=1;m<height-2;m++){
                            graph[m][k]=2;
                        }
                    }
                }else{
                    break;
                }
            }
        }

        //code for reducing the number of shelves accoring to the number of products
        int count=0;
        for(int i=0;i<graph.length;i++){
            for(int j=0;j<graph[0].length;j++){
                if(count<noOfProducts && graph[i][j]==2){
                    graph[i][j]=3;
                    count++;
                } else if(graph[i][j]==2){
                    graph[i][j]=0;
                }
            }
        }

       

        //creating the list of shelves by assigning directions to the shelves
        List<Point> shelves=new ArrayList<Point>();
        int m=graph.length;
        for(int i=graph[0].length-1;i>=0;i--){
            if(graph[2][i]!=3){
                continue;
            }
            if(m==0){
                for(int j=0;j<graph.length;j++){
                    if(i==0 && graph[j][i]==3){
                        shelves.add(new Point(i,j,"E"));
                    }else if(i%2==1 && graph[j][i]==3){
                        shelves.add(new Point(i,j,"W"));
                    }else if(i%2==0 && graph[j][i]==3){
                        if(i==width){
                            shelves.add(new Point(i,j,"W"));
                        }else{
                            shelves.add(new Point(i,j,"E"));
                        }
                    }
                }
                m=graph.length;
            }else{
                for(int j=graph.length-1;j>=0;j--){
                    if(i==0 && graph[j][i]==3){
                        shelves.add(new Point(i,j,"E"));
                    }else if(i%2==1 && graph[j][i]==3){
                        shelves.add(new Point(i,j,"W"));
                    }else if(i%2==0 && graph[j][i]==3){
                        if(i==width){
                            shelves.add(new Point(i,j,"W"));
                        }else{
                            shelves.add(new Point(i,j,"E"));
                        }
                    }
                }
                m=0;
            }
        }

        List<Point> shelves2=new ArrayList<>();

         for(int i=graph[0].length-1;i>=0;i--){

              for(int j=0;j<graph.length;j++){
                    if(i==0 && graph[j][i]==3){
                        shelves2.add(new Point(i,j,"E"));
                    }else if(i%2==1 && graph[j][i]==3){
                        shelves2.add(new Point(i,j,"W"));
                    }else if(i%2==0 && graph[j][i]==3){
                        if(i==width){
                            shelves2.add(new Point(i,j,"W"));
                        }else{
                            shelves2.add(new Point(i,j,"E"));
                        }
                    }
                }
         }

        //  for(int i=0;i<shelves2.size();i++){
        //     //  g2[shelves2.get(i).getY()][shelves2.get(i).getX()]=shelves2.get(i).getName();
        //      System.out.print(shelves2.get(i).getX());
             
        // }
        // System.out.println();


        Map<ArrayList<String>, Integer> list=id.getPairs();
        

        List<ArrayList<String>> keys=new ArrayList<>(list.keySet());

        Collections.shuffle(keys);
        // System.out.println(shelves2);
        //Filling the Shelves with the products
        if(shelves.size()<noOfProducts){
            System.out.println("Store full");
        }else{
                for(ArrayList<String> h:sortedList.keySet()){
                        for(int j=0;j<h.size();j++ ){
                            for(int i=0;i<shelves.size();i++){
                                if(shelves.get(i).getName()==null){
                                    shelves.get(i).setName(h.get(j));
                                }

                                if(shelves.get(i).getName().equals(h.get(j))){
                                    break;
                                }
                            }
                        }
                }

                for(ArrayList<String> h:keys){
                        // System.out.println(h.toString());
                        for(int j=0;j<h.size();j++ ){
                            for(int i=0;i<shelves.size();i++){
                                if(shelves2.get(i).getName()==null){
                                    shelves2.get(i).setName(h.get(j));
                                }

                                if(shelves2.get(i).getName().equals(h.get(j))){
                                    break;
                                }
                            }
                        }
                }
        }
               
        double sum=0;
        double sum2=0;
        //Calculating Distance Product of Proposed Store
        Dijkstra d=new Dijkstra();
        for(ArrayList<String> h:sortedList.keySet()){
            ArrayList<Point> plist=new ArrayList<Point>();
            for(int j=0;j<h.size();j++ ){
                    for(int i=0;i<shelves.size();i++){
                        if(h.get(j).equals(shelves.get(i).getName())){
                            plist.add(shelves.get(i));

                        }
                    }  
            }
            
            int distance=d.findMinDistance(graph, plist.get(0), plist.get(1));
            int entDistance1=d.findMinDistance(graph, plist.get(0), new Point(1,0));
            int entDistance2=d.findMinDistance(graph, plist.get(1), new Point(1,0));
            sum=sum+((distance*list.get(h))/(entDistance1*entDistance2));
        }
        //Calculating Distance Product of Random Store 
        for(ArrayList<String> h:list.keySet()){
            ArrayList<Point> plist=new ArrayList<Point>();
            for(int j=0;j<h.size();j++ ){
                    for(int i=0;i<shelves2.size();i++){
                        if(h.get(j).equals(shelves2.get(i).getName())){
                            plist.add(shelves2.get(i));
                        }
                    }
            }
            int distance=d.findMinDistance(graph, plist.get(0), plist.get(1));
            int entDistance1=d.findMinDistance(graph, plist.get(0), new Point(1,0));
            int entDistance2=d.findMinDistance(graph, plist.get(1), new Point(1,0));
            sum2=sum2+((distance*list.get(h))/(entDistance1*entDistance2));
        }

        double percentage=((sum2-sum)/sum2)*100;


        System.out.println("Pair-Distance Product of Proposed Approach : "+ sum);
        System.out.println("Pair-Distance Product of Random Approach : "+ sum2);
        System.out.println("Pair-Distance Efficiency of the Proposed Store : "+String.format("%.5f", percentage)+"%");


        System.out.println();

        
        //Creating the Matrix String
        String[][] g=new String[height][width];
        String[][] g2=new String[height][width];

        for(int i=0;i<graph.length;i++){
            for(int j=0;j<graph[0].length;j++){
                String ele=Integer.toString(graph[i][j]);
                g[i][j]=ele;
            }
            // System.out.println();
        }

        for(int i=0;i<graph.length;i++){
            for(int j=0;j<graph[0].length;j++){
                String ele=Integer.toString(graph[i][j]);
                g2[i][j]=ele;
            }
            // System.out.println();
        }

        

        //Filling the Matrix String(Store) with products
        for(int i=0;i<shelves.size();i++){
             g[shelves.get(i).getY()][shelves.get(i).getX()]=shelves.get(i).getName();
            //  System.out.print(shelves.get(i).getName());

             
        }
       

        for(int i=0;i<shelves2.size();i++){
             g2[shelves2.get(i).getY()][shelves2.get(i).getX()]=shelves2.get(i).getName();
            //  System.out.print(shelves2.get(i).getName());
             
        }

        //Printing the Stores
        System.out.println("Proposed Store: ");
         System.out.println();
       
        for(int i=0;i<graph.length;i++){
            for(int j=0;j<graph[0].length;j++){
                System.out.print(" "+g[i][j]+" ");
            }
            System.out.println();
        }

        System.out.println();
        System.out.println();

        System.out.println("Random Store: ");
        System.out.println();

        for(int i=0;i<graph.length;i++){
            for(int j=0;j<graph[0].length;j++){
                System.out.print(" "+g2[i][j]+" ");
            }
            System.out.println();
        }


        g[0][1]="EN";
        g[0][g.length-2]="EX";

         g2[0][1]="EN";
        g2[0][g.length-2]="EX";

        // System.out.println();
        // for(int i=0;i<shelves.size();i++){
        //     System.out.println(shelves.get(i).getName()+": ("+ shelves.get(i).getX()+", "+shelves.get(i).getY()+") | direction: "+shelves.get(i).getDir());
        // }

         SwingUtilities.invokeLater(() -> {
            Represent visualizer = new Represent(g2);
            visualizer.setTitle("Random Store");
            visualizer.setVisible(true);
        });

        SwingUtilities.invokeLater(() -> {
            Represent visualizer = new Represent(g);
            visualizer.setTitle("Proposed Store");
            visualizer.setVisible(true);
        });

       
    
    }
}