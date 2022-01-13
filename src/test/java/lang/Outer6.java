package test.java.lang;

/**
 * @author sj
 * @Title Outer6
 * @description 当被private修饰的内部类实现某个接口，并进行向上转型，对外部类来说，接口的实现已经隐藏起来了，很好的体现了封装性
 * @date 2021/3/16 19:25
 */
public class Outer6 {

    private class Inner6 implements InterFace6{

        @Override
        public String getName() {
            System.out.println("inner .");
            return "innner";
        }
    }

    //对外提供内部类对象的方法
    public InterFace6 getInterFace6(){
        return new Inner6();
    }

    public static void main(String[] args) {
        Outer6 outer6 = new Outer6();
        InterFace6 interFace6 = outer6.getInterFace6();
        interFace6.getName();
    }
}

interface InterFace6{
    String getName();
}