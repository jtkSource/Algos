package jtk.basic.tree;

import java.util.*;
import java.util.function.Consumer;

/**
 *
 */
public class Tree<T extends Comparable, B extends Tree.TreeNode.Branch<? extends Comparable>> {

    private TreeNode<T, B> root;

    public Tree(T t) {
        this.root = new Tree.TreeNode<>(t);
    }

    public Tree(T t, int degree) {
        this.root = new Tree.TreeNode<>(t, degree);
    }

    public TreeNode getRoot() {
        return root;
    }

    public <D extends Comparable> void addBranch(TreeNode root, TreeNode child, D branchData) {
        root.addBranch(new TreeNode.Branch(branchData, root, child));
    }

    public <N extends Comparable, D extends Comparable> void addBranch(TreeNode root, N childData, D branchData) {
        root.addBranch(new TreeNode.Branch(branchData, root, new Tree.TreeNode<>(childData)));
    }

    public <N extends Comparable> Optional<B> searchNodesChildrenForData(TreeNode<T, B> node, N childData) {
        return node.branches.stream().filter(b -> b.getChild().getData().compareTo(childData) == 0)
                .findFirst();
    }

    public <N extends Comparable, D extends Comparable> void addBranchSortedToBinaryTree(N nodeData, D branchData) {
        addBranchSortedToBinaryTree(root, new Tree.TreeNode<>(nodeData, 2), branchData);
    }

    public <D extends Comparable> TreeNode findElementInBinarySortedTree(D data) {
        return findElementInBinarySortedTree(root, data);
    }

    public <D extends Comparable> boolean deleteElementInBinarySortedTree(D data) {
        return deleteElementInBinarySortedTree(root, new Tree.TreeNode.Branch<>("rootHolder", null, root), data);
    }

    private <D extends Comparable> boolean deleteElementInBinarySortedTree(TreeNode<T, B> root, TreeNode.Branch parentBranch, D data) {
        if (root.getDegree() != 2) {
            throw new RuntimeException("Should be degree 2");
        }
        if (root.getData().compareTo(data) == 0) {
            if (root.branches.stream().allMatch(o -> o == null))
                parentBranch.setChild(null);
            if (root.branches.get(0) != null && root.branches.get(1) != null) {//if both left and right child is present
                TreeNode leftChild = root.branches.get(0).getChild();
                TreeNode.Branch rightMostBranch = getRightMostBranch((TreeNode.Branch) leftChild.branches.get(1));
                parentBranch.setChild(rightMostBranch.getChild());
                if (rightMostBranch.getChild().branches.get(0) != null) {
                    // move left child on the right most branch to the child of right most branch
                    // (which already replaced the deleted node)
                    rightMostBranch.setChild(((TreeNode.Branch) rightMostBranch.getChild().branches.get(0)).getChild());
                } else {//else stub out the right branch
                    rightMostBranch.getParent().branches.set(1, null);
                }
                parentBranch.getChild().addBranch(root.getBranches(0), 0);
                parentBranch.getChild().addBranch(root.getBranches(1), 1);
                if (parentBranch.getBranchData().equals("rootHolder"))
                    this.root = parentBranch.getChild();

            } else if (root.branches.get(1) != null && root.branches.get(0) == null) {// if only right child directly replace
                parentBranch.setChild(root.branches.get(1).getChild());
            } else if (root.branches.get(0) != null && root.branches.get(1) == null) { // if only left child exists
                parentBranch.setChild(root.branches.get(0).getChild());
            }
            return true;
        } else {
            if (root.getData().compareTo(data) > 0 && root.branches.get(0) != null) {
                return deleteElementInBinarySortedTree(root.branches.get(0).getChild(), root.branches.get(0), data);
            } else if (root.getData().compareTo(data) < 0 && root.branches.get(1) != null) {
                return deleteElementInBinarySortedTree(root.branches.get(1).getChild(), root.branches.get(1), data);
            } else return false;
        }
    }

    private TreeNode.Branch getRightMostBranch(TreeNode.Branch branch) {
        if (branch.getChild().branches.get(1) != null)
            branch = getRightMostBranch((TreeNode.Branch) branch.getChild().branches.get(1));
        return branch;
    }

    private <D extends Comparable> TreeNode findElementInBinarySortedTree(TreeNode<T, B> root, D data) {
        if (root.getDegree() != 2) {
            throw new RuntimeException("Should be degree 2");
        }
        if (root.getData().compareTo(data) == 0)
            return root;
        if (root.getData().compareTo(data) >= 0) {
            if (root.branches.get(0) != null)
                return findElementInBinarySortedTree(root.branches.get(0).getChild(), data);
        } else {
            if (root.branches.get(1) != null)
                return findElementInBinarySortedTree(root.branches.get(1).getChild(), data);
        }
        return null;//
    }


    private <D extends Comparable> void addBranchSortedToBinaryTree(TreeNode root, TreeNode child, D branchData) {
        if (root.getDegree() == 2 && child.getDegree() == 2) {

            if (root.getData().compareTo(child.getData()) < 0) {
                if (root.branches.get(1) != null)
                    addBranchSortedToBinaryTree(((TreeNode.Branch) root.branches.get(1)).getChild(), child, "R");
                else
                    root.addBranch(new TreeNode.Branch("R", root, child), 1);
            } else if (root.getData().compareTo(child.getData()) > 0) {
                if (root.branches.get(0) != null)
                    addBranchSortedToBinaryTree(((TreeNode.Branch) root.branches.get(0)).getChild(), child, "L");
                else
                    root.addBranch(new TreeNode.Branch("L", root, child), 0);
            }

        } else {
            throw new RuntimeException("Tree should be degree 2");
        }
    }

    public void traversePreOrder(TreeNode<T, B> node, Consumer<TreeNode<T, B>> doSomething) {
        doSomething.accept(node);
        if (node.branches.isEmpty()) {
            return;
        }
        for (TreeNode.Branch branch : node.branches) {
            traversePreOrder(branch.getChild(), doSomething);
        }
    }

    public void traverseInOrder(TreeNode<T, B> node, Consumer<TreeNode<T, B>> doSomething) {
        if (node.branches.isEmpty()) {
            doSomething.accept(node);
            return;
        }
        for (int i = 0; i < node.branches.size(); i++) {
            if (i == (node.branches.size()) / 2)
                doSomething.accept(node);
            traverseInOrder(node.branches.get(i).getChild(), doSomething);
        }
    }

    public void traversePostOrder(TreeNode<T, B> node, Consumer<TreeNode<T, B>> doSomething) {
        if (node.branches.isEmpty()) {
            doSomething.accept(node);
            return;
        }
        for (TreeNode.Branch branch : node.branches) {
            traversePostOrder(branch.getChild(), doSomething);
        }
        doSomething.accept(node);
    }

    public void traverseDepthFirst(TreeNode<T, B> node, Consumer<TreeNode<T, B>> doSomething) {
        Queue<TreeNode<T, B>> treeNodes = new LinkedList<>();
        treeNodes.add(node);
        while (!treeNodes.isEmpty()) {
            TreeNode<T, B> treeNode = treeNodes.remove();
            doSomething.accept(treeNode);
            for (TreeNode.Branch branch : treeNode.branches) {
                if (branch == null) continue;
                treeNodes.add(branch.getChild());
            }
        }
    }


    public void printTree() {
        printNode(root, 0);
    }

    public void printTreeWithBranchData(String branchData) {
        printNode(root, 0, branchData);
    }

    private void printNode(TreeNode<T, B> node, int level) {
        node.prettyPrint(level);
        for (int i = 0; i < node.branches.size(); i++) {
            if (node.branches.get(i) == null) continue;
            printNode(node.branches.get(i).getChild(), level + 1);
        }
    }

    private void printNode(TreeNode<T, B> node, int level, String rootBranchData) {
        node.prettyPrint(level, rootBranchData);
        for (int i = 0; i < node.branches.size(); i++) {
            if (node.branches.get(i) == null) continue;
            printNode(node.branches.get(i).getChild(), level + 1, node.branches.get(i).getBranchData().toString());
        }
    }

    public static class TreeNode<T extends Comparable, B extends TreeNode.Branch<? extends Comparable>> {

        private final T data;
        private int degree;

        private List<B> branches = new ArrayList<>();

        public TreeNode(T data) {
            this.data = data;
        }


        public TreeNode(T data, int degree) {
            this.data = data;
            this.degree = degree;
            branches = new ArrayList<>(degree);
            for (int i = 0; i < degree; i++) {
                branches.add(null);
            }
        }

        public T getData() {
            return data;
        }

        public int getDegree() {
            return degree;
        }

        public List<B> getBranches() {
            return branches;
        }

        public B getBranches(int index) {
            return branches.get(index);
        }

        public void addBranch(B branch) {
            this.branches.add(branch);
        }

        public void addBranch(B branch, int index) {
            this.branches.set(index, branch);
        }

        public void prettyPrint(int level) {
            String pad = new String(new char[level]).replace('\0', ' ');
            System.out.println(pad + "|- " + getData());
        }


        public void prettyPrint(int level, String branchData) {
            String pad = new String(new char[level]).replace('\0', ' ');
            System.out.println(pad + "|- " + getData() + " (" + branchData + ":" + level + ")");
        }

        public static class Branch<B extends Comparable> {
            private final TreeNode parent;
            private B branchData;
            private TreeNode child;

            public Branch(B branchData, TreeNode parent, TreeNode child) {
                this.branchData = branchData;
                this.parent = parent;
                this.child = child;
            }

            public TreeNode getChild() {
                return child;
            }

            public void setChild(TreeNode child) {
                this.child = child;
            }

            public TreeNode getParent() {
                return parent;
            }

            public B getBranchData() {
                return branchData;
            }

            public void setBranchData(B branchData) {
                this.branchData = branchData;
            }
        }
    }

}
