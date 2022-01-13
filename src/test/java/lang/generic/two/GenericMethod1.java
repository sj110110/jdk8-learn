package test.java.lang.generic.two;

/**
 * @author sj
 * @Title GenericMethod1
 * @description 泛型方法-泛型擦除演示
 * @date 2021/4/11 16:44
 */
public class GenericMethod1 {

    public <T> T getT(T t){
        return t;
    }

    public static void main(String[] args) {
        GenericMethod1 method1 = new GenericMethod1();
        String aa = method1.getT("aa");
    }
}
