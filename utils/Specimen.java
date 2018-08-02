package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Specimen {

    private List<Integer> locationType;
    private List<Integer> grainIndeces;

    private List<Double> x;
    private List<Double> y;
    private List<Double> z;

    private List<Double> temperature;

    public Specimen(){
        locationType = new ArrayList<Integer>();
        grainIndeces = new ArrayList<Integer>();

        x = new ArrayList<Double>();
        y = new ArrayList<Double>();
        z = new ArrayList<Double>();

        temperature = new ArrayList<Double>();
    }

    private String specimenName;

    public Specimen(String specimenName){
        this.specimenName = specimenName;

    }

    public void read(String specimenName) throws IOException {
        System.out.println("Reading : " + specimenName);
        File file = new File(".\\src\\main\\java\\utils\\"+specimenName);
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            System.out.println("Файл "+ file.getAbsolutePath() +" не найден!");
            System.out.println(e.getMessage());
        }

        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = null;

        while( (line = bufferedReader.readLine()) != null ){
            String[] tokens = line.split(" ");
            locationType.add(Integer.parseInt(tokens[0]));
            grainIndeces.add(Integer.parseInt(tokens[1]));
            x.add(Double.parseDouble(tokens[2]));
            y.add(Double.parseDouble(tokens[3]));
            z.add(Double.parseDouble(tokens[4]));
        }
        bufferedReader.close();
    }

    public List<Integer> getLocationType(){
        return locationType;
    }

    public void setLocationType(List<Integer> list){
        this.locationType = list;
    }

    public List<Integer> getGrainIndeces(){
        return grainIndeces;
    }

    public void setGrainIndeces(List<Integer> list){
        this.grainIndeces = list;
    }

    public List<Double> getX(){
        return this.x;
    }

    public void setX(List<Double> list){
        this.x = list;
    }

    public List<Double> getY(){
        return this.y;
    }

    public void setY(List<Double> list){
        this.y = list;
    }

    public List<Double> getZ(){
        return this.z;
    }

    public void setZ(List<Double> list){
        this.z = list;
    }

    public List<Double> getTemperature(){
        return temperature;
    }

    public void setTemperature(List<Double> temperature) {
        this.temperature = temperature;
    }
}
