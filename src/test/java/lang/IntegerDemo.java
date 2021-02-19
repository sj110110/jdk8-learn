package test.java.lang;

/**
 * 分析基本数据类型包装类Integer类
 */
public class IntegerDemo {
    public static void main(String[] args) {
        //测试boolean类型
        boolean flag = true;
        System.out.println(flag);

        char ch = 'a';
        Character character = ch;

        Integer a1 = 127;//自动装箱调用Integer.valueOf(127);方法
        Integer a2 = 127;
        System.out.println(a1 == a2);
        System.out.println(a1.equals(a2));
//
//        Integer b1 = 128;
//        Integer b2 = 128;
//        System.out.println(b1 == b2);
//        System.out.println(b1.equals(b2));

//        Class a = int.class;
//        Class b = Integer.TYPE;
//        Class c = Integer.class;
//
//        System.out.println(System.identityHashCode(a));
//        System.out.println(System.identityHashCode(b));
//        System.out.println(System.identityHashCode(c));



//        Integer i = 1;
//        int a = i;
//        System.out.println(a);

//        Integer i = -9;
//        Integer i = 65537;//i >= 2^16=65535
//        i.toString();
//        int i = 65537;
//        String num = Integer.toString(i, 16);

//        String str = "1234";
//        int num = Integer.parseInt(str);

//        String str = "-2553";
//        Integer i = Integer.valueOf(str);

//        int i = 255;
//        String num = Integer.toHexString(i);

//        int a = 255;
//        Integer integer = a;
//        int b = integer;
    }
}
