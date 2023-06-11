package Algorithms.Trees;

import Algorithms.Generic;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;

class BINode<K, V> extends Generic<K, V> {
    BINode<K, V> left, right;

    public BINode(K key, V value) {
        super(key, value);
        this.left = null;
        this.right = null;
    }

    public BINode<K, V> getLeft() {
        return left;
    }

    public BINode<K, V> getRight() {
        return right;
    }
}

public class BITree<K, V> implements ITree<K, V> {
    public BINode<K, V> root;

    /**
     * Função para inserir um elemento
     */
    public void put(K key, V value) {
        root = put(this.root, key, value);
    }
    private BINode<K, V> put(BINode<K, V> root, K key, V value) {
        BINode<K, V> newAVLNode = new BINode(key, value);

        if (root == null) {
            return new BINode<>(newAVLNode.getKey(), newAVLNode.getValue());
        }

        int compare = root.compareTo(newAVLNode);

        if (compare > 0) {
            root.left = put(root.getLeft(), key, value);
        } else if (compare < 0) {
            root.right = put(root.getRight(), key, value);
        } else {
            root.right = put(root.getRight(), key, value);
        }

        return root;
    }

    /**
     * Função para pegar todos elementos
     */
    public List<V> getAll() {
        List<V> values = new ArrayList();

        getAllRecursive(root, values);

        return values;
    }
    private void getAllRecursive(BINode node, List values){
        if (node == null) {
            return;
        }

        getAllRecursive(node.left, values);
        values.add(node.getValue());
        getAllRecursive(node.right, values);
    }

    /**
     * Função para procurar um elemento
     */
    public V get(K key) {
        BINode<K, V> node = getRecursive(this.root, key);

        if (node != null) {
            return node.getValue();
        } else
            return null;
    }
    private BINode<K, V> getRecursive(BINode<K, V> root, K key) {

        // Base Cases: root is null or key is present at root
        if (root == null || root.getKey() == key) {
            return root;
        }

        // val is greater than root's key
        int compare = root.compareTo(new BINode(key, null));
        if (compare > 0) {
            return getRecursive(root.left, key);
        }

        // val is less than root's key
        return getRecursive(root.right, key);
    }

    /**
     * Função para remover um elemento
     */
    public boolean removeNode(BINode<?, ?> node) {
        if (root == null) {
            return false;
        } else if (get((K) node.getKey()) == null) {
            return false;
        } else {
            this.root = removeRecursive(this.root, (K) node.getKey());
            return true;
        }
    }
    private BINode<K, V> removeRecursive(BINode<K, V> rootNode, K key) {

        /// REMOÇÃO PADRÃO ARVORE BINÁRIA
        if (rootNode == null) {
            return rootNode;
        }

        // Se a chave a ser excluída for menor que a chave da raiz, então ela fica na subárvore esquerda
        if ((int) key < (int) rootNode.getKey()) {
            rootNode.left = removeRecursive(rootNode.left, key);
        }
        // Se a chave a ser excluída for maior que a chave da raiz, então ela fica na subárvore direita
        else if ((int) key > (int) rootNode.getKey()) {
            rootNode.right = removeRecursive(rootNode.right, key);
        } else { // se a chave for igual à chave da raiz, então este é o nó a ser deletado
            // nó com apenas um filho ou nenhum filho
            if ((rootNode.left == null) || (rootNode.right == null)) {
                BINode<K, V> temp = null;

                if (temp == rootNode.left) {
                    temp = rootNode.right;
                } else {
                    temp = rootNode.left;
                }

                // Se não tiver filho
                if (temp == null) {
                    temp = rootNode;
                    rootNode = null;
                } else {// Se tiver um filho
                    rootNode = temp; // Copia o elemento para o filho não vazio
                }
            } else {
                // no com dois filhos: pega o menor da subarvore à direita
                BINode<K, V> temp = minValueNode(rootNode.right);

                // Copia o sucessor para esse nó
                rootNode.setKey(temp.getKey());

                // deleta o menor da arvore à direita
                rootNode.right = removeRecursive(rootNode.right, temp.getKey());
            }

            if (rootNode == null) {
                return rootNode;
            }
        }
        return rootNode;
    }

    private BINode<K, V> minValueNode(BINode<K, V> node) {
        BINode<K, V> current = node;

        /* loop down to find the leftmost leaf */
        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    @Override
    public String getName() {
        return "BITree";
    }
}
