package test.java.lang;

/**
 * 分析Object类的clone方法
 */
public class ObjectCloneDemo {
    public static void main(String[] args) throws CloneNotSupportedException {
        PersonDemo p1 = new PersonDemo("张三",18);
        PersonDemo p2 = (PersonDemo) p1.clone();
        System.out.println(p1 == p2);
        System.out.println(p1.getName() == p2.getName());
//        PersonDemo p1 = new PersonDemo();
//        PersonDemo p2 = (PersonDemo) p1.clone();
        System.out.println(p1 == p2);
    }
}

class PersonDemo implements Cloneable {

    private String name;

    private int age;


    public PersonDemo(String name) {
        this.name = name;
    }

    public PersonDemo(int age) {
    }

        public PersonDemo(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

