package test.java.lang.annotation;

import java.lang.annotation.*;
import java.util.Arrays;

/**
 * @author sj
 * @Title MyAnnotation2
 * @description 演示@Document注解的作用
 * @date 2021/3/2 20:06
 */
public class MyAnnotation2{
    public static void main(String[] args) {
        ClassA1 classA1 = new ClassA1();
        Annotation[] annotations1 = classA1.getClass().getAnnotations();
        System.out.println(Arrays.toString(annotations1));

        ClassB1 classB1 = new ClassB1();
        Annotation[] annotations2 = classB1.getClass().getAnnotations();
        System.out.println(Arrays.toString(annotations2));
    }

}

@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotationA1 {
}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotationB1 {
}

@MyAnnotationA1
class ClassA {

}

class ClassA1 extends ClassA{

}

@MyAnnotationB1
class ClassB{

}

class ClassB1 extends ClassB{

}