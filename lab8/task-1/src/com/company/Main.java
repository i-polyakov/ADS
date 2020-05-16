package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        BST bst = new BST();
        bst.add(8);
        bst.add(3);
        bst.add(10);
        bst.add(1);
        bst.add(6);
        bst.add(14);
        bst.add(2);
        bst.add(4);
        bst.add(7);
        bst.add(13);
        bst.print();

        Scanner scan=new Scanner(System.in);
        while (true){
            System.out.println("    0. Выход");
            System.out.println("Базовые операции:");
            System.out.println("    1. Добавление узла");
            System.out.println("    2. Поиск узла");
            System.out.println("    3. Удаление(левое) узла");
            System.out.println("    4. Удаление(правое) узла");
            System.out.println("Обход дерева:");
            System.out.println("    5. Прямой");
            System.out.println("    6. Обратный");
            System.out.println("    7. Симметричный");
            System.out.println("Методы для определения: ");
            System.out.println("    8. Глубины");
            System.out.println("    9. Высоты");
            System.out.println("    10. Уровня узла");
            System.out.println("11. Вывод дерева");
            switch (scan.nextInt()){
                case 0:
                    System.exit(1);
                    break;
                case 1:
                    System.out.println("Ключ:");
                    bst.add(scan.nextInt());
                    break;
                case 2:
                    System.out.println("Ключ:");
                    BST.Node node= bst.search(scan.nextInt());
                    if(node==null)
                        System.out.println("Узел не найден!");
                    else
                    node.print("",node.getKey()+"",true);
                    break;
                case 3:
                    System.out.println("Ключ:");
                    bst.leftDeletion(scan.nextInt());
                    break;
                case 4:
                    System.out.println("Ключ:");
                    bst.rightDeletion(scan.nextInt());
                    break;
                case 5:
                   System.out.println( bst.straightTraversal(bst.getRoot()));
                    break;
                case 6:
                    System.out.println( bst.reverseTraversal(bst.getRoot()));
                    break;
                case 7:
                    System.out.println( bst.symmetricalTraversal(bst.getRoot()));
                    break;
                case 8:
                    System.out.println("Ключ:");
                    int depth=bst.depth(scan.nextInt());
                    if(depth==-1)
                    System.out.println("Узел не найден!");
                    else System.out.println(depth);
                    break;
                case 9:
                    System.out.println("Ключ:");
                    int height= bst.height(scan.nextInt());
                    if(height==-1)
                        System.out.println("Узел не найден!");
                    else System.out.println(height);
                    break;
                case 10:
                    System.out.println("Ключ:");
                    int level=bst.level(scan.nextInt());
                    if(level==-1)
                        System.out.println("Узел не найден!");
                    else System.out.println(level);
                    break;
                case 11:
                    bst.print();
                    break;

            }

        }

    }

}
