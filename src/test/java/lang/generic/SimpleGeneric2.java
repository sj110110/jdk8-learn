package test.java.lang.generic;

import java.util.List;

/**
 * @author sj
 * @Title 泛型的作用
 * @description TODO
 * @date 2021/4/10 9:50
 */
public class SimpleGeneric2<T> {

    T t;

    public SimpleGeneric2(T t) {
        this.t = t;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public static void main(String[] args) {
        SimpleGeneric2<String> stringSimpleGeneric2 = new SimpleGeneric2<>("aa");
        String aa = stringSimpleGeneric2.getT();

//        stringSimpleGeneric2.setT(1);
    }
}
