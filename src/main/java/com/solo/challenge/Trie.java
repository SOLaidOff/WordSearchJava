package com.solo.challenge;

public class Trie {
    private TrieNode root;
    
    public Trie() {
        root = new TrieNode(false);
    }
    
    public void addWord(String word) {
        root.addWord(word);
    }
    
    public boolean containsWord(String word) {
        return root.containsWord(word);
    }
    
    public TrieNode getRoot() {
        return root;
    }
}
