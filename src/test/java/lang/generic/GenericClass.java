package test.java.lang.generic;

/**
 * @author sj
 * @Title GenericClass 泛型类
 * @description TODO
 * @date 2021/3/24 19:29
 */
public class GenericClass<T> {
    private T t;

    public GenericClass(T t) {
        this.t = t;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public static void main(String[] args) {
        GenericClass<String> genericClass = new GenericClass<>("aaa");
        String t = genericClass.getT();
        System.out.println(t);
    }
}
