package com.zhiming.p20200314;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeDemo {
    public static void main(String[] args) {

    }


    /**
     * 递归法
     * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/(二叉树的中序遍历)
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(res, root);
        return res;
    }

    public void helper(List<Integer> res, TreeNode root) {
        if (root == null) return;
        helper(res, root.left);
        res.add(root.val);
        helper(res, root.right);
    }

    /**
     * 栈辅助循环
     * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/(二叉树的中序遍历)
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> cacheStack = new Stack<>();
        TreeNode curNode = root;

        while (curNode != null || !cacheStack.isEmpty()) {
            while (curNode != null) {
                cacheStack.push(curNode);
                curNode = curNode.left;
            }
            curNode = cacheStack.pop();
            res.add(curNode.val);
            curNode = curNode.right;
        }


        return res;
    }

    /**
     * 颜色栈辅助循环
     * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/(二叉树的中序遍历)
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal3(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Stack<ColorNode> cacheStack = new Stack<>();
        cacheStack.push(new ColorNode("white", root));

        while (!cacheStack.isEmpty()) {
            ColorNode curNode = cacheStack.pop();
            if (curNode.color.equals("white")) {
                if (curNode.node.right != null) cacheStack.push(new ColorNode("white", curNode.node.right));
                cacheStack.push(new ColorNode("gray", curNode.node));
                if (curNode.node.left != null) cacheStack.push(new ColorNode("white", curNode.node.left));
            } else {
                res.add(curNode.node.val);
            }
        }

        return res;
    }


    /**
     *
     * https://leetcode-cn.com/problems/n-ary-tree-postorder-traversal/(N叉树的后序遍历)
     * @param root
     * @return
     */
    public List<Integer> postorder(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        helper2(res, root);

        return res;
    }

    public void helper2(List<Integer> res, Node root) {
        if (root == null || root.children == null) return;
        root.children.forEach(child -> {
            helper2(res, child);
        });
        res.add(root.val);
    }
}

class ColorNode {
    String color;
    TreeNode node;

    public ColorNode(String color, TreeNode node) {
        this.color = color;
        this.node = node;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}


class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};




