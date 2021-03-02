package test.java.lang.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author sj
 * @Title MyAnnotation1
 * @description 这是一个没有任何元素的注解，这个注解的作用起到标识的作用
 * @date 2021/3/1 19:49
 */
@MyAnnotation
public class MyAnnotation1 {
    public static void main(String[] args) {
        System.out.println("反编译注解");
    }
}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation {
}