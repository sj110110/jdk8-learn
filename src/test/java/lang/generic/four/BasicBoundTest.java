package test.java.lang.generic.four;

/**
 * @author sj
 * @Title BasicBoundTest
 * @description 测试边界
 * @date 2021/4/17 13:56
 */
public class BasicBoundTest {
    public static void main(String[] args) {
        BoundGeneric<Bound> boundGeneric = new BoundGeneric<>(new Bound());
        boundGeneric.f_A();
        boundGeneric.f_B();
        boundGeneric.f_C();
    }
}

interface A{
    void f_A();
}

interface B{
    void f_B();
}

class C {
    public void f_C(){
        System.out.println("C");
    }
}

class Bound extends C implements A,B{

    @Override
    public void f_A() {
        System.out.println("A");
    }

    @Override
    public void f_B() {
        System.out.println("B");
    }
}

/**
 * //This don't work:类型一定要现在前面，然后才是接口
 * //class BoundHolder<T extends A & B & C>{} 这种情况编译不过
 *
 */
class BoundGeneric<T extends C & A & B> {
    T t;

    public BoundGeneric(T t) {
        this.t = t;
    }

    void f_A(){
        t.f_A();//类型参数T有边界A，能调用A的方法f_A();
    }

    void f_B(){
        t.f_B();//类型参数T有边界B，能调用B的方法f_B();
    }

    void f_C(){
        t.f_C();//类型参数T有边界C，能调用C的方法f_C();
    }
}

