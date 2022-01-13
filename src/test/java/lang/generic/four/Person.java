package test.java.lang.generic.four;

/**
 * @author sj
 * @Title jdk8-learn
 * @description TODO
 * @date 2021/4/21 20:11
 */
public class Person<T> {

    private T t;

    public Person(T t) {
        this.t = t;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
