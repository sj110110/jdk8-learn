package test.java.lang.generic.four;

/**
 * @author sj
 * @Title jdk8-learn
 * @description TODO
 * @date 2021/4/22 11:12
 */
public class ArrayTest {
    public static void main(String[] args) {
        Integer[] num = {10};

        func(num);
        System.out.println(num[0]);
    }

    private static void func(Integer[] num) {
        num[0] = 8;
    }
}
