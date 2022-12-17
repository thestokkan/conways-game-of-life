package conwaysgame;

import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode
public class Field {
  int x;
  int y;

  public Field(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public List<Field> getNeighbors() {
    return List.of(new Field(x - 1, y + 1),
                   new Field(x, y + 1),
                   new Field(x + 1, y + 1),
                   new Field(x - 1, y),
                   new Field(x + 1, y),
                   new Field(x - 1, y - 1),
                   new Field(x, y - 1),
                   new Field(x + 1, y - 1));
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

}