import java.io.*;
import java.util.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class ProductInformation{
    public static void main(String[] args) throws IOException{

        File file=new File("input.txt");

        String [][] inputList=new String[100][10];

        Scanner input=new Scanner(file);
        List<String> products =new ArrayList<String>();
        while (input.hasNext()) {
            int customers = Integer.parseInt(input.next());
            for(int i=0;i<customers;i++){
                int pQty=Integer.parseInt(input.next());
                for(int j=0;j<pQty;j++){
                    inputList[i][j]=input.next();
                }
            }

            for(int i=0;i<inputList.length;i++){
                for(int j=0;j<inputList[i].length;j++){
                    if(inputList[i][j]!=null){
                        products.add(inputList[i][j]);
                    }
                } 
            }

            Map<String, Integer> prodCount =new HashMap<>();

            for(String product:products){
                if(prodCount.containsKey(product)){
                    prodCount.put(product, prodCount.get(product)+1);
                }else{
                    prodCount.put(product, 1);
                }
            }

            // Map<String, Integer> hm1 = prodCount.entrySet().stream()
            // .sorted(Map.Entry.<String, Integer>comparingByValue()
            //                 .reversed()
            //         .thenComparing(Map.Entry.comparingByKey())).collect(Collectors.toMap(Entry::getKey,
            //                 Entry::getValue,
            //                 (a, b) -> a,      // merge function
            //                 LinkedHashMap::new));
        
            Map<String, Integer> hm1 = sortByValue(prodCount);

             int noOfQueries = Integer.parseInt(input.next());
            for(int i=0;i<noOfQueries;i++){
                int start = Integer.parseInt(input.next());
                int end = Integer.parseInt(input.next());
                List<String> keys=new ArrayList<>();
               

                for(String product:hm1.keySet()){
                    keys.add(product);
                    // if()
                    // for(int j=0;j<=end-1;j++){
                    //     if(j<(start-1)){
                    //         continue;
                    //     }else if(j>end){
                    //         break;
                    //     }else{
                    //         output.put(product,prodCount.get(product));
                    //     }
                    // }
                }

                String startEle= keys.get(start-1);
                String endEle=keys.get(end-1);
                boolean print=false;
                
                for(String product:hm1.keySet()){
                    if(product==startEle){
                        print=true;
                        
                    }else if(product==endEle){
                        System.out.println(prodCount.get(product)  + " : "+ product );
                        print=false;
                    }

                    if(print==true){
                        System.out.println(prodCount.get(product)  + " : "+ product );
                    }
                }
            }

            // Map<String, Integer> output =new HashMap<>();

            // for(int i=0;i<noOfQueries;i++){

            //     List<String> keys=new ArrayList<>();

            //     for(String product:hm1.keySet()){
            //         keys.add(product);
            //         // if()
            //         // for(int j=0;j<=end-1;j++){
            //         //     if(j<(start-1)){
            //         //         continue;
            //         //     }else if(j>end){
            //         //         break;
            //         //     }else{
            //         //         output.put(product,prodCount.get(product));
            //         //     }
            //         // }
            //     }

                
            // }

            // for(String product:hm1.keySet()){
            //      System.out.println(prodCount.get(product)  + " : "+ product );
            // }  
            // Map<String, Integer> sortProList =new HashMap<>(); 
            // List<String> valueList =new ArrayList<String>(); 
            // for(String product:hm1.keySet()){
            //     if(productsList.size()!=0 && hm1.get(product)!=valueList.get(valueList.size()-1)){
            //         so
            //     }else{
            //         sortProList.put(product,hm1.get(product));
            //     }
            // }  
        }
    }

    public static Map<String, Integer> sortByValue(Map<String, Integer> hm)
    {
        Map<String, Integer> sortedList = hm.entrySet().stream()
        .sorted(Map.Entry.<String, Integer>comparingByValue()
        .reversed()
        .thenComparing(Map.Entry.comparingByKey())).collect(Collectors.toMap(Entry::getKey,
                        Entry::getValue,
                        (a, b) -> a,      // merge function
                        LinkedHashMap::new));
        return sortedList;
    }
}