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

    /**
     * Função para inserir um elemento
     */
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
     * Função para retornar todos os elementos
     */
    public List<V> getAll() {
        List<V> values = new ArrayList();

        getAllRecursive(root, values);

        return values;
    }
    private void getAllRecursive(AVLNode node, List values){
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
     * Função para remover um elemento
     */
    public boolean removeNode(AVLNode<?, ?> node) {
        if (isEmpty()) {
            return false;
        } else if (get((K) node.getKey()) == null) {
            return false;
        } else {
            this.root = removeRecursive(this.root, (K) node.getKey());
            return true;
        }
    }
    private AVLNode<K, V> removeRecursive(AVLNode<K, V> rootNode, K key) {

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
                AVLNode<K, V> temp = null;

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
                AVLNode<K, V> temp = minValueNode(rootNode.right);

                // Copia o sucessor para esse nó
                rootNode.setKey(temp.getKey());

                // deleta o menor da arvore à direita
                rootNode.right = removeRecursive(rootNode.right, temp.getKey());
            }

            // If the tree had only one node then return
            if (rootNode == null) {
                return rootNode;
            }

            // ATUALIZA A ALTURA DO NÓ ATUAL
            rootNode.height = max(height(rootNode.left), height(rootNode.right)) + 1;

            // ATUALIZA O FATOR DE BALANCEAMENTO DO NÓ ATUAL
            int balance = getBalance(rootNode);

            // Se este nó se tornar desbalanceado, então há 4 casos
            // Caso 1 -> rotação simples à direita
            if (balance > 1 && getBalance(rootNode.left) >= 0)
                return rightRotate(rootNode);

            // Caso 2 -> rotação dupla à direita
            if (balance > 1 && getBalance(rootNode.left) < 0) {
                rootNode.left = leftRotate(rootNode.left);
                return rightRotate(rootNode);
            }

            // Caso 4 -> rotação sumples à esquerda
            if (balance < -1 && getBalance(rootNode.right) <= 0) {
                return leftRotate(rootNode);
            }

            // Caso 4 -> rotação dupla à esquerda
            if (balance < -1 && getBalance(rootNode.right) > 0) {
                rootNode.right = rightRotate(rootNode.left);
                return leftRotate(rootNode);
            }
        }
        return rootNode;
    }

    /**
     * Dada uma árvore de pesquisa binária não vazia, retorna o
     * nó com valor mínimo de chave encontrado nessa árvore.
     */
    private AVLNode<K, V> minValueNode(AVLNode<K, V> node) {
        AVLNode<K, V> current = node;

        /* loop down to find the leftmost leaf */
        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    public boolean isEmpty() {
        return root == null;
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

    @Override
    public String getName() {
        return "AVLTree";
    }
}
