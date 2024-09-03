package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.AlternateMvcController;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class MessageView implements FXComponent {
  private final AlternateMvcController controller;
  private final Label messageLabel;

  public MessageView(AlternateMvcController controller) {
    this.controller = controller;
    this.messageLabel = new Label("Welcome to Akari Puzzle!");
    messageLabel.setFont(new Font("Arial", 16));
    messageLabel.setTextAlignment(TextAlignment.CENTER);
  }

  @Override
  public Parent render() {
    updateMessage();
    return new VBox(messageLabel);
  }

  private void updateMessage() {
    if (controller.isSolved()) {
      messageLabel.setText("Congratulations on Solving the Puzzle!");
    } else {
      messageLabel.setText("Solve the puzzle.");
    }
  }
}