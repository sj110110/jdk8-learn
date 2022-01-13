package test.java.lang;

/**
 * @author sj
 * @Title Outer1
 * @description 成员内部类
 * @date 2021/3/15 20:07
 */
public class Outer1 {
    private static String name = "name";

    private int count =2;

    class Inner1 {

        public void num(){
            System.out.println("InnerClass, name:" + name + " count:" +count);
        }
    }

    public void num(){
        System.out.println("OuterClass, name:" + name + " count:" +count);
    }

    public static void main(String[] args) {
        Outer1 outer1 = new Outer1();
        outer1.num();

        Outer1.Inner1 inner1 = outer1.new Inner1();
        inner1.num();
    }
}
