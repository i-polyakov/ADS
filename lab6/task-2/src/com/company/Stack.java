package com.company;

public class Stack {
    private int maxSize; //mSize - максимальный размер
    private int[] stackArr;
    private int top;

    public Stack(int size) {
        this.maxSize = size;
        stackArr = new int[maxSize];
        top = -1;
    }
    public void Push(int value)
    {
        if (isFull())
            System.out.println("Нет места в очереди!");
        else
            stackArr[++top] = value;
    }

    public int Pop()
    {
        if (isEmpty()){
            System.out.println("Стек пуст!");
            return -1;
        }
         int buff= stackArr[top];
        stackArr[top]=0;
        top--;
        return buff;
    }

    public int Peek()
    {
        if (isEmpty()) {
            System.out.println("Стек пуст!");
            return -1;
        }
        return stackArr[top];
    }


    public boolean isEmpty() {
        return (top == -1);
    }

    public boolean isFull() {
        return (top == maxSize - 1);
    }

    public void  outPut(){
        System.out.println("top->bottom:");
        for (int i = top; i >=0; i--)
            System.out.print( stackArr[i]+" ");
        System.out.println();

    }
}
