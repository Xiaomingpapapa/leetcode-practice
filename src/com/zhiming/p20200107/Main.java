package com.zhiming.p20200107;




import com.sun.org.apache.regexp.internal.REUtil;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        String haystack = "aaaaa", needle = "bba";
        System.out.println(strStr(haystack, needle));
    }


    public static int strStr(String haystack, String needle) {
        if (needle == null || needle.length() == 0) return 0;
        if(haystack.length() < needle.length()) return -1;
        // 1.遍历主字符串 2.嵌套遍历字符串，挨个匹配，如果发现不等，直接退出当前循环
        char[] hchars = haystack.toCharArray();
        char[] nchars = needle.toCharArray();

        outerLoop:for (int i = 0; i < haystack.length() - needle.length(); i++) {
            int p = i;
            for (int j = 0; j < needle.length(); j++) {
                if (hchars[p++] != nchars[j]) continue outerLoop;
            }
            return i;
        }

        return -1;
    }


//    public static List<List<Integer>> threeSum(int[] nums) {
//        List<List<Integer>> res = new ArrayList<>();
//        Map<Integer, List<Integer>> hash = new HashMap<>();
//        for (int i = 0; i < nums.length - 2; i++) {
//            for (int j = 0; j < nums.length - 1; j++) {
//                if (hash.containsKey(nums[i])) {
//                    List<Integer> temp = hash.get(nums[i]);
//                    temp.add(nums[i]);
//                    res.add(temp);
//                    hash.remove(nums[i]);
//                } else {
//                    int y = 0 - nums[i] - nums[j];
//                    List<Integer> temp = new ArrayList<>();
//                    temp.add(nums[i]);
//                    temp.add(nums[j]);
//                    hash.put(y, temp);
//                }
//            }
//        }
//        return res;
//
//    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 3) return res;
        // 排序
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            // 如果当前数大于 0，则意味着三数只和肯定大于 0（数组升序排列），直接退出循环
            if (nums[i] > 0) break;
            // 防止答案重复
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return res;
    }



    public static List<List<Integer>> pathWithObstacles(int[][] obstacleGrid) {
        // 递归出口 到达终点右下角
        List<List<Integer>> result = new ArrayList<>();
        int row = obstacleGrid.length;
        if (row == 0) return result;
        int col = obstacleGrid[0].length;
        if (col == 0) return result;
        if (obstacleGrid[row - 1][col - 1] == 1) return result;

        setWay(obstacleGrid, 0, 0, result);
        return result;
    }

    public static String addBinary(String a, String b) {
        // 区分字符长短
        // 区分是否进制，而过进制需要额外 +1
        char[] aChars = a.toCharArray();
        char[] bChars = b.toCharArray();
        StringBuilder res = new StringBuilder();
        int jin = 0;
        for (int i = aChars.length - 1, j = bChars.length - 1; i >= 0 || j >= 0; i--, j--) {
            int sum = jin;
            sum += i >=0 ? aChars[i] - '0' : 0;
            sum += j >=0 ? bChars[j] - '0' : 0;
            StringBuilder tempRes = new StringBuilder().append(sum % 2);
            res = tempRes.append(res);
            jin = sum / 2;
        }

        if (jin == 1) {
            res = new StringBuilder().append("1").append(res);
        }

        return res.toString();
    }

    public static boolean setWay(int[][] obstacleGrid, int i, int j, List<List<Integer>> result) {
        if (i >= obstacleGrid.length || i < 0 || j < 0 || j >= obstacleGrid[0].length) return false;
        if (i == obstacleGrid.length - 1 && j == obstacleGrid[0].length - 1) {
            // 把终点加入
            result.add(Arrays.asList(i, j));
            return true;
        }

        if (obstacleGrid[i][j] == 0) {
            // 向右
            if (setWay(obstacleGrid, i ,j + 1, result)) {
                result.add(Arrays.asList(i, j));
                return true;
            }
            // 向下
            if (setWay(obstacleGrid, i + 1, j, result)) {
                result.add(Arrays.asList(i, j));
                return true;
            }
            // 此路不通
            return false;
        } else {
            return false;
        }
    }

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> iterResult = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> rowNums = new ArrayList<>();
            // 每一行的个数 row + 1
            for (int j = 0; j < i + 1; j++) {
                // 最左，最右元素都为 1
                if (j == 0 || j == i) {
                    rowNums.add(1);
                    continue;
                }
                // f(i, j) = f(i-1, j-1) + f(i-1, j)
                rowNums.add(iterResult.get(i - 1).get(j - 1) + iterResult.get(i - 1).get(j));
            }
            iterResult.add(rowNums);
        }
        return iterResult;
    }


    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix.length == 0) return result;
        int m = matrix.length;
        if (matrix[0].length == 0) return result;
        int n = matrix[0].length;

        int up = 0;
        int down = m - 1;
        int left = 0;
        int right = n - 1;
        result = new ArrayList<>(m * n);
        while (true) {
            for (int i = left; i <= right; i++) result.add(matrix[up][i]);
            if (++up > down) break;

            for (int i = up; i <= down; i++) result.add(matrix[i][right]);
            if (--right < left) break;

            for (int i = right; i >= left; i--) result.add(matrix[down][i]);
            if (--down < up) break;

            for (int i = down; i >= up; i--) result.add(matrix[i][left]);
            if (++left > right) break;
        }
        return result;
    }


    public static int[] findDiagonalOrder(int[][] matrix) {
        if (matrix.length == 0) return new int[0];
        int m = matrix.length;

        if (matrix[0].length == 0) return new int[0];
        int n = matrix[0].length;

        int row = 0;
        int col = 0;
        int[] result = new int[m * n];
        for (int i = 0; i < m * n; i++) {
            result[i] = matrix[row][col];
            // 根据 row + col 之和来判断下一步走向
            if ((row + col) % 2 == 0) {
                // 和为偶数，向右上走
                if (col == n -1) {
                    // 需要注意边界情况，如果在边界只走 1 步
                    row++;
                } else if (row == 0) {
                    col++;
                } else {
                    // 普通情况可以走 2 步
                    row--;
                    col++;
                }
            } else {
                // 和为奇数，向左下走
                if (row == m - 1) {
                    col++;
                } else if (col == 0) {
                    row++;
                } else {
                    row++;
                    col--;
                }
            }
        }
        return result;
    }

    public static int pivotIndex(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        // 存储向量和
        int[] sums = new int[nums.length];
        sums[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sums[i] = sums[i - 1] + nums[i];
        }

        for (int i = 0; i < nums.length; i++) {
            int preSum = 0;
            int rearSum = sums[nums.length - 1] - sums[i];
            if (i != 0) preSum = sums[i] - nums[i];
            if (preSum == rearSum) return i;
        }
        return -1;
    }

    public static int[] plusOne(int[] digits) {
        return recursionNums(digits, digits.length - 1);
    }

    public static int[] recursionNums(int[] digits, int index) {
        if (index < 0) return digits;
        if (index == 0 && digits[index] == 9) {
            digits = new int[digits.length + 1];
            digits[0] = 1;
            return digits;
        }
        if (digits[index] == 9) {
            digits[index] = 0;
            return recursionNums(digits, --index);
        }
        digits[index]++;
        return digits;
    }


    public static int dominantIndex(int[] nums) {
        if (nums.length == 1) return 0;
        int oneMax = 0;
        int towMax = 0;
        int result = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > oneMax) {
                towMax = oneMax;
                oneMax = nums[i];
                result = i;
            } else if (nums[i] > towMax) {
                towMax = nums[i];
            }
        }
        if (oneMax >= 2 * towMax) return result;
        return -1;
    }


    public static ListNode reverseList(ListNode head) {
        // 设置递归出口
        if (head == null || head.next == null) return head;
        // 存放迭代结果
        ListNode pre = reverseList(head.next);
        // 让当前节点的下一个元素指向当前元素
        head.next.next = head;
        // 切断当前元素原本的指向（所有指针都反向了，因此原来的正向指针无效）
        head.next = null;
        return pre;
    }
}

class MyCircularQueue {
    private int[] arr;
    private int front = 0;
    private int tail = 0;
    private int maxSize = 0;

    /**
     * Initialize your data structure here. Set the size of the queue to be k.
     */
    public MyCircularQueue(int k) {
        arr = new int[k + 1];
        this.maxSize = k + 1;
    }

    /**
     * Insert an element into the circular queue. Return true if the operation is successful.
     */
    public boolean enQueue(int value) {
        // 队列已满
        if (isFull()) return false;
        arr[tail] = value;
        tail = (tail + 1) % maxSize;
        return true;
    }

    /**
     * Delete an element from the circular queue. Return true if the operation is successful.
     */
    public boolean deQueue() {
        // 队列为空
        if (isEmpty()) return false;
        int value = arr[front];
        front = (front + 1) % maxSize;
        return true;
    }

    /**
     * Get the front item from the queue.
     */
    public int Front() {
        if (isEmpty()) return -1;
        return arr[front];
    }

    /**
     * Get the last item from the queue.
     */
    public int Rear() {
        if (isEmpty()) return -1;
        return arr[(tail - 1 + maxSize) % maxSize];
    }

    /**
     * Checks whether the circular queue is empty or not.
     */
    public boolean isEmpty() {
        return front == tail;
    }

    /**
     * Checks whether the circular queue is full or not.
     */
    public boolean isFull() {
        return (tail + 1) % maxSize == front;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
