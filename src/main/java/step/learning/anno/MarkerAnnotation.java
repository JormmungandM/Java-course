package step.learning.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // annotation available at run
@Target(ElementType.TYPE)           // annotation for marking classes (types)
public @interface MarkerAnnotation {
}
