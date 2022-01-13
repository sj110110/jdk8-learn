package test.java.lang;

/**
 * @author sj
 * @Title OuterClass1
 * @description 内部类
 * @date 2021/3/15 19:57
 */
public class OuterClass1 {

    class InnerClass{

        public void name(){
            System.out.println("InnerClass.");
        }
    }

    public void name(){
        System.out.println("OuterClass");
    }

    public static void main(String[] args) {
        OuterClass1 outerClass1 = new OuterClass1();
        outerClass1.name();

        OuterClass1.InnerClass innerClass = outerClass1.new InnerClass();
        innerClass.name();
    }
}
