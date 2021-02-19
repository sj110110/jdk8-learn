package test.java.lang;

/**
 * 分析Object类的clone方法
 */
public class ObjectCloneDemo2 {
    public static void main(String[] args) throws CloneNotSupportedException {
        HeadDemo headDemo = new HeadDemo();
        BodyDemo body = new BodyDemo(headDemo);
        PersonDemo2 p1 = new PersonDemo2(body, 18);
        PersonDemo2 p2 = (PersonDemo2) p1.clone();
        System.out.println(p1 == p2);
        System.out.println(p1.getBody() == p2.getBody());
        System.out.println(p1.getBody().getHeadDemo() == p2.getBody().getHeadDemo());
    }
}

class PersonDemo2 implements Cloneable {

    private BodyDemo body;

    private int age;

    public PersonDemo2() {
    }

    public PersonDemo2(BodyDemo body, int age) {
        this.body = body;
        this.age = age;
    }

    public BodyDemo getBody() {
        return body;
    }

    public void setBody(BodyDemo body) {
        this.body = body;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        PersonDemo2 personDemo2 = (PersonDemo2) super.clone();
        personDemo2.body = (BodyDemo) body.clone();
        return personDemo2;
    }
}

class BodyDemo implements Cloneable{

    private HeadDemo headDemo;

    public BodyDemo() {
    }

    public BodyDemo(HeadDemo headDemo) {
        this.headDemo = headDemo;
    }

    public HeadDemo getHeadDemo() {
        return headDemo;
    }

    public void setHeadDemo(HeadDemo headDemo) {
        this.headDemo = headDemo;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        BodyDemo bodyDemo = (BodyDemo) super.clone();
        bodyDemo.headDemo = (HeadDemo) this.headDemo.clone();
        return bodyDemo;
    }
}

class HeadDemo implements Cloneable{
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

