package step.learning.oop;

import java.io.Serializable;

public class Hologram
        extends Literature implements Serializable {

    public Hologram setTitle(String Title){
        return (Hologram) super.setTitle(Title);
    }

    @Override
    public void print() {
        System.out.printf( "Hologram. Title: %s%n", super.getTitle() ) ;
    }

}
