package test.java.lang;

/**
 * @author sj
 * @Title Outer2
 * @description 局部内部类
 * @date 2021/3/15 20:17
 */
public class Outer2 {

    private static String name = "name";

    private int num = 12;

    public void method(){
        class Inner21{
            public void fun1(){
                System.out.println(name + num);
            }
        }
    }

    public static void method2(){
        class Inner22{
            public void fun1(){
                //System.out.println(name+num);局部内部类在静态方法中时只能访问外部类的静态成员
                System.out.println(name);
            }
        }

        Inner22 inner22 = new Inner22();
        inner22.fun1();
    }
}
