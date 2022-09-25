package step.learning.oop;

import step.learning.ConsoleColors;

import java.io.*;
import java.text.ParseException;
import java.util.*; // import all utils

public class Library implements Serializable {
    private final List<Literature> funds ; // this list contains all the literatures (book, journal and newspaper)

    public Library() {
        funds = new ArrayList<>() ; // create list
    }

    public void add(Literature literature) {
        funds.add(literature); // this method adds some literature
    }

    // method shows all literature
    public void showAllFunds(){
        for(Literature literature : funds) {
            literature.print();
        }
    }
    // method shows literature that implements 'Printable'
    public void showPrintable()
    {
        System.out.println(ConsoleColors.GREEN_BOLD + "Printable:" + ConsoleColors.RESET);
        for(Literature literature : funds) {
            if( literature instanceof Printable) // if object implements 'Printable', then show this literature
            {
                ((Printable)literature).print();
            }
        }
    }
    // method shows literature that hasn't implements 'Printable'
    public void showUnPrintable()
    {
        System.out.println(ConsoleColors.RED_BOLD + "Unprintable: " + ConsoleColors.RESET);
        for(Literature literature : funds) {
            if( !(literature instanceof Printable)) // if object hasn't implements 'Printable', then show this literature
            {
                System.out.println(literature.getTitle());
            }
        }
    }

    // method shows literature that implements 'Periodic'
    public void showPeriodic()
    {
        System.out.println(ConsoleColors.BLUE_BOLD + "Periodic: " + ConsoleColors.RESET);
        for(Literature literature : funds) {
            if(literature instanceof Periodic)  // if object implements 'Periodic'
            {
                if (literature instanceof Printable)  // if object implements 'Periodic' & 'Printable'
                {
                    literature.print();
                }
                else  // if object implements only 'Periodic'. The 'Periodic' hasn't printing method
                {
                    System.out.println(literature.getTitle()) ;
                }
            }
        }
    }

    // method shows literature that hasn't implements 'Periodic'
    public void showNonPeriodic()
    {
        System.out.println(ConsoleColors.RED_BOLD + "NonPeriodic: " + ConsoleColors.RESET);
        for(Literature literature : funds) {
            if(!(literature instanceof Periodic))   // if object hasn't implements 'Periodic'
            {
                if (literature instanceof Printable)    // if object implements 'Printable'
                {
                    literature.print();
                }
                else // if object hasn't implements 'Periodic' and 'Printable'
                {
                    System.out.println(literature.getTitle()) ;
                }
            }
        }
    }

    public void serializeFunds()
    {
        add(new Book().setAuthor("J. R. R. Tolkien").setTitle("The Lord of the Rings"));    // Adds new book
        add(new Journal().setNumber(322).setTitle("RandomName"));                           // Adds new journal
        add(new Hologram().setTitle("Pectoral"));                                           // Adds new hologram
        try{
            add(new Newspaper().setDate("2022-09-21").setTitle("Daily Planet"));        // Adds new newspaper
            add(new Newspaper().setDate("2022-09-22").setTitle("The Washing Post"));    // Adds new newspaper
            add(new Newspaper().setDate("2021-09-21").setTitle("The Daily Mail"));      // Adds new newspaper
        }
        catch (ParseException ex)
        {
            System.out.println("Funds creation failed: "+ ex.getMessage());
            return;
        }
        add(new Poster().setTitle("RandomPoster1"));    // Adds new poster
        add(new Poster().setTitle("RandomPoster2"));    // Adds new poster
        add(new Poster().setTitle("RandomPoster3"));    // Adds new poster


        try (FileOutputStream file = new FileOutputStream("fund_data.ser"))
        {
            ObjectOutputStream oos = new ObjectOutputStream(file);
            oos.writeObject(this.funds);
            oos.flush();
            System.out.println( "List serialized" );
        }
        catch (IOException ex)
        {
            System.out.println( "List error serialization: " + ex.getMessage() );
            return;
        }
    }

    public void deserializeFunds()
    {
        try (FileInputStream file = new FileInputStream("fund_data.ser"))
        {
            ObjectInputStream ois = new ObjectInputStream(file);
            List<?> list = (List<?>) ois.readObject();                          // temporally list for read file
            System.out.printf("Fund data size: %s object(s) %n",list.size());   // shows fund data size
            for( Object data : list) {
                if(data instanceof Literature){    // object type check
                    add((Literature)data);         // adds to funds
                }
            }
            System.out.println("End of deserialization");
        }
        catch (Exception ex)
        {
            System.out.println( "List error deserialization: " + ex.getMessage() );
            return;
        }
    }

    public void Run() {
        // serializeFunds();    // serialize only once
        deserializeFunds();     // every run deserialize

        System.out.print("""
                1. All
                2. Periodic & NonPeriodic
                3. Printable & UnPrintable
                0. Exit
                Enter choose(1,2...):\s""");
        Scanner kbScanner = new Scanner(System.in);
        String str = kbScanner.nextLine();
        System.out.println(" ");
        switch (str)
        {
            case "1":                       // All
                showAllFunds();
                break;
            case "2":                       // Periodic & NonPeriodic
                showPeriodic();
                showNonPeriodic();
                break;
            case "3":                       // Printable & UnPrintable
                showPrintable();
                showUnPrintable();
                break;
            default:
                return;
        }
    }
}