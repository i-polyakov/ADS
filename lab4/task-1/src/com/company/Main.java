package com.company;

import java.util.Scanner;

public class Main {
    static int countSwap=0;
    static int countCompare=0;
    static long start;
    static long finish;
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        System.out.println("Array size: ");
        int n = in.nextInt();
            if (n<=0) {
                System.out.println("Negative array size!");
                System.exit(0);
            }

        int[] arr=new int[n];//Исходный массив

        long[][] sumTime = new long[5][3];//для подсчёта среднего времени
        int[][] sumSwapAndComp=new int[5][2];//для подсчёта среднего кол-ва свапов сравнений
        int countStart=1000;//Кол-во экспериментов
        for (int j = 0; j < countStart; j++) {
            //заполнение  массива
            for (int i = 0; i < n; i++)
                arr[i]= (int) (Math.random()*1000);


            //подсчёт числа свапов и сравнений
            System.out.println("arr "+j+" :");
            int[][] randSwapAndCompare =countSwapAndCompare(arr);
            for (int i = 0; i < 5; i++)
                for (int k = 0; k <2 ; k++)
                    sumSwapAndComp[i][k]+=randSwapAndCompare[i][k];

            //опрделение времени работы
            System.out.println("arr "+j+" :");
            long[][] time =getSortingTime(arr);
            for (int i = 0; i < 5; i++)
                for (int k = 0; k <3 ; k++)
                    sumTime[i][k]+=time[i][k];
        }
        //вывод результа экспериментов
        System.out.println("Rand swap and comp: ");
        for (int i = 0; i < 5; i++) {
            System.out.print((i+1)+" : ");
            for (int k = 0; k < 2; k++)
                System.out.print(sumSwapAndComp[i][k] / countStart + "  ");
            System.out.println();
        }
        System.out.println("Increase decrease random: ");
        for (int i = 0; i < 5; i++) {
            System.out.print((i+1)+": ");
            for (int k = 0; k < 3; k++)
                System.out.print(sumTime[i][k] / countStart / Math.pow(10, 6) + "  ");
            System.out.println();
        }
    }
    //Сортировки
    static void selectionSort(int arr[]){
        int n = arr.length;
         countSwap=0;
         countCompare=0;

        for (int i = 0; i < n-1; i++)
        {
            int minIndex = i;
            for (int j = i+1; j < n; j++) {//Нахождение мин элемента в неотсортированном массиве

                if (arr[j] < arr[minIndex])
                    minIndex = j;

                countCompare++;
            }
            swap(arr,minIndex,i);
                countSwap++;
        }

    }
    static void insertionSort(int arr[]){
        int n = arr.length;
        countSwap=0;
        countCompare=0;
        boolean check=false;//будет ли swap
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {//смещение элементов больших чем ключ на позицию вперед
                check=true;
                countCompare++;
                arr[j + 1] = arr[j];
                j--;
            }
            if(j >= 0)
                countCompare++;
            if(check){
                countSwap++;
                check=false;
            }
            arr[j + 1] = key;
        }

    }
    static void insertionSort(int arr[],int left,int right){

        boolean check=false;//будет ли swap
        for (int i = left + 1; i <= right; i++)
        {
            int temp = arr[i];
            int j = i - 1;
            while (j >= left && arr[j] > temp)
            {
                check=true;
                countCompare++;
                arr[j + 1] = arr[j];
                j--;
            }

            if(j >= left)
                countCompare++;
            if(check){
                countSwap++;
                check=false;
            }
            arr[j + 1] = temp;
        }


    }
    static void shakerSort(int arr[]) {
        countSwap=0;
        countCompare=0;
        int left = 0;
        int right = arr.length-1;

        do {
            for (int i = left; i < right; ++i) {
                if (arr[i] > arr[i + 1]) {
                    countSwap++;
                    swap(arr,i,i+1);
                }
                countCompare++;
            }
            right--;

            for (int i = right; i > left; i--) {
                if (arr[i] < arr[i - 1]) {
                    countSwap++;
                    swap(arr,i,i-1);
                }
                countCompare++;
            }
            left++;

        }while (left<right);
    }
    static void quickSort(int arr[],int left,int right){
        int i=left;
        int j=right;
        int x=arr[(left+right)/2];
        do{
            while (arr[i]<x) {
                countCompare++;
                i++;
            }
            countCompare++;
            while (arr[j]>x) {
                countCompare++;
                j--;
            }
            countCompare++;
            if(i<=j) {
                countSwap++;
                swap(arr,i,j);

                i++;
                j--;
            }
        }while (i<=j);
        if(left<j)
            quickSort(arr,left,j);
        if (i<right)
            quickSort(arr,i,right);

    }
    static void timSort(int arr[]){
        countSwap=0;
        countCompare=0;
        int n= arr.length;

        int RUN = 32;

        // Сортировка отдельных подмассивов размера RUN
        for (int i = 0; i < n; i += RUN)
            insertionSort(arr, i, Math.min((i + 31), (n - 1)));


        //Слияние начиная с Run затем 64 128...
        for (int size = RUN; size < n; size = 2 * size)
        {
            for (int left = 0; left < n; left += 2 * size)
            {
                int right = Math.min((left + 2 * size - 1), (n - 1));
                int mid =  Math.min((left + size - 1), (n - 1));

                merge(arr, left, mid, right);
            }
        }


    }

    static void merge(int[] arr, int l,int m, int r) {

        int leftSize = m-l+1;
        int rightSize = r-m;

        int[] left = new int[leftSize];
        int[] right = new int[rightSize];

        for (int i = 0; i < leftSize; i++)
            left[i] = arr[l + i];

        for (int i = 0; i < rightSize; i++)
            right[i] = arr[m+1+i];

        int i = 0;
        int j = 0;
        int k = l;

        while (i < leftSize && j < rightSize)
        {
            if (left[i] <= right[j])
            {
                arr[k] = left[i];
                i++;
            }
            else
            {
                arr[k] = right[j];
                j++;
            }
            countCompare++;
            k++;
        }

        // копирование оставшихся элементов left, если есть
        while (i < leftSize)
        {
            arr[k] = left[i];
            k++;
            i++;
        }
        // копирование оставшихся элементов right, если есть
        while (j < rightSize)
        {
            arr[k] = right[j];
            k++;
            j++;
        }
    }

    static void swap( int arr[],int i,int j){//swap
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

    }
    static int[] getReversArray(int[] arr) {
        int j = 0;
        int[] res = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--, j++)
            res[j] = arr[i];

        return res;
    }
    //подсчет кол-ва свапов и сравнений
   private static int[][] countSwapAndCompare(int[] arr){
        int[][] randSwapAndCompare = new int[5][2];//для хранения кол-ва свапов и сравнений в массиве ранд чисел
       int[]tempArr;

       switch (1) {
           case 1: {
               System.out.println("\nselectionSort:");

               tempArr = arr.clone();
               selectionSort(tempArr);//сортировка входных данных по возрастанию

               start = System.nanoTime();
               selectionSort(tempArr);
               finish = System.nanoTime();

               System.out.print("arr increase ");
               System.out.println("countSwap: "+countSwap+"  countCompare: "+countCompare);


               tempArr = arr.clone();
               selectionSort(tempArr);
               tempArr = getReversArray(tempArr);//сортировка входных данных по убыванию

               start = System.nanoTime();
               selectionSort(tempArr);
               finish = System.nanoTime();

               System.out.print("arr decrease ");
               System.out.println("countSwap: "+countSwap+"  countCompare: "+countCompare);

               tempArr = arr.clone();

               start = System.nanoTime();
               selectionSort(tempArr);
               finish = System.nanoTime();


               randSwapAndCompare[0][0]=countSwap;
               randSwapAndCompare[0][1]=countCompare;
               System.out.print("arr random   ");
               System.out.println("countSwap: "+countSwap+"  countCompare: "+countCompare);
           }
           case 2:{
               System.out.println("\ninsertionSort:");

               tempArr = arr.clone();
               insertionSort(tempArr);

               start = System.nanoTime();
               insertionSort(tempArr);
               finish = System.nanoTime();

               System.out.print("arr increase ");
               System.out.println("countSwap: "+countSwap+"  countCompare: "+countCompare);


               tempArr = arr.clone();
               insertionSort(tempArr);
               tempArr = getReversArray(tempArr);

               start = System.nanoTime();
               insertionSort(tempArr);
               finish = System.nanoTime();

               System.out.print("arr decrease ");
               System.out.println("countSwap: "+countSwap+"  countCompare: "+countCompare);

               tempArr = arr.clone();

               start = System.nanoTime();
               insertionSort(tempArr);
               finish = System.nanoTime();

               randSwapAndCompare[1][0]=countSwap;
               randSwapAndCompare[1][1]=countCompare;
               System.out.print("arr random   ");
               System.out.println("countSwap: "+countSwap+"  countCompare: "+countCompare);
           }
           case 3:{
               System.out.println("\nshakerSort:");

               tempArr = arr.clone();
               shakerSort(tempArr);

               start = System.nanoTime();
               shakerSort(tempArr);
               finish = System.nanoTime();

               System.out.print("arr increase ");
               System.out.println("countSwap: "+countSwap+"  countCompare: "+countCompare);


               tempArr = arr.clone();
               shakerSort(tempArr);
               tempArr = getReversArray(tempArr);

               start = System.nanoTime();
               shakerSort(tempArr);
               finish = System.nanoTime();

               System.out.print("arr decrease ");
               System.out.println("countSwap: "+countSwap+"  countCompare: "+countCompare);

               tempArr = arr.clone();

               start = System.nanoTime();
               shakerSort(tempArr);
               finish = System.nanoTime();

               randSwapAndCompare[2][0]=countSwap;
               randSwapAndCompare[2][1]=countCompare;
               System.out.print("arr random   ");
               System.out.println("countSwap: "+countSwap+"  countCompare: "+countCompare);
           }
           case 4:{
               countSwap=0;
               countCompare=0;
               System.out.println("\nquickSort:");

               tempArr = arr.clone();
               int n=arr.length-1;
               quickSort(tempArr,0,n);

               countSwap=0;
               countCompare=0;

               start = System.nanoTime();
               quickSort(tempArr,0,n);
               finish = System.nanoTime();

               System.out.print("arr increase ");
               System.out.println("countSwap: "+countSwap+"  countCompare: "+countCompare);

               countSwap=0;
               countCompare=0;

               tempArr = arr.clone();
               quickSort(tempArr,0,n);
               tempArr = getReversArray(tempArr);

               countSwap=0;
               countCompare=0;

               start = System.nanoTime();
               quickSort(tempArr,0,n);
               finish = System.nanoTime();

               System.out.print("arr decrease ");
               System.out.println("countSwap: "+countSwap+"  countCompare: "+countCompare);

               countSwap=0;
               countCompare=0;

               tempArr = arr.clone();

               start = System.nanoTime();
               quickSort(tempArr,0,n);
               finish = System.nanoTime();

               randSwapAndCompare[3][0]=countSwap;
               randSwapAndCompare[3][1]=countCompare;
               System.out.print("arr random   ");
               System.out.println("countSwap: "+countSwap+"  countCompare: "+countCompare);
           }
           case 5:{
               System.out.println("\ntimSort:");

               tempArr = arr.clone();
               timSort(tempArr);

               start = System.nanoTime();
               timSort(tempArr);
               finish = System.nanoTime();

               System.out.print("arr increase ");
               System.out.println("countSwap: "+countSwap+"  countCompare: "+countCompare);


               tempArr = arr.clone();
               timSort(tempArr);
               tempArr = getReversArray(tempArr);

               start = System.nanoTime();
               timSort(tempArr);
               finish = System.nanoTime();

               System.out.print("arr decrease ");
               System.out.println("countSwap: "+countSwap+"  countCompare: "+countCompare);

               tempArr = arr.clone();

               start = System.nanoTime();
               timSort(tempArr);
               finish = System.nanoTime();

               randSwapAndCompare[4][0]=countSwap;
               randSwapAndCompare[4][1]=countCompare;
               System.out.print("arr random   ");
               System.out.println("countSwap: "+countSwap+"  countCompare: "+countCompare);
           }
       }
       return randSwapAndCompare;

   }
    //время сортировки
   private static long[][] getSortingTime(int[] arr){
       long[][]time=new long[5][3];
       int[]tempArr;

       switch (1) {
           case 1: {
               System.out.println("\nselectionSort:");

               tempArr = arr.clone();
               selectionSort(tempArr);//сортировка входных данных по возрастанию
               //printArray(tempArr1);
               start = System.nanoTime();
               selectionSort(tempArr);
               finish = System.nanoTime();
               //printArray(tempArr1);

               time[0][0]=finish - start;
               System.out.print("arr increase ");
               System.out.println("Time: " + (finish - start) / Math.pow(10, 6) + "ms");


               tempArr = arr.clone();
               selectionSort(tempArr);
               tempArr = getReversArray(tempArr);//сортировка входных данных по убыванию
               //printArray(tempArr1);
               start = System.nanoTime();
               selectionSort(tempArr);
               finish = System.nanoTime();
               //printArray(tempArr1);
               time[0][1]=finish - start;
               System.out.print("arr decrease ");
               System.out.println("Time: " + (finish - start) / Math.pow(10, 6) + "ms");

               tempArr = arr.clone();
               //printArray(tempArr1);
               start = System.nanoTime();
               selectionSort(tempArr);
               finish = System.nanoTime();
               //printArray(tempArr1);
               time[0][2]=finish - start;
               System.out.print("arr random   ");
               System.out.println("Time: " + (finish - start) / Math.pow(10, 6) + "ms");
           }
           case 2:{
               System.out.println("\ninsertionSort:");

               tempArr = arr.clone();
               insertionSort(tempArr);
               //printArray(tempArr1);
               start = System.nanoTime();
               insertionSort(tempArr);
               finish = System.nanoTime();
               //printArray(tempArr1);
               time[1][0]=finish - start;
               System.out.print("arr increase ");
               System.out.println("Time: " + (finish - start) / Math.pow(10, 6) + "ms");


               tempArr = arr.clone();
               insertionSort(tempArr);
               tempArr = getReversArray(tempArr);
               //printArray(tempArr1);
               start = System.nanoTime();
               insertionSort(tempArr);
               finish = System.nanoTime();
               //printArray(tempArr1);
               time[1][1]=finish - start;
               System.out.print("arr decrease ");
               System.out.println("Time: " + (finish - start) / Math.pow(10, 6) + "ms");

               tempArr = arr.clone();
               //printArray(tempArr1);
               start = System.nanoTime();
               insertionSort(tempArr);
               finish = System.nanoTime();
               //printArray(tempArr1);
               time[1][2]=finish - start;
               System.out.print("arr random   ");
               System.out.println("Time: " + (finish - start) / Math.pow(10, 6) + "ms");
           }
           case 3:{
           System.out.println("\nshakerSort:");

           tempArr = arr.clone();
           shakerSort(tempArr);
           //printArray(tempArr1);
           start = System.nanoTime();
           shakerSort(tempArr);
           finish = System.nanoTime();
           //printArray(tempArr1);
           time[2][0]=finish - start;
           System.out.print("arr increase ");
           System.out.println("Time: " + (finish - start) / Math.pow(10, 6) + "ms");


           tempArr = arr.clone();
           shakerSort(tempArr);
           tempArr = getReversArray(tempArr);
           //printArray(tempArr1);
           start = System.nanoTime();
           shakerSort(tempArr);
           finish = System.nanoTime();
           //printArray(tempArr1);
           time[2][1]=finish - start;
           System.out.print("arr decrease ");
           System.out.println("Time: " + (finish - start) / Math.pow(10, 6) + "ms");

           tempArr = arr.clone();
           //printArray(tempArr1);
           start = System.nanoTime();
           shakerSort(tempArr);
           finish = System.nanoTime();
           //printArray(tempArr1);
           time[2][2]=finish - start;
           System.out.print("arr random   ");
           System.out.println("Time: " + (finish - start) / Math.pow(10, 6) + "ms");
       }
           case 4:{
           System.out.println("\nquickSort:");

           tempArr = arr.clone();
           int n=arr.length-1;
           quickSort(tempArr,0,n);
           //printArray(tempArr1);
           start = System.nanoTime();
           quickSort(tempArr,0,n);
           finish = System.nanoTime();
           //printArray(tempArr1);
           time[3][0]=finish - start;
           System.out.print("arr increase ");
           System.out.println("Time: " + (finish - start) / Math.pow(10, 6) + "ms");


           tempArr = arr.clone();
           quickSort(tempArr,0,n);
           tempArr = getReversArray(tempArr);
           //printArray(tempArr1);
           start = System.nanoTime();
           quickSort(tempArr,0,n);
           finish = System.nanoTime();
           //printArray(tempArr1);
           time[3][1]=finish - start;
           System.out.print("arr decrease ");
           System.out.println("Time: " + (finish - start) / Math.pow(10, 6) + "ms");

           tempArr = arr.clone();
           //printArray(tempArr1);
           start = System.nanoTime();
           quickSort(tempArr,0,n);
           finish = System.nanoTime();
           //printArray(tempArr1);
           time[3][2]=finish - start;
           System.out.print("arr random   ");
           System.out.println("Time: " + (finish - start) / Math.pow(10, 6) + "ms");
       }
           case 5:{
           System.out.println("\ntimSort:");

           tempArr = arr.clone();
           timSort(tempArr);
           //printArray(tempArr1);
           start = System.nanoTime();
           timSort(tempArr);
           finish = System.nanoTime();
           //printArray(tempArr1);
           time[4][0]=finish - start;
           System.out.print("arr increase ");
           System.out.println("Time: " + (finish - start) / Math.pow(10, 6) + "ms");


           tempArr = arr.clone();
           timSort(tempArr);
           tempArr = getReversArray(tempArr);
           //printArray(tempArr1);
           start = System.nanoTime();
           timSort(tempArr);
           finish = System.nanoTime();
           //printArray(tempArr1);
           time[4][1]=finish - start;
           System.out.print("arr decrease ");
           System.out.println("Time: " + (finish - start) / Math.pow(10, 6) + "ms");

           tempArr = arr.clone();
           //printArray(tempArr1);
           start = System.nanoTime();
           timSort(tempArr);
           finish = System.nanoTime();
           // printArray(tempArr1);
           time[4][2]=finish - start;
           System.out.print("arr random   ");
           System.out.println("Time: " + (finish - start) / Math.pow(10, 6) + "ms");
       }
   }
        return time;
    }
    //Вывод массива
    static void printArray(int arr[])
    {
        System.out.print("arr: ");
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i]+" ");
        System.out.println();
    }
}
