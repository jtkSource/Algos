package jtk.basic.tree;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by jubin on 4/27/2017.
 */
public class TreeTest {

    private Tree.TreeNode<String, Tree.TreeNode.Branch<String>> root;

    private Tree<String, Tree.TreeNode.Branch<String>> tree;

    @Before
    public void setUp() {
        root = new Tree.TreeNode<>("D");
        tree = new Tree<>(root);
        Tree.TreeNode<String, Tree.TreeNode.Branch<String>> level11 = new Tree.TreeNode<>("B");
        Tree.TreeNode<String, Tree.TreeNode.Branch<String>> level12 = new Tree.TreeNode<>("E");
        Tree.TreeNode<String, Tree.TreeNode.Branch<String>> level21 = new Tree.TreeNode<>("A");
        Tree.TreeNode<String, Tree.TreeNode.Branch<String>> level22 = new Tree.TreeNode<>("C");
        tree.addBranch(tree.getRoot(), level11, "");
        tree.addBranch(tree.getRoot(), level12, "");
        tree.addBranch(level11, level21, "");
        tree.addBranch(level11, level22, "");

    }


    @Test
    public void print_tree() {
        tree.printTree();
    }

    /***  O(N) run time. Depth of recursion equal to the tree's height.
     * If the tree is very tall, that could cause a stack overflow. ***/
    @Test
    public void traversePreOrder_should_traverse_the_tree_preorder() {
        ArrayList<String> arrayList = new ArrayList<>();
        tree.traversePreOrder(root, stringBranchTreeNode -> arrayList.add(stringBranchTreeNode.getData()));
        Assert.assertEquals("D", arrayList.get(0));
        Assert.assertEquals("B", arrayList.get(1));
        Assert.assertEquals("A", arrayList.get(2));
        Assert.assertEquals("C", arrayList.get(3));
        Assert.assertEquals("E", arrayList.get(4));
    }

    @Test
    public void traverseInOrder_should_traverse_the_tree_inorder() {
        ArrayList<String> arrayList = new ArrayList<>();
        tree.traverseInOrder(root, stringBranchTreeNode -> arrayList.add(stringBranchTreeNode.getData()));
        Assert.assertEquals("A", arrayList.get(0));
        Assert.assertEquals("B", arrayList.get(1));
        Assert.assertEquals("C", arrayList.get(2));
        Assert.assertEquals("D", arrayList.get(3));
        Assert.assertEquals("E", arrayList.get(4));
    }

    @Test
    public void traversePostOrder_should_traverse_the_tree_postorder() {
        ArrayList<String> arrayList = new ArrayList<>();
        tree.traversePostOrder(root, stringBranchTreeNode -> arrayList.add(stringBranchTreeNode.getData()));
        Assert.assertEquals("A", arrayList.get(0));
        Assert.assertEquals("C", arrayList.get(1));
        Assert.assertEquals("B", arrayList.get(2));
        Assert.assertEquals("E", arrayList.get(3));
        Assert.assertEquals("D", arrayList.get(4));
    }


    /***  the algorithm takes O(N) time              ***/
    @Test
    public void traverseDepthFirst_should_traverse_the_tree_depthFirst() {
        ArrayList<String> arrayList = new ArrayList<>();
        tree.traverseDepthFirst(root, stringBranchTreeNode -> arrayList.add(stringBranchTreeNode.getData()));
        Assert.assertEquals("D", arrayList.get(0));
        Assert.assertEquals("B", arrayList.get(1));
        Assert.assertEquals("E", arrayList.get(2));
        Assert.assertEquals("A", arrayList.get(3));
        Assert.assertEquals("C", arrayList.get(4));
    }

    //creates complexity NLogN - elements are random
    @Test
    public void create_sorted_binary_tree_from_random_integers() {
        Random random = new Random();
        int bound = 49;
        int randomInt = random.nextInt(bound);
        System.out.println(randomInt);
        Tree.TreeNode<Integer, Tree.TreeNode.Branch<Integer>> root = new Tree.TreeNode<>(randomInt, 2);
        Tree<Integer, Tree.TreeNode.Branch<Integer>> tree = new Tree<>(root);

        System.out.println("[");
        for (int i = 1; i < 10; i++) {
            randomInt = random.nextInt(bound);
            System.out.print("\t" + randomInt);
            tree.addBranchSortedToBinaryTree(randomInt, "");
        }
        System.out.println("]");
        tree.printTreeWithBranchData("root");
    }


    @Test // creates complexity N^2
    public void create_sorted_binary_tree_from_sorted_numbers() {
        Random random = new Random();
        int bound = 49;
        System.out.println(0);
        Tree.TreeNode<Integer, Tree.TreeNode.Branch<Integer>> root = new Tree.TreeNode<>(0, 2);
        Tree<Integer, Tree.TreeNode.Branch<Integer>> tree = new Tree<>(root);

        System.out.println("[");
        for (int i = 1; i < 10; i++) {
            System.out.print("\t" + i);
            tree.addBranchSortedToBinaryTree(i, "");
        }
        System.out.println("]");
        tree.printTreeWithBranchData("root");
    }


    //creates complexity NLogN - elements are random
    @Test
    public void find_sorted_binary_tree_from_random_integers() {
        Random random = new Random();
        int bound = 1000;
        int randomInt = random.nextInt(bound);
        System.out.println(randomInt);
        Tree.TreeNode<Integer, Tree.TreeNode.Branch<Integer>> root = new Tree.TreeNode<>(randomInt, 2);
        Tree<Integer, Tree.TreeNode.Branch<Integer>> tree = new Tree<>(root);

        System.out.println("[");
        for (int i = 1; i < 1000; i++) {
            randomInt = random.nextInt(bound);
            System.out.print("\t" + randomInt);
            tree.addBranchSortedToBinaryTree(randomInt, "");
        }
        System.out.println("]");
        tree.printTreeWithBranchData("root");
        Tree.TreeNode node = tree.findElementInBinarySortedTree(randomInt);
        System.out.println("finding: " + randomInt);
        Assert.assertEquals(randomInt, node.getData());
    }

    @Test
    public void find_sorted_binary_tree_from_random_integers_for_value_not_in_tree() {
        Random random = new Random();
        int bound = 49;
        int randomInt = random.nextInt(bound);
        System.out.println(randomInt);
        Tree.TreeNode<Integer, Tree.TreeNode.Branch<Integer>> root = new Tree.TreeNode<>(randomInt, 2);
        Tree<Integer, Tree.TreeNode.Branch<Integer>> tree = new Tree<>(root);
        System.out.println("[");
        for (int i = 1; i < 10; i++) {
            randomInt = random.nextInt(bound);
            System.out.print("\t" + randomInt);
            tree.addBranchSortedToBinaryTree(randomInt, "");
        }
        System.out.println("]");
        tree.printTreeWithBranchData("root");
        Tree.TreeNode node = tree.findElementInBinarySortedTree(290);
        System.out.println("finding: " + 290);
        Assert.assertNull(node);
    }

    @Test
    public void deleteElementInBinarySortedTree_should_delete_node_with_left_and_right_child() {
        int bound = 49;
        int[] arrays = {35, 76, 17, 42, 68, 11, 24, 63, 69, 23};
        System.out.println(60);
        Tree.TreeNode<Integer, Tree.TreeNode.Branch<Integer>> root = new Tree.TreeNode<>(60, 2);
        Tree<Integer, Tree.TreeNode.Branch<Integer>> tree = new Tree<>(root);
        System.out.println("[");
        for (int i = 0; i < arrays.length; i++) {
            System.out.print("\t" + arrays[i]);
            tree.addBranchSortedToBinaryTree(arrays[i], "");
        }
        System.out.println("]");
        tree.printTreeWithBranchData("root");
        tree.deleteElementInBinarySortedTree(35);
        //System.out.println("After deleting");
        tree.printTreeWithBranchData("root");
        ArrayList<Integer> arrayList = new ArrayList<>();
        tree.traverseDepthFirst(root, integerBranchTreeNode -> arrayList.add(integerBranchTreeNode.getData()));
        Assert.assertEquals(Integer.valueOf(60), arrayList.get(0));
        Assert.assertEquals(Integer.valueOf(24), arrayList.get(1));
        Assert.assertEquals(Integer.valueOf(76), arrayList.get(2));
        Assert.assertEquals(Integer.valueOf(17), arrayList.get(3));
        Assert.assertEquals(Integer.valueOf(42), arrayList.get(4));
        Assert.assertEquals(Integer.valueOf(68), arrayList.get(5));
        Assert.assertEquals(Integer.valueOf(11), arrayList.get(6));
        Assert.assertEquals(Integer.valueOf(23), arrayList.get(7));
        Assert.assertEquals(Integer.valueOf(63), arrayList.get(8));
        Assert.assertEquals(Integer.valueOf(69), arrayList.get(9));
    }

    @Test
    public void deleteElementInBinarySortedTree_should_delete_with_only_left_child() {
        int bound = 49;
        int[] arrays = {35, 76, 17, 42, 68, 11, 24, 63, 69, 23};
        System.out.println(60);
        Tree.TreeNode<Integer, Tree.TreeNode.Branch<Integer>> root = new Tree.TreeNode<>(60, 2);
        Tree<Integer, Tree.TreeNode.Branch<Integer>> tree = new Tree<>(root);
        System.out.println("[");
        for (int i = 0; i < arrays.length; i++) {
            System.out.print("\t" + arrays[i]);
            tree.addBranchSortedToBinaryTree(arrays[i], "");
        }
        System.out.println("]");
        //tree.printTreeWithBranchData("root");
        tree.deleteElementInBinarySortedTree(76);
        //System.out.println("After deleting");
        //tree.printTreeWithBranchData("root");
        ArrayList<Integer> arrayList = new ArrayList<>();
        tree.traverseDepthFirst(root, integerBranchTreeNode -> arrayList.add(integerBranchTreeNode.getData()));
        Assert.assertEquals(Integer.valueOf(60), arrayList.get(0));
        Assert.assertEquals(Integer.valueOf(35), arrayList.get(1));
        Assert.assertEquals(Integer.valueOf(68), arrayList.get(2));
        Assert.assertEquals(Integer.valueOf(17), arrayList.get(3));
        Assert.assertEquals(Integer.valueOf(42), arrayList.get(4));
        Assert.assertEquals(Integer.valueOf(63), arrayList.get(5));
        Assert.assertEquals(Integer.valueOf(69), arrayList.get(6));
        Assert.assertEquals(Integer.valueOf(11), arrayList.get(7));
        Assert.assertEquals(Integer.valueOf(24), arrayList.get(8));
        Assert.assertEquals(Integer.valueOf(23), arrayList.get(9));
    }


    @Test
    public void deleteElementInBinarySortedTree_should_delete_with_only_right_child() {
        int bound = 49;
        int[] arrays = {35, 76, 17, 42, 68, 11, 24, 43, 63, 69, 23};
        System.out.println(60);
        Tree.TreeNode<Integer, Tree.TreeNode.Branch<Integer>> root = new Tree.TreeNode<>(60, 2);
        Tree<Integer, Tree.TreeNode.Branch<Integer>> tree = new Tree<>(root);
        System.out.println("[");
        for (int i = 0; i < arrays.length; i++) {
            System.out.print("\t" + arrays[i]);
            tree.addBranchSortedToBinaryTree(arrays[i], "");
        }
        System.out.println("]");
        tree.printTreeWithBranchData("root");
        tree.deleteElementInBinarySortedTree(42);
        System.out.println("After deleting");
        tree.printTreeWithBranchData("root");
        ArrayList<Integer> arrayList = new ArrayList<>();
        tree.traverseDepthFirst(root, integerBranchTreeNode -> arrayList.add(integerBranchTreeNode.getData()));
        Assert.assertEquals(Integer.valueOf(60), arrayList.get(0));
        Assert.assertEquals(Integer.valueOf(35), arrayList.get(1));
        Assert.assertEquals(Integer.valueOf(76), arrayList.get(2));
        Assert.assertEquals(Integer.valueOf(17), arrayList.get(3));
        Assert.assertEquals(Integer.valueOf(43), arrayList.get(4));
        Assert.assertEquals(Integer.valueOf(68), arrayList.get(5));
        Assert.assertEquals(Integer.valueOf(11), arrayList.get(6));
        Assert.assertEquals(Integer.valueOf(24), arrayList.get(7));
        Assert.assertEquals(Integer.valueOf(63), arrayList.get(8));
        Assert.assertEquals(Integer.valueOf(69), arrayList.get(9));
        Assert.assertEquals(Integer.valueOf(23), arrayList.get(10));
    }

    @Test
    public void deleteElementInBinarySortedTree_should_delete_with_only_root() {
        int bound = 49;
        int[] arrays = {35, 76, 17, 42, 68, 11, 24, 63, 69, 23};
        System.out.println(60);
        Tree.TreeNode<Integer, Tree.TreeNode.Branch<Integer>> root = new Tree.TreeNode<>(60, 2);
        Tree<Integer, Tree.TreeNode.Branch<Integer>> tree = new Tree<>(root);
        System.out.println("[");
        for (int i = 0; i < arrays.length; i++) {
            System.out.print("\t" + arrays[i]);
            tree.addBranchSortedToBinaryTree(arrays[i], "");
        }
        System.out.println("]");
        tree.deleteElementInBinarySortedTree(60);
        ArrayList<Integer> arrayList = new ArrayList<>();
        tree.traverseDepthFirst(tree.getRoot(), integerBranchTreeNode -> arrayList.add(integerBranchTreeNode.getData()));
        Assert.assertEquals(Integer.valueOf(42), arrayList.get(0));
        Assert.assertEquals(Integer.valueOf(35), arrayList.get(1));
        Assert.assertEquals(Integer.valueOf(76), arrayList.get(2));
        Assert.assertEquals(Integer.valueOf(17), arrayList.get(3));
        Assert.assertEquals(Integer.valueOf(68), arrayList.get(4));
        Assert.assertEquals(Integer.valueOf(11), arrayList.get(5));
        Assert.assertEquals(Integer.valueOf(24), arrayList.get(6));
        Assert.assertEquals(Integer.valueOf(63), arrayList.get(7));
        Assert.assertEquals(Integer.valueOf(69), arrayList.get(8));
        Assert.assertEquals(Integer.valueOf(23), arrayList.get(9));
    }
}