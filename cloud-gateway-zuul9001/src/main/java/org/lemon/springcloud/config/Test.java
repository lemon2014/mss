package org.lemon.springcloud.config;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

//    private static String a = "(?<version>v.*$)";
    private static String a = "(?<name>.*$)";
    private static String b = "cloud-payment-service"; //注册中心服务名
    private static String c = "${name}";

    public static void main(String[] args) {

//        Pattern pattern = Pattern.compile(a);
//        Matcher matcher = pattern.matcher(b);
//
//        b = matcher.replaceFirst(c);
//        System.out.println(b);

        //(?<name>.*)-(?<version>v.*$)","${version}/${name}


//        Pattern pattern = Pattern.compile("(?<name>.*)-(?<version>v.*$)");
//        Matcher matcher = pattern.matcher("cloud-payment-service-v2");
//
//        // 怎么拿到匹配的值
//
//        String temp = matcher.replaceFirst("${version}/${name}");
//        System.out.println(temp);

        // 将默认的跳转服务名进行替换
        Pattern pattern = Pattern.compile("(cloud)-(?<name>.*)-(service.*$)");
        Matcher matcher = pattern.matcher("cloud-payment-service-v2");

        // 怎么拿到匹配的值

        String temp = matcher.replaceFirst("${name}");
        System.out.println(temp);

    }

}
