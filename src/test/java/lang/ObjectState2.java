package test.java.lang;

import java.util.Map;

/**
 * @author sj
 * @Title jdk8-learn
 * @description TODO
 * @date 2021/2/25 19:38
 */
public class ObjectState2 {

    private final int num;

    private final String[] arr;

    public ObjectState2(int num, String[] arr) {
        this.num = num;
        this.arr = arr;
    }

    public void modifyMap(){
        this.arr[0] = "2";
    }
}
