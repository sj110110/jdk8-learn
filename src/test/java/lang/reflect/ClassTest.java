package test.java.lang.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ClassTest {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, NoSuchFieldException, IllegalAccessException, InstantiationException {
//        ClassDemo classDemo = new ClassDemo();
//        Class clazz = classDemo.getClass();
//        System.out.println(clazz);
//        Class clazz1 = ClassDemo.class;
//        System.out.println(clazz1);
//        Class clazz2 = Class.forName("test.java.lang.reflect.ClassDemo");
//        System.out.println(clazz2);
        ClassDemo classDemo = new ClassDemo();
        Class clazz = classDemo.getClass();
//        Constructor[] declaredConstructors = clazz.getDeclaredConstructors();
//        System.out.println(Arrays.toString(declaredConstructors));
        Constructor[] constructors = clazz.getConstructors();
        System.out.println(Arrays.toString(constructors));
        Constructor constructor = clazz.getConstructor(String.class);
        System.out.println(constructor);

        ClassDemo classDemo1 = (ClassDemo) clazz.newInstance();
        System.out.println(classDemo1);

//
//        Method[] declaredMethods = clazz.getDeclaredMethods();
//        System.out.println(Arrays.toString(declaredMethods));
//        Method[] methods = clazz.getMethods();
//        System.out.println(Arrays.toString(methods));
//        Method setNameMethod = clazz.getMethod("setName", String.class);
//        System.out.println(setNameMethod);

//        Field[] declaredFields = clazz.getDeclaredFields();
//        System.out.println(Arrays.toString(declaredFields));
//        Field[] fields = clazz.getFields();
//        System.out.println(Arrays.toString(fields));
//        Field name = clazz.getDeclaredField("name");
//        System.out.println(name);


//        int modifiers = clazz.getModifiers();
//        System.out.println(modifiers);
//        boolean anInterface = clazz.isInterface();
//        System.out.println(anInterface);
//        String clazzName = clazz.getName();
//        System.out.println(clazzName);
//        Class clazzSuperclass = clazz.getSuperclass();
//        System.out.println(clazzSuperclass);



    }
}
