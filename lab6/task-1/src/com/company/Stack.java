package com.company;

public class Stack {
    private int maxSize; //mSize - максимальный размер
    private int[] stackArray;
    private int top;

    public Stack(int size) {
        this.maxSize = size;
        stackArray = new int[maxSize];
        top = -1;
    }
    public void Push(int value)
    {
        if (isFull())
            System.out.println("Нет места в очереди!");
        else
            stackArray[++top] = value;
    }

    public int Pop()
    {
        if (isEmpty()){
            System.out.println("Стек пуст!");
            return -1;
        }
        return stackArray[top--];
    }

    public int Peek()
    {
        if (isEmpty()) {
            System.out.println("Стек пуст!");
            return -1;
        }
        return stackArray[top];
    }


    public boolean isEmpty() {
        return (top == -1);
    }

    public boolean isFull() {
        return (top == maxSize - 1);
    }
}
