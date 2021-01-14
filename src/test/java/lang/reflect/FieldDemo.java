package test.java.lang.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author sj
 * @title: FieldDemo
 * @description: 用于学习反射类中的Field类
 *      java.lang.reflect.Field 为我们提供了获取当前对象的成员变量的类型，和重新设值的方法。
 * @date 2021/1/1319:05
 */
public class FieldDemo<T> {

    private String name = "";

    private Integer age;

    public static final Integer num = 0;

    public static Integer getNum(){
        return num;
    }

    String defaultString;

    private List<T> list = new ArrayList<>();

    @Override
    public String toString() {
        return "FieldDemo{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", defaultString='" + defaultString + '\'' +
                ", list=" + list +
                '}';
    }



    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        Class clazz = Class.forName("test.java.lang.reflect.FieldDemo");
        System.out.println(clazz.getName());
        //获取Field对象
        Field[] fields = clazz.getDeclaredFields();
        System.out.println(Arrays.toString(fields));//[private java.lang.String test.java.lang.reflect.FieldDemo.name, private java.lang.Integer test.java.lang.reflect.FieldDemo.age, public static final java.lang.Integer test.java.lang.reflect.FieldDemo.num, java.lang.String test.java.lang.reflect.FieldDemo.defaultString, private java.util.List test.java.lang.reflect.FieldDemo.list]
        for (Field field : fields){
            //获取变量名称
            String fieldName = field.getName();
            System.out.println(fieldName);

            //获取属性的声明类型，包含泛型
            Type type = field.getGenericType(); //返回Type对象如：java.util.List<T>
            System.out.println(type.getTypeName());

            //获取该属性的修饰符和值
            int modifier = field.getModifiers();
            System.out.println(modifier);
            System.out.println( Modifier.toString(modifier));//打印该修饰符

        }
        /**
         * 设置属性值
         */
        //获取并修改成员变量/属性的值
        System.out.println("---------------------------------------------------");
        FieldDemo demo = new FieldDemo();
        System.out.println(demo);
        //Field field = clazz.getField("name");//这个方法是获取公共public的属性//name时private说以会报错：Exception in thread "main" java.lang.NoSuchFieldException: name
        Field field = clazz.getDeclaredField("name");//获取所有声明的属性值
        field.setAccessible(true);  //指示发射的对象在使用时应该取消java语言访问检查
        System.out.println(field.isAccessible());
        //获取该对象指定的属性值
        String name = (String) field.get(demo);
        System.out.println("name:"+name);
        field.set(demo, "lisi");//修改对象对应成员变量的值。虽然我们不能访问private变量，但是我们可以给这个值赋值。底层是通过UnsafeObjectFieldAccessorImpl字段访问器
        System.out.println(demo);

        Field field1 = clazz.getDeclaredField("num");
        Object object = field1.get("num");//获取该字段值，底层通过UnsafeStaticObjectFieldAccessorImpl访问器来进行字段操作
        System.out.println(object);
        field1.setAccessible(true);////指示发射的对象在使用时取消java语言访问检查。
        //由于num字段修饰符 public static final ，所有在没有修改它的修饰符情况下是不能被就该的，如果直接修改会报错：
        //Exception in thread "main" java.lang.IllegalAccessException: Can not set static final java.lang.Integer field test.java.lang.reflect.FieldDemo.num to java.lang.Integer
        //field1.set(demo, 2);

        System.out.println(demo);



        /**
         *  小结：
         *    field的set方法和get方法。
         *      1、get方法的访问权限涉及访问修饰符：private、public、protected、默认
         *          如果没有该字段的访问权限，及时通过方式，也无法直接访问
         *      2、set方法的赋值权限涉及语言描述符：final、abstract、volatile
         *          如果没有该字段的操作权限，也是无法直接操作
         *     get方法和set方法底层都是通过创建对应的访问器FieldAccessor来判断操作权限。
         */

        /**
         *    想要通过反射老访问或者操作没有权限的字段时一般通过一下手段：
         *      1）先把name字段通过反射取出来, 这个和之前的步骤都一样, 反射出来的字段类型(Field)命名为'nameField'；
         *      2）接下来再通过反射, 把nameField的final修饰符去掉；
         *      3）然后就可以正常对name字段进行值的修改了；
         *      4）最后别忘了再把final修饰符加回来。
         */
        //例如我们要修改public static final num字段值
        FieldDemo fieldDemo = new FieldDemo();
        Class clazz2 =  fieldDemo.getClass();
        Field fieldNum = clazz2.getDeclaredField("num");
        Field modifiers = fieldNum.getClass().getDeclaredField("modifiers");//获取name子对的class对象的变量modifiers
        modifiers.setAccessible(true);
        modifiers.setInt(fieldNum, fieldNum.getModifiers() & ~Modifier.FINAL);
        fieldNum.setAccessible(true);
        fieldNum.set(fieldDemo, 12);
        System.out.println("fieldDemo:"+FieldDemo.getNum());//12


        /**
         * getFieldAccessor(obj)
         * 生成字段访问器的逻辑：
         *  1.判断修饰符是否邳static
         *      1.1 判断修饰符是否有!(final || volatile)
         *          - 有：判断字段类型
         *          - 无：判断字段类型
         *      1.2 判断字段类型
         *  2.判断修饰符是否有!(final || volatile)
         *      判断字段类型
         *  3.判断字段类型
         */




    }


}
