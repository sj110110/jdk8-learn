package test;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1,"zhangsan");
        map.put(2, "lisi");
        map.put(3, "wangwu");
        System.out.println(map.get(1));
    }
}
