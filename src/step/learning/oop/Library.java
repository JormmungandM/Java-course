package step.learning.oop;

import step.learning.ConsoleColors;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.util.*; // import all utils

public class Library {
    private final List<Literature> funds ; // this list contains all the literatures (book, journal and newspaper)

    public Library() {
        funds = new ArrayList<>() ; // create list
    }

    public void add(Literature literature) {
        funds.add(literature); // this method adds some literature
    }


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
                    System.out.println(ConsoleColors.GREEN_BOLD + "Unprintable and nonPeriodic: " + ConsoleColors.RESET + literature.getTitle()) ;
                }
            }
        }
    }

    public void Run() {
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

        // Shows the literature who implements Printable
        // showPrintable();
        // showUnPrintable();

        // Shows the literature who implements Periodic
        showPeriodic();
        showNonPeriodic();
    }
}