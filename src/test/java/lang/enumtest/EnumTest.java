package test.java.lang.enumtest;

import java.util.Arrays;

/**
 * @author sj
 * @Title EnumTest
 * @description TODO
 * @date 2021/3/9 18:45
 */
public class EnumTest {
    public static void main(String[] args) {

        System.out.println(MyEnum1.AA);
        MyEnum1[] values = MyEnum1.values();
        System.out.println(Arrays.toString(values));
        MyEnum1 aa = MyEnum1.valueOf("AA");
        System.out.println(aa);
    }
}
