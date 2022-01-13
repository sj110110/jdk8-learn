package test.java.lang.generic;

/**
 * @author sj
 * @Title GenericMethod 泛型方法
 * @description TODO
 * @date 2021/4/10 11:37
 */
public class GenericMethod {

    public <T> T method(T t){
        System.out.println("泛型方法");
        return t;
    }

    public <T> void method2(T t){
        System.out.println("泛型方法");
        return;
    }

//    //这个不是泛型方法
//    public void method3(T t){
//        System.out.println("泛型方法");
//        return;
//    }

    public static void main(String[] args) {
        GenericMethod genericMethod = new GenericMethod();
        String str = genericMethod.method("aa");
        Integer num = genericMethod.method(11);
        Boolean flag = genericMethod.method(false);
        GenericMethod genericMethod1 = genericMethod.method(genericMethod);
    }

}
