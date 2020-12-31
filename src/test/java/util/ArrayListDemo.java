package test.java.util;

import java.util.ArrayList;

/**
 * @title: ArrayListDemo
 * @description: 学习ArrayList源码
 * @author $sj
 * @date 2020/12/31 10:05
 */
public class ArrayListDemo {
     public static void main(String[] args) {
         //无参构造器
         ArrayList<String> arrayList = new ArrayList<>();
         //add方法
         arrayList.add("张三");
         for(int i = 0; i < 15; i++){
             arrayList.add("shijun"+i);
         }
         System.out.println(arrayList);

         //在某个下标处添加元素
         arrayList.add(1, "lisi");

//         //返回集合大小
//         arrayList.size();
//
//         //删除指定下标的元素
//         arrayList.remove(2);
//
//         //删除指定的元素
//         arrayList.remove("lisi");
//
//         //添加集合元素
//         arrayList.addAll(new ArrayList<>(2));
//
//         //清空集合
//         arrayList.clear();

         //调整集合容量大小
         arrayList.trimToSize();

         //获取集合中某个元素所在的位置
         arrayList.indexOf("shijun3");

         //获取指定下标下的元素
         arrayList.get(10);

         //替换指定下标下的元素
         arrayList.set(2,"wangwu");

     }
}
