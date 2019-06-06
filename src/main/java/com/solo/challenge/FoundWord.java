package com.solo.challenge;

public class FoundWord {
    private String word;
    private GridCoordinate startingPoint;
    private GridCoordinate endingPoint;

    public FoundWord(String word, GridCoordinate startingPoint, GridCoordinate endingPoint) {
        this.word = word;
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(word);
        builder.append(" ");
        builder.append(startingPoint.toString());
        builder.append(" ");
        builder.append(endingPoint.toString());
                
        return builder.toString();
    }
}
