package com.company;

import java.util.Random;

public class Shelf {
    private Deque deque;
    Shelf(int n){
        deque=new Deque(n);
    }

    public void putClean(int[] dishes) {
        Random rand=new Random();
        if (dishes[0]!=-1)
            if(rand.nextInt(2)==0)
                deque.EnqueueFirst(dishes[0]);
            else
               deque.EnqueueLast(dishes[0]);
            if (dishes[1]!=-1)
            if(rand.nextInt(2)==0)
                deque.EnqueueFirst(dishes[1]);
            else
                deque.EnqueueLast(dishes[1]);
    }

    public int makeDirty() {
        Random rand=new Random();
        if(!deque.isEmpty())
            if(rand.nextInt(2)==0)
                return deque.DequeueFirst();
            else
                return deque.DequeueLast();

         return -1;
    }
    public void  outPut(){
        if(!deque.isEmpty()) {
            System.out.println("    Полка");
            deque.outPut();
        }
    }
}
