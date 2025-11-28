package org.example.Anotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    String name() default "kirill";
    int dateCreate() default 2025;
    String description() default "test";
}
