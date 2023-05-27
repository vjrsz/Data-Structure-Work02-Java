package Main;

import Algorithms.Generic;
import Algorithms.HashTable;
import Algorithms.HashingQuadratic;

import java.util.Scanner;

public class Manager {
    private static HashTable[] hashTables = { new HashingQuadratic() };
    private static Scanner input = new Scanner(System.in);
    private HashTable hashtable;

    public Manager() {}

    public void create(){
        boolean loop = true;

        while(loop) {
            System.out.println("Escolha um método para tratar as colisões");
            System.out.println("1 - HashingQuadratic");
            System.out.println("2 - HashingQuadratic");
            System.out.println("3 - HashingQuadratic");
            System.out.println("4 - HashingQuadratic");
            System.out.printf(":: ");

            int indexAlgorithms = input.nextInt();

            if ( indexAlgorithms <= 0 || indexAlgorithms > hashTables.length ){
                System.out.println("Insira um valor válido\n");
                continue;
            }

            this.hashtable = getAlgorithmHash(indexAlgorithms - 1);
            break;
        }
    }

    public void insert(Generic generic){
        hashtable.put(generic.getKey(), generic.getValue());

        System.out.println("% Inserted Element");
    }

    public Generic[] getAll(){
        System.out.println("% Recovering All");

        return hashtable.getAll();
    }

    public static HashTable getAlgorithmHash(int i){
        return Manager.hashTables[i];
    }


}
