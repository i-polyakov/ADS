package com.company;

public class Queue {
    private int[] queueArr;
    private int maxSize; // максимальное количество элементов в очереди
    private int front;
    private int back;

    public Queue(int maxSize) {
        this.maxSize = maxSize;
        queueArr = new int[maxSize];
        back = -1;
        front = 0;
    }
    public void Enqueue(int value)
    {
        if(isFull())
            System.out.println("Нет места в очереди!");
        else {
            queueArr[++back] = value;
        }
    }

    public int Dequeue()
    {
        if(isEmpty()) {
            System.out.println("Очередь пустая!");
            return -1;
        }
        else {
            int temp = queueArr[front]; // получаем первый элемент из очереди
            for (int i=0; i<back; i++) //смещение элементов
                queueArr[i]=queueArr[i+1];
            queueArr[back]=0;
            back--;
            return temp;
        }
    }
    public int  getFront(){
        if (isEmpty()) {
            System.out.println("Очередь пустая!");
            return -1;
        }
        return queueArr[front];
    }
    public int getBack(){
        if (isEmpty()) {
            System.out.println("Очередь пустая!");
            return -1;
        }
        return queueArr[back];
    }

    public boolean isFull() {
        return (back == maxSize - 1);
    }

    public boolean isEmpty() {
        return (back == -1);
    }
    public int getSize() {
        return back+1;
    }
    public void  outPut(){
        System.out.println("back->front:");
        for (int i = back; i >=front; i--)
            System.out.print( queueArr[i]+" ");
        System.out.println();

    }
}
