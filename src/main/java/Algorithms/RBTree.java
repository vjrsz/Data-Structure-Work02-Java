package Algorithms;

public class RBTree<K extends Comparable<K>, V> implements ITree
{
    protected static final boolean RED = true;
    protected static final boolean BLACK = false;

    @Override
    public void put(Object key, Object value) {

    }

    @Override
    public void all() {

    }

    protected class Node {
        public K chave;
        public V valor;
        public Node esq, dir;

        boolean cor;
        int size;

        Node(K k, V v, int size, boolean color) {
            this.chave = k;
            this.valor = v;

            this.size = size;
            this.cor = color;
        }
    }

    protected Node raiz;

    private boolean isRed(Node h) {
        return true;
    }

    private boolean isBlack(Node h) {
       return true;
    }

    public int size() {
        return size(raiz);
    }

    protected int size(Node no) {
        if (no == null) {
            return 0;
        }

        return no.size;
    }

    public boolean isEmpty() {
        return size(raiz) == 0;
    }

    protected Node rotacaoEsquerda(Node no) {
        if (no == null || no.dir == null) {
            return no;
        }

        Node novaRaiz = no.dir;

        no.dir = novaRaiz.esq;
        novaRaiz.esq = no;

        novaRaiz.cor = no.cor;
        novaRaiz.cor = RED;

        novaRaiz.size = no.size;
        novaRaiz.size = size(no.esq) + 1 + size(no.dir);

        return novaRaiz;
    }

    /**
     * Implementar o esse método
     * @param h
     * @return
     */
    private Node rotacaoDireita(Node h) {
        // Implementar método que aplica a rotação à direita.
        return null;
    }
    private void trocaCor(Node h) {
        // Implementar método que troca as cores.
    }


    /**
     * Insere um novo nó
     * @param k
     * @param val
     */
    public void put(K k, V val){
        raiz = put(raiz, k, val);
        raiz.cor = BLACK;
    }

    private Node put(Node h, K k, V val)
    {
        if (h == null) // Do standard insert, with red link to parent.
            return new Node(k, val, 1, RED);

        int cmp = k.compareTo(h.chave);
        if (cmp < 0)
            h.esq = put(h.esq, k, val);
        else if (cmp > 0)
            h.dir = put(h.dir, k, val);
        else h.valor = val;

        if (isRed(h.dir) && !isRed(h.esq))
            h = rotacaoEsquerda(h);
        if (isRed(h.esq) && isRed(h.esq.esq))
            h = rotacaoDireita(h);
        if (isRed(h.esq) && isRed(h.dir))
            trocaCor(h);

        h.size = size(h.esq) + size(h.dir) + 1;
        return h;
    }
}
