package test.java.lang.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author sj
 * @title: ReflectDemo
 * @description: 学习，理解放射原理
 *      JAVA反射机制是在运行状态中，对于任意一个类，都能够知道这个类的所有属性和方法；
 *      对于任意一个对象，都能够调用它的任意一个方法和属性；
 *      这种动态获取的信息以及动态调用对象的方法的功能称为java语言的反射机制。
 * @date 2021/1/719:29
 */
public class ClassDemo extends ClassDemoPareson implements IClassDemo {


    public ClassDemo() {
    }

    public static final Integer MIN = 0;

    public String age;

    private String name;

    public ClassDemo(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int getNum() {
        return 0;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String test(String arg){
        return null;
    }


    public static void main(String[] args) throws Exception {
        //要通过放射获取到一个类的所有属性和方法，首先就是要要获取该类的字节码文件对象，即Class对象。
        /*
         * 获取一个类的Class对象的方式有3种：
         *      1）通过对象实例的getClass()方法；
         *      2）任何数据类型都有一个class的属性；
         *      3）通过Class类的静态方法forName(String className)（常用这个）。//全类名
         */
        //通过Object.getClass()方法；
//        ReflectDemo reflectDemo = new ReflectDemo("shijun");
//        System.out.println(reflectDemo);
//        Class clazz = reflectDemo.getClass();
//        System.out.println(clazz);
//        //通过任何数据类型的class属性
//        Class clazz = ReflectDemo.class;
//        System.out.println(clazz2);
        //通过Class.forName()方法
        Class clazz = Class.forName("test.java.lang.reflect.ClassDemo");//注意是全类名
        System.out.println(clazz.getName());//test.java.lang.reflect.ReflectDemo

        /**
         *  常用操作
         */
        //1.通过Class对象获取该类的实例
        System.out.println("----------------------------------------------------------------------");
        /**
         * newInstance()方法小结：
         *   1）当使用class对象的 {@link Class#newInstance}方法时，需要无参构造器
         *   2）本质就是通过调用类的构造方法来构造一个实例
         *      - 先获取调用类的无参构造器
         *      - 设置无参构造器的访问权限
         *      - 调用无参构造器生成实例
         */
         ClassDemo reflectDemo1 = (ClassDemo) clazz.newInstance();//获取类的实例
//            System.out.println(reflectDemo1);
        boolean flag = clazz.isInstance(reflectDemo1);//判断某个实例是否属于该Class类型
        System.out.println(flag);



        /**
         * 2.通过Class对象获取类的构造方法及相关数据
         *  getConstructors():用于获取调用类的构造函数
         */
        System.out.println("----------------------------------------------------------------------");
        //获取有参和无参构造器
        Constructor<?>[] constructors = clazz.getConstructors();//获取当前类的构造函数
        System.out.println(constructors);
        //遍历构造器
        for(Constructor constructor : constructors){
              //获取构造函数中参数类型的字节码对象
            Class[] parameterTypesClasses = constructor.getParameterTypes();
            System.out.println(parameterTypesClasses);
            for(Class parameterType : parameterTypesClasses){
                System.out.println(parameterType.getName());//获取该参数类型的名称，java.lang.String
            }
        }


        /**
         * 3、获取属性
         *
         */
        System.out.println("----------------------------------------------------------------------");
        //获取属性（所有访问修饰符）
        Field[] fields = clazz.getDeclaredFields();
        System.out.println(Arrays.toString(fields));//[public static final java.lang.Integer test.java.lang.reflect.ReflectDemo.MIN, private java.lang.String test.java.lang.reflect.ReflectDemo.name]

        //根据名称获取属性
        Field field = clazz.getDeclaredField("name");
        System.out.println(field); //private java.lang.String test.java.lang.reflect.ReflectDemo.name
//        field.setAccessible(true);//设置访问权限
        field.set(reflectDemo1, "zhangsan");//给属性赋值
        System.out.println(field.get(reflectDemo1));//zhangsan

        //获取公共属性（public）
        Field[] fields1 = clazz.getFields();
        System.out.println(Arrays.toString(fields1)); //[public static final java.lang.Integer test.java.lang.reflect.ReflectDemo.MIN]

        //根据名称获取公共属性
//        Field field1 = clazz.getField("name");//error:Exception in thread "main" java.lang.NoSuchFieldException: name
        // name是private访问修饰，不能通过getField()方法获取到。
        Field field1 = clazz.getField("age");//public java.lang.String test.java.lang.reflect.ReflectDemo.age
        //给属性赋值
        field1.set(reflectDemo1, "20");
        System.out.println(field1.get(reflectDemo1));//20


        /**
         * 获取方法
         */
        System.out.println("----------------------------------------------------------------------");
        //获取类中声明的方法（非构造方法）
        Method[] methods = clazz.getDeclaredMethods();
        System.out.println(Arrays.toString(methods));//[public static void test.java.lang.reflect.ReflectDemo.main(java.lang.String[]) throws java.lang.Exception, public java.lang.String test.java.lang.reflect.ReflectDemo.getName(), public void test.java.lang.reflect.ReflectDemo.setName(java.lang.String), private java.lang.String test.java.lang.reflect.ReflectDemo.test(java.lang.String), public int test.java.lang.reflect.ReflectDemo.getNum()]

        //根据方法名和参数类型获取方法对象
        Method method = clazz.getDeclaredMethod("setName", String.class);
        System.out.println(method);//public void test.java.lang.reflect.ReflectDemo.setName(java.lang.String)

        //获取公共方法
        Method[] methods1 = clazz.getMethods(); //包含父类方法
        System.out.println(Arrays.toString(methods1));//[public static void test.java.lang.reflect.ReflectDemo.main(java.lang.String[]) throws java.lang.Exception, public java.lang.String test.java.lang.reflect.ReflectDemo.getName(), public void test.java.lang.reflect.ReflectDemo.setName(java.lang.String), public int test.java.lang.reflect.ReflectDemo.getNum(), public final void java.lang.Object.wait() throws java.lang.InterruptedException, public final void java.lang.Object.wait(long,int) throws java.lang.InterruptedException, public final native void java.lang.Object.wait(long) throws java.lang.InterruptedException, public boolean java.lang.Object.equals(java.lang.Object), public java.lang.String java.lang.Object.toString(), public native int java.lang.Object.hashCode(), public final native java.lang.Class java.lang.Object.getClass(), public final native void java.lang.Object.notify(), public final native void java.lang.Object.notifyAll()]

        //根据方法名称获取
        Method method1 = clazz.getMethod("getNum", null);//public int test.java.lang.reflect.ReflectDemo.getNum()
        System.out.println(method1);


        /**
         * 获取注解
         */
        System.out.println("----------------------------------------------------------------------");
        Annotation[] annotations = clazz.getAnnotations();//获取当前类上的注解
        System.out.println(Arrays.toString(annotations));//当在类上加上@Deprecated注解时返回[@java.lang.Deprecated()]


    }


}
