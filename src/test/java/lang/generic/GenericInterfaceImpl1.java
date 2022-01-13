package test.java.lang.generic;

/**
 * @author sj
 * @Title GenericInterfaceImpl1
 * @description 指定具体参数类型的泛型接口实现类
 * @date 2021/4/10 10:54
 */
public class GenericInterfaceImpl1 implements GenericInterface<String>{
    @Override
    public String next() {
        System.out.println("next ");
        return "hello";
    }
}
