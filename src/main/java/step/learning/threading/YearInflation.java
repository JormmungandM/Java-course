package step.learning.threading;

import step.learning.anno.DemoClass;
import step.learning.anno.EntryPoint;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@DemoClass
public class YearInflation {

    private final NumberFormat ResultFormat = new DecimalFormat("#.##");    // Used to shorten the result

    @EntryPoint
    public void run(){

        // Scanner for input
        Scanner kbScanner = new Scanner(System.in);

        // User values for calculate inflation
        System.out.print("Enter value before inflation: ");
        double a = kbScanner.nextDouble();
        System.out.print("Enter value after yearly inflation: ");
        double b = kbScanner.nextDouble();

        ExecutorService pool = Executors.newFixedThreadPool( 3 );   // Create the pool

        double inflation;   // stores the result

        // calculation
        if(a>b) inflation = (a / b - 1 ) * 100;
        else inflation = (b / a - 1 ) *  100;

        pool.submit( () -> System.out.println( "Inflation amounted to " + ResultFormat.format( inflation ) + "%" ) );
        pool.shutdown();    // Close the pool
    }
}
