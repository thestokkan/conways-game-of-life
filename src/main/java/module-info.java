module src.main.java.conwaysgame {
  requires javafx.media;
  requires com.googlecode.lanterna;
  requires lombok;

  opens conwaysgame to javafx.fxml;
  exports conwaysgame;
}