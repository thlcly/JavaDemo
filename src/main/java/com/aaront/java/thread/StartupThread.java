package com.aaront.java.thread;

/**
 * @author tonyhui
 * @since 17/1/27
 */
public class StartupThread {
    public static class TmpThread extends Thread {

        @Override
        public void run() {
            for(long i = 0;i<100;i++) {
                    System.out.println(currentThread() + "  " + this);
                    Thread.yield();
            }
        }
    }

    public static void main(String[] args){
        TmpThread tt = new TmpThread();
        tt.start();
        tt.start();
    }
}
