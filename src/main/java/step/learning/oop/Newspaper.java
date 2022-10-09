package step.learning.oop;


import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Newspaper
        extends Literature
        implements Periodic, Printable, Serializable {
    private Date date;
    private Calendar calendar;
    static private final SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd");
    static private final SimpleDateFormat datePrinter= new SimpleDateFormat("dd.MM.yyyy");
    static private final SimpleDateFormat datePrinterShort= new SimpleDateFormat("dd.MM");



    public Date getDate() {
        return date;
    }

    public Newspaper setDate( String dateString ) throws ParseException {
        this.date = dateParser.parse( dateString ) ;
        this.calendar = Calendar.getInstance();
        calendar.setTime(this.date);
        return this;
    }

    @Override
    public Newspaper setTitle(String title) {
        return (Newspaper) super.setTitle(title);
    }

    private String Date()
    {
        Calendar now = Calendar.getInstance();
        if(now.get(Calendar.DATE) == calendar.get(Calendar.DATE)){
            return "Today";
        }
        else if (now.get(Calendar.YEAR) == calendar.get(Calendar.YEAR)){
            return datePrinterShort.format(this.getDate());
        }
        else{
            return datePrinter.format(this.getDate());
        }
    }

    @Override
    public void print() {
        System.out.printf( "Newspaper. Date: %s. Title: %s%n", Date(), super.getTitle());
    }

}
