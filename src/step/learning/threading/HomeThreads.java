package step.learning.threading;

import step.learning.ConsoleColors;
import step.learning.anno.DemoClass;
import step.learning.anno.EntryPoint;

import java.util.Scanner;

@DemoClass
public class HomeThreads {
    Scanner kbScanner = new Scanner(System.in);

    @EntryPoint
    public void run(){
        System.out.print("Enter N: ");
        int n = kbScanner.nextInt();
        for(int i = 1; i <= n ; i++ ){      // loop that creates threads
            new MyThread(i).start();
        }
    }

    static class MyThread
            extends  Thread{     // The run method has no overloads, so it takes no parameters.
        private final int thread;    // stores number of thread

        public MyThread(int arg) {
            this.thread = arg;
        }

        @Override
        public void run() {     // 'thread works' have underline style
            System.out.println(ConsoleColors.RED_UNDERLINED + thread + " threads are working" + ConsoleColors.RESET);
        }

    }
}
