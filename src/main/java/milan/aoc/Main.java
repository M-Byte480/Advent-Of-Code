package milan.aoc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
  static BufferedReader reader;
  static ArrayList<StringBuilder> lines = new ArrayList<>();
  ArrayList<Game> games = new ArrayList<>();

  static {
    try {
      reader = new BufferedReader(new FileReader("src/main/resources/2023/Day_2.txt"));
      String ln = reader.readLine();
      while( ln != null ){
        lines.add(new StringBuilder(ln));
        ln = reader.readLine();
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  public static void main(String[] args) {
    Main main = new Main();
    lines.forEach(l -> main.games.add(new Game(l)));
    int result = 0;
    for (Game game : main.games) {
      result += game.maxGreen * game.maxRed * game.maxBlue;
    }

    System.out.println(result);
  }
}