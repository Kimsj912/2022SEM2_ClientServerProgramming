package Lists;

import Objects.IObject;
import Objects.Student;

import java.io.*;
import java.util.ArrayList;

public class CommonList<T extends IObject> extends ArrayList<T> {
    protected String fileName;
    protected BufferedReader objFile;

    public CommonList(String fileName) throws IOException{
        this.fileName = fileName;
        objFile = new BufferedReader(new FileReader(fileName));
    }

    protected void writeToFile(){
        try {
            BufferedWriter fileObj = new BufferedWriter(new FileWriter(fileName));
            for (IObject obj : this) {
                fileObj.write(obj.toString());
                fileObj.newLine();
            }
            fileObj.close();
        } catch (IOException e) {
            System.out.println("Error in writing to file");
        }
    }

    public boolean add(T obj){
        boolean result = super.add(obj);
        if (result) writeToFile();
        return result;
    }

    public boolean remove(T obj){
        boolean result = super.remove(obj);
        if (result) writeToFile();
        return result;
    }

    public T set(int index, T obj){
        boolean result = super.set(index, obj) != null;
        if (result) writeToFile();
        return obj;
    }
}