package test.java.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author sj
 * @title: LinkedListDemo
 * @description: learn LinkedList
 * @date 2021/1/418:50
 */
public class LinkedListDemo {

    public static void main(String[] args) {
        //无参构造
        LinkedList<String> list = new LinkedList<>();
        //添加元素
        list.add("aaa");
        list.add("bbb");
        list.add("bbb");

        list.addFirst("111");
        //添加10次
        for(int i = 0; i < 10; i++){
            list.add("test_"+i);//LinkedList是链表结构，不需要预先设置容量，直接添加即可，与ArrayList底层是数组不同
        }

        //在指定下标处添加元素
        list.add(1, "222");

        //添加一个集合
        List<String> addList = new ArrayList<>();
        addList.add("list1");
        addList.add("list2");
        list.addAll(addList);

        //判断是否包含元素
        list.contains("bbb");

        //清空集合元素
        list.clear();

        //获取元素
        list.get(1);

        //删除某个元素
        list.remove(1);

        //删除头节点
        list.removeFirst();


        System.out.println(list);
    }
}
