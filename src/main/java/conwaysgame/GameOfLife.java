package conwaysgame;

import java.util.*;
import java.util.stream.Collectors;

public class GameOfLife {
  private final List<Cell> newCells = new ArrayList<>();
  private final List<Cell> cells = new ArrayList<>();

  public static void main(String[] args) {
    GameOfLife game = new GameOfLife();

    game.setUpGame();



    game.testStuff();

  }

  private void testStuff() {

  }


  public void setUpGame() {
    Cell c1 = new Cell(1, 2);
    Cell c2 = new Cell(2, 2);
    Cell c3 = new Cell(3, 2);
    Cell c4 = new Cell(3, 3);

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

  public void removeDeadCells(List<Cell> cellsList) {
    cellsList.removeIf(Cell::isDoomed);
  }

  public void populateNewCells(Set<Field> fieldsToCheck) {
    for (Field field : fieldsToCheck) {
      if (numberOfNeighbors(field) == 3) {
        newCells.add(new Cell(field));
      }
    }

  }

  public List<Cell> getNewCells() {
    return newCells;
  }

  public List<Cell> getCells() {
    return cells;
  }
}