public class PrimeTester implements Runnable
{
    private PrimeList primeList;
    private PrimeCollector primeCollector;
    private boolean running;

    public PrimeTester(PrimeList primeList, PrimeCollector primeCollector){
        this.primeList = primeList;
        this.primeCollector = primeCollector;
        this.running = true;
    }

    @Override
    public void run() {
        while(running) {
            try {
                primeCollector.put(test(primeList.take()));
            } catch (InterruptedException e) {
                running = false;
            }
        }
    }

    private String test(Integer x){
        if(x == 1){
            return(x + " is not prime.");
        }
        for(int i = 2; i < x; i++) {
            if(x % i == 0){
                return(x + " is not prime.");
            }
        }
        return(x + " is prime.");
    }
}
