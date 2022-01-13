package test.java.lang.serializable;

import java.io.Serializable;

/**
 * @author sj
 * @Title User
 * @description TODO
 * @date 2021/5/6 14:55
 */
public class User implements Serializable {


    private static final long serialVersionUID = 3097971644635762223L;
    
    private String name;

    private transient int age;

    public User(String name, int age) {
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
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
