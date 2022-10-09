package step.learning.services;


import java.text.SimpleDateFormat;
import java.util.Date;


public class DataTime
        implements DataTimeService {    // использует интерфейс DataTimeService

    public String getDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");   // pattern for date
        return simpleDateFormat.format(new Date());                                     // data formatting
    }

    public String getTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");   // pattern for time
        return simpleDateFormat.format(new Date());                                   // time formatting
    }

}
