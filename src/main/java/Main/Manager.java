package Main;

import Algorithms.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Manager {
    private static final Map<Integer, IHashTable> IHashTables = new HashMap<>();
    private static final Scanner input = new Scanner(System.in);
    private static final Map<Integer, ITree> ITrees = new HashMap<>();
    private IHashTable hashtable;
    private ITree tree;

    static{
        Manager.IHashTables.put(1, new QuadraticHashTable());
        Manager.IHashTables.put(2, new LinkedListHashTable());
        Manager.ITrees.put(1, new AVLTree<>());
        Manager.ITrees.put(2, new RBTree<>());
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
            if ( indexAlgorithms < 1 && indexAlgorithms > IHashTables.size() ){
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
        boolean loop = true;

        while(loop) {
            System.out.println("? Choose a method to ....");
            System.out.println("1 - AVLTree");
            System.out.println("2 - RBTree");
            System.out.printf(":: ");

            int indexAlgorithms = input.nextInt();

            if ( indexAlgorithms < 1 && indexAlgorithms > ITrees.size() ){
                System.out.println("! Enter a valid value\n");
                continue;
            }

            this.tree = getAlgorithmTree(indexAlgorithms);
            break;
        }
        //exercicio_conferencia;planilha_livro;exercicio_viagem;apresentacao_livro
        System.out.println("? Enter a name file (obs: separe os nomes por ;)");
        System.out.printf(":: ");
        String[] search = input.next().split(";");

        for (String name : search) {
            Generic g = new Generic(name, null);
            g.setValue( this.hashtable.get(name) );

            if( g.getValue() != null ){
                this.tree.put(g.getKey(), g.getValue());
            }
        }

        this.tree.all();
    }

    public Generic get(Generic generic){
        System.out.println("% Recovering Element");

        generic.setValue( hashtable.get(generic.getKey()) );

        return generic;
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

    public static ITree getAlgorithmTree(int i){
        return Manager.ITrees.get(i);
    }
}
