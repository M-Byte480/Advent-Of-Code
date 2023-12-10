package milan.aoc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class Day9 {

  ArrayList<Sequence> sequences = new ArrayList<>();

  Day9(){
    ArrayList<StringBuilder> lines = FileParser.readLines("Day_9.txt");
    for (StringBuilder line : lines) {
      Sequence sequence = new Sequence();
      sequence.sequence = Arrays.stream(line.toString().split(" "))
          .map(Long::parseLong)
          .collect(Collectors.toCollection(ArrayList::new));
      sequences.add(sequence);
    }
  }

  public void solve(boolean part1){
    long total = 0;
    for (Sequence sequence : sequences) {
      total += sequence.solve(part1);
    }
    System.out.println(total);
  }

  public static void main(String[] args) {
    Day9 d9 = new Day9();
    boolean part1 = true;
    d9.solve(part1);
    d9.solve(!part1);
  }
}

class Sequence{
  ArrayList<Long> sequence = new ArrayList<>();
  ArrayList<ArrayList<Long>> differences = new ArrayList<>();
  Sequence(){
  }
  
  public long solve( boolean part1) {
    this.populateDifferences();

    if(part1){
      return this.getNextElement(differences, 0);
    }else {
      return this.getPreviousElement(differences, 0);
    }
  }


  public long getNextElement(ArrayList<ArrayList<Long>> listOfLongs, int index){
    if(containsZeros(listOfLongs.get(index))){
      return 0;
    }
    return listOfLongs.get(index).get(listOfLongs.get(index).size() - 1) + getNextElement(listOfLongs, index + 1);
  }

  public long getPreviousElement(ArrayList<ArrayList<Long>> listOfLongs, int index){
    if(containsZeros(listOfLongs.get(index))){
      return 0;
    }
    return listOfLongs.get(index).get(0) - getPreviousElement(listOfLongs, index + 1);
  }

  public <T extends Number> boolean containsZeros(ArrayList<T> list){
    for (int i = 0; i < list.size(); i++) {
      if(!Objects.equals(list.get(i), 0)){
        return false;
      }
    }

    return true;
  }

  public void populateDifferences(){
    boolean isConstant = false;
    differences.add(sequence);
    int key = 0;
    while (!isConstant) {
      ArrayList<Long> previousDiff = differences.get(key);
      ArrayList<Long> arrayToAdd = new ArrayList<>();
      for (int i = 0; i < previousDiff.size() - 1; i++) {
        long num1 = previousDiff.get(i);
        long num2 = previousDiff.get(i + 1);
        arrayToAdd.add(num2 - num1);
      }
      differences.add(arrayToAdd);
      if (containsZeros(arrayToAdd)) {
        isConstant = true;
      }
      key++;
    }
  }
}
