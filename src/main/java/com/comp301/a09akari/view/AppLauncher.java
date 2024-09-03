package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.AlternateMvcController;
import com.comp301.a09akari.controller.ControllerImpl;
import com.comp301.a09akari.SamplePuzzles;
import com.comp301.a09akari.model.*;
import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AppLauncher extends Application {
  @Override
  public void start(Stage stage) {
  stage.setTitle("Akari Puzzle Game");

    PuzzleLibrary library = new PuzzleLibraryImpl();
    addSamplePuzzles(library);

    Model model = new ModelImpl(library);
    AlternateMvcController controller = new ControllerImpl(model);

    ControlView controlView = new ControlView(controller);
    PuzzleView puzzleView = new PuzzleView(controller);
    MessageView messageView = new MessageView(controller);

    Label puzzleIndexLabel = new Label();
    updatePuzzleIndexLabel(puzzleIndexLabel, model);

    VBox root = new VBox(puzzleIndexLabel,
                          controlView.render(),
                          puzzleView.render(),
                          messageView.render());
    Scene scene = new Scene(root, 500, 475);

    model.addObserver((Model type) -> {
      updatePuzzleIndexLabel(puzzleIndexLabel, model);
      root.getChildren().setAll(puzzleIndexLabel, controlView.render(),
          puzzleView.render(), messageView.render());
    });

    stage.setScene(scene);
    stage.show();
  }

  private void addSamplePuzzles(PuzzleLibrary library) {
    library.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_01));
    library.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_02));
    library.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_03));
    library.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_04));
    library.addPuzzle(new PuzzleImpl(SamplePuzzles.PUZZLE_05));
  }

  private void updatePuzzleIndexLabel(Label label, Model model) {
    label.setText("Puzzle " + (model.getActivePuzzleIndex() + 1) + " out of " + model.getPuzzleLibrarySize());
  }
}