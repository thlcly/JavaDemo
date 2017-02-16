package com.aaront.java.jmx.MXBean;

import com.aaront.java.jmx.Book;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class MyMXBeanServer {
    public static void main(String[] args) throws Exception {  
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
          
        ObjectName mbeanName = new ObjectName("me.clarenceau.jmx.mxbean:type=Hello");
          
        Hello mbean = new Hello("ClarenceAu", 23, "ozhencong@gmail.com", new Book());
        mbean.setBook(new Book("Thinking in Java", 99.0));

        server.registerMBean(mbean, mbeanName);  
          
        System.out.println("Wait ...");  
        Thread.sleep(Long.MAX_VALUE);  
    }  
}  