package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.println("Введите количество дней: ");
        int day =in.nextInt();
        if(day<=0) {
            System.out.println("Не положительное кол-во дней!");
            System.exit(0);
        }

        double[] stockPrice = new double[day];//Цены акций

        for (int i = 0; i < day; i++) {
            System.out.println("Цена акций в "+(i)+" день:");
            stockPrice[i] = in.nextDouble();/*(int)(Math.random()*100)*/;
        }
        //stockPrice = new double[]{100D, 113D, 110D, 85D, 105D, 102D, 86D, 63D, 81D, 101D, 94D, 106D, 101D, 79D, 94D, 90D, 97D};

        double[] priceChange = new double[stockPrice.length-1];//Изменение
        for (int i=0; i<priceChange.length; i++) {
            priceChange[i] = stockPrice[i+1] - stockPrice [i];
        }

        System.out.print("\nДень     :");
        for (int i=0;i<day;i++)
            System.out.printf("%9s",i+"|");

        System.out.print("\nЦена     :");
        for (int i=0;i<day;i++)
            System.out.printf("%8.2f%s",stockPrice[i],"|");

        System.out.print("\nИзменение:         ");
        for (int i=0;i<day-1;i++)
            System.out.printf("%8.2f%s",priceChange[i],"|");
        System.out.println();

        int left = 0, right = priceChange.length-1;

        System.out.println("Купить акции на "+(int)(maxSubArray(priceChange, left, right))[1]+" день");
        System.out.println("Продать акции на "+(int)((maxSubArray(priceChange, left, right))[2]+1)+" день");
        System.out.println("Прибыль: "+ (maxSubArray(priceChange, left, right))[0]);


    }


    static double[] maxSubArray(double[] arr, int l, int r) {

        if (l == r) // 1 элемент
            return  new double[]{arr[l], (double) l, (double) r};

        int m = (l + r) / 2; //Центр

        //поиск макс подмассива в левой части
        Double lMax = 0D, sum = 0D;
        int lIndex=m;
        for (int i = m; i >= l; i--) {
            sum += arr[i];
            if(sum>lMax){
                lMax=sum;
                lIndex = i;
            }
        }

        //поиск макс подмассива в правой части
        Double rMax = 0D;
        int rIndex=m+1;
        sum = 0D;
        for (int i = m + 1; i <= r ; i++) {
            sum += arr[i];

            if(sum>rMax) {
                rMax=sum;
                rIndex = i;
            }
        }


        if(maxSubArray( arr,l, m)[0]>maxSubArray(arr,m + 1, r)[0]&&maxSubArray( arr,l, m)[0]>lMax + rMax)//макс подмассив слева
            return maxSubArray( arr,l, m);
        else if(maxSubArray(arr,m + 1, r)[0]>maxSubArray( arr,l, m)[0]&&maxSubArray(arr,m + 1, r)[0]>lMax+rMax)//макс подмассив справа
            return maxSubArray(arr,m + 1, r);
        else
            return new double[]{lMax+rMax, (double) lIndex, (double) rIndex};//макс подмассив между двух частей исходного массива
    }





}


