package com.company;

/**
 * com.company.TreeNode represents a node of the interval tree
 * interval - Fixed interval of size 2 {start, end}
 * max - max end time in the subtree
 * left - left child of the node
 * right - right child of the node
 *
 */

public class TreeNode {

    int[] interval;
    int max;
    TreeNode left;
    TreeNode right;

    public TreeNode(int[] interval) {
        this.interval = interval;

    }
}
