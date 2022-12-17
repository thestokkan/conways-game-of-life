package conwaysgame;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Set;

public class ConwayTests {
  GameOfLife game;

  @BeforeEach
  void setUp() throws IOException {
    game = new GameOfLife();
    game.setUpForTesting();
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
  void shouldMarkCellsAsDoomed() {
    Cell c1 = new Cell(2, 3);
    game.addToCells(c1);
    Assertions.assertEquals(5, game.getCellsSize());
    game.markCellsAsDoomed();
    Assertions.assertTrue(c1.isDoomed());
  }

  @Test
  void shouldDeleteDoomedCells() {
    shouldMarkCellsAsDoomed();
    game.removeDeadCells();
    Assertions.assertEquals(3, game.getCellsSize());
  }

  @Test
  void ShouldCreateNewCell() {
    Set<Field> fieldsToCheck = game.getEmptyFieldsToCheck();

    game.populateNewCells(fieldsToCheck);

    Assertions.assertEquals(5, game.getCellsSize());
    Assertions.assertEquals(new Field(2, 1), game.getFromCells(4).getPosition());
  }

}