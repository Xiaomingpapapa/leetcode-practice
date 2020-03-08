package com.zhiming.p20200308;

import java.util.Arrays;
import java.util.HashMap;

public class HashMapDemo {

    /**
     * 排序比较法
     * https://leetcode-cn.com/problems/valid-anagram/description/（有效的字母异词）
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;

        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        Arrays.sort(sChars);
        Arrays.sort(tChars);

        return Arrays.equals(sChars, tChars);
    }



    /**
     * hash 表映射字母个数
     * https://leetcode-cn.com/problems/valid-anagram/description/（有效的字母异词）
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram1(String s, String t) {
        if (s.length() != t.length()) return false;

        // 通过数组统计字符数量，只有小写字母
        int[] sNums = new int[26];
        for (char c : s.toCharArray()) {
            // 使用 ascii 码进行 hash 映射
            sNums[c - 'a']++;
        }

        // 检查 t 中的字符
        for (char c : t.toCharArray()) {
            sNums[c - 'a']--;
        }

        // 检查是否满足条件
        for (int sNum : sNums) {
            if (sNum != 0) return false;
        }

        return true;
    }

    /**
     * HashMap 缓存目标值
     * https://leetcode-cn.com/problems/two-sum/description/(两数之和)
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> targerMap = new HashMap<>(nums.length);

        for (int i = 0; i < nums.length; i++) {
            int curTarget = target - nums[i];
            if (targerMap.containsKey(curTarget)) return new int[]{targerMap.get(curTarget), i};
            targerMap.put(nums[i], i);
        }

        return new int[2];
    }
}
