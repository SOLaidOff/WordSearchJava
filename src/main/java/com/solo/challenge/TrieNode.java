package com.solo.challenge;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    private boolean terminal;
    private Map<Character, TrieNode> children;
    
    public TrieNode() {
        this(false);
    }
    
    public TrieNode(boolean terminal) {
        children = new HashMap<Character, TrieNode>();
        this.terminal = terminal;
    }
    
    public void addWord(String word) {
        char currentLetter = word.charAt(0);
        boolean endOfWord = word.length() == 1;
        
        TrieNode child = getOrMakeChild(currentLetter);
        child.setTerminal(child.isTerminal() || endOfWord); // Don't overwrite terminal marker for longer words that start with shorter words, e.g. CAR and CARPET
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
    
    private void setTerminal(boolean value) {
        terminal = value;
    }
    
    boolean isTerminal() {
        return terminal;
    }
    
    public boolean containsWord(String word) {
        char currentLetter = word.charAt(0);
        
        TrieNode child = children.get(currentLetter);
        if(child == null) {
            return false;
        }
        
        boolean endOfWord = word.length() == 1;
        if(endOfWord) {
            return child.isTerminal();
        } else {
            return child.containsWord(word.substring(1, word.length()));
        }
    }
}
