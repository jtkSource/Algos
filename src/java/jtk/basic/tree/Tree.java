package jtk.basic.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by jubin on 1/2/17.
 */
public class Tree<T, B extends Tree.TreeNode.Branch<?>> {

    private final TreeNode<T, B> root;

    public Tree(TreeNode<T, B> root) {
        this.root = root;
    }

    public static void main(String[] args) {

        /**
         *                  D
         *                B   E
         *              A   C
         */
        TreeNode<String, TreeNode.Branch<String>> root = new TreeNode<>("D");
        Tree<String, TreeNode.Branch<String>> tree = new Tree<>(root);
        TreeNode<String, TreeNode.Branch<String>> level11 = new TreeNode<>("B");
        TreeNode<String, TreeNode.Branch<String>> level12 = new TreeNode<>("E");
        TreeNode<String, TreeNode.Branch<String>> level21 = new TreeNode<>("A");
        TreeNode<String, TreeNode.Branch<String>> level22 = new TreeNode<>("C");
        root.addBranch(new TreeNode.Branch<>("", root, level11)).addBranch(new TreeNode.Branch<>("", root, level12));
        level11.addBranch(new TreeNode.Branch<>("", level11, level21)).addBranch(new TreeNode.Branch<>("", level11, level22));

        /***  O(N) run time. Depth of recursion equal to the tree's height.
         * If the tree is very tall, that could cause a stack overflow. ***/
        System.out.println("tree.traversePreOrder(root); = ");
        tree.traversePreOrder(root);
        System.out.println("tree.traverseInOrder(root) = ");
        tree.traverseInOrder(root);
        System.out.println("tree.traversePostOrder(root); = ");
        tree.traversePostOrder(root);
        /***  the algorithm takes O(N) time              ***/
        System.out.println("tree.traverseDepthFirst(root); = ");
        tree.traverseDepthFirst(root);
    }

    public void traversePreOrder(TreeNode<T, B> node) {
        System.out.println("node.getData() = " + node.getData());
        if (node.branches.isEmpty()) {
            return;
        }
        for (TreeNode.Branch branch : node.branches) {
            traversePreOrder(branch.getChild());
        }
    }

    public void traverseInOrder(TreeNode<T, B> node) {
        if (node.branches.isEmpty()) {
            System.out.println("node.getData() = " + node.getData());
            return;
        }
        for (int i = 0; i < node.branches.size(); i++) {
            if (i == (node.branches.size()) / 2)
                System.out.println("node.getData() = " + node.getData());
            traverseInOrder(node.branches.get(i).getChild());
        }
    }

    public void traversePostOrder(TreeNode<T, B> node) {
        if (node.branches.isEmpty()) {
            System.out.println("node.getData() = " + node.getData());
            return;
        }
        for (TreeNode.Branch branch : node.branches) {
            traversePostOrder(branch.getChild());
        }
        System.out.println("node.getData() = " + node.getData());
    }

    public void traverseDepthFirst(TreeNode<T, B> node) {
        Queue<TreeNode<T, B>> treeNodes = new LinkedList<>();
        treeNodes.add(node);
        while (!treeNodes.isEmpty()) {
            TreeNode<T, B> treeNode = treeNodes.remove();
            System.out.println("node.getData() = " + treeNode.getData());
            for (TreeNode.Branch branch : treeNode.branches) {
                treeNodes.add(branch.getChild());
            }
        }
    }

    public static class TreeNode<T, B extends TreeNode.Branch<?>> {
        private final T data;
        private List<B> branches = new ArrayList<>();

        public TreeNode(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }

        public TreeNode addBranch(B branch) {
            this.branches.add(branch);
            return this;
        }

        public static class Branch<B> {
            private final B branchData;
            private final TreeNode parent;
            private final TreeNode child;

            public Branch(B branchData, TreeNode parent, TreeNode child) {
                this.branchData = branchData;
                this.parent = parent;
                this.child = child;
            }

            public TreeNode getChild() {
                return child;
            }

            public TreeNode getParent() {
                return parent;
            }

            public B getBranchData() {
                return branchData;
            }
        }
    }

}
