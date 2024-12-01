import java.io.File;
import java.io.FileReader;

abstract class AbstractAoc implements IAdventOfCode{
    protected FileReader fileReader;

    public AbstractAoc(){
        this.fileReader = null;
    }

    public void loadInput(String fileName){

        try{
            fileName = String.format("./AoC_2024/resource/%s.txt" ,fileName);
            this.fileReader = new FileReader(fileName);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}