package test.java.lang;

/**
 * @author sj
 * @Title Outer7
 * @description 通过内部类，实现“多继承”
 * @date 2021/3/16 19:40
 */
public class Outer7 {

    private int a = 10;

    class Inner7{
        public void show(){
            System.out.println(a);
        }
    }

}
