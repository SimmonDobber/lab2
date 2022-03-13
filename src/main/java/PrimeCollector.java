import java.util.ArrayList;
import java.util.List;

public class PrimeCollector
{
    private List<String> results;

    public PrimeCollector() {
        results = new ArrayList<>();
    }

    public synchronized void put(String s){
        results.add(s);
        System.out.println(s);
    }

    public void giveResults(){
        for(String result : results)
            System.out.println(result);
    }
}
