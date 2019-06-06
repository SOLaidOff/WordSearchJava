package com.solo.challenge;

public class GridCoordinate {
    private int row;
    private int col;
    
    public GridCoordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }
    
    public int getRow() {
        return row;
    }
    
    public int getCol() {
        return col;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(row);
        builder.append(":");
        builder.append(col);
        
        return builder.toString();
    }
}
