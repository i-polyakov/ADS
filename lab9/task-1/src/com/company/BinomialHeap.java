package com.company;

import java.util.LinkedList;

public class BinomialHeap {
    private LinkedList<Node> root=new LinkedList<>();

    static class Node {

        private int key;
        private int degree;//количество дочерних узлов
        private Node parent;//родительский узел
        private  Node child;//крайний левый дочерний узел
        private Node sibling;//правый сестринский узел

        Node(){
            key=-1;
        }
        Node(int value) {
            this.key = value;
            degree=0;
        }

        public int getKey() {
            return key;
        }

        private Node addSubTree(Node node){
            node.parent=this;
            node.sibling=child;
            child=node;
            degree++;
            return this;
        }

        //вывод
        public String toString(int level) {
            Node current = this;
            StringBuilder result = new StringBuilder();

            while (current != null) {

                result.append("  ").append(("| ").repeat(level-1));

                if (current.sibling!=null)
                    result .append("├─").append(current.key).append("\n");
                else
                    result .append("└─").append(current.key).append("\n");

                if (current.child != null) {
                    result.append(current.child.toString(level + 1));
                }

                current = current.sibling;
            }
            return result.toString();
        }
    }
    public BinomialHeap() {
    }

    public BinomialHeap(Node node) {
        root.add (node);
    }

    //добавление элемента в кучу
    public void insert(int value) {

        BinomialHeap binomialHeap = new BinomialHeap(new Node(value));
        root = mergeHeap(this,binomialHeap).root;
    }

    //удаление элемента
    Node extractMin( ){
        if(root.size()!=0) {
            Node curX = root.getFirst();
            Node x = curX;
            int min = curX.key;
            int j = 0;
            //поиск корня х с минимальным значением ключа в списке корней
            while (j < root.size()) {
                curX = root.get(j);
                if (curX.key < min) {

                    min = curX.key;
                    x = curX;
                }

                j++;
            }
            //удаление найденного корня x из списка корней
            root.remove(x);

            Node child = x.child;
            Node prev = null;
            while (child != null) {
                child.parent = null;
                Node sibling = child.sibling;
                child.sibling = prev;
                prev = child;
                child = sibling;
            }

            BinomialHeap HH = new BinomialHeap();   //построение кучи детей вершины x

            while (prev != null) {
                HH.root.add(prev);
                prev = prev.sibling;
                HH.root.getLast().sibling = null;
            }

            root = mergeHeap(this, HH).root;// слияние  кучи с кучей HH
            return x;
        }
        return null;
    }
    //Слияние куч
    public BinomialHeap mergeHeap(BinomialHeap H1, BinomialHeap H2) {
        if (H1 == null||H1.root.size()==0)
            return H2;
        if (H2 == null||H2.root.size()==0)
            return H1;

        BinomialHeap  H = new BinomialHeap();   // H - результат слияния

        // слияние корневых списков
        LinkedList<Node> curH1 = H1.root;
        LinkedList<Node> curH2 = H2.root;
        if (curH1!=null&&curH2!=null)
            while (curH1.size() != 0 && curH2.size() != 0) {
                Node N1=curH1.getFirst();
                Node N2=curH2.getFirst();
                if (N1.degree< N2.degree) {
                    H.root.add(N1);
                    curH1.removeFirst();
                }
                else {
                    H.root.add(N2);
                    curH2.removeFirst();
                }
            }
        if (curH1 == null||curH1.size()==0)
            H.root.addAll(curH2);

        else
            H.root.addAll(curH1);



        // объединение деревьев одной степени
        int i=0;
        Node buff=new Node();
        if (H.root.size()!=0) {
            buff = H.root.get(i);
            H.root.removeFirst();
        }
        while (H.root.size()>i&&H.root.get(i)!=null){
            Node curN=H.root.get(i);
            if(curN.degree==buff.degree){
                if(curN.key<buff.key){

                    buff=curN.addSubTree(buff);
                    H.root.remove(i);
                }else {

                    buff=buff.addSubTree(curN);
                    H.root.remove(i);
                }
            }
            else if(curN.degree>buff.degree){

                H.root.set(i,buff);
                buff= curN;
                i++;
            }
            else i++;
        }
        H.root.add(buff);

        return H;
    }

    @Override
    public String toString() {
        int i=0;
        while(i<root.size()) {
            System.out.println("\n└─"+root.get(i).key);
            if(root.get(i).child!=null)
                System.out.print(root.get(i).child.toString(1));
            i++;
        }

        return "";
    }
}