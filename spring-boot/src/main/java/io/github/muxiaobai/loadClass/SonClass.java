package io.github.muxiaobai.loadClass;

public class SonClass  extends   FatherClass{
    public  static  int S_NUM = 2222;
    static {
        F_NUM = 3333;
        System.out.println(S_NUM);
    }

    static {
        S_NUM = 4444;
        System.out.println(F_NUM);
    }
    SonClass(){

    }
    SonClass(int inNUM){
        System.out.println(F_NUM);
    }
}
