package step.learning.oop;

public class Poster
        extends Literature
        implements Periodic{

    public Poster setTitle(String Title){
        return (Poster) super.setTitle(Title);
    }

    @Override
    public void print() {
    }
}
