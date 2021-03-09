package test.java.lang.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author sj
 * @Title PersonNameTest
 * @description TODO
 * @date 2021/3/6 10:14
 */
public class PersonNameTest {
    public static void main(String[] args) throws NoSuchMethodException {
        //通过Class对象拿到getPerson方法对象Method
        Method method = PersonNameTest.class.getDeclaredMethod("getPerson", null);
        //根据Method获取到该方法上的注解
        Personname annotation = method.getAnnotation(Personname.class);
        System.out.println(annotation);
        String name = annotation.name();
        System.out.println(name);
        Class<? extends Annotation> aClass = annotation.annotationType();
        System.out.println(aClass);
    }

    @Personname
    public static String getPerson(){
        return "";
    }
}
