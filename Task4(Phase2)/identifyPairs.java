import java.io.*;
import java.util.*;
import java.util.HashMap;
import java.util.Map;

class identifyPairs{
    public static void main(String[] args) throws IOException{
        File file = null;
          
        if(0<args[0].length()){
            file=new File(args[0]);
        }
        
        Scanner input=new Scanner(file);

        // List<String> products =new ArrayList<String>();
        DataManipulation dataM=new DataManipulation();
        ArrayList<ArrayList<String>> pairs=new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> firstOrderPairs=new ArrayList<ArrayList<String>>();
        while (input.hasNext()) {
            int customers = Integer.parseInt(input.next());

            for(int i=0;i<customers;i++){
                int pQty=Integer.parseInt(input.next());
                List<String> order=new ArrayList<>();
                for(int j=0;j<pQty;j++){
                    order.add(input.next());
                }
                 ArrayList<ArrayList<String>> p=dataM.getPairs(order);
                for(int j=0;j<p.size();j++){
                    pairs.add(p.get(j));
                } 

                if(i==0){
                    for(int j=0;j<p.size();j++){
                        firstOrderPairs.add(pairs.get(j));
                    } 
                }
            }

            Map<ArrayList<String>, Integer> pairCount=dataM.getDistinctPairs(pairs,firstOrderPairs );

            Map<String, Integer> finalSetCount =new HashMap<>();

            finalSetCount= dataM.sortByValueAndKey(pairCount);

            int noOfQueries = Integer.parseInt(input.next());
            for(int i=0;i<noOfQueries;i++){
                int start = Integer.parseInt(input.next());
                int end = Integer.parseInt(input.next());
                List<String> keys=new ArrayList<>();

                for(String product:finalSetCount.keySet()){
                    keys.add(product);
                }

                String startEle= keys.get(start-1);
                String endEle=keys.get(end-1);
                boolean print=false;
                
                for(String product:finalSetCount.keySet()){
                    if(product==startEle){
                        print=true;
                        
                    }else if(product==endEle){
                        System.out.println(finalSetCount.get(product)  + " : "+ product );
                        print=false;
                    }

                    if(print==true){
                        System.out.println(finalSetCount.get(product)  + " : "+ product );
                    }
                }
            } 
        }
    }
}