package com.comp301.a09akari.model;

import java.util.ArrayList;

public class ModelImpl implements Model {
  private final PuzzleLibrary library;
  private final ArrayList<ModelObserver> observers;
  private int [][] lamp;
  private int puzzle;

  public ModelImpl(PuzzleLibrary library) {
    if (library == null) {
      throw new IllegalArgumentException();
    }
    this.library = library;
    observers = new ArrayList<>();
    puzzle = 0;
    lamp = new int[library.getPuzzle(puzzle).getHeight()][library.getPuzzle(puzzle).getWidth()];
    for (int i = 0; i < lamp.length; i++) {
      for (int x = 0; x < lamp[0].length; x++) {
        lamp[i][x] = 0;
      }
    }

  }

  @Override
  public void addLamp(int r, int c) {
    if (r < 0 || c < 0
        || r > library.getPuzzle(puzzle).getHeight()
        || c > library.getPuzzle(puzzle).getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    if (library.getPuzzle(puzzle).getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    lamp[r][c] = 1;
    notifyObservers();
  }

  @Override
  public void removeLamp(int r, int c) {
    if (r < 0 || c < 0
        || r >= library.getPuzzle(puzzle).getHeight()
        || c >= library.getPuzzle(puzzle).getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    if (library.getPuzzle(puzzle).getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    lamp[r][c] = 0;
    notifyObservers();
  }

  @Override
  public boolean isLit(int r, int c) {
    if (r < 0 || c < 0
        || r >= library.getPuzzle(puzzle).getHeight()
        || c >= library.getPuzzle(puzzle).getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    if (library.getPuzzle(puzzle).getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    if (lamp[r][c] == 1) {
      return true;
    }
    for (int i = c - 1; i >= 0; i--) {
      if (library.getPuzzle(puzzle).getCellType(r, i) != CellType.CORRIDOR) {
        break;
      }
      if (lamp[r][i] == 1) {
        return true;
      }
    }
    for (int i = r - 1; i >= 0; i--) {
      if (library.getPuzzle(puzzle).getCellType(i, c) != CellType.CORRIDOR) {
        break;
      }
      if (lamp[i][c] == 1) {
        return true;
      }
    }

    for (int i = c + 1; i < lamp[0].length; i++) {
      if (library.getPuzzle(puzzle).getCellType(r, i) != CellType.CORRIDOR) {
        break;
      }
      if (lamp[r][i] == 1) {
        return true;
      }
    }
    for (int i = r + 1; i < lamp.length; i++) {
      if (library.getPuzzle(puzzle).getCellType(i, c) != CellType.CORRIDOR) {
        break;
      }
      if (lamp[i][c] == 1) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean isLamp(int r, int c) {
    if (r < 0 || c < 0
        || r >= library.getPuzzle(puzzle).getHeight()
        || c >= library.getPuzzle(puzzle).getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    if (library.getPuzzle(puzzle).getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    return lamp[r][c] == 1;
  }

  @Override
  public boolean isLampIllegal(int r, int c) {
    if (r < 0 || c < 0
        || r >= library.getPuzzle(puzzle).getHeight()
        || c >= library.getPuzzle(puzzle).getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    if (library.getPuzzle(puzzle).getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    for (int i = c - 1; i >= 0; i--) {
      if (library.getPuzzle(puzzle).getCellType(r, i) != CellType.CORRIDOR) {
        break;
      }
      if (lamp[r][i] == 1) {
        return true;
      }
    }
    for (int i = r - 1; i >= 0; i--) {
      if (library.getPuzzle(puzzle).getCellType(i, c) != CellType.CORRIDOR) {
        break;
      }
      if (lamp[i][c] == 1) {
        return true;
      }
    }
    for (int i = c + 1; i < lamp[0].length; i++) {
      if (library.getPuzzle(puzzle).getCellType(r, i) != CellType.CORRIDOR) {
        break;
      }
      if (lamp[r][i] == 1) {
        return true;
      }
    }
    for (int i = r + 1; i < lamp.length; i++) {
      if (library.getPuzzle(puzzle).getCellType(i, c) != CellType.CORRIDOR) {
        break;
      }
      if (lamp[i][c] == 1) {
        return true;
      }
    }
    return false;
  }

  @Override
  public Puzzle getActivePuzzle() {
      return library.getPuzzle(puzzle);
  }

  @Override
  public int getActivePuzzleIndex() {
    return puzzle;
  }

  @Override
  public void setActivePuzzleIndex(int index) {
    if (index < 0 || index >= getPuzzleLibrarySize()) {
      throw new IndexOutOfBoundsException();
    }
    puzzle = index;
    resetPuzzle();
  }

  @Override
 public int getPuzzleLibrarySize() {
    return library.size();
  }

 @Override
 public void resetPuzzle() {
   lamp = new int[library.getPuzzle(puzzle).getHeight()][library.getPuzzle(puzzle).getWidth()];
   for (int i = 0; i < library.getPuzzle(puzzle).getWidth(); i++) {
     for (int x = 0; x < library.getPuzzle(puzzle).getHeight(); x++) {
       lamp[x][i] = 0;
     }
   }
   notifyObservers();
 }

  @Override
  public boolean isSolved() {
    for (int x = 0; x < lamp[0].length; x++) {
      for (int y = 0; y < lamp.length; y++) {
        if (library.getPuzzle(puzzle).getCellType(y, x) == CellType.CLUE) {
          if (!isClueSatisfied(y, x)) {
            return false;
          }
        } else if (library.getPuzzle(puzzle).getCellType(y, x) == CellType.CORRIDOR) {
          if (!isLit(y, x)) {
            return false;
          }
          if (isLamp(y, x)) {
            if (isLampIllegal(y, x)) {
              return false;
            }
          }
        }
      }
    }
    return true;
  }

  @Override
  public boolean isClueSatisfied(int r, int c) {
    if (r < 0
        || c < 0
        || r >= library.getPuzzle(puzzle).getHeight()
        || c >= library.getPuzzle(puzzle).getWidth()) {
      throw new IndexOutOfBoundsException();
    }

    if (library.getPuzzle(puzzle).getCellType(r, c) != CellType.CLUE) {
      throw new IllegalArgumentException();
    }

    int solved = library.getPuzzle(puzzle).getClue(r, c);
    int left = c - 1;
    int right = c + 1;
    int up = r - 1;
    int down = r + 1;

    return solved == (lampCount(r, left) +
                      lampCount(r, right) +
                      lampCount(up, c) +
                      lampCount(down, c));
  }

  public int lampCount(int r, int c) {
    if (r < 0
        || c < 0
        || r >= library.getPuzzle(puzzle).getHeight()
        || c >= library.getPuzzle(puzzle).getWidth()) {
      return 0;
    }
    if (library.getPuzzle(puzzle).getCellType(r, c) != CellType.CORRIDOR) {
      return 0;
    }
    return lamp[r][c];
  }
  @Override
  public void addObserver(ModelObserver observer) {
    if (observer == null) {
      throw new IllegalArgumentException();
    }

    if (!observers.contains(observer)) {
      observers.add(observer);
    }
  }

  @Override
  public void removeObserver(ModelObserver observer) {
    if (observer == null || !observers.contains(observer)) {
      throw new IllegalArgumentException();
    }
    observers.remove(observer);
  }

  public void notifyObservers() {
    for (ModelObserver observer : observers) {
      observer.update(this);
    }
  }
}