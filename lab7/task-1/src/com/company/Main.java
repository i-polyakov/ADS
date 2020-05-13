package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static long start=0;
    static long finish;
    public static void main(String[] args) {

        int n=100000;
        HashTable hashTable=new HashTable((int) (n*1.2));
        Scanner scan=new Scanner(System.in);
        for (int j = 0; j <10; j++) {
         hashTable= new HashTable((int) (n*1.2));

            for (int i = 1; i <= 100; i++) {
                if (i == 1)
                    start = System.nanoTime();
                hashTable.insert(i, String.valueOf(i));

                if (i == 100) {
                    finish = System.nanoTime();
                    System.out.println("add first 100: " + (finish - start) / Math.pow(10, 6));
                }
            }
                for (int i = 101; i <= n; i++) {
                    if (i == n - 99)
                        start = System.nanoTime();
                    hashTable.insert(i, String.valueOf(i));

                    if (i == n) {
                        finish = System.nanoTime();
                        System.out.println("add last 100: " + (finish - start) / Math.pow(10, 6));
                    }
                }


        }
        System.out.println("Введите ключ");
        int buff=scan.nextInt();
        start=System.nanoTime();
        System.out.println(hashTable.find(buff));
        finish=System.nanoTime();
        System.out.println("search time: "+(finish-start)/Math.pow(10,6));


    }
}
