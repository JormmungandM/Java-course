package step.learning.oop;

import java.io.Serializable;

public class Poster
        extends Literature
        implements Periodic, Serializable {

    public Poster setTitle(String Title){
        return (Poster) super.setTitle(Title);
    }

    @Override
    public void print() {
        System.out.printf( "Poster. Title: %s%n", super.getTitle() ) ;
    }
}
