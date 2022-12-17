package conwaysgame;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ConwayTests {
  GameOfLife game;

  @BeforeEach
  void setUp() {
    game = new GameOfLife();
    game.setUpGame();
  }

  @Test
  void shouldReturnNumberOfNeighbors() {
    int numberOfNeighbors = game.numberOfNeighbors(new Field(3, 2));
    Assertions.assertEquals(2, numberOfNeighbors);
  }

  @Test
  void shouldGetCorrectNumberOfFieldsToCheck() {
    int numberOfFieldsToCheck = game.getEmptyFieldsToCheck().size();
    Assertions.assertEquals(14, numberOfFieldsToCheck);
  }

  @Test
  void shouldSetIsDoomedToTrue() {
    Cell cell = new Cell(1, 1);
    cell.setDoomed();
    Assertions.assertTrue(cell.isDoomed());
  }

  @Test
  void shouldDeleteDoomedCells() {
    Cell doomedCell1 = new Cell(1, 1);
    doomedCell1.setDoomed();
    Cell doomedCell2 = new Cell(1, 2);
    doomedCell2.setDoomed();

    List<Cell> cells = new ArrayList<>();
    cells.add(new Cell(0, 0));
    cells.add(doomedCell1);
    cells.add(doomedCell2);

    game.removeDeadCells();
    Assertions.assertEquals(1, game.getCells().size());
  }

  @Test
  void ShouldCreateNewCell() {
    Set<Field> fieldsToCheck = game.getEmptyFieldsToCheck();

    game.populateNewCells(fieldsToCheck);
    Cell newCell = new Cell(2, 1);

    Assertions.assertEquals(1, game.getNewCells().size());
    Assertions.assertEquals(newCell.getPosition(), game.getNewCells().get(0).getPosition());
  }

}