package conwaysgame;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameOfLife {
  private final DefaultTerminalFactory d = new DefaultTerminalFactory();
  private final Terminal t = d.createTerminal();
  private final int xMax = t.getTerminalSize().getColumns();
  private final int yMax = t.getTerminalSize().getRows();

  CellStructures cs = new CellStructures();
  // === SETTINGS ===
  private final List<List<Field>> cellStructures = List.of(cs.getGlider1(), cs.getGlider1(), cs.getGlider1());
  private final boolean isRandomStartingPosition = false;
  private final List<Field> fixedStartingPositions = List.of(new Field(0, 0), new Field(20, 0),
                                                             new Field(40, 0));
  static final int cycleTimeMs = 100;
  // === END SETTINGS ===

  private final List<Cell> newCells = new ArrayList<>();
  private final List<Cell> cells = new ArrayList<>();

  public GameOfLife() throws IOException {
  }

  public static void main(String[] args) throws IOException, InterruptedException {
    GameOfLife g = new GameOfLife();
    int cycles = 0;

    g.setUpGame();
    g.displayCells();

    while (g.cells.size() > 0) {
      g.showCyclesOnScreen(cycles);
      cycles++;
      g.markCellsAsDoomed();
      g.populateNewCells(g.getEmptyFieldsToCheck());
      g.removeDeadCells();
      Thread.sleep(cycleTimeMs);
      g.displayCells();
    }
    g.printExtinctMessage();
    g.printCycles(cycles);
    System.out.println("All life is extinct...");
  }

  private Field getRandomStartingPosition() {
    int randX = (int)(Math.random() * xMax);
    int randY = (int)(Math.random() * yMax);
    return new Field(randX, randY);
  }

  public void setUpGame() throws IOException {
    t.setCursorVisible(false);

    cells.clear();
    Field startingPosition;
    for (int i = 0; i < cellStructures.size(); i++) {
      if (isRandomStartingPosition) {
         startingPosition = getRandomStartingPosition();
      } else {
          startingPosition = fixedStartingPositions.get(i);
      }
      for (Field field : cellStructures.get(i)) {
        cells.add(new Cell(field.add(startingPosition)));
      }
    }
  }

  private void showCyclesOnScreen(int cycles) throws IOException {
    t.setCursorPosition(5, 1);
    t.setForegroundColor(TextColor.ANSI.WHITE_BRIGHT);
    t.putString("" + cycles);
    t.flush();
  }

  private void displayCells() throws IOException {
    t.clearScreen();
    t.flush();
    for (Cell cell : cells) {
      int cellX = cell.getPosition().getX();
      int cellY = cell.getPosition().getY();
      t.setCursorPosition(cellX, cellY);
      t.putString(cell.getMarker());
    }
    t.flush();
  }

  public void setUpForTesting() {
    Cell c1 = new Cell(1, 2);
    Cell c2 = new Cell(2, 2);
    Cell c3 = new Cell(3, 2);
    Cell c4 = new Cell(3, 3);

    cells.clear();
    cells.add(c1);
    cells.add(c2);
    cells.add(c3);
    cells.add(c4);
  }

  public int numberOfNeighbors(Field field) {
    int numberOfNeighbors = 0;

    List<Field> neighbors = field.getNeighbors();

    for (Field neighborPosition : neighbors) {
      for (Cell cell : cells) {
        if (neighborPosition.equals(cell.getPosition())) {
          numberOfNeighbors++;
        }
      }
    }

    return numberOfNeighbors;
  }

  void markCellsAsDoomed() {
    for (Cell cell : cells) {
      int numberOfNeighbors = numberOfNeighbors(cell.getPosition());
      if (numberOfNeighbors < 2 || numberOfNeighbors > 3) {
        cell.setDoomed();
      }

      // Also set cell as doomed if it moves off-screen
      int cellX = cell.getPosition().getX();
      int cellY = cell.getPosition().getY();
      if (cellX > xMax || cellY > yMax || cellX < 0 || cellY < 0) {
        cell.setDoomed();
      }
    }
  }

  public Set<Field> getEmptyFieldsToCheck() {
    Set<Field> fieldsToCheck = new HashSet<>();

    for (Cell cell : cells) {
      List<Field> neighborPositions = cell.getPosition().getNeighbors();

      for (Field neighborPosition : neighborPositions) {
        boolean isEmpty = true;
        for (Cell cell2 : cells) {
          if (neighborPosition.equals(cell2.getPosition())) {
            isEmpty = false;
            break;
          }
        }
        if (isEmpty) {
          fieldsToCheck.add(neighborPosition);
        }
      }
    }

    return fieldsToCheck;
  }

  public void removeDeadCells() {
    cells.removeIf(Cell::isDoomed);
  }

  public void populateNewCells(Set<Field> fieldsToCheck) {
    for (Field field : fieldsToCheck) {
      if (numberOfNeighbors(field) == 3) {
        newCells.add(new Cell(field));
      }
    }
    cells.addAll(newCells);
    newCells.clear();
  }

  private void printExtinctMessage() throws IOException {
    t.clearScreen();
    t.flush();
    String message = "ALL LIFE IS EXTINCT...";
    t.setCursorPosition((xMax / 2) - (message.length() / 2), (yMax / 2) - 1);
    t.putString(message);
    t.flush();
  }

  public int getCellsSize() {
    return cells.size();
  }

  public void addToCells(Cell cell) {
    cells.add(cell);
  }

  public Cell getFromCells(int i) {
    return cells.get(i);
  }
}