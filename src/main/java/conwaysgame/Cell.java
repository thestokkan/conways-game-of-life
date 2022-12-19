package conwaysgame;

public class Cell {
  private final Field position;
  private Boolean isDoomed;

  public Cell(Field field) {
    this.position = field;
    isDoomed = false;
  }

  public Cell(int x, int y) {
    this.position = new Field(x, y);
    isDoomed = false;
  }

  public Field getPosition() {
    return position;
  }

  public Boolean isDoomed() {
    return isDoomed;
  }

  public void setDoomed() {
    isDoomed = true;
  }

  @Override
  public String toString() {
    return "Cell{" + "position=(" + position.getX() + "," + position.getY() + "), isDoomed=" + isDoomed + '}';
  }

  public Character getMarker() {
    return '*';
  }
}