package test.java.lang;

/**
 * @author sj
 * @Title Outer3
 * @description 匿名内部类
 * @date 2021/3/15 21:01
 */
public class Outer3 {

    private static String name = "outer";

    private int num = 10;

    public Inner3 method(final String name){
        return new Inner3(){

            private int num = 10;

            public String name(){
                return name;
            }
        };
    }

    public static void main(String[] args) {
        Outer3 outer3 = new Outer3();
        outer3.method("张三");

    }
}

//匿名内部类必须继承或者实现一个已存在的基类或者接口
class Inner3{

}
