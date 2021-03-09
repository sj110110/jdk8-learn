package test.java.lang.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author sj
 * @Title MyPersonAnnotation
 * @description TODO
 * @date 2021/3/3 19:16
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyPersonAnnotation {

    //姓名
    String name() default "";

    //年龄
    int age() default 0;

    //性格
    Character character() default Character.OPEN;

}

enum Character{
    OPEN,
    IRRITABLE,
    GENTLE;
}