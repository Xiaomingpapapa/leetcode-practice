package com.zhiming.p20200301;

import java.util.*;

public class TowSum {
    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        System.out.println(threeSum(nums));
    }

//    public static int[] twoSum(int[] nums, int target) {
//    }

    public static List<List<Integer>> threeSum(int[] nums) {
        // 排序
        // int k, l, r
        // k 遍历数组，l, r 分别为 k 右边区域的左右边界 [l, r]
        // l r 在判断结果是否符合条件时，不断往中间夹逼，中途需要跳过重复的数字
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 3) return res;

        Arrays.sort(nums);

        for (int k = 0; k < nums.length; k++) {
            if (nums[k] > 0) break;
            // 去重
            if (k > 0 && nums[k] == nums[k - 1]) continue;
            int l = k + 1;
            int r = nums.length - 1;
            while (l < r) {
                int sum = nums[k] + nums[l] + nums[r];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[k], nums[l], nums[r]));
                    // 去重
                    while (l < r && nums[l] == nums[l + 1]) l++;
                    while (l < r && nums[r] == nums[r - 1]) r--;
                    l++;
                    r--;
                }
                if (sum < 0) l++;
                if (sum > 0) r--;
            }
        }

        return res;
    }
}
