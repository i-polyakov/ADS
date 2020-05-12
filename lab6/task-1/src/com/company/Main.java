package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);

        int n;
        do {
            System.out.println("0: Выйти");
            System.out.println("1: Стек");
            System.out.println("2: Очередь");
            System.out.println("3: Дек");
            switch (scan.nextInt()) {

                case 0:
                    System.exit(1);
                    break;
                case 1:
                    int i = 0;
                    System.out.println("Размер стэка:");
                     n=scan.nextInt();
                    if (n<0) {
                        System.out.println("отриц размер!");
                      break;
                    }
                    Stack stack = new Stack(n);
                    do {
                        System.out.println("0: Выйти");
                        System.out.println("1: Добавить элемент");
                        System.out.println("2: Удалить элемент");
                        System.out.println("3: Вывести верхний элемент");
                        System.out.println("4: Вывод всех элементов");

                        switch (scan.nextInt()) {
                            case 0:
                                i = 1;
                                break;
                            case 1:
                                stack.Push(scan.nextInt());
                                break;
                            case 2:
                                System.out.println(stack.Pop());
                                break;
                            case 3:
                                System.out.println(stack.Peek());
                                break;
                            case 4:
                                while (!stack.isEmpty()) {
                                    System.out.print(stack.Peek() + " ");
                                    stack.Pop();
                                }
                                System.out.println();
                                break;
                        }
                    }
                    while (i == 0);
                    break;
                case 2:
                    i = 0;
                    System.out.println("Размер очереди:");
                     n=scan.nextInt();
                    if (n<0) {
                        System.out.println("отриц размер!");
                        break;
                    }
                    Queue queue = new Queue(n);
                    do {
                        System.out.println("0: Выйти");
                        System.out.println("1: Добавить элемент");
                        System.out.println("2: Удалить элемент");
                        System.out.println("3: Вывести первый элемент");
                        System.out.println("4: Вывести последний элемент");
                        System.out.println("5: Вывод всех элементов");


                        switch (scan.nextInt()) {
                            case 0:
                                i = 1;
                                break;
                            case 1:
                                queue.Enqueue(scan.nextInt());
                                break;
                            case 2:
                                System.out.println(queue.Dequeue());
                                break;
                            case 3:
                                System.out.println(queue.getFront());
                                break;
                            case 4:
                                System.out.println(queue.getBack());
                                break;
                            case 5:
                                while (!queue.isEmpty()) {
                                    System.out.print(queue.getFront() + " ");
                                    queue.Dequeue();
                                }
                                System.out.println();
                                break;
                        }
                    }
                    while (i == 0);
                case 3:
                    i = 0;
                    System.out.println("Размер дека:");
                    n=scan.nextInt();
                    if (n<0) {
                        System.out.println("отриц размер!");
                        break;
                    }
                    Deque deque = new Deque(n);
                    do {
                        System.out.println("0: Выйти");
                        System.out.println("1: Добавить элемент в начало");
                        System.out.println("2: Добавить элемент в конец");
                        System.out.println("3: Удалить первый элемент");
                        System.out.println("4: Удалить последний элемент");
                        System.out.println("5: Вывести первый элемент");
                        System.out.println("6: Вывести последний элемент");
                        System.out.println("7: Вывод всех элементов");

                        switch (scan.nextInt()) {
                            case 0:
                                i = 1;
                                break;
                            case 1:
                                deque.EnqueueFirst(scan.nextInt());
                                break;
                            case 2:
                                deque.EnqueueLast(scan.nextInt());
                                break;
                            case 3:
                                deque.DequeueFirst();
                                break;
                            case 4:
                                deque.DequeueLast();
                                break;
                            case 5:
                                System.out.println(deque.getFront());
                                break;
                            case 6:
                                System.out.println(deque.getBack());
                                break;
                            case 7:
                                while (!deque.isEmpty()) {
                                    System.out.print(deque.getFront() + " ");
                                    deque.DequeueFirst();
                                }
                                System.out.println();
                                break;
                        }
                    }
                    while (i == 0);
                    break;

            }
        }while (true);


    }
}
