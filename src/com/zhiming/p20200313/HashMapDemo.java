package com.zhiming.p20200313;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class HashMapDemo {
    public static void main(String[] args) {
        String[] strs = {"eat","tea","tan","ate","nat","bat"};
        List<List<String>> res = groupAnagrams2(strs);
        System.out.println(res);
    }


    /**
     * 普通 hashmap 排序映射法
     * https://leetcode-cn.com/problems/group-anagrams/(字母异位词分组)
     * @param strs
     * @return
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> cacheMap = new HashMap<>();

        for (String str : strs) {
            // 对字符串进行排序
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sortedStr = new String(chars);
            if (cacheMap.containsKey(sortedStr)) {
                List<String> tempList = cacheMap.get(sortedStr);
                tempList.add(str);
                continue;
            }
            List<String> stringList = new ArrayList<>();
            stringList.add(str);
            cacheMap.put(sortedStr, stringList);
        }


        return new ArrayList<>(cacheMap.values());
    }


    /**
     * 优化 hashmap 映射法
     * https://leetcode-cn.com/problems/group-anagrams/(字母异位词分组)
     * @param strs
     * @return
     */
    public static List<List<String>> groupAnagrams2(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        HashMap<String, List<String>> cacheMap = new HashMap<>();

        for (String str : strs) {
            // 对字符串进行排序
            String hashStr = asciiHash(str);
            if (cacheMap.containsKey(hashStr)) {
                List<String> tempList = cacheMap.get(hashStr);
                tempList.add(str);
                continue;
            }
            List<String> stringList = new ArrayList<>();
            stringList.add(str);
            cacheMap.put(hashStr, stringList);
        }

        cacheMap.forEach((key, value) -> {
            res.add(value);
        });

        return res;
    }

    public static String asciiHash(String str) {
        int[] hashArr = new int[26];
        for (char c : str.toCharArray()) {
            hashArr[c - 'a']++;
        }
        return Arrays.toString(hashArr);
    }
}
