package milan.aoc;

import static milan.aoc.Day5.FileParser.seeds;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Day5 {

  public static void main(String[] args) {
    Mapper mapper = new Mapper();
    mapper.Part1();
  }



  class FileParser {

    static BufferedReader reader;
    static ArrayList<StringBuilder> lines = new ArrayList<>();
    static ArrayList<Long> seeds = new ArrayList<>();

    static HashMap<Class<?>, ArrayList<ThreeLongs>> dataSet = new HashMap<>();

    static Class<?>[] classes = {
        SeedToSoil.class,
        SoilToFertilizer.class,
        FertilizerToWater.class,
        WaterToLight.class,
        LightToTemperature.class,
        TemperatureToHumidity.class,
        HumidityToLocation.class
    };

    static {
      try {
        reader = new BufferedReader(new FileReader("src/main/resources/2023/Day_5.txt"));
        String ln = reader.readLine();
        while (ln != null) {
          lines.add(new StringBuilder(ln));
          ln = reader.readLine();
        }

        int classPointer = 0;

        for (int i = 0; i < lines.size(); i++) {
          if(i == 0){
            ArrayList<String> stringSeeds = new ArrayList<String>(Arrays.asList(lines.get(0).toString().split(" ")));
            stringSeeds.remove(0);
            seeds.addAll(stringSeeds.stream().map(j -> Long.parseLong(j.toString())).toList());
          }

          if(!lines.get(i).toString().isEmpty() && Character.isDigit(lines.get(i).toString().charAt(0))) {
            i += 2;
            ArrayList<ThreeLongs> data = new ArrayList<>();
            while( i < lines.size() && !lines.get(i).toString().isEmpty()) {
              String[] line = lines.get(i).toString().split(" ");


              ThreeLongs seed = (ThreeLongs) classes[classPointer].getDeclaredConstructor().newInstance();
              seed.destination = Long.parseLong(line[0]);
              seed.source = Long.parseLong(line[1]);
              seed.range = Long.parseLong(line[2]);

              data.add(seed);
              i++;
            }
            dataSet.put(classes[classPointer], data);
            classPointer++;
          }
        }
      } catch (IOException | InstantiationException | IllegalAccessException |
               NoSuchMethodException | InvocationTargetException e) {
        throw new RuntimeException(e);
      }
    }
  }
}


class Mapper{
  static HashMap<Class<?>, ArrayList<ThreeLongs>> dataSet = Day5.FileParser.dataSet;

  Mapper(){

  }

  public void Part1(){
    ArrayList<ThreeLongs> seedToSoil = dataSet.get(SeedToSoil.class);
    seedToSoil.sort(ThreeLongs::compareTo);
    for (long seed : seeds) {
      for (ThreeLongs toSoil : seedToSoil) {
        if(toSoil.source <= seed && seed <= toSoil.upperSource()){

        }
      }

    }
  }

}

class SeedToSoil extends ThreeLongs{}
class SoilToFertilizer extends ThreeLongs{}
class FertilizerToWater extends ThreeLongs{}
class WaterToLight extends ThreeLongs{}
class LightToTemperature extends ThreeLongs{}
class TemperatureToHumidity extends ThreeLongs{}
class HumidityToLocation extends ThreeLongs{}

class ThreeLongs implements Comparable{
  long destination;
  long source;
  long range;

  public long upperDest(){
    return destination + range - 1;
  }

  public long upperSource(){
    return source + range - 1;
  }

  @Override
  public int compareTo(Object that){
    int ret = 0;
    if( that instanceof ThreeLongs ){
      if(this.destination < ((ThreeLongs) that).destination) {
        ret = -1;
      }else{
        ret = 1;
      }
    }
    return ret;
  }

  @Override
  public String toString(){
    return "" + destination + " " + source + " " + range;
  }
}