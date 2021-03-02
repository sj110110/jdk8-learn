package test.java.lang;

import java.util.List;
import java.util.Map;

/**
 * @author sj
 * @Title jdk8-learn
 * @description TODO
 * @date 2021/2/25 19:38
 */
public class ObjectState {

    private final int num;

    private final Map<String,Object> map;

    public ObjectState(int num, Map<String, Object> map) {
        this.num = num;
        this.map = map;
    }

    public void modifyMap(){
        this.map.put("obj1",new Object());
    }
}
