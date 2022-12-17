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


//  @Override
//  public boolean equals(Object obj) {
//    if(obj instanceof Field)
//    {
//      Field temp = (Field) obj;
//      return this.x == temp.x && this.y == temp.y;
//    }
//    return false;
//  }
//
//  @Override
//  public int hashCode() {
//    return (x.hashCode() + y.hashCode());
//  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

}