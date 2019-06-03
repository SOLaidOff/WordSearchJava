package com.solo.challenge;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TrieNodeTest {
    @Test
    @DisplayName("Insert short word")
    void insertShortWord() {
        TrieNode root = new TrieNode();
        root.addWord("A");
        assertTrue(root.containsWord("A"));
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
        assertFalse(root.containsWord("CAR"));
        assertFalse(root.containsWord("PET"));
    }
}
