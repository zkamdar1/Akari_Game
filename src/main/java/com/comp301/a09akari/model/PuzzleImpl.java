package com.comp301.a09akari.model;

import javafx.scene.control.Cell;

public class PuzzleImpl implements Puzzle {
  private final int [][] board;
  public PuzzleImpl(int[][] board) {
    if (board == null || board.length < 1) {
      throw new IllegalArgumentException("Invalid");
    }
    this.board = board;
  }

  @Override
  public int getWidth() {
    return board[0].length;
  }

  @Override
  public int getHeight() {
    return this.board.length;
  }

  @Override
  public CellType getCellType(int r, int c) {
    if (r < 0 || c < 0 || r >= this.getHeight() || c >= this.getWidth()) {
      throw new IndexOutOfBoundsException("Invalid");
    }
    if (this.board[r][c] == 0
          || this.board[r][c] == 1
          || this.board[r][c] == 2
          || this.board[r][c] == 3
          || this.board[r][c] == 4) {
      return CellType.CLUE;
    } else if (this.board[r][c] == 5) {
      return CellType.WALL;
    } else {
      return CellType.CORRIDOR;
    }
  }

  @Override
  public int getClue(int r, int c) {
    if (r < 0 || c < 0 || r >= this.getHeight() || c >= this.getWidth()) {
      throw new IndexOutOfBoundsException("Invalid");
    }
    if (getCellType(r, c) != CellType.CLUE) {
      throw new IllegalArgumentException("Not a Clue");
    }
    return this.board[r][c];
  }
}