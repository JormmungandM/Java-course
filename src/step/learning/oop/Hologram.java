package step.learning.oop;

public class Hologram
        extends Literature {

    public Hologram setTitle(String Title){
        return (Hologram) super.setTitle(Title);
    }

    @Override
    public void print() {

    }

}
