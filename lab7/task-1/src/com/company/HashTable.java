package com.company;

public class HashTable {
    private Pair[] hashTable;
    private boolean[] used;
    private int size;
    //переменные для повторного хеширования
    private long c=1;
    private long d=1;

    HashTable(int size){
        this.size=size;
        used=new boolean[size];
        hashTable = new Pair[size];
        for (int i = 0; i < size; i++){
            hashTable[i] =new Pair(0,"");
            used[i] = false;
        }
    }

    public void insert(int key, String data){
        int h = hash(key)%size;
        int i=1;

        while  ( used[ h] && hashTable[ h].getKey() != key) {
            h = (int) ((h + c * i + d * i * i) % size);//	квадратичное опробование
            i++;
        }
        if ( !used[ h] ) {
            used[ h] = true;
            hashTable[ h]=new Pair(key,data);
        }

    }
    public String find(int key){
        int i=1;
        int h = hash(key)%size;
        while ( used[h] && hashTable[h].getKey() != key ) {
            h = (int) ((h + c * i + d * i * i) % size);//	квадратичное опробование
           i++;
        }
        if( used[h] && hashTable[h].getKey() == key)
            return hashTable[h].getValue();
        else
            return "Объект не найден!";


    }
    // хеш-функция //середина квадрата
    private int hash(int key){
        if (key<=46340) {
            key *= key;
            key>>=11; // отбросить 11 младших бит
            return key%1024;// возвратить 10 младших бит
        }

            long buff = (long) key * key;
            buff >>= 21;           // отбросить 21 младших бит
            return (int) (buff % 1048576);    // возвратить 20 младших бит

    }
    public void output(){
        for (int i = 0; i < size; i++)
            System.out.print(hashTable[i].getKey()+" ");
        System.out.println();


    }

}
