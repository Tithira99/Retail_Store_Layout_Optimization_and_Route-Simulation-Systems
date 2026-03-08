import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class DataManipulation {
    public  ArrayList<ArrayList<String>> getPairs(List<String> inList){
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

    public  Map<String, Integer> sortByValueAndKey(Map<ArrayList<String>, Integer> hm)
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


    public  Map<ArrayList<String>, Integer> getDistinctPairs(  ArrayList<ArrayList<String>> pairs,ArrayList<ArrayList<String>> firstOrderPairs){
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
