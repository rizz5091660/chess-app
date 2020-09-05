package com.tyro.chess;

public class ChessBoard {
    public final static int boardSize = 8;
    public final static String board[][] = new String[boardSize][boardSize];
    public final static String columns = "abcdefgh";

    static {
        for (int i = 0; i < boardSize; i++) {
            int row = boardSize - i;
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = columns.charAt(j) + String.valueOf(row);
            }
        }
    }
}
