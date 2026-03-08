import java.util.Random;

public class RandomNumberGenerator {
    int maxLimit;

    RandomNumberGenerator(int max){
        this.maxLimit=max;
    }

    public int generateRandomNumber(){
        Random random=new Random();
        return  random.nextInt(maxLimit)+1;
    }

    public int generateRandomNumber(int min){
        Random random=new Random();
        return  random.nextInt(maxLimit-min+1)+min;
    }
}
