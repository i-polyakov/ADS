package com.company;

import javax.swing.*;

import java.util.Random;
import java.util.Scanner;


public class Kitchen extends JPanel {
    private static int n;
    private static Table table;
    private static Dishwasher dw;
    private static Shelf shelf;

    Kitchen() throws InterruptedException {
        Scanner scan=new Scanner(System.in);
        System.out.println("Кол-во посуды: ");
        n = scan.nextInt();
        if (n<=0) {
            System.out.println("Неположительное кол-во посуды!");
            System.exit(0);
        }
        table=new Table(n);
        dw=new Dishwasher(n);
        shelf=new Shelf(n);

        Random rand=new Random();
        for (int i = 1; i <= n; i++) {//начальное распределение посуды
            int buff= rand.nextInt(3);
            if(buff==0)
                table.putDirty(i);
            else if(buff==1)
                dw.putDirty(i);
            else
                shelf.putClean(new int[]{i, -1});
        }
            while (true){
                //Информация о посуде
                table.outPut();
                dw.outPut();
                shelf.outPut();
                Thread.sleep(4000);

                int buff= rand.nextInt(3);
                if(buff==0) {
                    working("-----Two days later...-----");
                    dw.putDirty(table.returnDirty());//поставить грязную посуду со стола в машинку
                }
                else if(buff==1) {
                    working("-----Мытьё посуды-----");
                    shelf.putClean(dw.returnClean());//поствить чистую посуду на полку из машинки
                }
                else {
                    working("-----Кушаем-----");
                    table.putDirty(shelf.makeDirty());//посавить грязную посуду на стол
                }

            }
    }

    static void working(String str) throws InterruptedException {//интервал для операций
        Random rand=new Random();
        System.out.println("\n"+str);
        Thread.sleep(rand.nextInt(2000)+1000);
    }
}
