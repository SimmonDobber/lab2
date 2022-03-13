import java.util.Scanner;

public class Main
{
    private static Thread[] threads;
    private static PrimeList primeList;
    private static PrimeCollector primeCollector;
    private static Scanner scanner;
    private static boolean running;

    public static void main(String[] args) {
        initialize(Integer.parseInt(args[0]));
        run();
    }

    private static void run(){
        while(running){
            try {
                handleInput();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void handleInput() throws InterruptedException {
        if(scanner.hasNextInt())
            readInput();
        else
            Thread.sleep(50);
    }

    private static void readInput(){
        int number = scanner.nextInt();
        if(number == -1)
            endProgram();
        else
            primeList.put(number);
    }

    private static void initialize(int threadCount){
        primeList = new PrimeList();
        primeCollector = new PrimeCollector();
        scanner = new Scanner(System.in);
        running = true;
        threads = initThreads(threadCount);
    }

    private static Thread[] initThreads(int count){
        Thread[] threads = new Thread[count];
        for(int i = 0; i < count; i++){
            threads[i] = new Thread(new PrimeTester(primeList, primeCollector));
            threads[i].start();
        }
        return threads;
    }

    private static void endProgram() {
        running = false;
        for(Thread thread : threads)
            thread.interrupt();
        primeCollector.giveResults();
    }
}
