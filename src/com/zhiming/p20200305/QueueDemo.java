package com.zhiming.p20200305;

import java.util.*;

public class QueueDemo {
    public static void main(String[] args) {

    }

    /**
     * 暴力破解法
     * https://leetcode-cn.com/problems/sliding-window-maximum/
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums != null || nums.length == 0) return nums;

        int[] res = new int[nums.length - k + 1];

        for (int i = 0; i <= nums.length - k; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i; j <= i + k - 1; j++) {
                max = Math.max(max, nums[j]);
            }
            res[i] = max;
        }

        return res;
    }


    int[] nums;
    int k;
    LinkedList<Integer> tempDeque = new LinkedList();
    public void cleanWindow(int i ) {
        // 清除队列首部，不在当前窗口中的元素
        if (!tempDeque.isEmpty() && tempDeque.getFirst() == i - k)
            tempDeque.removeFirst();

        // 清除队列中比当前元素小的元素
        while (!tempDeque.isEmpty() && nums[tempDeque.getLast()] < nums[i])
            tempDeque.removeLast();

     }

    /**
     * 双端队列优化
     * https://leetcode-cn.com/problems/sliding-window-maximum/
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums == null || nums.length == 0) return nums;
        this.nums = nums;
        this.k = k;

        int[] res = new int[nums.length - k + 1];

        // 初始化第一个窗口
        for (int i = 0; i < k; i++) {
            cleanWindow(i);
            tempDeque.addLast(i);
        }
        res[0] = nums[tempDeque.getFirst()];


        // 后续 n - k 个窗口
        for (int i = k; i < nums.length; i++) {
            cleanWindow(i);
            tempDeque.addLast(i);
            res[i - k + 1] = nums[tempDeque.getFirst()];
        }

        return res;

    }
}
