package dataStructure.trie;

import lombok.Data;
import org.junit.Assert;

import java.util.HashMap;

public class TrieUtility {
    private TrieNode root;

    public TrieUtility() {
        this.root = new TrieNode();
    }

    /**
     * 1. INSERT
     * @param word
     */
    public void insert(String word) {
        TrieNode current = root;

        for (char l : word.toCharArray()) {
            /**
             * If the current node has already an existing reference to the current letter
             * (through one of the elements in the “children” field),
             * then set current node to that referenced node. Otherwise, create a new node,
             * set the letter equal to the current letter, and also initialize current node to this new node
             */
            if(current.getChildren() != null) {
                current = current.getChildren().computeIfAbsent(l, c -> new TrieNode());
            }
        }
        current.setEndOfWord(true);
        current.setContent(word);
    }

    /**
     * 2. SEARCH
     * @param word
     * @return
     */
    public boolean find(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.getChildren().get(ch);
            if (node == null) {
                return false;
            }
            current = node; //keep moving current node into word's direction
        }
        return current.isEndOfWord() && current.getContent().equals(word);
    }

    public static void main(String[] args) {
        TrieUtility trie = new TrieUtility();
        trie.insert("Programming");
        trie.insert("is");
        trie.insert("a");
        trie.insert("way");
        trie.insert("of");
        trie.insert("life");

        Assert.assertTrue(trie.find("is"));
        Assert.assertTrue(trie.find("Programming"));
        Assert.assertFalse(trie.find("Programm"));
    }




}

@Data
class TrieNode {
    private HashMap<Character, TrieNode> children = new HashMap<>();
    private String content;
    private boolean isEndOfWord;
}