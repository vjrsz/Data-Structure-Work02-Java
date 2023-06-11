package Main;

import Algorithms.Hashs.IHashTable;
import Algorithms.Hashs.LinkedListHashTable;
import Algorithms.Hashs.QuadraticHashTable;
import Algorithms.Trees.AVLTree;
import Algorithms.Trees.BITree;
import Algorithms.Trees.ITree;
import Algorithms.Trees.RBTree;
import File.File;
import File.ReadFileCSV;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<File> files = ReadFileCSV.read("data/data.csv");

        IHashTable hashtable;
        ITree tree;
        Long init;
        Long timeInsertHash = 0L;
        Long timeGetHash = 0L;
        Long timeInsertTree = 0L;
        Long timeGetAllTree = 0L;

        // definindo hash
        for (int hash : new int[]{0, 1}) {
            hashtable = hash == 0 ? new QuadraticHashTable() : new LinkedListHashTable();
            // inserindo na hash

            init = System.currentTimeMillis();
            for (File file : files.subList(0, files.size())){
                hashtable.put(file.getName(), file);
            }
            timeInsertHash = System.currentTimeMillis() - init;

            // definindo chave de ordenacao
            for (int primaryKey : new int[]{0, 1, 2, 3, 4}) {

                // definindo arvore
                for (int treeI : new int[]{0, 1, 2}) {
                    timeInsertTree = 0L;

                    if(treeI == 0){ tree = new BITree(); }
                    else if(treeI == 1){ tree = new AVLTree(); }
                    else { tree = new RBTree(); }

                    File.setPrimaryKey(primaryKey);

                    // inserindo na arvore
                    for (File fileSearch : files.subList(0, files.size())) {
                        File file = (File) hashtable.get(fileSearch.getName());

                        init = System.currentTimeMillis();
                        if (file != null) {
                            tree.put(file.getKey(), file);
                        }
                        timeInsertTree += System.currentTimeMillis() - init;
                    }
                    init = System.currentTimeMillis();
                    List<File> filesSearch = tree.getAll();
                    timeGetAllTree = System.currentTimeMillis() - init;

                    System.out.println("-> " + hashtable.getName() + " | " + tree.getName() + " | " + File.getNameKey() + " | Time Insert Hash: " + timeInsertHash + "ms | Time Insert Tree: " + timeInsertTree + "ms | " + "Time Get All Tree: " + timeGetAllTree + "ms" + " | Comparisons: " + tree.getComparisons());
                    //FileTablePrinter.showFiles(filesSearch);
                    System.out.println();
                }
            }
            init = System.currentTimeMillis();
            File file = (File) hashtable.get(files.get(5000).getName());
            timeGetHash = System.currentTimeMillis() - init;
            System.out.println("<> " + "Time Get Hash: " + timeGetHash + "ms");
            System.out.println();
        }
    }
}