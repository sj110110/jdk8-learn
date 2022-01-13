package test.java.lang.annotation;

/**
 * @author sj
 * @Title MyMethodTest
 * @description TODO
 * @date 2021/3/8 18:59
 */
public class MyMethodTest {

    public static void main(String[] args){
        System.out.println("main start .");
        method();
    }

    @MethodTest
    public static void method(){
        System.out.println("method start .");
    }
}
