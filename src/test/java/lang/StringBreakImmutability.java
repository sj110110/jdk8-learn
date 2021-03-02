package test.java.lang;

/**
 * @author sj
 * @Title StringBreakImmutability
 * @description TODO
 * @date 2021/2/27 14:59
 */

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * 通过反射打破String的不可变性
 */
public class StringBreakImmutability {


    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        StringBuffer sb = new StringBuffer();
        String str1 = new String("ABC");
        System.out.println("反射前："+str1);
        //获取Class对象
        Class clazz = str1.getClass();
        //获取字段Field对象信息
        Field value = clazz.getDeclaredField("value");
        value.setAccessible(true);
        //获取值
        char[] ch = (char[]) value.get(str1);
        ch[0] = 'B';
        System.out.println("反射后："+str1);
    }
}
