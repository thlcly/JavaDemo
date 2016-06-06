package com.aaront.java.threadlocal;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.stream.IntStream;

/**
 * @author tonyhui
 * @since 16/6/6
 */
public class ThreadLocalTest {
    public static void main(String[] args){

        new Thread() {
            @Override
            public void run() {
                TicketWindow ticketWindow = TicketWindow.prepare();
                System.out.println(ticketWindow.getName());
                IntStream.range(0, 5).forEach(i -> new Buyer("buyer_" + i).enqueue());
                ticketWindow.open();
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                TicketWindow ticketWindow = TicketWindow.prepare();
                System.out.println(ticketWindow.getName());
                IntStream.range(5, 10).forEach(i -> new Buyer("buyer_" + i).enqueue());
                ticketWindow.open();
            }
        }.start();

    }
}


class TicketWindow {

    // sTicketWindow是线程共享的
    // 每个线程调用get()方法时,创建的TicketWidow不同
    private static ThreadLocal<TicketWindow> sTicketWindow = new ThreadLocal<TicketWindow>() {
        public TicketWindow initialValue() {
            return new TicketWindow(Thread.currentThread().getName());
        }
    };

    public static TicketWindow prepare() {
        System.out.println(sTicketWindow);
        return sTicketWindow.get();
    }

    private String name;
    private BuyerQueue buyerQueue;

    public TicketWindow(String name) {
        this.name = name;
        this.buyerQueue = new BuyerQueue();
    }

    public String getName() {
        return name;
    }

    public void enqueue(Buyer buyer) {
        buyerQueue.enqueue(buyer);
        System.out.println("enqueue:" + buyer.getName());
    }

    public void open() {
        while (true) {
            Buyer buyer = buyerQueue.poll();
            if (buyer != null) {
                System.out.println(getName() + " turn:" + buyer.getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                buyer.buy();
                System.out.println(getName() + " sold:" + buyer.getName());
            }
        }
    }
}

class BuyerQueue {
    private Queue<Buyer> queue = new LinkedBlockingDeque<>();

    public void enqueue(Buyer buyer) {
        queue.add(buyer);
    }

    public Buyer poll() {
        return queue.poll();
    }

}

class Buyer {

    private String name;

    public Buyer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void enqueue() {
        TicketWindow ticketWindow = TicketWindow.prepare();
        ticketWindow.enqueue(this);
    }

    public void buy() {
    }
}