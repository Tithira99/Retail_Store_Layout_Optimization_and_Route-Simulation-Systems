import java.util.List;

public class Data {
    int value;
    List<String> products;
    
    Data(List<String> productsIn, int valueIn){
        this.value=valueIn;
        this.products=productsIn;
    }

    int getValue(){
        return value;
    }

    List<String> getProducts(){
        return products;
    }
}
