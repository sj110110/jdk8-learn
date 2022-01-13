package test.java.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author sj
 * @Title jdk8-learn
 * @description TODO
 * @date 2021/8/17 19:57
 */
public class ProxyTest {

    public static void main(String[] args) {
        RealSubject subject = new RealSubject();
        InvocationHandler handler = new SubjectInvocationHandler(subject);

        ClassLoader classLoader = subject.getClass().getClassLoader();
        Class<?>[] interfaces = subject.getClass().getInterfaces();
        Subject sub = (Subject) Proxy.newProxyInstance(classLoader, interfaces, handler);
        sub.sayHello("美女");
    }

}
