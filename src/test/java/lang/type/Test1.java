package test.java.lang.type;

import java.util.ArrayList;
import java.util.List;

/**
 * 演示自动装箱和拆箱的场景
 */
public class Test1 {

    public static void main(String[] args) {
        Integer integer1 = new Integer(10);
        Integer integer2 = new Integer(10);
        System.out.println(integer1 == integer2);
        System.out.println(integer1.equals(integer2));

        Integer integer11 = 10;
        Integer integer12 = 10;
        System.out.println(integer11 == integer12);
        System.out.println(integer11.equals(integer12));

//        //将基本类型放入集合中
//        List<Integer> list = new ArrayList<>();
//        list.add(12);
//        list.add(13);
//        list.add(15);

//        //基本类型与包装类进行大小比较
//        int n1 = 1;
//        Integer n2 = new Integer(2);
//        System.out.println(n1 > n2);

//        int a1 = 22;
//        Integer a2 = new Integer(25);
//        System.out.println(a1 - a2);

        //演示缓存带来的空指针异常问题
        

    }
}
