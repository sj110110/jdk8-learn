package test.java.lang.reflect;

/**
 * @author sj
 * @title: MethodDemo
 * @description: TODO
 * @date 2021/1/1820:30
 */
public class MethodDemo {

    private Integer id;

    private String name;

    public MethodDemo() {
    }

    public MethodDemo(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void test(String str){
        System.out.println(str);
    }

    private static String getStr(){
        return "hello getStr!";
    }

}
