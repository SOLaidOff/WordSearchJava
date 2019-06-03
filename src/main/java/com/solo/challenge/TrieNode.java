package com.solo.challenge;

import java.util.HashMap;
import java.util.Set;

public class TrieNode {
    private boolean terminal;
    private HashMap<Character, TrieNode> children;
    
    public TrieNode() {
        
    }
    
    public void addWord(String word) {
        char currentLetter = word.charAt(0);
        boolean endOfWord = word.length() == 1;
        
        TrieNode child = getChild(currentLetter);
        child.setTerminal(endOfWord);
        if(!endOfWord) {
            child.addWord(word.substring(1, word.length()));
        }
    }
    
    private TrieNode getChild(char letter) {
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
    
//    public boolean containsWord(String word) {
//        char currentLetter = word.charAt(0);
//        boolean endOfWord = word.length() == 1;
//        
//        
//        
//        return true;//FIXME
//    }

//    // For debug use
//    @Override
//    public String toString() {
//        Set<Character> keys = children.keySet();
//        
//        for(Character key : keys) {
//            
//        }
//        
//        return root.toString();
//    }
}
