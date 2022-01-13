package test.java.lang.enumtest;

/**
 * @author sj
 * @Title jdk8-learn
 * @description TODO
 * @date 2021/3/9 18:48
 */
public enum MyEnum1 {
    AA(1, "zhangsan"),
    BB(2, "lisi");
    private Integer id;
    private String name;

    MyEnum1(Integer id, String name) {
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
}
