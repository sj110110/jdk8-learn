package test.java.lang;

/**
 * @author sj
 * @Title Outer4
 * @description 静态内部类
 * @date 2021/3/15 21:22
 */
public class Outer4 {
    private static String name = "outer";

    private int num = 8;

    static class Inner4 {
        public void method(){
            System.out.println(name);//匿名内部类不可以访问外部类的成员变量
            Outer4.method();
        }
    }

    public static void method() {
        System.out.println("out method");
    }

    public static void main(String[] args) {
        Outer4.Inner4 inner4 = new Outer4.Inner4();
        inner4.method();
    }
}
