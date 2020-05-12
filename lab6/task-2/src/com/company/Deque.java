package com.company;

public class Deque {
    private int[] dequeArr;
    private int maxSize; // максимальное количество элементов в очереди
    private int front;
    private int back;
    public Deque(int maxSize) {
        this.maxSize = maxSize;
        dequeArr = new int[maxSize];
        back = -1;
        front = 0;

    }
    public void EnqueueFirst(int value)
    {
        if (isFull())
            System.out.println("Нет места в деке!");
        else {
            for (int i = ++back; i > 0; i--)
                dequeArr[i] = dequeArr[i - 1];
            dequeArr[0] = value;
        }
    }

    public void EnqueueLast(int value)
    {
        if (isFull())
            System.out.println("Нет места в деке!");
        else
            dequeArr[++back] = value;

    }

    public int DequeueFirst() {
        if(isEmpty()) {
            System.out.println("Дек пуст!");
            return -1;
        }
        int temp = dequeArr[front]; // получаем первый элемент из очереди
        for (int i=0; i<back; i++) //смещение элементов
            dequeArr[i]=dequeArr[i+1];
        dequeArr[back]=0;
        back--;
        return temp;

    }

    public int DequeueLast()
    {
        if(isEmpty()) {
            System.out.println("Дек пуст!");
            return -1;
        }
        int buff=dequeArr[back];
         dequeArr[back]=0;
        back--;
        return buff;
    }

    public int  getFront(){
        if(isEmpty()) {
            System.out.println("Дек пуст!");
            return -1;
        }
        return dequeArr[front];

    }
    public int getBack(){
        if(isEmpty()) {
            System.out.println("Дек пуст!");
            return -1;
        }
        return dequeArr[back];
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
            System.out.print( dequeArr[i]+" ");
        System.out.println();

    }
}
