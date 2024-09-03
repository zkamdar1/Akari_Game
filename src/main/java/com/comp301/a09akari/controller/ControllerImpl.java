package com.comp301.a09akari.controller;

import com.comp301.a09akari.model.CellType;
import com.comp301.a09akari.model.Model;
import com.comp301.a09akari.model.Puzzle;

public class ControllerImpl implements AlternateMvcController {
  private final Model model;

  public ControllerImpl(Model model) {
    this.model = model;
  }

  public Model getModel() {
    return model;
  }

  public void clickNextPuzzle() {
    if (model.getActivePuzzleIndex() == model.getPuzzleLibrarySize() - 1) {
      return;
    } else {
      model.setActivePuzzleIndex(model.getActivePuzzleIndex() + 1);
    }
  }

  public void clickPrevPuzzle() {
    if (model.getActivePuzzleIndex() == 0) {
      return;
    } else {
      model.setActivePuzzleIndex(model.getActivePuzzleIndex() - 1);
    }
  }

  public void clickRandPuzzle() {
    double x = Math.random() * (model.getPuzzleLibrarySize() - 1);
    model.setActivePuzzleIndex((int) x);
  }

  public void clickResetPuzzle() {
    model.resetPuzzle();
  }

  public void clickCell(int r, int c) {
    if (model.getActivePuzzle().getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }

    if (model.isLamp(r, c)) {
      model.removeLamp(r, c);
    } else {
      model.addLamp(r, c);
    }
  }

  public boolean isLit(int r, int c) {
    return model.isLit(r, c);
  }

  public boolean isLamp(int r, int c) {
    return model.isLamp(r, c);
  }

  public boolean isClueSatisfied(int r, int c) {
    return model.isClueSatisfied(r, c);
  }

  public boolean isSolved() {
    return model.isSolved();
  }

  public Puzzle getActivePuzzle() {
    return model.getActivePuzzle();
  }
}
