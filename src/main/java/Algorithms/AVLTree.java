package Algorithms;

import java.util.Objects;

import static java.lang.Math.max;

public class AVLTree<K, V> implements ITree<K, V>  {
    private class AVLNode<K, V> extends Generic<K, V> {
        AVLNode<K, V> left, right;
        int height;

        public AVLNode(K key, V value) {
            super(key, value);
            this.height = 1;
        }

        public AVLNode(K key, V value, AVLNode<K, V> left, AVLNode<K, V> right) {
            super(key, value);
            this.left = left;
            this.right = right;
            this.height = 0;
        }

        public AVLNode<K, V> getLeft() {
            return left;
        }

        public void setLeft(AVLNode<?, ?> left) {
            this.left = (AVLNode<K, V>) left;
        }

        public AVLNode<K, V> getRight() {
            return right;
        }

        public void setRight(AVLNode<?, ?> right) {
            this.right = (AVLNode<K, V>) right;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getHeight(){
            return height;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            final AVLNode<K, V> other = (AVLNode<K, V>) obj;
            if (!Objects.equals(this.getKey(), other.getKey()))
                return false;
            return Objects.equals(this.getValue(), other.getValue());
        }

        @Override
        public String toString() {
            AVLNode<K, V> right = new AVLNode<>(null, null);
            AVLNode<K, V> left = new AVLNode<>(null, null);

            if( getRight() != null)
                right = getRight();
            if( getLeft() != null)
                left = getLeft();

            return "( KEY:" + getKey() + " | R:" +right.getKey() + "| L:" + left.getKey() + " ) - ";
        }
    }
    public AVLNode<K, V> root;

    public AVLNode<K, V> getRoot() {
        return root;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int height(AVLNode<K, V> node) {
        return node == null ? -1 : node.getHeight();
    }

    public void put(K key, V value) {
        root = insert(this.root, key, value);
    }

    private AVLNode<K, V> insert(AVLNode<K, V> root, K key, V value) {
        AVLNode<K, V> newNode = new AVLNode(key, value);

        if (root == null) {
            return new AVLNode<>(newNode.getKey(), newNode.getValue());
        }

        int compare = root.compareTo(newNode);

        if (compare > 0) {
            root.left = insert(root.getLeft(), key, value);
        } else if (compare < 0) {
            root.right = insert(root.getRight(), key, value);
        } else {
            return root;
        }

        root.height = max(height(root.left), height(root.right)) + 1;

        int balance = getBalance(root);

        // If this node becomes unbalanced, then there
        // are 4 cases Left Left Case
        if (balance > 1 && compare > 0) {
            return rightRotate(root);
        }

        // Right Right Case
        if (balance < -1 && compare < 0) {
            return leftRotate(root);
        }

        // Left Right Case
        if (balance > 1 && compare < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // Right Left Case
        if (balance < -1 && compare > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }
        return root;
    }

    /**
     * Função para procurar um elemento
     */
    public V get(K key) {
        AVLNode<K, V> node = getRecursive(this.root, key);

        if (node != null) {
            return node.getValue();
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
    int getBalance(AVLNode<K, V> N) {
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

    /**
     * Funcao para vizualizar a árvore
     */
    public String inorderRec(AVLNode<K, V> root) {
        String nodeToString = "";
        if (root != null) {
            nodeToString += inorderRec(root.getLeft());
            nodeToString += root.toString();
            nodeToString += inorderRec(root.getRight());
        }
        return nodeToString;
    }

    @Override
    public void all() {
        System.out.println(inorderRec(root));
    }
}
