package com.company;

import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {
        BST bstF=new BST();
        bstF.add(8);
        bstF.add(3);
        bstF.add(10);
        bstF.add(1);
        bstF.add(6);
        bstF.add(14);
        bstF.add(2);
        bstF.add(4);
        bstF.add(7);
        bstF.add(13);
        bstF.print();

        BST bstS=new BST();
        bstS.add(8);
        bstS.add(4);
        bstS.add(10);
        bstS.add(1);
        bstS.add(6);
        bstS.add(14);
        bstS.add(2);
        bstS.add(7);
        bstS.add(13);
        bstS.print();
        compareAfterDeletion(bstF,bstS);


    }
    static boolean compareAfterDeletion(BST first, BST second){
        String[] fewerNodes= first.straightTraversal(first.getRoot()).split(" ");
        String[] moreNodes= second.straightTraversal(second.getRoot()).split(" ");

        BST smallTree;
        BST bigTree;
        int buff=Math.max(fewerNodes.length,moreNodes.length);
        TreeMap<Integer, String> canDelete=new TreeMap<>();
        int key;

        if (fewerNodes.length==moreNodes.length){
            System.out.println("Нельзя получить одно дерево из второго.");
            return false;
        }

        if(fewerNodes.length>moreNodes.length) {
            String[] nodes=fewerNodes;
            fewerNodes=moreNodes;
            moreNodes = nodes;
        }


        smallTree=new BST();addNodes(smallTree,fewerNodes);//Запонение узлами

        for (int i = 0; i < buff; i++) {
            bigTree=new BST();addNodes(bigTree,moreNodes);//Запонение узлами

            key=Integer.parseInt( moreNodes[i]);//узел который будем удалять

            bigTree.rightDeletion(key);//Правое удаление
            if(smallTree.equals(bigTree.getRoot()))//проверка на равенство по структуре
                canDelete.put(key,"Правое удаление");//если равны запишем ключ и какое удаление


            bigTree=new BST();addNodes(bigTree,moreNodes);//Запонение узлами

            bigTree.leftDeletion(key);//Левое удаление
            if(smallTree.equals(bigTree.getRoot()))
                canDelete.put(key,"Левое удаление");
        }

        if(canDelete.isEmpty()) {
            System.out.println("Нельзя получить одно дерево из второго.");
            return false;
        }
        else {
            System.out.println("Можно удалить: ");
            canDelete.forEach((integer, s) ->System.out.println(integer+": "+s));
            System.out.println("Наибольший ключ: "+canDelete.lastKey());
            return true;
        }


    }
    static void addNodes(BST tree, String[] nodes){
        if(!nodes[0].equals(""))
            for(String str: nodes)
                tree.add(Integer.parseInt(str));

    }
}
