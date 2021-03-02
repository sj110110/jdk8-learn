package test.java.lang.annotation;

import java.lang.annotation.Inherited;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sj
 * @Title AnnotationDemo
 * @description 认识注解
 *      @Override是一个注解，这是一个java内置注解，java通过该注解表示某个方法重写了父类的方法。
 *      @Deprecated注解标记在方法或类上，说明该方法或是类都已经过期不建议再用
 *      @SuppressWarnings 则表示忽略指定警告
 * @date 2021/3/2 18:59
 */
public class AnnotationDemo {

    public static void main(String[] args) {

        @SuppressWarnings("unchecked")
        List list = new ArrayList();
        list.add("a");
        list.add(1);
        System.out.println(list);

    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Deprecated
    public static void methodA(){
        System.out.println("方法已过期");
    }

    @SuppressWarnings("uncheck")
    public static void methodB(){
        System.out.println("忽略检查警告");
    }

}
