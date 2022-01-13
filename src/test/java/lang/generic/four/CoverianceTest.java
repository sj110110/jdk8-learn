package test.java.lang.generic.four;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author sj
 * @Title CoverianceTest
 * @description 数组是协变的
 * @date 2021/4/17 14:28
 */
public class CoverianceTest {
    public static void main(String[] args) {
//        Animal[] animals = new Lion[2];
//        animals[0] = new Lion();
//        animals[1] = new Tiger();
//        System.out.println(Arrays.toString(animals));

//        List<Animal> list = new ArrayList<Lion>();

//        List<? extends Animal> list = new ArrayList<Lion>();
//        list.contains(new Lion());
//        list.contains(new Tiger());


//        list.add(new Lion());
//        list.add(new Animal());
//        list.add(new Object());
//        Animal animal = list.get(0);


        List<? super Tiger> list = new ArrayList<Animal>();
        list.add(new Tiger());//Tiger可以添加
        list.add(new DBTiger());//DBTiger是Tiger的子类，可以添加
//        list.add(new Object());//Object是超类，不可以
//        list.add(new Animal());//Animal是Tiger的父类，不可以
        Object object = list.get(0);
    }
}

class Animal{}

class Lion extends Animal{}

class Tiger extends Animal{}

class DBTiger extends Tiger{}




