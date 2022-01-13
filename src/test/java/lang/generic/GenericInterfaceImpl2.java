package test.java.lang.generic;

/**
 * @author sj
 * @Title GenericInterfaceImpl1
 * @description 未具体参数类型的泛型接口实现类
 * @date 2021/4/10 10:54
 */
public class GenericInterfaceImpl2<T> implements GenericInterface<T>{
    @Override
    public T next() {
        System.out.println("next ");
        return null;
    }
}
