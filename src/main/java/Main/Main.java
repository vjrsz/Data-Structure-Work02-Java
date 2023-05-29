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

        // insert
        for (File file : files.subList(0, 5)){
            Generic generic = new Generic(file.getName(), file);

            manager.insert(generic);
        }

        // get all
        for ( Generic generic : manager.getAll() ){
            showFile((File) generic.getValue());
        }

        // search

        // insert again
        Generic iGeneric = new Generic(files.get(0).getName(), files.get(1));
        manager.insert(iGeneric);

        // remove
        File rfile = files.get(0);
        Generic rGeneric = new Generic(rfile.getName(), null) ;
        manager.remove(rGeneric);

        /* get
        File gfile = files.get(0);
        Generic gGeneric = new Generic(gfile.getName(), null) ;
        gGeneric = manager.get(gGeneric);
        showFile( (File) gGeneric.getValue() ); */
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
        System.out.println("");
    }
}