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

        Arrays.sort(nums);

        List<List<Integer>> res = new ArrayList<>();
        for (int k = 0; k < nums.length - 2; k++) {
            // 如果 num[k] > 0 则 nums[k] + nums[l] + nums[r] 肯定大于 0，直接退出循环
            if (nums[k] > 0) break;
            // 跳过重复的 k
            if (k > 0 && nums[k] == nums[k - 1]) continue;

            int l = k + 1;
            int r = nums.length - 1;
            while (l < r) {
                int sum = nums[k] + nums[l] + nums[r];
                if (sum < 0) {
                    // l 向右收敛，碰到重复的数直接跳过
                    while (l < r && nums[l] == nums[++l]);
                } else if (sum > 0) {
                    // r 向右收敛，碰到重复的数直接跳过
                    while (l < r && nums[r] == nums[--r]);
                } else {
                    // 满足情况，加入结果集
                    res.add(Arrays.asList(nums[k], nums[l], nums[r]));
                    // l, r 同时向内收敛，碰到重复的数直接跳过
                    while (l < r && nums[l] == nums[++l]);
                    while (l < r && nums[r] == nums[--r]);
                }
            }
        }

        return res;
    }

//            Arrays.sort(nums);
//    List<List<Integer>> res = new ArrayList<>();
//        for(int k = 0; k < nums.length - 2; k++){
//        if(nums[k] > 0) break;
//        if(k > 0 && nums[k] == nums[k - 1]) continue;
//        int i = k + 1, j = nums.length - 1;
//        while(i < j){
//            int sum = nums[k] + nums[i] + nums[j];
//            if(sum < 0){
//                while(i < j && nums[i] == nums[++i]);
//            } else if (sum > 0) {
//                while(i < j && nums[j] == nums[--j]);
//            } else {
//                res.add(new ArrayList<Integer>(Arrays.asList(nums[k], nums[i], nums[j])));
//                while(i < j && nums[i] == nums[++i]);
//                while(i < j && nums[j] == nums[--j]);
//            }
//        }
//    }
//        return res;
}
