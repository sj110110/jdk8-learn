package test.java.lang.reflect;

import java.util.Date;

/**
 * @author sj
 * @title: ConstructorDemo1
 * @description: TODO
 * @date 2021/1/15 14:27
 */
public class ConstructorDemo {

    private Integer id;

    private String name;

    private ConstructorDemo() {
    }

    private ConstructorDemo(Integer id) {
        this.id = id;
    }

    public ConstructorDemo(String name) {
        this.name = name;
    }

//    @Deprecated
//    public ConstructorDemo(Integer id, String name) throws Exception{
//        this.id = id;
//        this.name = name;
//    }

    public ConstructorDemo(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "ConstructorDemo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
