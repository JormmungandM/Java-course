package step.learning.anno;

@MarkerAnnotation
public class ClassWithAnnotation {



    @FieldAnnotation( value = "For version 1.0", priority = 1 )
    public int field1;

    @FieldAnnotation( value = "For version 1.1", priority = 2 )
    public String field2;

    public ClassWithAnnotation()
    {                                                       // Random number
        field1 = ( int )( Math.random() * 40 );             // for int
        field2 = String.valueOf(Math.random() * 40);     // for string
    }

    @MethodAnnotation( "Deprecated" )
    public void method1() {
        System.out.println( "method1" ) ;
    }

    @MethodAnnotation( "Recommended" )
    private void method2() {
        System.out.println( "method2" ) ;
    }
}