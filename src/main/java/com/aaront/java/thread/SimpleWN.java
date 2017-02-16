package com.aaront.java.thread;

/**
 * @author tonyhui
 * @since 17/1/29
 */
public class SimpleWN {
    final static Object object = new Object();
    public static class T1 extends Thread {

        @Override
        public void run() {
            synchronized (object) {
                System.out.println(System.currentTimeMillis() + ": T1 start");
                try {
                    System.out.println(System.currentTimeMillis() + ": T1 wait for object");
                    object.wait();
                }catch(InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + ": T1 end");
            }
        }
    }
}
