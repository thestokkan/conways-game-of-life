package conwaysgame;

import java.util.List;

public class CellStructures {
  private final List<Field> froydisAndTherese =
          List.of(new Field(0, 0),
                  new Field(1, 0),
                  new Field(1, 1),
                  new Field(2, 1),
                  new Field(0, 3),
                  new Field(1, 3));

  private final List<Field> glider1 =
          List.of(new Field(0, 0),
                  new Field(2, 0),
                  new Field(1, 1),
                  new Field(1, 2),
                  new Field(2, 1));

  private final List<Field> glider2 =
          List.of(new Field(0, 0),
                  new Field(1, 0),
                  new Field(2, 0),
                  new Field(0, 1),
                  new Field(1, 2));

  private final List<Field> lightweightSpaceShip =
          List.of(new Field(1, 0),
                  new Field(4, 0),
                  new Field(0, 1),
                  new Field(0, 2),
                  new Field(0, 3),
                  new Field(1, 3),
                  new Field(2, 3),
                  new Field(3, 3),
                  new Field(4, 2));

  private final List<Field> staticLoaf =
          List.of(new Field(2, 1),
                  new Field(3, 1),
                  new Field(1, 2),
                  new Field(1, 3),
                  new Field(3, 2),
                  new Field(3, 3),
                  new Field(2, 3));

  public List<Field> getFroydisAndTherese() {
    return froydisAndTherese;
  }

  public List<Field> getGlider1() {
    return glider1;
  }

  public List<Field> getGlider2() {
    return glider2;
  }

  public List<Field> getLightweightSpaceShip() {
    return lightweightSpaceShip;
  }

  public List<Field> getStaticLoaf() {
    return staticLoaf;
  }
}