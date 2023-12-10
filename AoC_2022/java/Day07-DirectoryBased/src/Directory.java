import java.util.ArrayList;

public class Directory {
    ArrayList<Directory> directories = new ArrayList<>();
    ArrayList<String> files = new ArrayList<>();
    String name;


    public Directory(String name, ArrayList<Directory> directories, ArrayList<String> files){
        this.name = name;
        this.files = files;
        this.directories = directories;
    }

    public Directory(String name){
        this.name = name;
    }

    public boolean containsDirectory() {
        return directories.size() > 0;
    }

    public ArrayList<Directory> getDirectories() {
        return this.directories;
    }

    public void setDirectories(ArrayList<Directory> directories) {
        this.directories = directories;
    }

    public void setFiles(ArrayList<String> files) {
        this.files = files;
    }

    public String toString(){
        return this.name;
    }

    public String getContents(){
        return this.name + "\n" +
                this.files.toString() + "\n"+
                this.directories.toString() + "\n";
    }

    public int getDirectorySize(){
        int total = 0;
        for (String file :
                files) {
            String[] size = file.split(" ");
            total += Integer.parseInt(size[0]);
        }
        for(Directory directory : directories){
            total += directory.getDirectorySize();
        }
        return total;
    }


}
