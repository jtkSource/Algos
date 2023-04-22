package jtk.basic.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * Created by jubin on 5/2/2017.
 */
public class Trie {

    HashMap<Character, Tree<Character, Tree.TreeNode.Branch<Boolean>>> dictionary = new HashMap<>();

    public List<String> getAllWordsStartingWith(String preffix) {
        preffix = preffix.toLowerCase();
        Tree<Character, Tree.TreeNode.Branch<Boolean>> wordTree = dictionary.get(preffix.charAt(0));
        Tree.TreeNode<Character, Tree.TreeNode.Branch<Boolean>> parentNode = wordTree.getRoot();
        Tree.TreeNode<Character, Tree.TreeNode.Branch<Boolean>> rootNode = new Tree.TreeNode<>('*');
        Tree.TreeNode.Branch<Boolean> rootBranch = new Tree.TreeNode.Branch<>(false, null, parentNode);
        rootNode.addBranch(rootBranch);
        int lengthOfWord = preffix.toCharArray().length;
        StringBuilder stringBuilder = new StringBuilder();
        boolean foundPrefix = false;
        ArrayList<String> listOfWords = new ArrayList<>();
        for (int i = 0; i < lengthOfWord; i++) {
            Optional<Tree.TreeNode.Branch<Boolean>> branch = wordTree.searchNodesChildrenForData(rootNode, preffix.charAt(i));
            if (branch.isPresent()) {
                stringBuilder.append(branch.get().getChild().getData());
                if (branch.get().getBranchData()) {
                    listOfWords.add(stringBuilder.toString());
                }
                rootNode = branch.get().getChild();
                if (i == lengthOfWord - 1)
                    foundPrefix = true;
            }
        }
        if (foundPrefix) {
            findWordsFromNode(rootNode, stringBuilder.toString(), listOfWords);
        }
        return listOfWords;
    }

    private void findWordsFromNode(Tree.TreeNode<Character, Tree.TreeNode.Branch<Boolean>> parentNode, String prefix, ArrayList<String> listOfWords) {
        StringBuilder builder = null;
        for (Tree.TreeNode.Branch<Boolean> branch : parentNode.getBranches()) {
            builder = new StringBuilder();
            builder.append(prefix).append(branch.getChild().getData());
            if (branch.getBranchData())
                listOfWords.add(builder.toString());
            if (!branch.getChild().getBranches().isEmpty())
                findWordsFromNode(branch.getChild(), builder.toString(), listOfWords);
        }
    }

    public void printDictionary() {
        for (Character key : dictionary.keySet()) {
            Tree<Character, Tree.TreeNode.Branch<Boolean>> characterBranchTree = dictionary.get(key);
            characterBranchTree.printTreeWithBranchData("root");
        }
    }

    public void addWord(String word) {
        word = word.toLowerCase();
        dictionary.putIfAbsent(word.charAt(0), new Tree<Character, Tree.TreeNode.Branch<Boolean>>(word.charAt(0)));
        Tree<Character, Tree.TreeNode.Branch<Boolean>> wordTree = dictionary.get(word.charAt(0));
        Tree.TreeNode<Character, Tree.TreeNode.Branch<Boolean>> parentNode = wordTree.getRoot();
        int lengthOfWord = word.toCharArray().length;
        for (int i = 1; i < lengthOfWord; i++) {
            Optional<Tree.TreeNode.Branch<Boolean>> branch = wordTree.searchNodesChildrenForData(parentNode, word.charAt(i));
            if (!branch.isPresent()) {
                Tree.TreeNode childNode = new Tree.TreeNode(word.charAt(i));
                parentNode.addBranch(new Tree.TreeNode.Branch<>(i >= lengthOfWord - 1, parentNode, childNode));
                parentNode = childNode;
            } else {
                parentNode = branch.get().getChild();
                branch.get().setBranchData(i >= lengthOfWord - 1);

            }
        }
    }


}
