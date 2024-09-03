package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.AlternateMvcController;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;

public class ControlView implements FXComponent {
  private final AlternateMvcController controller;


  public ControlView(AlternateMvcController controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    HBox controlPanel = new HBox(10);
    controlPanel.setAlignment(Pos.CENTER);

    Button newButton = new Button("Generate a Puzzle!");
    newButton.setOnAction(event -> controller.clickRandPuzzle());

    Button nextButton = new Button("Go to Next Puzzle");
    nextButton.setOnAction(event -> controller.clickNextPuzzle());

    Button prevButton = new Button("Go to Previous Puzzle");
    prevButton.setOnAction(event -> controller.clickPrevPuzzle());

    Button resetButton = new Button("Start Over!");
    resetButton.setOnAction(event -> controller.clickResetPuzzle());

    controlPanel.getChildren().addAll(newButton, nextButton, prevButton, resetButton);

    return controlPanel;
  }
}