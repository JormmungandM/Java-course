package step.learning.anno;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@DemoClass
public class AnnotationsDemo {
    @FieldAnnotation( value = "For all versions", priority = -1 )
    private String separator = "------------------------------------------------";
    @MethodAnnotation( "Entry Point" )
    @EntryPoint
    public void run() {
        // Extract annotation - get information about the type
        Class<?> type = ClassWithAnnotation.class ;  // by class
        Class<?> thisType = this.getClass() ;        // by object
        Class<?> nameType ;
        try {
            nameType = Class.forName(                // by class name
                    "step.learning.anno.ClassWithoutAnnotation" ) ;
        } catch( ClassNotFoundException ex ) {
            System.out.println( "Class not found: " + ex.getMessage() ) ;
            return ;
        }
        // region Check class annotations
        // Request an annotation from a type
        MarkerAnnotation marker = type.getAnnotation( MarkerAnnotation.class ) ;
        if( marker != null )
            System.out.printf( "Class '%s' has MarkerAnnotation%n", type.getName() ) ;
        else
            System.out.printf( "Class '%s' has no MarkerAnnotation%n", type.getName() ) ;

        marker = thisType.getAnnotation( MarkerAnnotation.class ) ;
        if( marker != null )
            System.out.printf( "Class '%s' has MarkerAnnotation%n", thisType.getName() ) ;
        else
            System.out.printf( "Class '%s' has no MarkerAnnotation%n", thisType.getName() ) ;

        marker = nameType.getAnnotation( MarkerAnnotation.class ) ;
        if( marker != null )
            System.out.printf( "Class '%s' has MarkerAnnotation%n", nameType.getName() ) ;
        else
            System.out.printf( "Class '%s' has no MarkerAnnotation%n", nameType.getName() ) ;
        // endregion
        System.out.println( separator ) ;

        // region Check method annotations
        Method[] methods = type.getDeclaredMethods() ;   // array of methods (except inherited ones)
        Object obj ;
        try { obj = type.getDeclaredConstructor().newInstance() ; }
        catch( Exception ex ) {
            System.out.println( "Instantiate error: " + ex.getMessage() ) ;
            return;
        }
        for( Method method : methods ) {
            if( method.isAnnotationPresent( MethodAnnotation.class ) ) {
                MethodAnnotation methodAnnotation = method.getAnnotation( MethodAnnotation.class ) ;
                System.out.printf( "Method '%s' of class '%s' does have '%s' method annotation%n",
                        method.getName(), type.getName(), methodAnnotation.value() ) ;
                // method launch - for Recommended we launch, for Deprecated we issue a refusal
                try {
                    method.setAccessible( true ) ;  // to run inaccessible methods (private)
                    method.invoke( obj ) ;
                } catch( IllegalAccessException | InvocationTargetException ex ) {
                    System.out.println( "Invoke error: " + ex.getMessage() ) ;
                }
            }
            else {
                System.out.printf( "Method '%s' of class '%s' has not method annotation%n",
                        method.getName(), type.getName() ) ;
            }
        }
        // endregion
        System.out.println( separator ) ;

        // region Field annotations
        Field[] fields = type.getDeclaredFields() ;
        for( Field field : fields ) {
            if( field.isAnnotationPresent( FieldAnnotation.class ) ) {
                FieldAnnotation fieldAnnotation = field.getAnnotation( FieldAnnotation.class ) ;
                System.out.printf( "Field '%s' of  class '%s' if %s with priority %d%n",
                        field.getName(), type.getName(),
                        fieldAnnotation.value(), fieldAnnotation.priority());
            }
            else {
                System.out.printf( "Field '%s' of  class '%s' has no annotation%n",
                        field.getName(), type.getName() ) ;
            }
        }
    }
}
