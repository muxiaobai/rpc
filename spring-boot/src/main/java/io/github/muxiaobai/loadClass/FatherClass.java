package io.github.muxiaobai.loadClass;


public class FatherClass {
    public  static  int F_NUM = 1111;
    static {
        System.out.println(F_NUM);

    }
    FatherClass(){
        System.out.println(F_NUM);
        F_NUM = 5555;
    }
}
