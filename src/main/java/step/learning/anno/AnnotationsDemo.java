package step.learning.anno;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@DemoClass
public class AnnotationsDemo {
    @FieldAnnotation( value = "For all versions", priority = -1 )
    public final String separator = "------------------------------------------------";

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

        //checkClassAnnotations(type);
        //checkClassAnnotations(thisType);
        //checkClassAnnotations(nameType);
        //System.out.println( separator ) ;

        // endregion


        // region Check method annotations
//        Method[] methods = type.getDeclaredMethods() ;   // array of methods (except inherited ones)
//        Object obj ;
//        try { obj = type.getDeclaredConstructor().newInstance() ; }
//        catch( Exception ex ) {
//            System.out.println( "Instantiate error: " + ex.getMessage() ) ;
//            return;
//        }
//        for( Method method : methods ) {
//            if( method.isAnnotationPresent( MethodAnnotation.class ) ) {
//                MethodAnnotation methodAnnotation = method.getAnnotation( MethodAnnotation.class ) ;
//                System.out.printf( "Method '%s' of class '%s' does have '%s' method annotation%n",
//                        method.getName(), type.getName(), methodAnnotation.value() ) ;
//                // method launch - for Recommended we launch, for Deprecated we issue a refusal
//                try {
//                    method.setAccessible( true ) ;  // to run inaccessible methods (private)
//                    method.invoke( obj ) ;
//                } catch( IllegalAccessException | InvocationTargetException ex ) {
//                    System.out.println( "Invoke error: " + ex.getMessage() ) ;
//                }
//            }
//            else {
//                System.out.printf( "Method '%s' of class '%s' has not method annotation%n",
//                        method.getName(), type.getName() ) ;
//            }
//        }
//
//        System.out.println( separator ) ;
        // endregion


        // region Check field annotations

        //checkFieldAnnotations(type);
        //checkFieldAnnotations(nameType);
        //System.out.println( separator ) ;

        // endregion


        // region Homework

        Object temp = new ClassWithAnnotation();
        checkFieldValue(temp);
        checkFieldValue(type);
        checkFieldValue(this.getClass());
        checkFieldValue(ClassWithoutAnnotation.class);

        // endregion
    }

    private void checkClassAnnotations(Class<?> ClassCheck){
        MarkerAnnotation marker = ClassCheck.getAnnotation( MarkerAnnotation.class ) ;
        if( marker != null )
            System.out.printf( "Class '%s' has MarkerAnnotation%n", ClassCheck.getName() ) ;
        else
            System.out.printf( "Class '%s' has no MarkerAnnotation%n", ClassCheck.getName() ) ;
    }

    private void checkFieldAnnotations(Class<?> ClassCheck)
    {

        Field[] fields = ClassCheck.getDeclaredFields();
        for( Field field : fields ) {
            if( field.isAnnotationPresent( FieldAnnotation.class ) ) {
                FieldAnnotation fieldAnnotation = field.getAnnotation( FieldAnnotation.class ) ;
                System.out.printf( "Field '%s' of class '%s' if %s with priority %d%n",
                        field.getName(), ClassCheck.getName(),
                        fieldAnnotation.value(), fieldAnnotation.priority());
            }
            else {
                System.out.printf( "Field '%s' of class '%s' has no annotation%n",
                        field.getName(), ClassCheck.getName() ) ;
            }
        }
    }

    private void checkFieldValue(Object ObjectCheck)
    {

        Class<?> ClassCheck;                            // get object class
        if (ObjectCheck instanceof Class<?>){           // if ObjectCheck is of type Class<?>, then unpacking
            ClassCheck = (Class<?>) ObjectCheck;
        }
        else ClassCheck = ObjectCheck.getClass();       // else get class

        Field[] fields = ClassCheck.getDeclaredFields();    // fields array
        for( Field field : fields ) {
            if( field.isAnnotationPresent( FieldAnnotation.class ) ) {  // check FieldAnnotation

                try {                                       // Method cannot accept empty objects and the check for null was always false
                    if(ObjectCheck instanceof Class<?>)     //  I did this because I need to create a new object when ObjectCheck is of this type
                    {
                        ObjectCheck = ClassCheck.getDeclaredConstructor().newInstance() ;
                    }
                    System.out.printf( "Field '%s' of class '%s' has a type '%s' and value: %s %n",
                            field.getName(), ObjectCheck.getClass(),
                            field.getType(), field.get(ObjectCheck));
                }
                catch( Exception ex ) {
                    System.out.println( "Instantiate error: " + ex.getMessage() ) ;
                    return;
                }
            }
            else {  // called when the object does not have an annotation field
                System.out.printf( "Field '%s' of class '%s' has no annotation%n",
                        field.getName(), ClassCheck.getName() ) ;
            }
        }
    }

}
