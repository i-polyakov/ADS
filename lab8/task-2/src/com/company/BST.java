package com.company;

//Бинарное поисковое дерево
public class BST {
    private Node root;


    static class Node {
      private   int key;
      private  Node left;
      private  Node right;

        Node(int key) {
            this.key = key;
            this.right =null;
            this.left =null;
        }

        public void print(String indent, String position, boolean last) {

            System.out.print(indent);
            if (last){
                System.out.print("└─");
                indent += "  ";
            }
            else{
                System.out.print("├─");
                indent += "| ";
            }

            System.out.println(position);
            if( (left != null || right != null))
            {
                if (left != null)
                    left.print(indent, "L:"+left.key, false);
                else
                    System.out.println(indent+"├─"+"L:-");

                if (right != null)
                    right.print(indent, "R:"+right.key, true);
                else
                    System.out.println(indent+"└─"+"R:-");
            }
        }
        public int getKey(){
            return key;
        }
    }



    //Добавление
    public void add(int value) {
        root = insert(root, value);
    }
    private Node insert(Node current, int key) {

        if (current == null)
            return new Node(key);

        if (key < current.key)
            current.left = insert(current.left, key);
         else if (key > current.key)
            current.right = insert(current.right, key);
         else
            return current;

        return current;
    }
    //Поиск
    public Node search(int key){
       return searchR(root,key);

    }
    private Node searchR(Node node, int key){
        if( node == null || key == node.key)
            return node;
        if (key < node.key)
            return searchR(node.left, key);
        else
            return searchR(node.right, key);
    }

    //Удаление(правое)
    public void rightDeletion(int key){
        root=rightDel(root,key);
    }
    private Node rightDel(Node current, int key){
        if (current == null)
        return current;
        if (key < current.key)
        current.left = rightDel(current.left, key);
  else if (key > current.key)
        current.right = rightDel(current.right, key);
  else if (current.left != null && current.right != null) {
            current.key = minimum(current.right).key;
            current.right = rightDel(current.right, current.key);
        }
  else if (current.left != null)
        current = current.left;
    else if (current.right != null)
        current = current.right;
    else
        current = null;
        return current;


    }
    //Удаление(левое)
    public void leftDeletion(int key){
        root=leftDel(root,key);
    }
    private Node leftDel(Node current, int key){
        if (current == null)
            return current;
        if (key < current.key)
            current.left = leftDel(current.left, key);
        else if (key > current.key)
            current.right = leftDel(current.right, key);
        else if (current.left != null && current.right != null) {
            current.key = maximum(current.left).key;
            current.left = leftDel(current.left, current.key);
        }
        else if (current.left != null)
            current = current.left;
        else if (current.right != null)
            current = current.right;
        else
            current = null;
        return current;


    }

    public Node maximum( Node node) {
        if (node.right == null)
        return node;
  return maximum(node.right);
    }
    public Node minimum( Node node) {
        if (node.left == null)
        return node;
        return minimum(node.left);
    }

    //Симметричный обход. Рекурсивно: левое поддерево, корень, правое поддерево.
    public String  symmetricalTraversal(Node node){
        if (node != null)
            return    symmetricalTraversal(node.left)+node.key+" "+symmetricalTraversal(node.right);
        return "";
    }

    //Прямой обход. Рекурсивно: корень, левое поддерево, правое поддерево.
    public String  straightTraversal(Node node) {
        if (node != null)
            return  node.key+" "+  straightTraversal(node.left)+straightTraversal(node.right);
        return "";
    }

    //Обратный обход. Рекурсивно: левое поддерево, правое поддерево, корень.
    public String  reverseTraversal(Node node) {
        if (node != null)
            return   reverseTraversal(node.left)+  reverseTraversal(node.right)+node.key+" ";
        return "";
    }

    //глубина(кол-во предков)
    public int depth(int key){
        if(search(key)!=null)
            return depthR(root,key);
        return -1;
    }
    public int depthR(Node node,int key){
        if (key != node.key) {

            if (key < node.key)
                return depthR(node.left, key) + 1;
            else
                return depthR(node.right, key) + 1;
        }
        return 0;

    }
    //высота
    public  int height(int key){
        return heightR(search(key));
    }
    private int heightR(Node node) {
        if (node != null)
            return Math.max(heightR(node.left), heightR(node.right)) + 1;
        else
            return -1;
    }
    //уровень узла
    public int level(int key){
        if(search(key)!=null)
        return heightR(root)-depth(key);
        return -1;
    }
    //сравнение деревьев по структуре
    public boolean equals(Node node){
        return equalsR(root,node);
    }
    private boolean equalsR(Node first,Node second){
        if(first!=null&&second!=null) {
           if(equalsR(first.left, second.left))
           return equalsR(first.right, second.right);
           else return false;
        }
        else return first == null && second == null;

    }

    //вывод дерева
    public void print()
    {
        if(root!=null)
        root.print("", root.key+"", true);
        else System.out.println( "Дерево пустое!");

    }
    public Node getRoot(){
        return root;
    }

}
