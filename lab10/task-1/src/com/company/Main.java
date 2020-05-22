package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
      Scanner scan=new Scanner(System.in);

      Graph graph  =new Graph(0);
      int n=0;
      while (true){
        System.out.println("0. Выход");
        System.out.println("1. Задать новую сеть дорог");
        System.out.println("2. Исользовать готовую сеть");
        System.out.println("3. Кратчайший по вемени маршрут");

        switch (scan.nextInt()){
          case 0:
            System.exit(1);
            break;

          case 1:

            System.out.print("Кол-во перекрестков:");
             n=scan.nextInt();
            System.out.print("Кол-во дорог:");
            int k=scan.nextInt();
            if (n<=0) {
              System.out.println("Не положительное число перекрестков!");
              break;
            }
            if (k<0) {
              System.out.println("Отрицательное число дорог!");
              break;
            }
            if(k>(n*(n-1))/2) {
              System.out.println("Число дорог больше возможных дорог!");
              break;
            }

            graph=new Graph(n);

            //Добавление дорог
            System.out.println("\nДобавление дорог:");
            for (int i = 0; i < k; i++) {
              int buff=0;
              do {
                System.out.print("Первый перекресток:");
                int start = scan.nextInt();
                System.out.print("Второй перекресток:");
                int end = scan.nextInt();
                System.out.print("Время на дорогу:");
                int time = scan.nextInt();

                buff= graph.addLink(start,end,time);

              }while (buff==-1);

              System.out.println("Дорога добавлена!\n");
            }
            graph.addDegrees();//учет числа пересекающихся в этом перекрестке дорог
            break;

          case 2:
            n=9;
            //сеть построена по картинке, без красных ребер
            graph=new Graph(n);
            graph.addLink(1,2,10);
            graph.addLink(1,3,7);
            graph.addLink(2,4,15);
            graph.addLink(2,5,10);
            graph.addLink(3,5,15);
            graph.addLink(3,6,7);
            graph.addLink(4,7,9);
            graph.addLink(4,8,13);
            graph.addLink(5,7,12);
            graph.addLink(5,8,8);
            graph.addLink(6,8,15);
            graph.addLink(7,9,9);
            graph.addLink(8,9,5);
            System.out.println("Сеть дорог в виде матрицы смежности:\n");
            graph.print();
            graph.addDegrees();//учет числа пересекающихся в этом перекрестке дорог
            break;

          case 3:  //Кратчайший по времени маршрут
            System.out.print("Начало маршрута:");
            int start=scan.nextInt();
            System.out.print("Конец:");
            int end=scan.nextInt();
            if (start<1||start>n) {
              System.out.println("Перекрестка "+start+" нет!");
              break;
            }
            if (end<1||end>n) {
              System.out.println("Перекрестка "+end+" нет!");
              break;
            }
            if (start==end){
              System.out.println("Указан один и тот же перекресток!");
              break;
            }
            graph.shortWay(start-1,end-1);
            break;
        }
      }
    }
}
