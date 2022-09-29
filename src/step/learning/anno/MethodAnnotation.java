package step.learning.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)  // annotation available at run
@Target(ElementType.METHOD)          // annotation for marking methods
public @interface MethodAnnotation {
    // annotation with value,
    // value - default name,
    // "" - default value.
    String value() default "" ;
}
