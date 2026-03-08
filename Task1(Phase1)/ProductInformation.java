import java.io.*;
import java.util.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

class ProductInformation{
    public static void main(String[] args) throws IOException{
        File file = null;
       
            
        if(0<args[0].length()){
            file=new File(args[0]);
        }
        
        
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
        
            Map<String, Integer> hm1 = sortByValueAndKey(prodCount);

             int noOfQueries = Integer.parseInt(input.next());
            for(int i=0;i<noOfQueries;i++){
                int start = Integer.parseInt(input.next());
                int end = Integer.parseInt(input.next());
                List<String> keys=new ArrayList<>();
               

                for(String product:hm1.keySet()){
                    keys.add(product);
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
        }
    }

    public static Map<String, Integer> sortByValueAndKey(Map<String, Integer> hm)
    {
        Map<String, Integer> sortedList = hm.entrySet().stream()
        .sorted(Map.Entry.<String, Integer>comparingByValue()
        .reversed()
        .thenComparing(Map.Entry.comparingByKey())).collect(Collectors.toMap(Entry::getKey,
                        Entry::getValue,
                        (a, b) -> a,      
                        LinkedHashMap::new));
        return sortedList;
    }
}
