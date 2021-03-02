package test.java.lang;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class StringDemo {
    public static void main(String[] args) throws UnsupportedEncodingException {
//        String str = "abc";

        /*
        构造函数
         */
//        //1.使用字符串构造
//        String str1 = new String("aa");
//        //2.使用数组构造
//        char ch[] = {'a', 'b'};
//        String str2 = new String(ch);
//        System.out.println(str2);
//        //3.使用字节数组构造
//        byte[] b = {97, 106};
//        String str3 = new String(b);
//        System.out.println(str3);
//aaaaaaaaaa
        /*
        常见方法
         */
//        int len = str.length();
//        System.out.println(len);
//        System.out.println(str.isEmpty());
//        char ch = str.charAt(1);
//        System.out.println(ch);
//        int index = str.codePointAt(1);
//        System.out.println(index);
        String str = "abc";
//        char[] endCh = {'1', '2', '3'};
//        str.getChars(1, 2, endCh, 0);
//        System.out.println(endCh);
//        String str2 = str.concat("xxxl");
//        System.out.println(str2);

//        byte[] b = str.getBytes("utf-8");
//        System.out.println(Arrays.toString(b));

//        int n = str.compareTo("ABC");
//        System.out.println(n);

        String[] ch = str.split("b");
        System.out.println(Arrays.toString(ch));
    }
}
