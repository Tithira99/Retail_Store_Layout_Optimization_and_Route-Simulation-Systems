import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class testData {
    public static void main(String[] args) throws IOException {
        List<String> inputList = Arrays.asList("Apple", "Banana", "Orange", "Tomato", "Potato", "Onion", "Carrot", "Lettuce", "Cucumber", "Broccoli",
        "Avocado", "Pineapple", "Watermelon", "Grapes", "Strawberry", "Blueberry", "Raspberry", "Lemon", "Lime",
        "Mango", "Papaya", "Kiwi", "Pear", "Peach", "Plum", "Cherry", "Coconut", "Pomegranate", "Fig", "Apricot",
        "Cauliflower", "Spinach", "Radish", "Celery", "Bellpepper", "Eggplant", "Zucchini", "Mushroom", "Pumpkin",
        "Sweetpotato", "Artichoke", "Asparagus", "Corn", "Greenbeans", "Blackbeans", "Lentils", "Chickpeas",
        "Rice", "Pasta", "Bread");

        DataManipulation dataM=new DataManipulation();

        RandomNumberGenerator rndm=new RandomNumberGenerator(20);
        RandomNumberGenerator rndm2=new RandomNumberGenerator(10000);
        
        PrintWriter pw=new PrintWriter(new FileWriter(new File("input.txt")));
        ArrayList<Data> randomTestData=new ArrayList<Data>();
        int orders=rndm2.generateRandomNumber();
        ArrayList<ArrayList<String>> firstOrder=null;;
        ArrayList<ArrayList<String>> pairs=new ArrayList<ArrayList<String>>();
        Map<ArrayList<String>, Integer> pairDistict;
        pw.println(orders);
        int pairCount=0;
        
        for(int i=0;i<orders;i++){
            int products=rndm.generateRandomNumber();
            List<String> randomTestData2 = generateRandomTestData(inputList, products); 
            Integer size=new Integer(randomTestData2.size());
            
            if(i==0){
                firstOrder=dataM.getPairs(randomTestData2);
            }

            ArrayList<ArrayList<String>> p=dataM.getPairs(randomTestData2);
            for(int j=0;j<p.size();j++){
                pairs.add(p.get(j));
            }
            Data dataList=new Data(randomTestData2, size);
            randomTestData.add(dataList);
        }

        pairDistict=dataM.getDistinctPairs(pairs,firstOrder);
        pairCount=pairDistict.keySet().size();

        for(Data data:randomTestData){
            pw.print(data.getValue() +" "); 
            
          
            for(int i=0;i<data.getProducts().size();i++){
                if(i==data.getProducts().size()-1){
                    pw.println(data.getProducts().get(i));
                }else{
                    pw.print(data.getProducts().get(i)+" ");  
                }
            }
            
        }

        RandomNumberGenerator rndm3=new RandomNumberGenerator(10);
            int queries=rndm3.generateRandomNumber();
            pw.println(queries);

            for(int i=0;i<queries;i++){
                RandomNumberGenerator rndm4=new RandomNumberGenerator(pairCount);
                int firstNum=rndm4.generateRandomNumber();
                pw.print(firstNum+ " ");
                pw.println( rndm4.generateRandomNumber(firstNum+1));
            }
            pw.close();
    }

    public static List<String> generateRandomTestData(List<String> inputList, int numberOfElements) {
        List<String> testData = new ArrayList<>(inputList); // Create a new list to avoid modifying the original list
        Collections.shuffle(testData); // Shuffle the list

        if (numberOfElements >= testData.size()) {
            return testData; // Return the entire shuffled list if the requested number of elements is greater than or equal to the list size
        } else {
            return testData.subList(0, numberOfElements); // Return a sublist with the requested number of elements
        }
    }
}