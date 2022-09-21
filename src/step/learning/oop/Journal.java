package step.learning.oop;

public class Journal extends Literature {
    private Integer number;


    public Integer getNumber() {
        return number;
    }
    public Journal setNumber(Integer number) {
        this.number = number;
        return this;
    }

    public Journal setTitle(String title) {
        super.setTitle(title);
        return this;
    }

    // implementation "print" for journal
    public void print() {
        System.out.printf( "Journal. Number: %s. Title: %s%n",
                this.number, super.getTitle() ) ;
    }

}
