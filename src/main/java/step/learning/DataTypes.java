package step.learning;

import step.learning.anno.DemoClass;
import step.learning.anno.EntryPoint;

@DemoClass
public class DataTypes {
    @EntryPoint
    public void  run ()
    {
        // Primitives - value types
        byte bx = -100;         // В Java все числовые типы - знаковые
        byte by = -0x3C;
        short sx = -30000;
        int ix = 20;
        long lx = 100L;
        float fx = 0.1f;        //  0.1 double
        float fy = (float)0.1;
        double dx = 1.5e-7;
        char c = 'A';           // UTF-16 (2 байта)
        boolean b = true;

        System.out.println(ConsoleColors.BLUE_BOLD + bx + ConsoleColors.YELLOW_BOLD + "\t\t (byte)");
        System.out.println(ConsoleColors.BLUE_BOLD + by + ConsoleColors.YELLOW_BOLD + "\t\t\t (byte)");
        System.out.println(ConsoleColors.BLUE_BOLD + sx + ConsoleColors.YELLOW_BOLD + "\t\t (short)");
        System.out.println(ConsoleColors.BLUE_BOLD + ix + ConsoleColors.YELLOW_BOLD + "\t\t\t (int)");
        System.out.println(ConsoleColors.BLUE_BOLD + lx + ConsoleColors.YELLOW_BOLD + "\t\t\t (long)");
        System.out.println(ConsoleColors.BLUE_BOLD + fx + ConsoleColors.YELLOW_BOLD + "\t\t\t (float)");
        System.out.println(ConsoleColors.BLUE_BOLD + fy + ConsoleColors.YELLOW_BOLD + "\t\t\t (float)");
        System.out.println(ConsoleColors.BLUE_BOLD + dx + ConsoleColors.YELLOW_BOLD + "\t\t (double)");
        System.out.println(ConsoleColors.BLUE_BOLD + c + ConsoleColors.YELLOW_BOLD + "\t\t\t (char)");
        System.out.println(ConsoleColors.BLUE_BOLD + b + ConsoleColors.YELLOW_BOLD + "\t\t (boolean)");
        System.out.print(ConsoleColors.RESET);
    }
}
