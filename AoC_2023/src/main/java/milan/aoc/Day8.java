package milan.aoc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import org.w3c.dom.Node;

public class Day8 {

  public static void main(String[] args) {
    NodeMap map = new NodeMap();
//    map.traverse();
    map.solveMultiple();
  }
}

class NodeMap {

  char[] moves;
  HashMap<String, NodeReference> map = new HashMap<>();

  NodeMap() {
    ArrayList<StringBuilder> lines = FileParser.readLines("Day_8.txt");
    moves = lines.get(0).toString().toCharArray();
    for (int i = 2; i < lines.size(); i++) {
      String[] keyValuePair = lines.get(i).toString().split(" = ");
      String key = keyValuePair[0];
      String[] value = keyValuePair[1]
          .replace("(", "")
          .replace(")", "")
          .split(", ");
      map.put(key, new NodeReference(value[0], value[1]));
    }
  }

  public void traverse() {
    String start = "AAA";
    String lookingFor = "ZZZ";
    int count = 0;
    int i = 0;
    while (!start.equals(lookingFor)) {
      char next = moves[i];

      if (next == 'L') {
        start = map.get(start).left;
      } else {
        start = map.get(start).right;
      }

      count++;
      i++;
      if (i == moves.length) {
        i = 0;
      }
    }
    System.out.println(count);
  }

  public void solveMultiple() {
    ArrayList<String> startValues = new ArrayList<>();
    for (String s : map.keySet()) {
      if (s.charAt(2) == 'A') {
        startValues.add(s);
      }
    }

    boolean found = false;
    int i = 0;
    int count = 0;

    ArrayList<Integer> cycles = new ArrayList<>();
    for (String startValue : startValues) {
      ArrayList<Integer> cycle = new ArrayList<>();

      char currentStep = 23;
    }
    int lcm = cycles.get(0);
    for (Integer cycle : cycles) {
      lcm = (lcm * cycle) / findGCD(lcm, cycle);
    }
//    cycles.forEach(System.out::println)
    System.out.println(lcm);
  }

  static int findGCD(int a, int b)
  {
    if (b == 0)
      return a;
    return findGCD(b, a % b);
  }
}

class NodeReference {

  String left;
  String right;

  NodeReference() {

  }

  NodeReference(String l, String r) {
    left = l;
    right = r;
  }
}