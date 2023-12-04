package milan.aoc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Day3 {

  public static void main(String[] args) {
    Board board = new Board();
//    board.parseBoardP1();
    board.parseBoardP2();
  }
}

class Board{
  static BufferedReader reader;
  static ArrayList<StringBuilder> lines = new ArrayList<>();

  ArrayList<ArrayList<Character>> board = new ArrayList<>();

  Map<Integer, Map<Integer, Integer>> startIndex = new HashMap<>();
  static {
    try {
      reader = new BufferedReader(new FileReader("src/main/resources/2023/Day_3.txt"));
      String ln = reader.readLine();
      while( ln != null ){
        lines.add(new StringBuilder(ln));
        ln = reader.readLine();
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  Board(){
    for (StringBuilder line : lines) {
      ArrayList<Character> ca = new ArrayList<>();
      for(char c : line.toString().toCharArray()){
        ca.add(c);
      }
      board.add(ca);
    }
  }

  public void parseBoardP1(){
    StringBuilder sb = new StringBuilder();
    Character c;
    int start = 0;
    int result = 0;
    for (int row  = 0; row < board.size(); row++) {
      for (int column = 0; column < board.get(0).size(); column++) {
        c = board.get(row).get(column);
        if(Character.isDigit(c)){
          if(sb.isEmpty()){
            start = column;
          }
          sb.append(c);
        }else{
          if(!sb.isEmpty()){
            if(searchSymbol(start, column - 1, row)){
              result += Integer.parseInt(sb.toString());
            }
          }
          sb.setLength(0);
        }
      }
    }
    logger(result);
  }

  public void parseBoardP2(){
    Character c;
    int result = 0;
    int count = 0;
    for (int row  = 0; row < board.size(); row++) {
      for (int column = 0; column < board.get(0).size(); column++) {
        c = board.get(row).get(column);
        if(c.equals('*')){
          if(checkSurrounding(row, column))
            count ++;
            result += getSurrounding(row, column);
        }
      }
    }
    logger(result);
  }

  public boolean checkSurrounding(int row, int column){
    int counter = 0;
    for (int i = row - 1; i <= row + 1; i++) {
      if(i < 0)
        continue;
      if(i >= board.size())
        continue;

      for (int j = column - 1; j <= column + 1; j++) {
        if(j < 0)
          continue;
        if(j >= board.get(0).size())
          continue;

        Character c = board.get(i).get(j);

        if(Character.isDigit(c)){
          counter++;
          while(Character.isDigit(c) && j != board.get(0).size() - 1){
            if(j + 1 < board.get(0).size()){
              c = board.get(i).get(++j);
            }
          }
        }

      }
    }
    return counter == 2;
  }

  public int getSurrounding(int row, int column){
    int product = 1;
    if(column == -1){
      column = board.get(0).size() - 1;
    }
    StringBuilder sb = new StringBuilder();
    for (int i = row - 1; i <= row + 1; i++) {
      if(i < 0)
        continue;
      if(i >= board.size())
        continue;

      for (int j = column - 1; j <= column + 1; j++) {
        if (j < 0)
          continue;
        if (j >= board.get(0).size())
          continue;
        Character c = board.get(i).get(j);

        if (Character.isDigit(c)) {
          while (Character.isDigit(c)) {
            j--;
            if(j > -1){
              c = board.get(i).get(j);
            }else{
              break;
            }
          }
          j++;
          c = board.get(i).get(j);

          while (Character.isDigit(c)) {
            sb.append(c);
            j++;
            if(j < 140){
              c = board.get(i).get(j);
            }else{
              break;
            }
          }
          product *= Integer.parseInt(sb.toString());
          sb.setLength(0);
        }
      }
    }
    logger(product);
    return product;
  }

  public boolean searchSymbol(int start, int end, int row){
    if(end == -1){
      end = board.get(0).size();
    }
    for (int i = row - 1; i <= row + 1; i++) {
      if(i < 0)
        continue;
      if(i >= board.size())
        continue;

      for (int j = start - 1; j <= end + 1; j++) {
        if(j < 0)
          continue;
        if(j >= board.get(0).size())
          continue;

        Character c = board.get(i).get(j);

        if(c.equals('.') || Character.isDigit(c))
          continue;
        return true;
      }
    }
    return false;
  }

  public static <T> void logger(T s){
    System.out.println(s);
  }
}

