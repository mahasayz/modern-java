package com.practice.leetcode;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;

class Node {
    public Node left;
    public Node right;
    public int value;
    public Node(Node left, Node right, int value) {
        this.left = left;
        this.right = right;
        this.value = value;
    }
}

public class IdenticalBTrees {

    public static void DFS(Node node) {
        if (node == null)
            return;
        DFS(node.left);
        DFS(node.right);
        System.out.println(node.value);
    }

    public static boolean isIdentical(Node treeA, Node treeB) {
        if (treeA == null && treeB == null)
            return true;
        if (treeA == null || treeB == null)
            return false;
        if (treeA.value != treeB.value)
            return false;
        return isIdentical(treeA.left, treeB.left) && isIdentical(treeA.right, treeB.right);
    }

    public static void main(String[] args) {
        Node tree1 = new Node(
                new Node(new Node(null, null, 1), new Node(null, null, 2), 3),
                new Node(new Node(null, null, 4), new Node(null, null, 5), 6),
                7
        );
        Node tree2 = new Node(
                new Node(new Node(null, null, 1), new Node(null, null, 2), 3),
                new Node(new Node(null, null, 4), new Node(null, null, 3), 6),
                7
        );
        System.out.println(isIdentical(tree1, tree2));
    }

}
