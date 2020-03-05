package com.zhiming.p20200304;

import java.util.Stack;

public class StackDemo {
    public static void main(String[] args) {

    }

    public static boolean isValid(String s) {
        char[] sChars = s.toCharArray();
        Stack<Character> tempStack = new Stack<>();

        for (char sChar : sChars) {
            if (sChar == '(') {
                tempStack.push(')');
            } else if (sChar == '{') {
                tempStack.push('}');
            } else if (sChar == '[') {
                tempStack.push(']');
            } else if (!tempStack.empty() && tempStack.pop() != sChar) {
                return false;
            }
        }

        return true;
    }

    /**
     * 暴力法
     * https://leetcode-cn.com/problems/largest-rectangle-in-histogram/
     * @param heights
     * @return
     */
    public static int largestRectangleArea(int[] heights) {
        int maxArea = 0;

        for (int i = 0; i < heights.length; i++) {
            for (int j = i; j < heights.length; j++) {
                // 找右边界
                int minHeight = Integer.MAX_VALUE;
                for (int k = i; k <= j; k++)
                    minHeight = Math.min(minHeight, heights[k]);

                maxArea = Math.max(maxArea, (j - i + 1) * minHeight);
            }
        }

        return maxArea;
    }

    /**
     * 优化暴力法，第二个指针遍历过程中将最小高度缓存
     * https://leetcode-cn.com/problems/largest-rectangle-in-histogram/
     * @param heights
     * @return
     */
    public static int largestRectangleArea2(int[] heights) {
        int maxArea = 0;

        for (int i = 0; i < heights.length; i++) {
            int minHeight = Integer.MAX_VALUE;
            for (int j = i; j < heights.length; j++) {
                minHeight = Math.min(minHeight, heights[j]);
                maxArea = Math.max(maxArea, (j -  i + 1) * minHeight);
            }
        }

        return maxArea;
    }

    /**
     * 构造单调递增栈
     * https://leetcode-cn.com/problems/largest-rectangle-in-histogram/
     * @param heights
     * @return
     */
    public static int largestRectangleArea3(int[] heights) {
        int maxArea = 0;

        Stack<Integer> indexS = new Stack<>();
        indexS.add(-1);
        for (int i = 0; i < heights.length; i++) {
            while (indexS.peek() != -1 && heights[indexS.peek()] > heights[i]) {
                maxArea = Math.max(maxArea, heights[indexS.pop()] * (i - indexS.peek() - 1));
            }
            indexS.push(i);
        }

        while (indexS.peek() != -1) {
            maxArea = Math.max(maxArea, heights[indexS.pop()] * (heights.length - indexS.peek() - 1));
        }

        return maxArea;
    }

}
