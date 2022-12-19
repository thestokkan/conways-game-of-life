package conwaysgame;

import lombok.Getter;

import java.util.List;

@Getter
public class Seeds {
  private final List<Field> sevenInARow =
          List.of(new Field(0, 0),
                  new Field(1, 0),
                  new Field(2, 0),
                  new Field(3, 0),
                  new Field(4, 0),
                  new Field(5, 0),
                  new Field(6, 0));

  private final List<Field> threeInARow =
          List.of(new Field(0, 0),
                  new Field(1, 0),
                  new Field(2, 0));


  private final List<Field> repeater =
          List.of(new Field(1, 0),
                  new Field(0, 1),
                  new Field(1, 1),
                  new Field(2, 1));


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
}