package com.zhiming.p20200229;

import com.sun.org.apache.regexp.internal.RE;

import java.util.ArrayList;

public class MoveZeroes {
    public static void main(String[] args) {
    }


    public static int climbStairs(int n) {
        // 斐波那契数列 f(n) = f(n - 1) + f(n - 2)
        if (n <= 2) return n;
        int f1 = 1;
        int f2 = 2;
        int f3 = 3;
        for (int i = 3; i < n + 1; i++) {
            f3 = f1 + f2;
            f1 = f2;
            f2 = f3;
        }
        return f3;
    }


    public static int maxArea(int[] height) {
        int max = 0;
        for (int i = 0, j = height.length - 1; i < j;) {
            int minHeight = Math.min(height[i], height[j]);
            max = Math.max(minHeight * (j - i), max);

            while (height[i] <= minHeight && i < j) i++;
            while (height[j] <= minHeight && i < j) j--;
        }

        return max;
    }

    public static void moveZeroes(int[] nums) {
        // 不为 0 的数组下标
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[j] = nums[i];
                if (i != j) nums[i] = 0;
                j++;
            }
        }

        int insertPos = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) nums[insertPos++] = nums[i];
        }
        for (; insertPos < nums.length; insertPos++) {
            nums[insertPos] = 0;
        }
    }
}
