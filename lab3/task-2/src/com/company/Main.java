package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.println("Введите кол-во гвоздей: ");
        int n=in.nextInt();
        if(n<=0) {
            System.out.println("Не положительное кол-во гвоздей!");
            System.exit(0);
        }

        double[] x = new double[n];//координаты гвоздей
        System.out.println("Кординаты "+1+" гвоздя:");
        x[0]=in.nextDouble();

        for (int i = 1; i < n; i++) {
            System.out.println("Кординаты "+(i+1)+" гвоздя:");
            if((x[i]=in.nextDouble())<=x[i-1]) {
                System.out.println("Координаты гвоздя менньше или равны координатам предыдущего гвоздя!");
                System.exit(0);
            }
        }

        System.out.println("Длина верёвочек: "+lengthOfRopes(x));

    }


    static double lengthOfRopes(double[] x) {
        int length=x.length;
        double[] minLength = new double[length];//мин длина веревок
        if(length<=1)
            return 0;
        else if(length==2)
            return x[1] - x[0];

        minLength[0] = x[1] - x[0];
        minLength[1] = x[2] - x[0];
        for (int i = 2; i < length; i++)
            minLength[i] = Math.min(minLength[i - 1], minLength[i - 2]) + x[i] - x[i - 1];

        return minLength[length - 1];
    }
}


