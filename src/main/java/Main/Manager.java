package Main;

import Algorithms.Generic;
import Algorithms.IHashTable;
import Algorithms.LinkedListHashTable;
import Algorithms.QuadraticHashTable;

import java.util.*;

public class Manager {
    private static final Map<Integer, IHashTable> IHashTables = new HashMap<>();
    private static final Scanner input = new Scanner(System.in);
    private IHashTable hashtable;

    static{
        Manager.IHashTables.put(1, new QuadraticHashTable());
        Manager.IHashTables.put(2, new LinkedListHashTable());
    }

    public Manager() {}

    public void create(){
        boolean loop = true;

        while(loop) {
            System.out.println("? Choose a method to handle collisions");
            System.out.println("1 - HashingQuadratic");
            System.out.println("2 - LinkedListHashTable");
            System.out.printf(":: ");

            int indexAlgorithms = input.nextInt();

            if ( indexAlgorithms <= 0 || indexAlgorithms > IHashTables.size() ){
                System.out.println("! Enter a valid value\n");
                continue;
            }

            this.hashtable = getAlgorithmHash(indexAlgorithms);
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

        List<Generic> generics = new ArrayList();
        for (Generic generic : hashtable.getAll()) {
            if ( generic.getKey().toString().contains(search) ){
                generics.add(generic);
            }
        }
        return;
    }

    public Generic get(Generic generic){
        System.out.println("% Recovering Element");

        generic.setValue( hashtable.get(generic.getKey()) );

        return generic;
    }

    public List<Generic> getAll(){
        System.out.println("% Recovering All");

        return List.of(hashtable.getAll());
    }

    public void remove(Generic generic){
        if (hashtable.remove(generic.getKey())){
            System.out.println("% Removed Element");
            return;
        }

        System.out.println("! Element not found");
    }

    public static IHashTable getAlgorithmHash(int i){
        return Manager.IHashTables.get(i);
    }


}
