import java.util.ArrayList;

public class Directory {

    private String name;
    private ArrayList<Directory> children = new ArrayList<>();
    private ArrayList<File> files = new ArrayList<>();
    private Directory parent;
    private int size;

    public Directory(String name, Directory parent){
        this.name = name;
        this.parent = parent;
    }

    public Directory(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Directory getParent(){
        return parent;
    }

    public void addChild(Directory dir){
        this.children.add(dir);
    }

    public void addFile(File file){
        this.files.add(file);
    }


    public void setFiles(ArrayList<File> files) {
        this.files = files;
    }

    public String toString(){
        return this.name;
    }


    public int getDirectorySize(){
        int total = 0;
        for (File file : files) {
            total += file.getSize();
        }
        for(Directory directory : children){
            total += directory.getDirectorySize();
        }
        return total;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
