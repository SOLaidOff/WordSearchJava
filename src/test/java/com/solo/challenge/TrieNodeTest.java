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
        assertFalse(node.isEndOfWord());
    }

    @Test
    @DisplayName("New nodes may have word ending status set manually")
    void newNodesManualWordEndings() {
        TrieNode nodeTrue = new TrieNode(true);
        assertTrue(nodeTrue.isEndOfWord());
        
        TrieNode nodeFalse = new TrieNode(false);
        assertFalse(nodeFalse.isEndOfWord());
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

        assertTrue(root.containsWord("DOG"));

        TrieNode d = root.getChild('D');
        assertNotNull(d);
        assertFalse(d.isEndOfWord());
        TrieNode o = d.getChild('O');
        assertNotNull(o);
        assertFalse(o.isEndOfWord());
        TrieNode g = o.getChild('G');
        assertNotNull(g);
        assertTrue(g.isEndOfWord());
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
        assertNull(root.getChild('R'));
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
