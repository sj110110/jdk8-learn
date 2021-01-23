package test.java.lang.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author sj
 * @title: MethodTest
 * @description: TODO
 * @date 2021/1/1820:32
 */
public class MethodTest {

    public static void main(String[] args) {
        try {
            Class clazz = Class.forName("test.java.lang.reflect.MethodDemo");
//            //获取所有
//            Method[] methods =  clazz.getDeclaredMethods();
//            System.out.println(Arrays.toString(methods));
//            //获取public方法
//            Method[] methods1 = clazz.getMethods();
//            System.out.println(Arrays.toString(methods1));
            //根据方法名称和参数列表类型获取Method
//            Method setNameMethod = clazz.getMethod("setName", String.class);
//            Method setNameMethod2 = clazz.getMethod("setName", String.class);
//
//            //获取参数列表类型
//            Class<?>[] parameterTypes = setNameMethod.getParameterTypes();
//            System.out.println(Arrays.toString(parameterTypes));
            //获取该方法的声明类类型
//            Class<?> declaringClass = setNameMethod.getDeclaringClass();//class test.java.lang.reflect.MethodDemo
//            System.out.println(declaringClass);
            //返回该方法对象的默认返回值
//            Object defaultValue = setNameMethod.getDefaultValue();
//            System.out.println(defaultValue);
            Method setNameMethod = clazz.getMethod("setName", String.class);
            MethodDemo methodDemo = new MethodDemo();
            for(int i = 0; i < 16; i++){
                setNameMethod.invoke(methodDemo, "张三"+i);
                System.out.println(methodDemo.getName());
            }
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
