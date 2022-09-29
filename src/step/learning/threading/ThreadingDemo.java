package step.learning.threading;

import step.learning.ConsoleColors;
import step.learning.anno.DemoClass;
import step.learning.anno.EntryPoint;

@DemoClass
public class ThreadingDemo {
    @EntryPoint
    public void demo(){
        new PrinterThread().start();

        new ArgThread( "arg1" ).start();

        ArgThread argThread = new ArgThread();
        argThread.setArg("arg2");
        argThread.start();

        new Thread(){   // Anonymous object of an anonymous class
            @Override   // Redefine the class and immediately create an object
            public void run()
            {
                System.out.println(ConsoleColors.GREEN_BOLD + "Anon thread" + ConsoleColors.RESET);
            }
        }.start();

        new Thread( new PrintRunnable() ).start();

        new Thread( new Runnable(){     // Anonymous implementation
            @Override
            public void run() {
                System.out.println( ConsoleColors.GREEN_BOLD + "Anon Runnable" + ConsoleColors.RESET );
            }
        }).start();

        Runnable runnable = () -> {
            System.out.println( ConsoleColors.PURPLE_BOLD + "Arrow runnable" + ConsoleColors.RESET );
        };
        new Thread(runnable).start();

        new Thread( () -> System.out.println(  ConsoleColors.PURPLE_BOLD + "Arrow in Thread" + ConsoleColors.RESET )).start();

        new Thread( this::printDemo ) ;  // :: - Runnable from this.printDemo

        System.out.println( ConsoleColors.BLUE_BOLD + "Threading demo" + ConsoleColors.RESET );
    }

    private void printDemo() {
        System.out.println(
                ConsoleColors.GREEN_BOLD_BRIGHT + "printDemo method" + ConsoleColors.RESET ) ;
    }

    static class PrintRunnable
            implements Runnable{ // interface for running tasks (~functor, ~lambda)

        @Override
        public void run() {
            System.out.println( " PrintRunnable " );
        }
    }


    static class PrinterThread
            extends Thread {    // Threading is based on the Thread class.
        private String arg;

        @Override  //  The overloaded run method defines what gets executed.
        public void run(){
            System.out.println( "PrinterThread works" );
        }
    }

    static class ArgThread       // The run method has no overloads, so it takes no parameters.
            extends Thread {
        private String arg ;     // To switch parameters in a stream, they are encapsulated

        @Override
        public void run() {
            System.out.println( "Arg thread: " + this.arg ) ;
        }
        public void setArg(String arg) {
            this.arg = arg;
        }
        public ArgThread() {
            this.arg = "";
        }
        public ArgThread(String arg) {
            this.arg = arg;
        }
    }

}
