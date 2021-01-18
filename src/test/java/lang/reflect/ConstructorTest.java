package test.java.lang.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Arrays;

/**
 * @author sj
 * @title: ConstructorDemo
 * @description: 学习反射Constructor类源码
 * @date 2021/1/1417:02
 */
public class ConstructorTest {

    public static void main(String[] args) {
        try {
//            Class clazz = Class.forName("test.java.lang.reflect.ConstructorDemo");
//            Constructor[] declaredConstructors = clazz.getDeclaredConstructors();
//            System.out.println(Arrays.toString(declaredConstructors));
//            Constructor[] constructors = clazz.getConstructors();
//            System.out.println(Arrays.toString(constructors));
//            Constructor constructor1 = clazz.getConstructor(Integer.class, String.class);
//            System.out.println(constructor1);
//            Constructor constructor2 = clazz.getConstructor(Integer.class);
//            System.out.println(constructor2);
            Class clazz = Class.forName("test.java.lang.reflect.ConstructorDemo");
            Constructor constructor1 = clazz.getConstructor(Integer.class, String.class);
//            //获取构造器的参数列表类型
//            Class[] parameterTypes = constructor1.getParameterTypes();
//            System.out.println(Arrays.toString(parameterTypes));
//            Parameter[] parameters = constructor1.getParameters();
//            System.out.println(Arrays.toString(parameters));
//            Class declaringClass = constructor1.getDeclaringClass();
//            System.out.println(declaringClass);
//            Annotation[] declaredAnnotations = constructor1.getDeclaredAnnotations();
//            System.out.println(Arrays.toString(declaredAnnotations));
//            Class[] exceptionTypes = constructor1.getExceptionTypes();
//            System.out.println(Arrays.toString(exceptionTypes));
//            int modifiers = constructor1.getModifiers();
//            System.out.println(modifiers);
//            boolean varArgs = constructor1.isVarArgs();
//            System.out.println(varArgs);
            constructor1.setAccessible(true);
            ConstructorDemo constructorDemo1 = (ConstructorDemo) constructor1.newInstance(1, "zhangsan");
            System.out.println(constructorDemo1.toString());

        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException {
//        Class clazz = Class.forName("test.java.lang.reflect.ConstructorDemo");
//        /**
//         * 获取构造器器
//         */
//        //获取所有构造器
//        Constructor[] constructors = clazz.getDeclaredConstructors();
//        System.out.println(Arrays.toString(constructors));//[private test.java.lang.reflect.ConstructorDemo(java.lang.String), public test.java.lang.reflect.ConstructorDemo(java.lang.String,java.lang.Integer), public test.java.lang.reflect.ConstructorDemo()]
//        //获取公共构造器
//        Constructor[] constructors2 = clazz.getConstructors();
//        System.out.println(Arrays.toString(constructors2));   //[public test.java.lang.reflect.ConstructorDemo(), public test.java.lang.reflect.ConstructorDemo(java.lang.String,java.lang.Integer)]
//        //根据参数类型获取构造器
////        Constructor constructor = clazz.getConstructor(String.class);//这里无法获取到参数类型为String的构造器，因为该构造器的访问修饰防护private，报错：Exception in thread "main" java.lang.NoSuchMethodException: test.java.lang.reflect.ConstructorDemo.<init>(java.lang.String)
//        Constructor constructor = clazz.getConstructor(String.class, Integer.class);//可以获取到
//        System.out.println(constructor);//public test.java.lang.reflect.ConstructorDemo(java.lang.String,java.lang.Integer)
//
//        /**
//         * 构造器的使用
//         */
//        //获取该构造器的参数列表的类型
//        Class[] paramerTypeclasses = constructor.getParameterTypes();
//        System.out.println(Arrays.toString(paramerTypeclasses));//[class java.lang.String, class java.lang.Integer]
//        //获取该构造器所属类类型
//        Class declaringClass = constructor.getDeclaringClass();
//        System.out.println(declaringClass);//class test.java.lang.reflect.ConstructorDemo
//        //TODO 暂时不知道这个方法的作用
//        AnnotatedType annotatedType = constructor.getAnnotatedReceiverType();//constructor:public test.java.lang.reflect.ConstructorDemo(java.lang.String,java.lang.Integer)
//        System.out.println(annotatedType);//sun.reflect.annotation.AnnotatedTypeFactory$AnnotatedTypeBaseImpl@29453f44
//        //获取类型
//        AnnotatedType annotatedType1 = constructor.getAnnotatedReturnType();//constructor:public test.java.lang.reflect.ConstructorDemo(java.lang.String,java.lang.Integer)
//        System.out.println(annotatedType1);//sun.reflect.annotation.AnnotatedTypeFactory$AnnotatedTypeBaseImpl@5cad8086
//        //获取构造器上标注的注解
//        Annotation[] annotations = constructor.getDeclaredAnnotations();
//        System.out.println(Arrays.toString(annotations));//[]，如果我在某个构造器上标注了@Deprecated，则返回[@java.lang.Deprecated()]
//        //获取访问修饰符对应的整数值
//        int modifiers = constructor.getModifiers();
//        System.out.println(modifiers);
//        //放回构造器上的异常类型
//        Class[] execptionTypes = constructor.getExceptionTypes();
//        System.out.println(Arrays.toString(execptionTypes));//[class java.lang.Exception]
//        //获取声明该构造器对象抛出的异常
//        Type[] types = constructor.getGenericExceptionTypes();//[class java.lang.Exception]
//        System.out.println(Arrays.toString(types));
//        //获取构造器的参数数量
//        int paramNum = constructor.getParameterCount();
//        System.out.println(paramNum);//2
//        //返回构造器的参数列表
//        Parameter[] parameters = constructor.getParameters();
//        System.out.println(Arrays.toString(parameters));//[java.lang.String arg0, java.lang.Integer arg1]
//        //根据构造器生成对象实例
//        ConstructorTest constructorDemo1 = (ConstructorTest) constructor.newInstance("zhangsan",12);
//        boolean isAccessible = constructor.isAccessible();
//        System.out.println(isAccessible);
//    }
}
