import java.io.*;
import java.util.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

class identifyPairs{
    public static void main(String[] args) throws IOException{
        File file = null;
          
        if(0<args[0].length()){
            file=new File(args[0]);
        }
        
        Scanner input=new Scanner(file);
        
        ArrayList<ArrayList<String>> pairs=new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> firstOrderPairs=new ArrayList<ArrayList<String>>();
        while (input.hasNext()) {
            int customers = Integer.parseInt(input.next());
            for(int i=0;i<customers;i++){
                int pQty=Integer.parseInt(input.next());
                // System.out.println(pQty);
                List<String> order=new ArrayList<>();
                for(int j=0;j<pQty;j++){
                    order.add(input.next());
                }
                 ArrayList<ArrayList<String>> p=getPairs(order);
                for(int j=0;j<p.size();j++){
                    pairs.add(p.get(j));
                } 

                if(i==0){
                    for(int j=0;j<p.size();j++){
                        firstOrderPairs.add(pairs.get(j));
                    } 
                }
            }
            
            Map<ArrayList<String>, Integer> pairCount=getDistinctPairs(pairs,firstOrderPairs );

            Map<String, Integer> finalSetCount =new HashMap<>();

            finalSetCount= sortByValueAndKey(pairCount);
       
            int noOfQueries = Integer.parseInt(input.next());
            for(int i=0;i<noOfQueries;i++){
                int start = Integer.parseInt(input.next());
                int end = Integer.parseInt(input.next());
                List<String> keys=new ArrayList<>();
                // finalSetCount.keySet().size();
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
    
    public static ArrayList<ArrayList<String>> getPairs(List<String> inList){
        ArrayList<ArrayList<String>> pairs=new ArrayList<ArrayList<String>>();
        for(int i=0;i<inList.size()-1;i++){
                for(int j=i+1;j<inList.size();j++){
                    ArrayList<String> ele=new ArrayList<>();
                    ele.add(inList.get(i));
                    ele.add(inList.get(j));
                    pairs.add(ele);
                    
                }
        }
        return pairs;
    }

    public static Map<String, Integer> sortByValueAndKey(Map<ArrayList<String>, Integer> hm)
    {
        Map<String, Integer> output=new HashMap<>();
        for(ArrayList<String> pair:hm.keySet()){
            StringBuilder sb = new StringBuilder();
            for (String s : pair){
                sb.append(s);
                sb.append(" ");
            }
            output.put(sb.toString(), hm.get(pair));
        }
       
        Map<String, Integer> sortedList = output.entrySet().stream()
        .sorted(Map.Entry.<String, Integer>comparingByValue()
        .reversed()
        .thenComparing(Map.Entry.<String, Integer>comparingByKey())).collect(Collectors.toMap(Entry::getKey,
                        Entry::getValue,
                        (a, b) -> a,      
                        LinkedHashMap::new));

                      
        return sortedList;
    }


    public static   Map<ArrayList<String>, Integer> getDistinctPairs(  ArrayList<ArrayList<String>> pairs,ArrayList<ArrayList<String>> firstOrderPairs){
        for(int i=0;i<pairs.size();i++){
            Collections.sort(pairs.get(i));
        }
        boolean allEqual=true;

        for(int i=1;i<pairs.size();i++){
            if(pairs.get(i)!=pairs.get(i-1)){
                allEqual=false;
            }
        }

        Map<ArrayList<String>, Integer> pairCount =new HashMap<>();
        if(allEqual==true){
           
            for(ArrayList<String> pair:firstOrderPairs){
                // System.out.println(pairCount.size());
                if(pairCount.containsKey(pair)){
                    pairCount.put(pair, pairCount.get(pair)+1);
                }else{
                    pairCount.put(pair, 1);
                }
            }
        }else{

            for(ArrayList<String> pair:pairs){
               
                if(pairCount.containsKey(pair)){
                    pairCount.put(pair, pairCount.get(pair)+1);
                }else{
                    pairCount.put(pair, 1);  
                }
            }
        }
        
        return pairCount;
    }
}
