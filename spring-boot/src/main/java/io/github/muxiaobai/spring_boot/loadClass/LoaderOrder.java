package io.github.muxiaobai.spring_boot.loadClass;


public class LoaderOrder {
    private static volatile int a = 1;
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
//        testClassLoad();
        testClassForName();
    }
    public static void testClassLoad() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class clazz =LoaderOrder.class.getClassLoader().loadClass("io.github.muxiaobai.spring_boot.loadClass.SonClass");
        System.out.println("---------------------------");
        clazz.newInstance();
        System.out.println("===========================");
    }

    public static void testClassForName() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class clazz =Class.forName("io.github.muxiaobai.spring_boot.loadClass.SonClass");
        System.out.println("---------------------------");
        clazz.newInstance();
        System.out.println("===========================");
    }
}
