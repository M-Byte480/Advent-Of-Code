package milan.aoc;

import java.util.HashMap;

public class Round {

  HashMap<COLOURS, Integer> contents = new HashMap<>();

  public Round() {
    contents.put(COLOURS.GREEN, 0);
    contents.put(COLOURS.RED, 0);
    contents.put(COLOURS.BLUE, 0);
  }

  @Override
  public String toString() {
    return "red: " + contents.get(COLOURS.RED) + ", " +
        "blue: " + contents.get(COLOURS.BLUE) + ", " +
        "green: " + contents.get(COLOURS.GREEN);
  }
}
