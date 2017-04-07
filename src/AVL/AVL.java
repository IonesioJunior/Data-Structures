package AVL;

import java.awt.List;

public class AVL<T extends Comparable<T>> extends BST<T> {
	
	public AVL(){
		super();
		
	}
	//Calcula o balanço de um determinado nó na arvore
    public int calculateBalance(Node<T> node) {
        if (node.isNIL()) return 0;
        return recursiveHeight((Node<T>) node.getLeft()) - recursiveHeight((Node<T>) node.getRight());
    }

    //Faz o rebalanceamento duplo ou unico
    protected void rebalance(Node<T> node) {

        int balance = calculateBalance(node); //Balanceamento do nó
        int balanceLeftChild = calculateBalance((Node<T>) node.getLeft()); //Balanceamento do filho a esquerda
        int balanceRightChild = calculateBalance((Node<T>) node.getRight()); //Balanceamento do filho a direita


        if (balance > 1) { //Caso esteja desbalanceado para a esquerda
            
        	if (balanceLeftChild < 0) { //Caso tenha zig zag
                leftRotation((Node<T>) node.getLeft());
            }
            rightRotation(node); //Rebalanceamento default para a esquerda

        } else if (balance < -1) { // Caso esteja desbalanceado para a direita

            if (balanceRightChild > 0) { //Caso tenha zig zag
                rightRotation((Node<T>) node.getRight());
            }
            leftRotation(node); //Rebalanceamento default para a esquerda
        }

    }


    @Override
    protected void recursiveInsert(T element,Node<T> node, Node<T> parent) {

        if (node.isNIL()) { //Sendo um nó vazio, apenas muda-se o valor e cria-se seus vizinhos
            node.setData(element);
            node.setLeft(new Node<T>());
            node.setRight(new Node<T>());
            node.setParent(parent);
        } else {
            T nodeData = node.getData();

            if (nodeData.equals(element)) { //Ja exista um valor igual, retorna

                return;

            } else if (nodeData.compareTo(element) > 0) {//Caso o valor do nó seja maior

                recursiveInsert( element,(Node<T>) node.getLeft(), node); //Vá para a esquerda

            } else { // Do contrario

                recursiveInsert(element,(Node<T>) node.getRight(),  node); //Vá para a direita

            }

        }
        rebalance(node); //Rebalanceamento após o processo de inserção

    }


    @Override
    public void recursiveRemove(Node<T> node) {

        if (node.isLeaf()) { //Caso seja folha, apenas muda o valor da data para null e faz o rebalanceamento de baixo para cima
            node.setData(null);
            rebalanceUp((Node<T>) node);

        } else { //Caso nao seja folha

            if (!node.getRight().isNIL()) { //Se existir um nó a direita

                Node<T> sucessor = recursiveMinimum(node.getRight()); //Busca-se o sucessor
                node.setData(sucessor.getData()); //Troca-se o valor com o sucessor
                recursiveRemove(sucessor);//Aplica o mesmo algoritmo ao sucessor, até chegar a um nó folha ou que só tenha filho a esquerda

            } else {//Caso tenha dois filhos, ou só tenha filho a esquerda

                Node<T> predecessor = recursiveMinimum(node.getLeft()); //busca um predecessor
                node.setData(predecessor.getData());//Troca -se o valor com o predecessor
                recursiveRemove(predecessor);//Aplica o mesmo algoritmo ao predecessor
            }

            rebalanceUp((Node<T>) node); //Rebalanceamento de baixo para cima

        }
    }


    // AUXILIARY
    protected void rebalanceUp(Node<T> node) {
        Node<T> parent = (Node<T>) node.getParent();
        
        while (parent != null) { //Enquanto nao chegar na raiz, balanceia todos os nós acima deste
            rebalance(parent);
            parent =  parent.getParent();
        }

    }

    // AUXILIARY
    protected void leftRotation(Node<T> node) {
        Node<T> newRoot = leftRotationNodes(node); //Nó que ficou no lugar no rotacionado
        if (node == getRoot()) { //Caso o rotacionado fosse o root
            setRoot(newRoot);
        }
    }


    // AUXILIARY
    protected void rightRotation(Node<T> node) {
        Node<T> newRoot = rightRotationNodes(node);//Nó que ficou no lugar no rotacionado
        if (node == getRoot()) { //Caso o rotacionado fosse o root
            setRoot(newRoot);
        }
    }
    public static <T extends Comparable<T>> Node<T> rightRotationNodes(Node<T> node) {

        if (node != null && !node.isNIL()) {

            Node<T> parent = node.getParent();
            Node<T> left = node.getLeft();

            left.setParent(parent);
            if (parent != null) {

                if (parent.getLeft().equals(node))
                    parent.setLeft(left);

                else
                    parent.setRight(left);
            }

            node.setLeft(left.getRight());
            if (node.getLeft() != null)
                node.getLeft().setParent(node);

            left.setRight(node);
            node.setParent(left);

            return (Node<T>) left;
        }
        return null;
    }
    public static <T extends Comparable<T>> Node<T> leftRotationNodes(Node<T> node) {

        if (node != null && !node.isNIL()) {

            Node<T> parent = node.getParent();
            Node<T> right = node.getRight();

            right.setParent(parent);
            if (parent != null) {

                if(parent.getRight().equals(node))
                    parent.setRight(right);

                else
                    parent.setLeft(right);
            }

            node.setRight(right.getLeft());
            if (node.getRight() != null)
                node.getRight().setParent(node);

            right.setLeft(node);
            node.setParent(right);

            return (Node<T>) right;
        }
        return null;
    }

}
