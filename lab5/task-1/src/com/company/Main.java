package com.company;

import java.io.*;
import java.util.Scanner;




public class Main {

    static int countCalls= 0;
    static int countCompare = 0;
    static long start;
    static long finish;
    static int n = 0;

    public static void main(String[] args) throws Exception {

        Scanner in=new Scanner(System.in);
        System.out.println("Array size: ");
         n = in.nextInt();
        if (n<=0) {
            System.out.println("Negative array size!");
            System.exit(0);
        }
        int[] arr=new int[n];//Исходный массив
        //заполнение  массива
     /*   FileWriter fw = new FileWriter("input.txt");
        for (int i = 0; i < n; i++) {
            arr[i] = (int) (Math.random() * 1000);

            fw.write(arr[i]+" ");
        }
        printArray(arr);
        fw.close();
        long start=System.nanoTime();
        timSort(arr);
        long finish=System.nanoTime();
        printArray(arr);
        System.out.println((finish-start)/Math.pow(10,6));
         start=System.nanoTime();
        Split();
        threeWayMerge();
         finish=System.nanoTime();
        System.out.println((finish-start)/Math.pow(10,6));*/

        long[] sumTime = new long[3];//для подсчёта среднего времени
        int[][] sumCompareAndCalls=new int[2][3];//для подсчёта среднего кол-ва свапов сравнений
        int countStart=100;//Кол-во экспериментов

        for (int j = 0; j < countStart; j++) {
            //заполнение  массива
            for (int i = 0; i < n; i++)
                arr[i] = (int) (Math.random() * 1000);

         /* //подсчёт числа свапов и сравнений
            System.out.println("arr "+j+" :");
            int[][] CompareAndCalls =countCompareAndCalls(arr);

                for (int k = 0; k <2 ; k++)
                    for (int i=0; i<3;i++)
                    sumCompareAndCalls[k][i]+=CompareAndCalls[k][i];*/
            //опрделение времени работы
                System.out.println("arr " + j + " :");
                long[] time = getSortingTime(arr);
            if (j!=0)
                for (int k = 0; k < 3; k++)
                    sumTime[k] += time[k];


        }
       /* //вывод результа экспериментов
        System.out.println(" Calls And Compare: ");
        for (int k = 0; k < 2; k++)
        for (int i = 0; i < 3; i++) {
            System.out.print((i+1)+" : ");

                System.out.print(sumCompareAndCalls[k][i] / countStart + "  ");
            System.out.println();
        }*/
        System.out.println("Increase decrease random: ");

        for (int k = 0; k < 3; k++)
            System.out.print(sumTime[k] / (countStart-1) / Math.pow(10, 6) + "  ");
        System.out.println();



    }

    //Сортировки
    static void threeWayMerge() throws Exception {
//        countCalls=0;
//        countCompare=0;
        //Начальное разбиение исходных данных по 3-м файлом
        Split();
        //Начальные файлы на ввод
        String F1 = "F1.txt";
        String F2 = "F2.txt";
        String F3 = "F3.txt";
        //Начальные файлы на вывод сливаемых отрезков
        String F4 = "F4.txt";
        String F5 = "F5.txt";
        String F6 = "F6.txt";

        //Переменные для считывания данных
        Scanner scan1 = new Scanner(new FileReader(F1));
        Scanner scan2 = new Scanner(new FileReader(F2));
        Scanner scan3 = new Scanner(new FileReader(F3));
//        countCalls+=3;

        //Переменная для записи в файлы
        FileWriter fw =new FileWriter(F4);
        fw.close();
//        countCalls++;

        //Переменная для удаления содержимого файлов
        FileWriter clearF=new FileWriter(F5);
        clearF.close();
        clearF=new FileWriter(F6);
        clearF.close();
//        countCalls+=2;

        //Был ли достигнут конец сегмента(отрезка)
        boolean f1EndSegment = false;
        boolean f2EndSegment = false;
        boolean f3EndSegment = false;

        //Ключ сегмента(отрезка)
        int a1 = 0;
        int a2 = 0;
        int a3 = 0;

        //для чередования файла слияния
        int k = 0;
        //Поменять ли местами файлы ввода и вывода
        boolean swapFile=true;
        //Есть ли ещё записи в файлах ввода
        boolean hasNext;
        //для определения конца сегмента(отрезка)
        String str;
        //Пока не останется один отсортированный файл(два других пустые)
        while (scan1.hasNext() &&( scan2.hasNext() || scan3.hasNext())||scan2.hasNext()&&scan3.hasNext()) {

            hasNext=true;
            k=0;
            //пока есть записи в файлах
            while (hasNext) {
                //Определение файла для вывода сливаемых отрезков
                if (k == 0) {
                    fw = new FileWriter(F4, true);
//                    countCalls++;
                    k = 1;
                } else if (k == 1) {
                    fw = new FileWriter(F5, true);
//                    countCalls++;
                    k = 2;
                } else {
                    fw = new FileWriter(F6, true);
//                    countCalls++;
                    k = 0;
                }
                //Ключи сегментов
                if (scan1.hasNext()) {
                    f1EndSegment = false;
                    a1 = Integer.parseInt(scan1.next());
//                    countCalls++;
                } else f1EndSegment = true;

                if (scan2.hasNext()) {
                    a2 = Integer.parseInt(scan2.next());
//                    countCalls++;
                    f2EndSegment = false;
                } else f2EndSegment = true;

                if (scan3.hasNext()) {
                    a3 = Integer.parseInt(scan3.next());
//                    countCalls++;
                    f3EndSegment = false;
                } else f3EndSegment = true;

                //Пока не достигнут конец всех сегментов
                while (!f1EndSegment || !f2EndSegment || !f3EndSegment) {
                    //если не достигнут конец 1 отрезка
                    if (!f1EndSegment){
                        if(!f2EndSegment){
                            if(!f3EndSegment){ //если не достигнут конец всех 3 сегентов
                                if (a1 <= a2 &&a1 <= a3 ) {//первый ключ меньше 2-го и 3-го
//                                    countCompare+=2;
                                    fw.write(a1 + " ");//записываем его в файл
//                                    countCalls++;
                                    if (scan1.hasNext()) {
//                                            countCalls++;
                                        str = scan1.next();//считывем след ключ для этого сегмента
                                        if (str.equals("|"))//проверка на конец сегмента
                                            f1EndSegment = true;
                                        else
                                            a1 = Integer.parseInt(str);
                                    } else f1EndSegment = true;

                                }
                                else if ( a2<=a1&&a2 <= a3) {
//                                    countCompare++;
                                    fw.write(a2 + " ");
//                                    countCalls++;
                                    if (scan2.hasNext()) {
//                                        countCalls++;
                                        str = scan2.next();
                                        if (str.equals("|")) {
                                            f2EndSegment = true;
                                        } else
                                            a2 = Integer.parseInt(str);
                                    } else f2EndSegment = true;
                                }
                                else {
//                                    countCompare+=2;
                                    fw.write(a3 + " ");
//                                    countCalls++;
                                    if (scan3.hasNext()) {
//                                        countCalls++;
                                        str = scan3.next();
                                        if (str.equals("|"))
                                            f3EndSegment = true;
                                        else
                                            a3 = Integer.parseInt(str);
                                    } else f3EndSegment = true;
                                }
                            }else {
//                                countCompare++;
                                if (a2 <= a1) {
                                    fw.write(a2 + " ");
//                                    countCalls++;
                                    if (scan2.hasNext()) {
//                                        countCalls++;
                                        str = scan2.next();
                                        if (str.equals("|")) {
                                            f2EndSegment = true;
                                        } else {
                                            a2 = Integer.parseInt(str);
                                        }
                                    }else f2EndSegment = true;
                                } else {
                                    fw.write(a1 + " ");
//                                    countCalls++;
                                    if (scan1.hasNext()) {
//                                        countCalls++;
                                        str = scan1.next();
                                        if (str.equals("|")) {
                                            f1EndSegment = true;
                                        } else
                                            a1 = Integer.parseInt(str);

                                    }else f1EndSegment = true;
                                }

                            }
                        }else{
                            if(!f3EndSegment){
//                                countCompare++;
                                if (a1 <= a3) {
                                    fw.write(a1 + " ");
//                                    countCalls++;
                                    if (scan1.hasNext()) {
//                                        countCalls++;
                                        str = scan1.next();
                                        if (str.equals("|")) {
                                            f1EndSegment = true;
                                        } else
                                            a1 = Integer.parseInt(str);
                                    }else f1EndSegment = true;
                                } else {
                                    fw.write(a3 + " ");
//                                    countCalls++;
                                    if (scan3.hasNext()) {
//                                        countCalls++;
                                        str = scan3.next();
                                        if (str.equals("|"))
                                            f3EndSegment = true;
                                        else
                                            a3 = Integer.parseInt(str);
                                    }else f3EndSegment = true;
                                }

                            }
                            else{
                                fw.write(a1 + " ");
//                                countCalls++;
                                if (scan1.hasNext()) {
//                                    countCalls++;
                                    str = scan1.next();
                                    if (str.equals("|")) {
                                        f1EndSegment = true;
                                    } else
                                        a1 = Integer.parseInt(str);
                                }else f1EndSegment = true;
                            }
                        }
                    }
                    //1 сегмент пустой, второй нет
                    else if ( !f2EndSegment) {
                        if (!f3EndSegment) {
//                            countCompare++;
                            if (a2 <= a3) {
                                fw.write(a2 + " ");
//                                countCalls++;
                                if (scan2.hasNext()) {
//                                    countCalls++;
                                    str = scan2.next();
                                    if (str.equals("|")) {
                                        f2EndSegment = true;
                                    } else
                                        a2 = Integer.parseInt(str);
                                }
                            } else {
                                fw.write(a3 + " ");
//                                countCalls++;
                                if (scan3.hasNext()) {
//                                    countCalls++;
                                    str = scan3.next();
                                    if (str.equals("|"))
                                        f3EndSegment = true;
                                    else
                                        a3 = Integer.parseInt(str);
                                } else f3EndSegment = true;
                            }
                        }else {
                            fw.write(a2 + " ");
//                            countCalls++;
                            if (scan2.hasNext()) {
//                                countCalls++;
                                str = scan2.next();
                                if (str.equals("|")) {
                                    f2EndSegment = true;
                                } else
                                    a2 = Integer.parseInt(str);
                            }else f2EndSegment = true;
                        }
                    }
                    //1 и 2 сегмент пустой, 3 нет
                    else {
                        fw.write(a3 + " ");
//                        countCalls++;
                        if (scan3.hasNext() && !f3EndSegment) {
//                            countCalls++;
                            str = scan3.next();
                            if (str.equals("|"))
                                f3EndSegment = true;
                            else
                                a3 = Integer.parseInt(str);
                        }else f3EndSegment = true;

                    }
                }
                //пока есть записи в файлах добавляем разделитель сегментов
                if(scan1.hasNext() || scan2.hasNext() || scan3.hasNext()) {
//                    countCalls++;
                    fw.write("| ");
                }
                else
                    hasNext = false;

                fw.close();
            }

            scan1.close();
            scan2.close();
            scan3.close();
            //Удаление содержимого файлов
            clearF = new FileWriter(F1);
            clearF.close();
            clearF = new FileWriter(F2);
            clearF.close();
            clearF = new FileWriter(F3);
            clearF.close();
//            countCalls+=3;

            //Поменять местами файлы ввода и вывода
            if(swapFile){

                F4 = "F1.txt";
                F5 = "F2.txt";
                F6 = "F3.txt";

                F1 = "F4.txt";
                F2 = "F5.txt";
                F3 = "F6.txt";
                swapFile=false;
            }else {
                F1 = "F1.txt";
                F2 = "F2.txt";
                F3 = "F3.txt";

                F4 = "F4.txt";
                F5 = "F5.txt";
                F6 = "F6.txt";
                swapFile=true;
            }
            //обновить переменные для считываня данных
            scan1 = new Scanner(new FileReader(F1));
            scan2 = new Scanner(new FileReader(F2));
            scan3 = new Scanner(new FileReader(F3));
//            countCalls+=3;
        }
    }
    //Начальное разбиение исходных данных по 3-м файлом
    static void Split() throws Exception {
        FileReader fr =new FileReader("input.txt");
        Scanner scan= new Scanner(fr);
//        countCalls++;

        FileWriter fw1=new FileWriter("F1.txt");
        FileWriter fw2=new FileWriter("F2.txt");
        FileWriter fw3=new FileWriter("F3.txt");
//        countCalls+=3;

        int k=0;
        while (scan.hasNext())
            if(k==0) {
//                countCalls++;
                fw1.write(scan.next()  + " | ");
                k=1;
            }
            else if(k==1){
//                countCalls++;
                fw2.write(scan.next() + " | ");
                k=2;
            }
            else{
//                countCalls++;
                fw3.write(scan.next() +" | ");
                k=0;
            }

        fr.close();
        fw1.close();
        fw2.close();
        fw3.close();

    }


    static void insertionSort(int arr[], int left, int right) {

        boolean check = false;//будет ли swap
        for (int i = left + 1; i <= right; i++) {
            int temp = arr[i];
            int j = i - 1;
            while (j >= left && arr[j] > temp) {
                check = true;
                countCompare++;
                arr[j + 1] = arr[j];
                j--;
            }

            if (j >= left)
                countCompare++;
            if (check) {
                check = false;
            }
            arr[j + 1] = temp;
        }


    }
    static void timSort(int arr[]) {

        countCompare = 0;
        int n = arr.length;

        int RUN = 32;

        // Сортировка отдельных подмассивов размера RUN
        for (int i = 0; i < n; i += RUN)
            insertionSort(arr, i, Math.min((i + 31), (n - 1)));


        //Слияние начиная с Run затем 64 128...
        for (int size = RUN; size < n; size = 2 * size) {
            for (int left = 0; left < n; left += 2 * size) {
                int right = Math.min((left + 2 * size - 1), (n - 1));
                int mid = Math.min((left + size - 1), (n - 1));

                merge(arr, left, mid, right);
            }
        }


    }
    static void merge(int[] arr, int l, int m, int r) {

        int leftSize = m - l + 1;
        int rightSize = r - m;

        int[] left = new int[leftSize];
        int[] right = new int[rightSize];

        for (int i = 0; i < leftSize; i++)
            left[i] = arr[l + i];

        for (int i = 0; i < rightSize; i++)
            right[i] = arr[m + 1 + i];

        int i = 0;
        int j = 0;
        int k = l;

        while (i < leftSize && j < rightSize) {
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }
            countCompare++;
            k++;
        }

        // копирование оставшихся элементов left, если есть
        while (i < leftSize) {
            arr[k] = left[i];
            k++;
            i++;
        }
        // копирование оставшихся элементов right, если есть
        while (j < rightSize) {
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
    //подсчет кол-ва  сравнений
    private static int[][] countCompareAndCalls(int[] arr) throws Exception {
        int[][] randCompareAndCalls = new int[2][3];//для хранения кол-ва свапов и сравнений в массиве ранд чисел
        int[]tempArr;


        System.out.println("\nselectionSort:");

        tempArr = arr.clone();
        timSort(tempArr);//сортировка входных данных по возрастанию

        FileWriter fw = new FileWriter("input.txt");//запись в файл
        for (int i = 0; i < n; i++)
            fw.write(tempArr[i]+" ");
        fw.close();

        threeWayMerge();

        randCompareAndCalls[0][0]=countCalls;
        randCompareAndCalls[1][0]=countCompare;
        System.out.print("arr increase ");
        System.out.println("countCalls: "+countCalls+"  countCompare: "+countCompare);



        tempArr = arr.clone();
        timSort(tempArr);
        tempArr = getReversArray(tempArr);//сортировка входных данных по убыванию

         fw = new FileWriter("input.txt");//запись в файл
        for (int i = 0; i < n; i++)
            fw.write(tempArr[i]+" ");
        fw.close();

        threeWayMerge();

        randCompareAndCalls[0][1]=countCalls;
        randCompareAndCalls[1][1]=countCompare;
        System.out.print("arr decrease ");
        System.out.println("countCalls: "+countCalls+"  countCompare: "+countCompare);

        tempArr = arr.clone();
         fw = new FileWriter("input.txt");//запись в файл
        for (int i = 0; i < n; i++)
            fw.write(tempArr[i]+" ");
        fw.close();

        threeWayMerge();

        randCompareAndCalls[0][2]=countCalls;
        randCompareAndCalls[1][2]=countCompare;
        System.out.print("arr random   ");
        System.out.println("countCalls: "+countCalls+"  countCompare: "+countCompare);

        return randCompareAndCalls;

    }
    //время сортировки
    private static long[] getSortingTime( int[] arr )throws Exception {
        long[]time=new long[3];
        int[]tempArr;


        System.out.println("\nthreeWayMerge:");

        tempArr = arr.clone();
        timSort(tempArr);//сортировка входных данных по возрастанию

        FileWriter fw = new FileWriter("input.txt");//запись в файл
        for (int i = 0; i < n; i++)
            fw.write(tempArr[i]+" ");
        fw.close();

        start = System.nanoTime();
        threeWayMerge();
        finish = System.nanoTime();

        time[0]=finish - start;
        System.out.print("arr increase ");
        System.out.println("Time: " + (finish - start) / Math.pow(10, 6) + "ms");


        tempArr = arr.clone();
        timSort(tempArr);
        tempArr = getReversArray(tempArr);//сортировка входных данных по убыванию
         fw = new FileWriter("input.txt");//запись в файл
        for (int i = 0; i < n; i++)
            fw.write(tempArr[i]+" ");
        fw.close();

        start = System.nanoTime();
        threeWayMerge();
        finish = System.nanoTime();

        time[1]=finish - start;
       System.out.print("arr decrease ");
        System.out.println("Time: " + (finish - start) / Math.pow(10, 6) + "ms");


        tempArr = arr.clone();
        fw = new FileWriter("input.txt");//запись в файл
        for (int i = 0; i < n; i++)
            fw.write(tempArr[i]+" ");
        fw.close();

        start = System.nanoTime();
        threeWayMerge();
        finish = System.nanoTime();

        time[2]=finish - start;
        System.out.print("arr random   ");
        System.out.println("Time: " + (finish - start) / Math.pow(10, 6) + "ms");

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
