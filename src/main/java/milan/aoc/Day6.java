package milan.aoc;

import java.util.ArrayList;

public class Day6 {

  public static void main(String[] args) {
    Race race = new Race();
    System.out.println(race.part1());
    // Part two can be solved using the Quadratic Formula :)
  }
}

class Race{
  ArrayList<RaceGame> races = new ArrayList<>();

  Race(){
    ArrayList<StringBuilder> lines = FileParser.readLines("Day_6.txt");
    String[] times = lines.get(0).substring("Distance:".length() + 1).split(" ");
    String[] distance = lines.get(1).substring("Distance:".length() + 1).split(" ");

    int p1 = 0;
    int p2 = 0;
    for (int i = 0; i < 4; i++) {
      while (times[p1].isEmpty()) {
        p1++;
      }
      while(distance[p2].isEmpty()){
        p2++;
      }

      RaceGame r = new RaceGame();
      r.time = Integer.parseInt(times[p1]);
      r.distance = Integer.parseInt(distance[p2]);

      races.add(r);
      p1++; p2++;
    }
  }





  public int part1(){
    ArrayList<Integer> times = new ArrayList<>();
    for (RaceGame race : races) {
      int time = race.time;
      int recordDistance = race.distance;
      int count = 0;
      for (int i = 0; i <= time; i++) {
        int distanceTravelled = hold(i, time);

        if(distanceTravelled > recordDistance){
          count++;
        }
      }
      times.add(count);
    }


    int product = 1;
    for (Integer time : times) {
      product = product * time;
    }
    return product;
  }

  public int hold(int duration, int competitionDuration){
    int speed = duration;
    int timeRemaining = competitionDuration - duration;

    return speed * timeRemaining;
  }


}

class RaceGame{
  int time;
  int distance;

  RaceGame(){

  }

  @Override
  public String toString() {
    return "Time: " + time + " Distance: " + distance;
  }
}