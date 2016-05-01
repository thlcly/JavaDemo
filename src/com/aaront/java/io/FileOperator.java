package com.aaront.java.io;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author tonyhui
 * @since 16/5/1
 */
public class FileOperator {
    public static void main(String[] args) throws IOException {
        FileOperator fileOperator = new FileOperator();
        // 创建文件
        fileOperator.createFileOperator();
        // 使用文件分隔符创建文件(可以跨平台)
        fileOperator.createFileWithSeparatorOperator();
        // 两个文件的比较(是根据文件的路径的字典顺序进行比较)
        fileOperator.compareFiles();
        // 获取文件的路径(两种路径)
        fileOperator.getFilePath();
        // 删除文件
        fileOperator.deleteFile();
        // 在虚拟机退出时删除文件
        fileOperator.deleteFileOnExit();
        // 创建文件夹
        fileOperator.createFolder();
        // 列出指定目录下的所有的文件(得到的是文件路径)(包括隐藏目录,但是只会列出当前这一层的且是相对路径)
        fileOperator.listFiles();
        // 列出指定目录下的所有的文件(得到的是文件对象)(包括隐藏目录,但是只会列出当前这一层)
        fileOperator.listFiles2();
        // 列出指定目录下的所有的文件(没有列出文件夹)
        fileOperator.print(new File("." + File.separator));

    }

    public void createFileOperator() {
        String filename = "./test.txt";
        boolean res = _createFileWithName(filename);
        if (res) System.out.println("文件创建成功");
        else System.out.println("文件创建失败");
    }

    public void createFileWithSeparatorOperator() {
        String filename = "." + File.separator + "text2.txt";
        boolean res = _createFileWithName(filename);
        if (res) System.out.println("文件创建成功");
        else System.out.println("文件创建失败");
    }

    private boolean _createFileWithName(String filename) {
        File file = new File(filename);
        try {
            // 创建一个新文件当且仅当文件不存在且创建成功时返回true,文件存在返回false
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void compareFiles() throws IOException {
        File file1 = new File("./fold1/text3.txt");
        File file2 = new File("./fold2/text3.txt");
        System.out.println(file1.compareTo(file2));
    }

    public void getFilePath() {
        File file = new File("./text4.txt");
        try {
            // path: /Users/tonyhui/Code/JavaDemo/text4.txt
            System.out.println(file.getCanonicalPath());
            // path: /Users/tonyhui/Code/JavaDemo/./text4.txt
            System.out.println(file.getAbsolutePath());
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    public void deleteFile() {
        File file = new File("./text5.txt");
        if (file.exists()) {
            boolean res = file.delete();
            System.out.println(res);
        }
    }

    public void deleteFileOnExit() {
        File file = new File("./test.txt");
        file.deleteOnExit();
    }

    public void createFolder() {
        String fileName = "." + File.separator + "hello";
        File f = new File(fileName);
        boolean res = f.mkdir();
        if (res) System.out.println("文件夹创建成功");
        else System.out.println("文件夹创建失败");
    }

    public void listFiles() {
        String fileName = "." + File.separator;
        File f = new File(fileName);
        Arrays.stream(f.list()).forEach(System.out::println);
    }

    public void listFiles2() {
        String fileName = "." + File.separator;
        File f = new File(fileName);
        File[] files = f.listFiles();
        if(files == null) return;
        Arrays.stream(files).forEach(System.out::println);
    }

    public void print(File f) {
        if(f.isDirectory()) {
            File[] files = f.listFiles();
            if(files == null) return;
            for(File file : files ) {
                if(file.isDirectory()) print(file);
                else System.out.println(file);
            }
        }
    }
}
