package milan.aoc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Day7 {

  public static void main(String[] args) {
    PokerGame p = new PokerGame();
    p.part1();
  }
}

class PokerGame{
  ArrayList<Poker> pokerArray = new ArrayList<>();

  PokerGame(){
    ArrayList<StringBuilder> lines = FileParser.readLines("Day_7.txt");
    for (StringBuilder line : lines) {
      String[] l = line.toString().split(" ");
      pokerArray.add(new Poker(l[0], Integer.parseInt(l[1])));
    }

    for (Poker poker : pokerArray) {
      poker.setStrength2(); // Set this to strength 1 or 2 depending on which part you do
    }

    Collections.sort(pokerArray, new Poker());
  }

  public void part1(){
    int count = 0;
    int i = 1;
    for (Poker poker : pokerArray) {
      count += (poker.bet * i);
      i++;
    }
    System.out.println(count);
  }
}

enum Strength {
  FiveOfAKind(7),
  FourOfAKind(6),
  FullHouse(5),
  ThreeOfAKind(4),
  TwoPair(3),
  OnePair(2),
  HighCard(1);

  public static final Strength[] vals = values();

  public Strength getNewStrenght(int i){
    return vals[i];
  }

  public final int value;

  Strength(int val){
    this.value = val;
  }
}



class Poker implements Comparator<Poker> {

  static HashMap<Character, Integer> cardStrength = new HashMap<>();
  static{
    cardStrength.put('2', 2);
    cardStrength.put('3', 3);
    cardStrength.put('4', 4);
    cardStrength.put('5', 5);
    cardStrength.put('6', 6);
    cardStrength.put('7', 7);
    cardStrength.put('8', 8);
    cardStrength.put('9', 9);
    cardStrength.put('T', 10);
    cardStrength.put('J', 1); // Part one this is 11
    cardStrength.put('Q', 12);
    cardStrength.put('K', 13);
    cardStrength.put('A', 14);
  }

  String hand;
  int bet;
  Strength strength;

  Poker(){

  }

  Poker(String hand, int val){
    this.hand = hand;
    this.bet = val;
  }

  public void setStrength(){
    Set<Character> chars = new HashSet<>();
    char[] cards = hand.toCharArray();
    Arrays.sort(cards);
    HashMap<Character, Integer> counter = new HashMap<>();
    for (char c : cards) {
      if(chars.contains(c)){
        counter.put(c, counter.get(c) + 1);
      }else{
        chars.add(c);
        counter.put(c, 1);
      }
    }

    int typesOfCardCount = counter.size();

    switch (typesOfCardCount) {
      case 1 -> this.strength = Strength.FiveOfAKind;
      case 2 -> {
          if(counter.get(cards[2]) == 4){
            this.strength = Strength.FourOfAKind;
          }else{
            this.strength = Strength.FullHouse;
          }
      }
      case 3 -> {
        int cardCount = counter.get(cards[2]);
        if(cardCount == 3){
          this.strength = Strength.ThreeOfAKind;
        } else {
          this.strength = Strength.TwoPair;
        }
      }
      case 4 -> this.strength = Strength.OnePair;
      case 5 -> this.strength = Strength.HighCard;
    }
  }

  public void setStrength2(){


    boolean containsJoker = hand.contains("J");
    setStrength();

    if(!containsJoker){
      return;
    }

    int jokerCount = (int) hand.chars().filter(ch -> ch == 'J').count();
    setStrength();
    if(jokerCount == 5) return;
    if(jokerCount == 1){
      setStrength();
      switch (strength){
        case HighCard -> this.strength = Strength.OnePair;
        case OnePair -> this.strength = Strength.ThreeOfAKind;
        case TwoPair -> this.strength = Strength.FullHouse;
        case ThreeOfAKind, FullHouse -> this.strength = Strength.FourOfAKind;
        case FourOfAKind -> this.strength = Strength.FiveOfAKind;
        case FiveOfAKind -> {}
      }
      return;
    }

    if(jokerCount == 2){
      switch (strength){
        case OnePair -> this.strength = Strength.ThreeOfAKind;
        case TwoPair -> this.strength = Strength.FourOfAKind;
        case FullHouse -> this.strength = Strength.FiveOfAKind;
        default -> {}
      }
      return;
    }

    if(jokerCount == 3){
      switch (strength){
        case ThreeOfAKind -> this.strength = Strength.FourOfAKind;
        case FullHouse -> this.strength = Strength.FiveOfAKind;
      }
      return;
    }

    if(jokerCount == 4){
      this.strength = Strength.FiveOfAKind;
    }

  }

  private int compareHands(Poker hand1, Poker hand2){
    int handOne = hand1.strength.value;
    int handTwo = hand2.strength.value;
    if(handOne != handTwo){
      if(handOne < handTwo){
        return -1;
      }else{
        return 1;
      }
    }
    int i = 0;
    for (; i < 5; i++) {
      char a = hand1.hand.charAt(i);
      char b = hand2.hand.charAt(i);
      if(a != b){
        break;
      }
    }

    if(i == 5){
      return 0;
    }
    int val = 1;
    int card1 = cardStrength.get(hand1.hand.charAt(i));
    int card2 = cardStrength.get(hand2.hand.charAt(i));

    if(card1 < card2){
      val = -1;
    }

    return val;
  }

  private int compareHands2(Poker hand1, Poker hand2){
    int handOne = hand1.strength.value;
    int handTwo = hand2.strength.value;
    if(handOne != handTwo){
      if(handOne < handTwo){
        return -1;
      }else{
        return 1;
      }
    }
    int i = 0;
    for (; i < 5; i++) {
      char a = hand1.hand.charAt(i);
      char b = hand2.hand.charAt(i);
      if(a != b){
        break;
      }
    }

    if(i == 5){
      return 0;
    }
    int val = 1;
    int card1 = cardStrength.get(hand1.hand.charAt(i));
    int card2 = cardStrength.get(hand2.hand.charAt(i));

    if(card1 < card2){
      val = -1;
    }

    return val;
  }

  @Override
  public int compare(Poker hand1, Poker hand2){
    return compareHands(hand1, hand2);
  }

  @Override
  public String toString(){
    return hand + " : " + strength;
  }
}
