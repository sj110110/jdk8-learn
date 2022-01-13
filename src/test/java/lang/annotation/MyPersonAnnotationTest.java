package test.java.lang.annotation;


import java.lang.reflect.Method;

/**
 * @author sj
 * @Title MyPersonAnnotationTest
 * @description 使用自定义注解
 * @date 2021/3/3 19:28
 */
public class MyPersonAnnotationTest {
    public static void main(String[] args) throws NoSuchMethodException {
        process(MyPersonAnnotationTest.class);
    }

    @MyPersonAnnotation(name = "zhangsan", age = 18, character = Character.GENTLE)
    private static void getPersonInfo() {
    }

    private static void process(Class clazz) throws NoSuchMethodException {
        //通过Class对象拿到getPersonInfo方法对象Method
        Method method = clazz.getDeclaredMethod("getPersonInfo", null);
        System.out.println(method.getName());
        //根据Method获取到该方法上的注解
        MyPersonAnnotation declaredAnnotations = method.getAnnotation(MyPersonAnnotation.class);
        System.out.println(declaredAnnotations);
    }

}
