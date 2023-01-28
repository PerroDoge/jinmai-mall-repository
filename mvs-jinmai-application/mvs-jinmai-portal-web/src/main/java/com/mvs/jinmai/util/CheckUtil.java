package com.mvs.jinmai.util;

import java.util.*;

public class CheckUtil {

    private final static String appSecret = "maweishannqbi";

    public static boolean checkSign(String serverSign, String clientSign) {

        return serverSign.equals(clientSign);
    }

    public static String generatorSign(Map<String, Object> map) {
        // 排序
        Map<String, Object> stringObjectMap = sortPramByMap(map);
        // 转格式
        Set<Map.Entry<String, Object>> entries = stringObjectMap.entrySet();
        StringBuilder sb = new StringBuilder();
        entries.forEach((e) -> {
            sb.append(e.getKey() + e.getValue());
        });
        // 加上secret
        sb.append("appSecret").append(appSecret);

        return MD5Util.md5(sb.toString());
    }

    private static Map<String, Object> sortPramByMap(Map<String, Object> map) {
        Map<String, Object> sortMap = new TreeMap<>(new MyComparator());
        sortMap.putAll(map);
        return sortMap;
    }

    private static class MyComparator implements Comparator<String> {


        @Override
        public int compare(String o1, String o2) {
            return -o1.compareTo(o2);
        }
    }

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("appid", 1);
        map.put("name", 1);
        map.put("id", 10);
        String s = generatorSign(map);
        //029e1c6ab923775908bad8216ff91935
        System.out.println(s);
    }
}
