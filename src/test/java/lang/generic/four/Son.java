package test.java.lang.generic.four;

import java.util.List;

/**
 * @author sj
 * @Title jdk8-learn
 * @description TODO
 * @date 2021/4/21 20:11
 */
public class Son extends Person<List<Object>>{

    public Son(List<Object> objects) {
        super(objects);
    }
}
