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
import java.util.Scanner;

public class WordSearch {
    public static final char INPUT_SIZE_DELIMITER = 'x';
    
    private int numRows;
    private int numCols;
    private char[][] grid;
    private Trie targets;
    
    public static void main(String[] args) {
        WordSearch solver = new WordSearch();
        
        System.out.println("Welcome to the word search solver!");
        
        solver.loadInput();
    }
    
    public WordSearch() {
        
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
                grid[rowNumber][colNumber] = letters[colNumber].charAt(0);
            }
        }
    }
    
    private void saveTargets(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        while(line != null) {
            targets.addWord(line);
        }
    }

    // TODO: after getting started, remove this sample method that came with the bootstrap project
	public int add(int a, int b) {
		return a + b;
	}
}
