package test.java.lang.generic.three;

/**
 * @author sj
 * @Title ChildGenericObject
 * @description TODO
 * @date 2021/4/13 19:16
 */
public class ChildGenericObject extends GenericObject<String>{

    public ChildGenericObject(String s) {
        super(s);
    }

    @Override
    public void setT(String s) {
        super.setT(s);
    }

    public static void main(String[] args) {
        ChildGenericObject childGenericObject = new ChildGenericObject("Test");
        childGenericObject.setT("AAA");
    }
}
