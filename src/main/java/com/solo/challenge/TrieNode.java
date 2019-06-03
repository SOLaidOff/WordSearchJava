package com.solo.challenge;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    private boolean terminal;
    private Map<Character, TrieNode> children;
    
    public TrieNode() {
        children = new HashMap<Character, TrieNode>();
    }
    
    public TrieNode(boolean terminal) {
        children = new HashMap<Character, TrieNode>();
        this.terminal = terminal;
    }
    
    public void addWord(String word) {
        char currentLetter = word.charAt(0);
        boolean endOfWord = word.length() == 1;
        
        TrieNode child = getOrMakeChild(currentLetter);
        child.setTerminal(endOfWord);
        if(!endOfWord) {
            child.addWord(word.substring(1, word.length()));
        }
    }
    
    private TrieNode getOrMakeChild(char letter) {
        TrieNode mapEntry = children.get(letter);
        if(mapEntry == null) {
            children.put(letter, new TrieNode());
        }
        
        return children.get(letter);
    }
    
    private void setTerminal(boolean value) {
        terminal = value;
    }
    
    private boolean isTerminal() {
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

    // Mostly for debug use
    @Override
    public String toString() {
        return "TrieNode [children=" + children + "] [Is " + (terminal ? "" : "not ") + "a terminal]";
    }
}
