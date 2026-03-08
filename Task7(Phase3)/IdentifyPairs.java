import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class IdentifyPairs {
        Map<ArrayList<String>, Integer> sortedList=new HashMap<>();
        FileReader file = null;
        Scanner input=null;
        int noOfProducts;
        ArrayList<ArrayList<String>> pairs;
        Map<ArrayList<String>, Integer> pairCount;


        public IdentifyPairs(String args[]) throws FileNotFoundException{

            try{
                if(0<args[0].length()){
                    file=new FileReader(args[0]);
                }
            
                input= new Scanner(new BufferedReader(file));
                // List<String> products =new ArrayList<String>();
                DataManipulation dataM=new DataManipulation();
                pairs=new ArrayList<ArrayList<String>>();
                ArrayList<ArrayList<String>> firstOrderPairs=new ArrayList<ArrayList<String>>();
                // while (input.hasNext()) {
                    int customers = Integer.parseInt(input.next());
                    noOfProducts=Integer.parseInt(input.next());
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

                    pairCount=dataM.getDistinctPairs(pairs,firstOrderPairs);
                    
                    // finalSetCount= dataM.sortByValueAndKey(pairCount);

                    sortedList = pairCount.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).collect(Collectors.toMap(
                        Map.Entry::getKey, 
                        Map.Entry::getValue, 
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
                        

                    //  Collections.sort(pairCount, new Comparator<String>() {
                    //     public int compare(String str, String str1) {
                    //         return (str).compareTo(str1);
                    //     }
                    // });
                    // }
            }finally{
                if(input!=null){
                    input.close();
                }
            }

           
        }

        public Map<ArrayList<String>, Integer> getSortedList(){
            return sortedList;
        }

        public int getNoOfProducts(){
            return noOfProducts;
        }


        public  Map<ArrayList<String>, Integer> getPairs(){
            return pairCount;
        }


        // public  ArrayList<ArrayList<String>> getPairs2(){
        //     return pairs;
        // }

}
