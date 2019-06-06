package com.solo.challenge;

public class FoundWord {
    private String wordOriginal;
    private String wordDespaced;
    private GridCoordinate startingPoint;
    private GridCoordinate endingPoint;

    public FoundWord(String inputWordOriginal, String inputWordWithoutSpaces) {
        wordOriginal = inputWordOriginal;
        wordDespaced = inputWordWithoutSpaces;
    }

    public FoundWord(String wordOriginal, String wordDespaced, GridCoordinate startingPoint, GridCoordinate endingPoint) {
        this.wordOriginal = wordOriginal;
        this.wordDespaced = wordDespaced;
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
    }
    
    public String getWordWithoutSpaces() {
        return wordDespaced;
    }
    
    public void setStartAndEnd(GridCoordinate startingPoint, GridCoordinate endingPoint) {
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(wordOriginal);
        builder.append(" ");
        builder.append(startingPoint.toString());
        builder.append(" ");
        builder.append(endingPoint.toString());
                
        return builder.toString();
    }
}
