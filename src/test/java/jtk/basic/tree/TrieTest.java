package jtk.basic.tree;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

/**
 * Created by jubin on 5/3/2017.
 */
public class TrieTest {

    private static Trie trie;

    @BeforeClass
    public static void setUp() {
        trie = new Trie();
        trie.addWord("hello");
        trie.addWord("hell");
        trie.addWord("herman");
        trie.addWord("hero");
        trie.addWord("heart");
        trie.addWord("hate");
        trie.addWord("hair");
        trie.addWord("hat");
        trie.addWord("bark");
        trie.printDictionary();
    }

    @Test
    public void getAllWordsStartingWith_should_return_7_letters_stating_with_h() {
        List<String> words = trie.getAllWordsStartingWith("H");
        Assert.assertEquals(8, words.size());
        System.out.println(words);
    }


    @Test
    public void getAllWordsStartingWith_should_return_3_letters_stating_with_ha() {
        List<String> words = trie.getAllWordsStartingWith("ha");
        Assert.assertEquals(3, words.size());
        System.out.println(words);
    }

    @Test
    public void getAllWordsStartingWith_should_return_2_letters_stating_with_her() {
        List<String> words = trie.getAllWordsStartingWith("her");
        Assert.assertEquals(2, words.size());
        System.out.println(words);
    }

    @Test
    public void getAllWordsStartingWith_should_return_1_letters_stating_with_ba() {
        List<String> words = trie.getAllWordsStartingWith("ba");
        Assert.assertEquals(1, words.size());
        System.out.println(words);
    }


}