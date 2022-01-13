package test.java.proxy;

/**
 * @author sj
 * @Title jdk8-learn
 * @description TODO
 * @date 2021/8/17 19:52
 */
public class RealSubject implements Subject {
    @Override
    public String sayHello(String name) {
        System.out.println("hello:"+name);
        return "hello :"+name;
    }

    @Override
    public String doing(String name) {
        System.out.println("doing "+name);
        return "doing "+name;
    }
}
