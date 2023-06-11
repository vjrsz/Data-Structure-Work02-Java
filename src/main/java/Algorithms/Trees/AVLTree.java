package Algorithms.Trees;

import Algorithms.Generic;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;

class AVLNode<K, V> extends Generic<K, V> {
    AVLNode<K, V> left, right;
    int height;

    public AVLNode(K key, V value) {
        super(key, value);
        this.height = 1;
        this.left = null;
        this.right = null;
    }

    public AVLNode<K, V> getLeft() {
        return left;
    }

    public AVLNode<K, V> getRight() {
        return right;
    }

    public int getHeight(){
        return height;
    }
}

public class AVLTree<K, V> implements ITree<K, V> {
    public AVLNode<K, V> root;

    public int height(AVLNode<K, V> AVLNode) {
        return AVLNode == null ? -1 : AVLNode.getHeight();
    }

    public void put(K key, V value) {
        root = put(this.root, key, value);
    }
    private AVLNode<K, V> put(AVLNode<K, V> root, K key, V value) {
        AVLNode<K, V> newAVLNode = new AVLNode(key, value);

        if (root == null) {
            return new AVLNode<>(newAVLNode.getKey(), newAVLNode.getValue());
        }

        int compare = root.compareTo(newAVLNode);

        if (compare > 0) {
            root.left = put(root.getLeft(), key, value);
        } else {
            root.right = put(root.getRight(), key, value);
        }

        root.height = max(height(root.left), height(root.right)) + 1;

        int balance = getBalance(root);

        // If this node becomes unbalanced, then there
        // are 4 cases Left Left Case

        if ( root.left != null ) {
            compare = root.left.compareTo(newAVLNode);
            if (balance > 1 && compare >= 0) {
                return rightRotate(root);
            }
        }
        // Right Right Case
        if ( root.right != null ) {
            compare = root.right.compareTo(newAVLNode);
            if (balance < -1 && compare <= 0) {
                return leftRotate(root);
            }
        }
        // Left Right Case
        if ( root.left != null ) {
            compare = root.left.compareTo(newAVLNode);
            if (balance > 1 && compare <= 0) {
                root.left = leftRotate(root.left);
                return rightRotate(root);
            }
        }
        // Right Left Case
        if ( root.right != null ) {
            compare = root.right.compareTo(newAVLNode);
            if (balance < -1 && compare >= 0) {
                root.right = rightRotate(root.right);
                return leftRotate(root);
            }
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
        return "AVLTree";
    }

    private void getAllRecursive(AVLNode node, List values){
        if (node == null) {
            return;
        }

        getAllRecursive(node.left, values);
        values.add(node.getValue());
        getAllRecursive(node.right, values);
    }

    public V get(K key) {
        AVLNode<K, V> AVLNode = getRecursive(this.root, key);

        if (AVLNode != null) {
            return AVLNode.getValue();
        } else
            return null;
    }

    private AVLNode<K, V> getRecursive(AVLNode<K, V> root, K key) {

        // Base Cases: root is null or key is present at root
        if (root == null || root.getKey() == key) {
            return root;
        }

        // val is greater than root's key
        int compare = root.compareTo(new AVLNode(key, null));
        if (compare > 0) {
            return getRecursive(root.left, key);
        }

        // val is less than root's key
        return getRecursive(root.right, key);
    }

    /**
     * Função para pegar o fator de
     * balanceamento de um elemento
     */
    private int getBalance(AVLNode<K, V> N) {
        if (N == null) {
            return 0;
        }
        return height(N.left) - height(N.right);
    }

    /**
     * Rotaciona o nó da árvore binária com o filho esquerdo
     */
    private AVLNode<K, V> leftRotate(AVLNode<K, V> k1) {
        AVLNode<K, V> y = k1.right;
        AVLNode<K, V> T2 = y.left;

        // Perform rotation
        y.left = k1;
        k1.right = T2;

        // Update heights
        k1.height = max(height(k1.left), height(k1.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        // Return new root
        return y;
    }

    /**
     * Rotaciona o nó da árvore binária com o filho direito
     */
    private AVLNode<K, V> rightRotate(AVLNode<K, V> k2) {
        AVLNode<K, V> x = k2.left;
        AVLNode<K, V> T2 = x.right;

        // Perform rotation
        x.right = k2;
        k2.left = T2;

        // Update heights
        k2.height = max(height(k2.left), height(k2.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        // Return new root
        return x;
    }

    /**
     * Rotação dupla no nó da árvore AVL: primeiro filho esquerdo
     * com o seu filho direito; em seguida, nó pai com novo filho esquerdo
     */
    private AVLNode<K, V> doubleLeftRotate(AVLNode<K, V> k1) {
        k1.right = rightRotate(k1.right);
        return leftRotate(k1);
    }

    /**
     * Rotação dupla no nó da árvore AVL: primeiro filho direito
     * com o seu filho esquerdo; em seguida, nó pai com novo filho direito
     */
    private AVLNode<K, V> doubleRightRotate(AVLNode<K, V> k3) {
        k3.left = leftRotate(k3.left);
        return rightRotate(k3);
    }
}
