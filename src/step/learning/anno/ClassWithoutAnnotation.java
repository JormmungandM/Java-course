package step.learning.anno;

public class ClassWithoutAnnotation {

    public int field1;
    public String field2;

    public ClassWithoutAnnotation()
    {                                                       // Random number
        field1 = ( int )( Math.random() * 40 );             // for int
        field2 = String.valueOf(Math.random() * 40);     // for string
    }

    public void method1() {}
    public void method2() {}
}