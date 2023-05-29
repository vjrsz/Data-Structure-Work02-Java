package Main;

import Algorithms.Generic;
import Algorithms.IHashTable;
import Algorithms.LinkedListHashTable;
import Algorithms.QuadraticHashTable;

import java.util.Scanner;

public class Manager {
    private static IHashTable[] IHashTables = { new QuadraticHashTable(), new LinkedListHashTable() };
    private static Scanner input = new Scanner(System.in);
    private IHashTable hashtable;

    public Manager() {}

    public void create(){
        boolean loop = true;

        while(loop) {
            System.out.println("? Escolha um método para tratar as colisões");
            System.out.println("1 - HashingQuadratic");
            System.out.println("2 - LinkedListHashTable");
            System.out.println("3 - HashingQuadratic");
            System.out.println("4 - HashingQuadratic");
            System.out.printf(":: ");

            int indexAlgorithms = input.nextInt();

            if ( indexAlgorithms <= 0 || indexAlgorithms > IHashTables.length ){
                System.out.println("! Insira um valor válido\n");
                continue;
            }

            this.hashtable = getAlgorithmHash(indexAlgorithms - 1);
            break;
        }
    }

    public void insert(Generic generic){
        if( hashtable.get(generic.getKey()) != null ){
            System.out.println("! Element already");
            return;
        }

        hashtable.put(generic.getKey(), generic.getValue());

        System.out.println("% Inserted Element");
    }
        // GA GB
    public void update(Generic generic){
        if( hashtable.get(generic.getKey()) == null ){
            System.out.println("! Element does not exist");
            return;
        }

        hashtable.put(generic.getKey(), generic.getValue());

        System.out.println("% Updated Element");
    }


    public void search(){
        System.out.println("? Enter a name file ");
        System.out.printf(":: ");
        String search = input.next();

        for (:
             ) {
            
        }

    }

    public Generic get(Generic generic){
        System.out.println("% Recovering Element");

        generic.setValue( hashtable.get(generic.getKey()) );

        return generic;
    }

    public Generic[] getAll(){
        System.out.println("% Recovering All");

        return hashtable.getAll();
    }

    public void remove(Generic generic){
        hashtable.remove(generic.getKey());

        System.out.println("% Removed Element");
    }

    public static IHashTable getAlgorithmHash(int i){
        return Manager.IHashTables[i];
    }


}
