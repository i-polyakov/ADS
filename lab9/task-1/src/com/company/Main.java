package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
      Scanner  scan = new Scanner(System.in);
      BinomialHeap H=new BinomialHeap();
        while (true) {
            System.out.println("0. Выход");
            System.out.println("1. Добавление узла");
            System.out.println("2. Удаление узла");
            System.out.println("3. Вывод кучи");
            switch (scan.nextInt()){
                case 0:
                    System.exit(1);
                    break;
                case 1:
                    System.out.println("Ключ:");
                    H.insert(scan.nextInt());
                    break;
                case 2:
                    BinomialHeap.Node node =H.extractMin();
                    if (node!=null) {
                        System.out.println("Удален элемент:");
                        System.out.println(node.getKey());
                    }
                    else  System.out.println("Куча пустая!");
                    break;
                case 3:
                        H.toString();
                    break;

            }

        }
    }


}