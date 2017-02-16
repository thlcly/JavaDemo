package com.aaront.java.proxy.dynamic_proxy_v0;

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
import java.net.URL;
import java.net.URLClassLoader;

import static java.io.File.separator;

public class ProxyVersion_0 implements Serializable {

    private static final long serialVersionUID = -6619966479604466104L;

    public static Object newProxyInstance() throws Exception {
        // 获取接口所在的包
        Package aPackage= HelloWorld.class.getPackage();
        String packageName = aPackage.getName();
        String src = "package " + packageName + ";" + "\n\n" +
                "public class DynamicProxyV0 implements HelloWorld\n" +
                "{\n" +
                "\tHelloWorld helloWorld;\n\n" +
                "\tpublic DynamicProxyV0(HelloWorld helloWorld)\n" +
                "\t{\n" +
                "\t\tthis.helloWorld = helloWorld;\n" +
                "\t}\n\n" +
                "\tpublic void print()\n" +
                "\t{\n" +
                "\t\tSystem.out.println(\"Before Hello World!\");\n" +
                "\t\thelloWorld.print();\n" +
                "\t\tSystem.out.println(\"After Hello World!\");\n" +
                "\t}\n" +
                "}";

        /* 生成一段Java代码 */
        String fileDir = System.getProperty("user.dir");
        String fileName = fileDir + separator + "src" + separator + "main" + separator + "java";
        String[] paths = packageName.split("\\.");
        for(String path : paths) {
            fileName += separator + path;
        }
        fileName = fileName + separator + "DynamicProxyV0.java";
        File javaFile = new File(fileName);
        Writer writer = new FileWriter(javaFile);
        writer.write(src);
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
        Class<?> c = ul.loadClass(packageName + ".DynamicProxyV0");

        /* 利用反射将c实例化出来 */
        Constructor<?> constructor = c.getConstructor(HelloWorld.class);
        HelloWorld helloWorldImpl = new HelloWorldImpl();
        HelloWorld helloWorld = (HelloWorld) constructor.newInstance(helloWorldImpl);

        /* 使用完毕删除生成的代理.java文件和.class文件，这样就看不到动态生成的内容了 */
        File classFile = new File(fileName.replace(".java", ".class"));
        javaFile.delete();
        classFile.delete();

        return helloWorld;
    }
}