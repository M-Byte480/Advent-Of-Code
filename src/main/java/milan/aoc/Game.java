package milan.aoc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Game {

  int id;
  ArrayList<Round> rounds = new ArrayList<Round>();
  int maxRed;
  int maxBlue;
  int maxGreen;

  public Game(StringBuilder line) {
    line = line.delete(0, 5);
    this.id = Integer.parseInt(line.substring(0, line.indexOf(":")));
    line = line.delete(0, line.indexOf(" ") + 1);
    Arrays.stream(
            line.toString()
                .split("; "))
        .forEach(l -> rounds.add(addNewRound(new StringBuilder(l))));
  }

  public Round addNewRound(StringBuilder line) {
    Round round = new Round();
    Arrays.stream(line.toString().split(", ")).forEach(
        r -> {
          String[] contents = r.split(" ");
          round.contents.put(selectColour(contents[1]), Integer.parseInt(contents[0]));
          updateColour(selectColour(contents[1]), Integer.parseInt(contents[0]));
        }
    );

    return round;
  }

  public COLOURS selectColour(String colour) {
    COLOURS col = COLOURS.RED;
    switch (colour) {
      case "blue":
        col = COLOURS.BLUE;
        break;
      case "green":
        col = COLOURS.GREEN;
        break;
      default:
        break;
    }
    return col;
  }

  public void updateColour(COLOURS col, int count){
    switch (col){
      case RED:
        if(maxRed < count) maxRed = count;
        break;
      case BLUE:
        if(maxBlue < count) maxBlue = count;
        break;
      case GREEN:
        if(maxGreen < count) maxGreen = count;
        break;
    }
  }

  @Override
  public String toString(){
    return "Game: " + id + " - " + rounds.toString();
  }
}
