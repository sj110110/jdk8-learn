package test.java.lang;

/**
 * @author sj
 * @Title jdk8-learn
 * @description TODO
 * @date 2021/2/27 16:34
 */
public class JNIDemo {
    private String str = new String("hello java");
    {
        //系统加载其他语言的函数
        System.load("/home/sj/test/jni_string.so");
    }

    //native标识本地方法
    public native void stringJNI();

    public static void main(String[] args) {
        JNIDemo demo = new JNIDemo();
        System.out.println("before jni, str:"+demo.str);
        demo.stringJNI();
        System.out.println("after jni, str:"+demo.str);
    }
}
