package test.java.lang.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author sj
 * @Title jdk8-learn
 * @description TODO
 * @date 2021/3/2 20:57
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation3 {

//    Integer value();

    AnnotationX value() default @AnnotationX(flag = true);

}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface AnnotationX{
    boolean flag() default false;
}