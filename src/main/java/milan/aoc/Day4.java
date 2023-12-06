package milan.aoc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day4 {

  public static void main(String[] args) {
    Lottery lottery = new Lottery();
    System.out.println(lottery.getCount());
    Lottery lottery2 = new Lottery(false);
    lottery2.solve();
  }
}

class Lottery {

  ArrayList<Card> cards = new ArrayList<>();
  Map<Integer, Integer> cardRefs = new HashMap<>();

  Lottery() {
    for (StringBuilder line : FileParser.lines) {
      String[] cardString = line.toString().split(": ");
      Card card = new Card();
      card.id = Integer.parseInt(cardString[0].split(" ")[cardString[0].split(" ").length - 1]);
      String[] numbers = cardString[1].split(" \\| ");
      for (String number : numbers[0].split(" ")) {
        try {
          card.winningNumbers.add(Integer.parseInt(number));
        } catch (Exception ignored) {
        }
      }

      for (String number : numbers[1].split(" ")) {
        try {
          card.numbers.add(Integer.parseInt(number));
        } catch (Exception ignored) {
        }
      }
      card.sort();
      cards.add(card);
    }
  }

  Lottery(boolean t) {
    this();
    for (int i = 0; i < cards.size(); i++) {
      int id = cards.get(i).id;
      for (int j = 0; j < cards.size(); j++) {
        if (j == id - 1) {
          cardRefs.put(id, 1);
        }
      }
    }
  }

  public void solve() {
    ArrayList<Integer> result = new ArrayList<>();
    for (Integer key : cardRefs.keySet()) {
      result.add(cardRefs.get(key));

      Card card = cards.get(key - 1);
      int wins = card.calculatePoint2();
      int numOfCards = cardRefs.get(key);

      for (int i = 0; i < wins; i++) {
        int cardToGet = 1 + i + key;
        if (cardToGet > cardRefs.size() - 1) {
          result.add(numOfCards);
          continue;
        }
        int currentValue = cardRefs.get(cardToGet);
        cardRefs.put(cardToGet, currentValue + numOfCards);
      }
    }

    long total = 0;
    for (int i = 0; i < result.size(); i++) {
      total += result.get(i);
    }
    System.out.println(total);
  }

  public int getCount() {
    int count = 0;
    for (Card card : cards) {
      count += card.calculatePoint();
    }
    return count;
  }
}

class Card {

  int id;
  int point;
  ArrayList<Integer> winningNumbers = new ArrayList<>();
  ArrayList<Integer> numbers = new ArrayList<>();

  Card() {
  }

  public void sort() {
    Collections.sort(winningNumbers);
    Collections.sort(numbers);
  }

  public int calculatePoint() {
    point = 0;
    sort();

    for (Integer winningNumber : winningNumbers) {
      int index = binarySearch(numbers, winningNumber);
      if (index != -1) {
        if (point == 0) {
          point = 1;
        } else {
          point *= 2;
        }
      }
    }
    return point;
  }

  public int calculatePoint2() {
    point = 0;
    sort();

    for (Integer winningNumber : winningNumbers) {
      int index = binarySearch(numbers, winningNumber);
      if (index != -1) {
        point++;
      }
    }
    return point;
  }

  public int binarySearch(List<Integer> list, int value) {
    int left = 0, right = list.size() - 1;
    while (left <= right) {
      int middle = left + (right - left) / 2;

      if (list.get(middle) == value) {
        return middle;
      }

      if (list.get(middle) < value) {
        left = middle + 1;
      } else {
        right = middle - 1;
      }
    }

    return -1;
  }

  @Override
  public String toString() {
    return "" + id + " - " + winningNumbers.toString() + " | " + numbers.toString();
  }
}

class FileParser {

  static BufferedReader reader;
  static ArrayList<StringBuilder> lines = new ArrayList<>();

  static ArrayList<StringBuilder> readLines(String file){
    try {
      reader = new BufferedReader(new FileReader("src/main/resources/2023/" + file));
      String ln = reader.readLine();
      while (ln != null) {
        lines.add(new StringBuilder(ln));
        ln = reader.readLine();
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    return lines;
  }
}
