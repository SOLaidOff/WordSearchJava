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
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class WordSearch {
    public static final String INPUT_SIZE_DELIMITER = "x";
    public static final String INPUT_GRID_DELIMITER = " ";
    
    private String inputFileName;
    
    private int numRows;
    private int numCols;
    private char[][] grid;
    private TrieNode wordsToFindTrie;
    private List<FoundWord> foundWords;
    
    public static void main(String[] args) {
        WordSearch solver = new WordSearch();
        
        String inputFileNameArg = args.length == 1 ? args[0] : null;
        solver.setInputFileName(inputFileNameArg);
        
        System.out.println("Welcome to the word search solver!");
        System.out.println();
        
        solver.loadInput();
        solver.solve(); // Currently, if a word appears in the grid multiple times, program displays only one instance; change this if not desired behavior
        solver.displayAnswers();
    }
    
    public WordSearch() {
        wordsToFindTrie = new TrieNode();
        foundWords = new LinkedList<FoundWord>();
    }
    
    private void setInputFileName(String inputFileNameArg) {
        inputFileName = inputFileNameArg;
    }

    private void loadInput() {
        inputFileName = inputFileName == null ? getFilename() : inputFileName;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName))) {
            handleFirstLine(reader);
            createGrid(reader);
            saveTargets(reader);
        } catch(IOException e) {
            System.err.println("File I/O error. Irrelevant because assignment says to ignore invalid input.");
            System.exit(1);
        }
    }
    
    private String getFilename() {
        System.out.println("Please enter the location of the input file:");
        
        Scanner scanner = new Scanner(System.in);
        String filename = scanner.nextLine();
        scanner.close();
        
        System.out.println();
        
        return filename;
    }
    
    private void handleFirstLine(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        
        String[] pieces = line.split(INPUT_SIZE_DELIMITER);

        numRows = Integer.valueOf(pieces[0]);
        numCols = Integer.valueOf(pieces[1]);
        
        grid = new char[numRows][numCols];
    }
    
    private void createGrid(BufferedReader reader) throws IOException {
        String line;
        
        for(int rowNumber = 0; rowNumber < numRows; rowNumber++) {
            line = reader.readLine();

            String[] letters = line.split(INPUT_GRID_DELIMITER);
            
            for(int colNumber = 0; colNumber < numCols; colNumber++) {
                char nextLetter = letters[colNumber].charAt(0);
                grid[rowNumber][colNumber] = Character.toUpperCase(nextLetter);
            }
        }
    }
    
    private void saveTargets(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        // Per requirements, words to find may start out with spaces but spaces won't be included in the grid
        while(line != null) {
            String lineWithoutSpaces = line.replaceAll("\\s","");
            foundWords.add(new FoundWord(line, lineWithoutSpaces));
            wordsToFindTrie.addWord(lineWithoutSpaces.toUpperCase());
            
            line = reader.readLine();
        }
    }
    
    private void solve() {
        // Overall algorithm: for all cells in grid, look at each one as a possible starting point for words in every direction
        // Potential minor optimizations: stop searching when exceeding maximum search term length; stop searching after all words found
        for(int row = 0; row < numRows; row++) {
            for(int col = 0; col < numCols; col++) {
                searchStartingPoint(row, col);
            }
        }
    }
    
    private void searchStartingPoint(int row, int col) {
        GridCoordinate startingPoint = new GridCoordinate(row, col);
        
        for(SearchDirection direction : SearchDirection.values()) {
            directionalSearch(startingPoint, direction, "", wordsToFindTrie, startingPoint);
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
        if(childNode.isEndOfWord()) {
            try {
                FoundWord answer = getAnswerLocationForWord(newWord);
                answer.setStartAndEnd(startingPoint, currentLocation);
            } catch(WordSearchException e) {
                System.err.println(e);
                System.exit(1);
            }
        }
        
        GridCoordinate nextLocation = null;
        try {
            nextLocation = moveInDirection(searchDirection, currentLocation);
        } catch(WordSearchException e) {
            System.err.println(e);
            System.exit(1);
        }
        directionalSearch(nextLocation, searchDirection, newWord, childNode, startingPoint);
    }
    
    private boolean isValidLocation(int row, int col) {
        return row >= 0 && row < numRows && col >= 0 && col < numCols;
    }
    
    private FoundWord getAnswerLocationForWord(String wordWithoutSpaces) throws WordSearchException {
        for(FoundWord foundWord : foundWords) {
            if(wordWithoutSpaces.equals(foundWord.getWordWithoutSpaces())) {
                return foundWord;
            }
        }
        
        throw new WordSearchException("Found a word not in the list of target words");
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
        for(FoundWord word : foundWords) {
            System.out.println(word);
        }

        System.out.println();
        System.out.println("Word search complete!");
        System.out.println();
    }
}
