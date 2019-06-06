package com.solo.challenge;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    private boolean endOfWord;
    private Map<Character, TrieNode> children;
    
    public TrieNode() {
        this(false);
    }
    
    public TrieNode(boolean endOfWord) {
        children = new HashMap<Character, TrieNode>();
        this.endOfWord = endOfWord;
    }
    
    public void addWord(String word) {
        char currentLetter = word.charAt(0);
        boolean endOfWord = word.length() == 1;
        
        TrieNode child = getOrMakeChild(currentLetter);
        child.setEndOfWord(child.isEndOfWord() || endOfWord); // Don't overwrite terminal marker for longer words that start with shorter words, e.g. CAR and CARPET
        if(!endOfWord) {
            child.addWord(word.substring(1, word.length()));
        }
    }

    TrieNode getChild(char letter) {
        return children.get(letter);
    }
    
    private TrieNode getOrMakeChild(char letter) {
        TrieNode mapEntry = getChild(letter);
        if(mapEntry == null) {
            children.put(letter, new TrieNode());
        }
        
        return children.get(letter);
    }
    
    private void setEndOfWord(boolean value) {
        endOfWord = value;
    }
    
    boolean isEndOfWord() {
        return endOfWord;
    }
    
    public boolean containsWord(String word) {
        char currentLetter = word.charAt(0);
        
        TrieNode child = children.get(currentLetter);
        if(child == null) {
            return false;
        }
        
        boolean endOfWord = word.length() == 1;
        if(endOfWord) {
            return child.isEndOfWord();
        } else {
            return child.containsWord(word.substring(1, word.length()));
        }
    }
}
