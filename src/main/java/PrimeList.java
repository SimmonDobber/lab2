import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrimeList
{
    private List<Integer> list;

    public PrimeList() {
        list = Collections.synchronizedList(new ArrayList<>());
    }

    public synchronized Integer take() throws InterruptedException{
        while(list.isEmpty())
            wait();
        Integer ret = list.get(0);
        list.remove(0);
        return ret;
    }

    public synchronized void put(Integer prime){
        list.add(prime);
        notifyAll();
    }
}
