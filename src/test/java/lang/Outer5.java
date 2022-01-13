package test.java.lang;

/**
 * @author sj
 * @Title Outer5
 * @description 同一个包下，其它外部类无法访问被private修饰的内部类
 * @date 2021/3/16 19:13
 */
public class Outer5 {

    private class Inner5{
        private int a = 10;

        public void num(){
            System.out.println(a);
        }
    }
}

class Other5{

    public void test(){
        Outer5 outer5 = new Outer5();
//        Outer5.Inner5 inner5 = outer5.new Inner5();//这里编译报错
    }
}