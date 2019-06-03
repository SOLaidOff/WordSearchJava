package com.solo.challenge;

public class Trie {
    private TrieNode root;
    
    public void addWord(String word) {
        root.addWord(word);
    }

//    // For debug use
//    @Override
//    public String toString() {
//        return root.toString();
//    }
}
