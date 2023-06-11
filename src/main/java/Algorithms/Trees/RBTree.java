package Algorithms.Trees;

import Algorithms.Generic;

import java.util.ArrayList;
import java.util.List;

enum Color { RED, BLACK }

class RBNode<K, V> extends Generic<K, V> {
    Color color;
    RBNode left, right, parent;

    public RBNode(K key, V value) {
        super(key, value);
        this.color = Color.RED;
        this.left = null;
        this.right = null;
        this.parent = null;
    }
}

public class RBTree<K, V> implements ITree<K, V> {
    private RBNode root;

    public RBTree() {
        root = null;
    }

    public void put(K key, V value) {
        RBNode newNode = new RBNode(key, value);
        put(newNode);
    }
    private void put(RBNode RBNode) {
        RBNode parent = null;
        RBNode current = root;

        while (current != null) { // enquanto o atual diferente de null ele faz a comparacao pra ver onde inserir
            parent = current;
            int compare = RBNode.compareTo(current);
            if (compare < 0) { // define se vai para esquerda ou direita
                current = current.left;
            } else {
                current = current.right;
            }
        }

        RBNode.parent = parent;

        if (parent == null) {
            root = RBNode;
        } else {
            int compare = RBNode.compareTo(parent);

            if (compare < 0) { // define se vai para esquerda ou direita
                parent.left = RBNode;
            } else {
                parent.right = RBNode;
            }
        }

        fixViolation(RBNode); // verifica se ha alguma quebra de regra
    }
    public List<V> getAll() {
        List<V> values = new ArrayList();

        getAllRecursive(root, values); // busca recursiva

        return values;
    }

    @Override
    public String getName() {
        return "RBTree";
    }

    private void getAllRecursive(RBNode node, List values){
        if (node == null) {
            return;
        }

        getAllRecursive(node.left, values);
        values.add(node.getValue());
        getAllRecursive(node.right, values);
    }

    private void rotateLeft(RBNode RBNode) {
        RBNode rightChild = RBNode.right;
        RBNode.right = rightChild.left;

        if (rightChild.left != null) {
            rightChild.left.parent = RBNode;
        }

        rightChild.parent = RBNode.parent;

        if (RBNode.parent == null) {
            root = rightChild;
        } else if (RBNode == RBNode.parent.left) {
            RBNode.parent.left = rightChild;
        } else {
            RBNode.parent.right = rightChild;
        }

        rightChild.left = RBNode;
        RBNode.parent = rightChild;
    }
    private void rotateRight(RBNode RBNode) {
        RBNode leftChild = RBNode.left;
        RBNode.left = leftChild.right;

        if (leftChild.right != null) {
            leftChild.right.parent = RBNode;
        }

        leftChild.parent = RBNode.parent;

        if (RBNode.parent == null) {
            root = leftChild;
        } else if (RBNode == RBNode.parent.right) {
            RBNode.parent.right = leftChild;
        } else {
            RBNode.parent.left = leftChild;
        }

        leftChild.right = RBNode;
        RBNode.parent = leftChild;
    }
    private void fixViolation(RBNode RBNode) {
        while (RBNode != root && RBNode.color == Color.RED && RBNode.parent.color == Color.RED) {
            if (RBNode.parent == RBNode.parent.parent.left) {
                RBNode uncle = RBNode.parent.parent.right;

                if (uncle != null && uncle.color == Color.RED) {
                    RBNode.parent.color = Color.BLACK;
                    uncle.color = Color.BLACK;
                    RBNode.parent.parent.color = Color.RED;
                    RBNode = RBNode.parent.parent;
                } else {
                    if (RBNode == RBNode.parent.right) {
                        RBNode = RBNode.parent;
                        rotateLeft(RBNode);
                    }

                    RBNode.parent.color = Color.BLACK;
                    RBNode.parent.parent.color = Color.RED;
                    rotateRight(RBNode.parent.parent);
                }
            } else {
                RBNode uncle = RBNode.parent.parent.left;

                if (uncle != null && uncle.color == Color.RED) {
                    RBNode.parent.color = Color.BLACK;
                    uncle.color = Color.BLACK;
                    RBNode.parent.parent.color = Color.RED;
                    RBNode = RBNode.parent.parent;
                } else {
                    if (RBNode == RBNode.parent.left) {
                        RBNode = RBNode.parent;
                        rotateRight(RBNode);
                    }

                    RBNode.parent.color = Color.BLACK;
                    RBNode.parent.parent.color = Color.RED;
                    rotateLeft(RBNode.parent.parent);
                }
            }
        }

        root.color = Color.BLACK;
    }

}
