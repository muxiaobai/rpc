package io.github.muxiaobai.spring_my_demo.loadClass;


public class SonClass  extends  FatherClass{
    public  static  int S_NUM = 2222;
    static {
        F_NUM = 3333;
        System.out.println(S_NUM);
    }

    static {
        F_NUM = 4444;
        System.out.println(F_NUM);
    }
    SonClass(int inNUM){
        System.out.println(F_NUM);
    }
}
