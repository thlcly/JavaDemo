package com.aaront.java.regex;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author tonyhui
 * @since 16/4/29
 */
public class JavaRegexDemo {
    public static void main(String[] args){
        // 由于 Console 访问操作系统平台上的控制台，因此这个测试用具只能在操作系统的字符控制台中运行，不能运行在 IDE 的控制台中。
        /* 使用Scanner进行替代
            Console console = System.console();
            if (console == null) {
                System.err.println("No console.");
                System.exit(1);
            }
        */
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.printf("%nEnter your regex: ");
            Pattern pattern = Pattern.compile(scanner.nextLine());
            System.out.printf("Enter input string to search: ");
            Matcher matcher = pattern.matcher(scanner.nextLine());
            boolean found = false;
            while (matcher.find()) {
                System.out.printf(
                        "I found the text \"%s\" starting at index %d and ending at index %d.%n",
                        matcher.group(), matcher.start(), matcher.end()
                );
                found = true;
            }
            if (!found) {
                System.out.printf("No match found.%n");
            }
        }
    }
}
