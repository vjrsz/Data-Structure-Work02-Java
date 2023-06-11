package Main;

import Algorithms.Generic;
import File.File;
import File.FileTablePrinter;
import File.ReadFileCSV;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<File> files = ReadFileCSV.read("data/data.csv"); // pegando os dados de um csv e botando em um objeto FILE
        Manager manager = new Manager();

        manager.create(); // criando hashtable que vai ser usada

        // inserindo dados na hashtable
        for (File file : files){
            Generic generic = new Generic(file.getName(), file);

            manager.insert(generic);
        }

        // função de pesquisa na hashtable e colocando o resultado em uma arvore
        List<File> filesSearch = manager.search();

        if ( filesSearch.size() > 0){
            FileTablePrinter.showFiles(filesSearch); // imprimindo resultado ordenado
        }else {
            System.out.println("% No files found!");
        }
    }

}