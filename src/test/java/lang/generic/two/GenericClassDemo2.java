package test.java.lang.generic.two;

import test.java.lang.generic.SimpleGeneric;

/**
 * @author sj
 * @Title GenericClassDemo2
 * @description 泛型擦除和边界
 * @date 2021/4/11 16:18
 */
public class GenericClassDemo2<T extends SimpleGeneric> {
    private T t;

    public GenericClassDemo2(T t) {
        this.t = t;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public static void main(String[] args) {
        GenericClassDemo2<SimpleGeneric> demo2 = new GenericClassDemo2<>(new SimpleGeneric("aa"));
        SimpleGeneric t = demo2.getT();
    }
}
