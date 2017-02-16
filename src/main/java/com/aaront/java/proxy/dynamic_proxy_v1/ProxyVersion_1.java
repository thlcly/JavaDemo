package com.aaront.java.proxy.dynamic_proxy_v1;

import com.aaront.java.proxy.static_proxy.HelloWorld;
import com.aaront.java.proxy.static_proxy.HelloWorldImpl;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.Serializable;
import java.io.Writer;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import static java.io.File.separator;

public class ProxyVersion_1 implements Serializable {

    private static final long serialVersionUID = 8264462158804709627L;

    public static Object newProxyInstance(Class<?> interfaces) throws Exception {
        Method[] methods = interfaces.getMethods();

        StringBuilder sb = new StringBuilder(700);

        // 获取接口所在的package
        String packageName = interfaces.getPackage().getName();
        String interfaceName = interfaces.getSimpleName();

        sb.append("package ").append(packageName).append(";\n\n");
        sb.append("public class DynamicProxyV1 implements ").append(interfaceName).append("\n");
        sb.append("{\n");
        sb.append("\t").append(interfaceName).append(" interfaces;\n\n");
        sb.append("\tpublic DynamicProxyV1(").append(interfaceName).append(" interfaces)\n");
        sb.append("\t{\n");
        sb.append("\t\tthis.interfaces = interfaces;\n");
        sb.append("\t}\n\n");
        for (Method m : methods) {
            sb.append("\tpublic " + m.getReturnType() + " " + m.getName() + "()\n");
            sb.append("\t{\n");
            sb.append("\t\tSystem.out.println(\"Before Hello World!\");\n");
            sb.append("\t\tinterfaces." + m.getName() + "();\n");
            sb.append("\t\tSystem.out.println(\"After Hello World!\");\n");
            sb.append("\t}\n");
        }
        sb.append("}");

        /* 生成一段Java代码 */
        String fileDir = System.getProperty("user.dir");
        String fileName = fileDir + separator + "src" + separator + "main" + separator + "java";
        String[] paths = packageName.split("\\.");
        for(String path : paths) {
            fileName += separator + path;
        }
        fileName = fileName + separator + "DynamicProxyV1.java";
        File javaFile = new File(fileName);
        Writer writer = new FileWriter(javaFile);
        writer.write(sb.toString());
        writer.close();

        /* 动态编译这段Java代码,生成.class文件 */
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager sjfm = compiler.getStandardFileManager(null, null, null);
        Iterable<? extends JavaFileObject> iter = sjfm.getJavaFileObjects(fileName);
        JavaCompiler.CompilationTask ct = compiler.getTask(null, sjfm, null, null, null, iter);
        ct.call();
        sjfm.close();

        /* 将生成的.class文件载入内存，默认的ClassLoader只能载入CLASSPATH下的.class文件 */
        URL[] urls = new URL[]{(new URL("file:" + System.getProperty("user.dir") + separator + "src" + separator + "main" + separator + "java/"))};
        URLClassLoader ul = new URLClassLoader(urls);
        Class<?> c = ul.loadClass(packageName + ".DynamicProxyV1");

        /* 利用反射将c实例化出来 */
        Constructor<?> constructor = c.getConstructor(HelloWorld.class);
        HelloWorld helloWorldImpl = new HelloWorldImpl();
        Object obj = constructor.newInstance(helloWorldImpl);

        /** 使用完毕删除生成的代理.java文件和.class文件，这样就看不到动态生成的内容了 */
        /*File classFile = new File(fileDir + "\\src\\com\\xrq\\proxy\\StaticProxy.class");
        javaFile.delete();
        classFile.delete();*/

        return obj;
    }
}