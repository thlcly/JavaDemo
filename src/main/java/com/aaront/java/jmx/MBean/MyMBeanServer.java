package com.aaront.java.jmx.MBean;


import com.aaront.java.jmx.Book;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class MyMBeanServer {
    public static void main(String[] args) throws Exception {
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();

        ObjectName mbeanName = new ObjectName("me.clarenceau.jmx.mbean:type=Hello");

        Hello mbean = new Hello("ClarenceAu", 23, "ozhencong@gmail.com", new Book());

        server.registerMBean(mbean, mbeanName);

        System.out.println("Wait ...");
        Thread.sleep(Long.MAX_VALUE);
    }
}
