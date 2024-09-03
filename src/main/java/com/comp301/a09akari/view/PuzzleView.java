package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.AlternateMvcController;
import com.comp301.a09akari.controller.ControllerImpl;
import com.comp301.a09akari.model.CellType;
import com.comp301.a09akari.model.Puzzle;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.layout.StackPane;

public class PuzzleView implements FXComponent {
  private final AlternateMvcController controller;

  public PuzzleView(AlternateMvcController controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    GridPane puzzleGrid = new GridPane();
    puzzleGrid.setAlignment(Pos.CENTER);
    puzzleGrid.setGridLinesVisible(true);

    Puzzle activePuzzle = controller.getActivePuzzle();
    for (int r = 0; r < activePuzzle.getHeight(); r++) {
      for (int c = 0; c < activePuzzle.getWidth(); c++) {
        StackPane cell = createCell(r, c);
        puzzleGrid.add(cell, c, r);
      }
    }

    return puzzleGrid;
  }

  private StackPane createCell(int r, int c) {
    StackPane cell = new StackPane();
    cell.setMinSize(40, 40);
    CellType cellType = controller.getActivePuzzle().getCellType(r, c);

    switch (cellType) {
      case WALL:
        cell.setStyle("-fx-background-color: gray;");
        break;
      case CLUE:
        Label clueNum = new Label(String.valueOf(controller.getActivePuzzle().getClue(r, c)));
        clueNum.setTextFill(Color.WHITE);
        cell.getChildren().add(clueNum);
        cell.setStyle("-fx-background-color: black;");
        break;
      case CORRIDOR:
        Button button = new Button();
        button.setMinSize(40, 40);
        button.setOnAction(event -> {
          controller.clickCell(r, c);
          updateCellAppearance(button, r, c);
        });
        updateCellAppearance(button, r, c);
        cell.getChildren().add(button);
        break;
    }

    return cell;
  }

  private void updateCellAppearance(Button button, int r, int c) {
    Image bulbImage = new Image("/light-bulb.png", 18, 20, false, false);
    button.setGraphic(controller.isLamp(r, c) ? new ImageView(bulbImage) : null);

    boolean isLampIllegal = false;
    if (controller instanceof ControllerImpl) {
      isLampIllegal = ((ControllerImpl) controller).getModel().isLampIllegal(r, c);
    }

    if (controller.isLamp(r, c) && isLampIllegal) {
      button.setStyle("-fx-background-color: red;");
    } else if (controller.isLit(r, c)) {
      button.setStyle("-fx-background-color: #FFF000;");
    } else {
      button.setStyle("");
    }
  }
}