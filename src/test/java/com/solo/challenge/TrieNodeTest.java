package com.solo.challenge;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TrieNodeTest {
    @Test
    @DisplayName("By default, new nodes are not word-endings")
    void newNodesNotWordEndings() {
        TrieNode node = new TrieNode();
        assertFalse(node.isTerminal());
    }

    @Test
    @DisplayName("New nodes may have word ending status set manually")
    void newNodesManualWordEndings() {
        TrieNode nodeTrue = new TrieNode(true);
        assertTrue(nodeTrue.isTerminal());
        
        TrieNode nodeFalse = new TrieNode(false);
        assertFalse(nodeFalse.isTerminal());
    }

    @Test
    @DisplayName("Insert short word")
    void insertShortWord() {
        TrieNode root = new TrieNode();
        root.addWord("A");
        assertTrue(root.containsWord("A"));
    }

    @Test
    @DisplayName("Insert long word")
    void insertLongWord() {
        TrieNode root = new TrieNode();
        root.addWord("DOG");
        assertFalse(root.containsWord("A"));
        assertFalse(root.containsWord("D"));
        assertTrue(root.containsWord("DOG"));
    }

    @Test
    @DisplayName("Find real children")
    void findRealChildren() {
        TrieNode root = new TrieNode();
        root.addWord("PLANE");
        root.addWord("TRAIN");
        root.addWord("TRUCK");
        assertNotNull(root.getChild('P'));
        assertNotNull(root.getChild('T'));
        assertNull(root.getChild('T'));
    }

    @Test
    @DisplayName("Do not find missing word")
    void doNotFindMissingWord() {
        TrieNode root = new TrieNode();
        root.addWord("A");
        assertFalse(root.containsWord("B"));
    }

    @Test
    @DisplayName("Find only legitimate words")
    void findOnlyLegitimateWords() {
        TrieNode root = new TrieNode();
        root.addWord("CARPET");
        assertTrue(root.containsWord("CARPET"));
        assertFalse(root.containsWord("CARP"));
        assertFalse(root.containsWord("PET"));
    }
}
