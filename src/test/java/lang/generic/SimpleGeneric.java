package test.java.lang.generic;

/**
 * @author sj
 * @Title jdk8-learn
 * @description TODO
 * @date 2021/4/10 9:52
 */
public class SimpleGeneric {
    Object obj;

    public SimpleGeneric(Object obj) {
        this.obj = obj;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public static void main(String[] args) {
        SimpleGeneric simpleGeneric = new SimpleGeneric("aa");
        Object aa = simpleGeneric.getObj();

        simpleGeneric.setObj(1);
        Integer i = (Integer) simpleGeneric.getObj();

        String ii =  (String) simpleGeneric.getObj();
    }
}
