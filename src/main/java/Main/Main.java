package Main;

import Algorithms.Generic;
import File.File;
import File.ReadFileCSV;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<File> files = ReadFileCSV.read("data/data.csv");
        Manager manager = new Manager();

        manager.create();

        //File fs[] = Arrays.copyOfRange(files.toArray(new File[0]), 0, 10);
        // insert
        for (File file : files){
            Generic generic = new Generic(file.getName(), file);

            manager.insert(generic);
        }

        // show all
        for ( Generic generic : manager.getAll() ){
            if ( generic != null ){
                showFile((File) generic.getValue());
            }
        }

    }
    public static void showFiles(List<File> files){
        for (File file : files) {
            Main.showFile(file);
        }
    }

    public static void showFile(File file){
        System.out.println("--");
        System.out.println("+ Name : " + file.getName());
        System.out.println("+ Type : " + file.getType());
        System.out.println("+ Size : " + file.getSize());
        System.out.println("+ Dt Update : " + file.getDateUpdate());
        System.out.println("+ Dt Created : " + file.getDateCreated());
        System.out.println("--");
    }
}