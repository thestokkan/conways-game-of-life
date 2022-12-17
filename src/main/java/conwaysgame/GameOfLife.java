package conwaysgame;

import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.*;

public class GameOfLife {
  private final DefaultTerminalFactory d = new DefaultTerminalFactory();
  private final Terminal t = d.createTerminal();
  private final int xMax = t.getTerminalSize().getColumns();
  private final int yMax = t.getTerminalSize().getRows();

  private final List<Cell> newCells = new ArrayList<>();
  private final List<Cell> cells = new ArrayList<>();

  public GameOfLife() throws IOException {
  }

  public void setUpGame() throws IOException {
    t.setCursorVisible(false);
    cells.clear();
    cells.add(new Cell(1, 1));
    cells.add(new Cell(3, 1));
    cells.add(new Cell(2, 2));
    cells.add(new Cell(2, 3));
    cells.add(new Cell(3, 2));

  }

  public static void main(String[] args) throws IOException, InterruptedException {
    GameOfLife g = new GameOfLife();
    int cycles = 0;

    g.setUpGame();
    g.displayCells();

    while (g.cells.size() > 0) {
      cycles++;
      if (g.allCellsOffScreen()) {
        g.t.clearScreen();
        g.t.flush();
        break;
      }
      g.markCellsAsDoomed();
      g.populateNewCells(g.getEmptyFieldsToCheck());
      g.removeDeadCells();
      Thread.sleep(100);
      g.displayCells();
    }
    System.out.println("Number of cycles: " + cycles);
  }

  private boolean allCellsOffScreen() {
    boolean offScreen = true;

    for (Cell cell : cells) {
      int cellX = cell.getPosition().getX();
      int cellY = cell.getPosition().getY();
      if (cellX < xMax && cellY < yMax) {
        offScreen = false;
        break;
      }
    }
    return offScreen;
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

  // For testing purposes
  private void printCells() {
    for (Cell cell : cells) {
      System.out.println(cell.toString());
    }
  }

  public void setUpGameForTesting() {
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