package test.java.lang.generic.three;

/**
 * @author sj
 * @Title GenericObject
 * @description TODO
 * @date 2021/4/13 19:08
 */
public class GenericObject<T> {
    private T t;

    public GenericObject(T t) {
        this.t = t;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
