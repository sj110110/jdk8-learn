package test.java.lang.generic.two;

/**
 * @author sj
 * @Title GeneriClassDemo
 * @description 演示泛型擦除
 * @date 2021/4/11 15:27
 */
public class GenericClassDemo<T> {

    private T t;

    public GenericClassDemo(T t) {
        this.t = t;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public static void main(String[] args) {
        GenericClassDemo<String> demo = new GenericClassDemo<>("demo");
        String t = demo.getT();
        System.out.println(t);
    }
}
