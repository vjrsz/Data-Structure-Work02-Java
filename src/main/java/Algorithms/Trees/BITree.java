package Algorithms.Trees;

import Algorithms.Generic;

import java.util.ArrayList;
import java.util.List;

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
     * Função para procurar um elemento
     */
    public List<V> getAll() {
        List<V> values = new ArrayList();

        getAllRecursive(root, values);

        return values;
    }

    @Override
    public String getName() {
        return "BITree";
    }

    private void getAllRecursive(BINode node, List values){
        if (node == null) {
            return;
        }

        getAllRecursive(node.left, values);
        values.add(node.getValue());
        getAllRecursive(node.right, values);
    }
}
