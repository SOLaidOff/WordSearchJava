/*
 * Copyright 2015-2018 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v20.html
 */

package com.solo.challenge;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class WordSearch {
    public static final char INPUT_SIZE_DELIMITER = 'x';
    
    private int numRows;
    private int numCols;
    private char[][] grid;
    private Trie targets;
    private Set<FoundWord> answers;
    
    public static void main(String[] args) {
        WordSearch solver = new WordSearch();
        
        System.out.println("Welcome to the word search solver!");
        
        solver.loadInput();
        solver.solve(); // TODO: currently displays all instances of a word in the grid if it appears multiple times; confirm desired behavior
        solver.displayAnswers(); // TODO: currently displays answers in random order; confirm this is acceptable
    }
    
    public WordSearch() {
        answers = new HashSet<FoundWord>();
    }

    private void loadInput() {
        BufferedReader reader = openFile();
        
        System.out.println();
        
        try {
            handleFirstLine(reader);
            createGrid(reader);
            saveTargets(reader);
            
            reader.close();
        } catch(IOException e) {
            System.err.println("File I/O error. Irrelevant because assignment says to ignore invalid input.");
            System.exit(1);
        }
    }
    
    private BufferedReader openFile() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(getFilename()));
        } catch(FileNotFoundException e) {
            System.err.println("File not found. Irrelevant because assignment says to ignore invalid input.");
            System.exit(1);
        }
        
        return reader;
    }
    
    private String getFilename() {
        System.out.println();
        System.out.println("Please enter the location of the input file:");
        
        Scanner scanner = new Scanner(System.in);
        String filename = scanner.nextLine();
        scanner.close();
        
        return filename;
    }
    
    private void handleFirstLine(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        
        int breakpoint = line.indexOf(INPUT_SIZE_DELIMITER);
        
        String rowString = line.substring(0, breakpoint);
        String colString = line.substring(breakpoint + 1, line.length());
        numRows = Integer.valueOf(rowString);
        numCols = Integer.valueOf(colString);
        
        grid = new char[numRows][numCols];
    }
    
    private void createGrid(BufferedReader reader) throws IOException {
        String line;
        
        for(int rowNumber = 0; rowNumber < numRows; rowNumber++) {
            line = reader.readLine();

            String[] letters = line.split(" ");
            
            for(int colNumber = 0; colNumber < numCols; colNumber++) {
                char nextLetter = letters[colNumber].charAt(0);
                grid[rowNumber][colNumber] = Character.toUpperCase(nextLetter);
            }
        }
    }
    
    private void saveTargets(BufferedReader reader) throws IOException {
        targets = new Trie();
        
        String line = reader.readLine();
        line = line.replaceAll("\\s",""); // Per requirements, words to find may start out with spaces but spaces won't be included in the grid
        while(line != null) {
            targets.addWord(line.toUpperCase());
            
            line = reader.readLine();
        }
    }
    
    private void solve() {
        for(int row = 0; row < numRows; row++) {
            for(int col = 0; col < numCols; col++) {
                searchStartingPoint(row, col);
            }
        }
    }
    
    private void searchStartingPoint(int row, int col) {
        GridCoordinate startingPoint = new GridCoordinate(row, col);
        
        for(SearchDirection direction : SearchDirection.values()) {
            directionalSearch(startingPoint, direction, "", targets.getRoot(), startingPoint);
        }
    }
    
    private void directionalSearch(GridCoordinate currentLocation, SearchDirection searchDirection, String wordSoFar, TrieNode currentNode, GridCoordinate startingPoint) {
        int row = currentLocation.getRow();
        int col = currentLocation.getCol();
        
        // Recursive base case 1 of 2: grid exceeded
        if(!isValidLocation(row, col)) {
            return;
        }
        // Recursive base case 2 of 2: none of the target words start with the letters seen so far
        TrieNode childNode = currentNode.getChild(grid[row][col]);
        if(childNode == null) {
            return;
        }
        
        // Recursive case: append the current letter to the string generated so far, see if it matches a word, and continue searching in the desired direction
        String newWord = wordSoFar + grid[row][col];
        if(childNode.isTerminal()) {
            answers.add(new FoundWord(newWord, startingPoint, new GridCoordinate(row, col)));
        }
        
        GridCoordinate nextLocation = null;
        try {
            nextLocation = moveInDirection(searchDirection, currentLocation);
        } catch(WordSearchException e) {
            System.exit(1);
        }
        directionalSearch(nextLocation, searchDirection, newWord, childNode, startingPoint);
    }
    
    private boolean isValidLocation(int row, int col) {
        return row >= 0 && row < numRows && col >= 0 && col < numCols;
    }
    
    private GridCoordinate moveInDirection(SearchDirection direction, GridCoordinate currentLocation) throws WordSearchException {
        switch (direction) {
            case UP:
                return new GridCoordinate(currentLocation.getRow() - 1, currentLocation.getCol());
            case UP_RIGHT:
                return new GridCoordinate(currentLocation.getRow() - 1, currentLocation.getCol() + 1);
            case RIGHT:
                return new GridCoordinate(currentLocation.getRow(), currentLocation.getCol() + 1);
            case DOWN_RIGHT:
                return new GridCoordinate(currentLocation.getRow() + 1, currentLocation.getCol() + 1);
            case DOWN:
                return new GridCoordinate(currentLocation.getRow() + 1, currentLocation.getCol());
            case DOWN_LEFT:
                return new GridCoordinate(currentLocation.getRow() + 1, currentLocation.getCol() - 1);
            case LEFT:
                return new GridCoordinate(currentLocation.getRow(), currentLocation.getCol() - 1);
            case UP_LEFT:
                return new GridCoordinate(currentLocation.getRow() - 1, currentLocation.getCol() - 1);
            default:
                throw new WordSearchException("Unknown direction");
        }
    }
    
    private void displayAnswers() {
        for(FoundWord word : answers) {
            System.out.println(word);
        }
    }
}
