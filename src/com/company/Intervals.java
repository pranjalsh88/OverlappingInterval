package com.company;


import java.util.*;
import java.util.logging.Logger;

public class Intervals {
    static final Logger log = Logger.getLogger(Intervals.class.getName());

    /**
     * This method inserts the current interval into the tree. Its based on the BST order.
     * If the current interval is less than the node being accessed, the node is assigned to the left child
     * Otherwise, it points to the right child.
     *
     * This can be replaced by a recursive approach, however, recursion doesn't scale well if the tree grows bigger.
     * @param root - The root of the interval tree
     * @param current_interval - The interval in the form of array of {start, end}
     */
    public void insert(TreeNode root, int[] current_interval)
    {

        log.info("Attempting to insert interval {" + current_interval[0] + "," + current_interval[1] + "} into the tree");

        if (root == null) {
            root = new TreeNode(current_interval);
            return;
        }
        TreeNode node = root;
        // Run until a break is encountered, marking that a null node is encountered
        while (true) {
            int start_time = node.interval[0];

            if (current_interval[0] < start_time) {
                if (node.left == null) {
                    node.left = new TreeNode(current_interval);
                    if (node.left.max < current_interval[1])
                        node.left.max = current_interval[1];
                    break;
                }
                else {
                    node = node.left;


                }
            }

            else {
                if (node.right == null) {
                    node.right = new TreeNode(current_interval);
                    if (node.right.max < current_interval[1])
                        node.right.max = current_interval[1];
                    break;
                }
                else {
                    node = node.right;
                }
            }
            if (node.max < current_interval[1])
                node.max = current_interval[1];
        }

        log.info("Interval {" + current_interval[0] + "," + current_interval[1] + "} inserted successfully");

    }

    /**
     *
     * A utility function to check if given two intervals conflict
     *
     * @param interval1 - in the form of {start, end}
     * @param interval2 - in the form of {start, end}
     * @return true if there is an overlap
     */
    public boolean areTheIntervalsConflicting(int[] interval1, int[] interval2)
    {
        return (interval1[0] < interval2[1] && interval2[0] < interval1[1]);
    }

    /**
     * This method returns the interval that the interval conflicts with in the interval tree
     * @param root - Root node of the tree
     * @param interval - in the form of {start, end}
     * @return interval in the form of {start, end} if there is a conflict, otherwise null
     */
    public int[] findWhereTheIntervalOverlapInTheTree(TreeNode root, int[] interval)
    {

        if (root == null)
            return null;
        TreeNode node = root;

        while (node != null) {
            if (areTheIntervalsConflicting(node.interval, interval))
                return node.interval;

            if (node.left != null && node.left.max >= interval[0])
                node = node.left;

            else {
                node = node.right;
            }

        }
        return null;
    }

    /**
     * This is the API that will find all the pairs of overlapping intervals based on inputList (a list of intervals
     * represented as array of {start, end} times
     * @param inputList - list of intervals to be checked
     * @param n
     * @return
     */
    public List<String> findOverlappingIntervals(List<int[]> inputList)
    {

        if (inputList == null)
            throw new IllegalArgumentException();

        if (inputList.size() == 0) {
            return new ArrayList<>();
        }

        int inputSize = inputList.size();
        List<String> list = new ArrayList<>();
        TreeNode root = new TreeNode(inputList.get(0));
        for (int i=1; i<inputSize; i++)
        {
            int[] res = findWhereTheIntervalOverlapInTheTree(root, inputList.get(i));
            if (res != null) {
                log.info("Conflicting intervals found");
                list.add("[{" + res[0] + "," + res[1] + "} conflicts with " +  "{" + inputList.get(i)[0] + ","
                        + inputList.get(i)[1] + "}]");

            }
            // Insert the interval into the tree
            insert(root, inputList.get(i));
        }
        return list;
    }
}
