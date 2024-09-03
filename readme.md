# Akari Game

## Introduction

Welcome to the Akari Game! Akari, also known as Light Up, is a logic-based puzzle game where the goal is to illuminate all the corridors of a grid by placing lamps, following certain rules. This game is a single-player experience that challenges your problem-solving skills.

This implementation of the Akari game is built using the Model-View-Controller (MVC) design pattern and the JavaFX UI library to provide a complete, interactive graphical user interface (GUI).

## About Akari

Akari puzzles consist of a grid made up of different types of cells:
- **Corridors**: These are empty cells where lamps can be placed to light up the area.
- **Walls**: These cells block light, and lamps cannot be placed on them.
- **Clue cells**: These are special wall cells with a number (0-4) that indicate the exact number of lamps that should be placed adjacent (horizontally and vertically) to them.

### Objective

The main objective is to place lamps in the corridors so that:
1. All corridors are illuminated.
2. The number of lamps adjacent to each clue cell matches the number in the clue.
3. No two lamps illuminate each other directly.

To understand the rules better and practice solving Akari puzzles, you can refer to the [Akari Wikipedia page](https://en.wikipedia.org/wiki/Light_Up_(puzzle)) and try some puzzles on [this online Akari puzzle site](https://www.puzzle-light-up.com/).

## How to Play

1. **Start the Game**: When you run the game, it will display the first puzzle from a set of built-in puzzles. You will see a grid representing the puzzle board.
2. **Place Lamps**: Click on any corridor cell to place a lamp. Clicking a cell with a lamp will remove the lamp. Lamps will illuminate all corridor cells in a straight line until they hit a wall or the edge of the grid.
3. **Satisfy Clues**: Make sure the number of lamps around each clue cell matches the number on the clue.
4. **Avoid Overlapping Light**: Ensure no two lamps' light beams overlap. Overlapping light means the lamps are incorrectly placed.
5. **Navigate Puzzles**: Use the provided buttons to move to the next puzzle, go back to the previous puzzle, or jump to a random puzzle.
6. **Reset Puzzle**: Use the "Reset" button to clear all lamps and start the current puzzle over.

### Winning the Game

A puzzle is considered solved when all the following conditions are met:
- All corridors are lit.
- All clue cells have the correct number of adjacent lamps.
- No lamps are shining directly on each other.

When these conditions are satisfied, a success message will be displayed on the screen.

## Setting Up the Game

### Prerequisites

- **Java Development Kit (JDK)**: Ensure you have JDK installed (Java 8 or higher).
- **Maven**: This project uses Maven for dependency management. Ensure Maven is installed.
- **IntelliJ IDEA**: Although any Java IDE can be used, the instructions below assume you are using IntelliJ.

### Installation Steps

1. **Clone the Repository**:  
   Open your terminal and run the following command to clone the repository:

   ```bash
   git clone https://github.com/zkamdar1/Akari_Game.git
  ```
2. **Navigate into the project directory**:
  Change into the project directory:

  ```bash
    cd Akari_Game
  ```
3. **Open the Project in IntelliJ:**
    - Open IntelliJ IDEA.
    - Select "Open" and navigate to the Akari_game directory.

4. **Build the Project**:
    - Once the project is opened in IntelliJ, it will automatically recognize it as a Maven project and download the necessary dependencies.
    - Wait for Maven to finish downloading dependencies and building the project.

5. **Running the Application**:

    - In IntelliJ, open the "Maven" tool window (usually found on the right-hand side).
    - Expand the "Plugins" section, then expand the "javafx" section.
    - Double-click on "javafx:run" to launch the application.

## Features
  - Interactive Grid: Clickable grid to place and remove lamps.
  - Puzzle Navigation: Buttons to navigate through different puzzles.
  - Reset Option: Reset the puzzle to its initial state.
  - Dynamic Board Sizes: Supports puzzles of varying sizes.

## Troubleshooting
  - If you encounter any issues with the application not starting, ensure that JavaFX is correctly set up in your environment and that all Maven dependencies have been resolved.
  - If the GUI does not display correctly, check for JavaFX version compatibility and update the dependencies as needed.

## Conclusion
  Enjoy playing Akari, and may your logic skills light the way to success!