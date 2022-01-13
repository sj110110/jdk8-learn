package test.java.lang.generic.three;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sj
 * @Title GenericDemo
 * @description TODO
 * @date 2021/4/14 18:53
 */
public class GenericDemo<T> {

    private T t;

    public GenericDemo(T t) {
        this.t = t;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public static void main(String[] args) {
        GenericDemo<String> demo1 = new GenericDemo<>("aaa");
        GenericDemo<Integer> demo2 = new GenericDemo<>(12);

        System.out.println(demo1.getClass().isInstance(demo2));

//        List<String> list1 = new ArrayList<>();
//        List<Integer> list2 = new ArrayList<>();
////        System.out.println(list1 instanceof List<Integer>);

        List<String> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        System.out.println(list1.getClass() == list2.getClass());
        System.out.println(list1.getClass().isInstance(list2));
    }
}
