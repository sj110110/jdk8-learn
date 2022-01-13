package test.java.lang.serializable;

import java.io.*;

/**
 * @author sj
 * @Title SerializableDemo
 * @description 序列化和反序列化
 * @date 2021/5/6 14:53
 */
public class SerializableDemo {

    public static void main(String[] args) {
        //序列化
        User user = new User("zhangsan", 18);
        System.out.println("before:"+user);
        ObjectOutputStream out = null;
        try {
             out = new ObjectOutputStream(new FileOutputStream("D:\\tmp\\user"));
             out.writeObject(user);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (out != null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //反序列化
        ObjectInputStream in = null;
        User user1 = null;
        try {
            in = new ObjectInputStream(new FileInputStream("D:\\tmp\\user"));
            user1 = (User) in.readObject();
            System.out.println("after:"+user1);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            if (in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
