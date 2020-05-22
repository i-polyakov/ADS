package com.company;

public class Graph{

    private int[][] matrixAdjacency;//матрица смежности
    private int[] degrees;//степени узлов

    private int n;//число узлов

    Graph(int n){
        this.n=n;

        degrees=new int[n];
        matrixAdjacency=new int[n][n];
    }

    //поиск минимального пути
    public void shortWay(int startNode,int endNode){

        int[]weights=new int[n];//минимальный вес
        boolean[] used = new boolean [n]; // массив исп узлов
        int[] routes=new int[n];//маршруты
        for(int i=0;i<n;i++)
            routes[i]=-1;

        routes[startNode]=startNode;

        for (int i = 0; i <n ; i++)
            weights[i]=matrixAdjacency[startNode][i];

        used[startNode]=true;
        int w;
        while (true) {
            w=-1;
            for (int i = 0; i < n; i++)
                if (!used[i] && weights[i] != 0 && (w == -1 || weights[i] < weights[w]))//выбираем вершину с мин весом
                    w = i;
            if (w==-1||w==endNode)//если вершина не найдена, или является конечной выход
                break;

            used[w] = true;

            for (int i = 0;   i <n; i++)
                if (!used[i] &&matrixAdjacency[w][i]!=0&& (weights[i] == 0 || (weights[w] + matrixAdjacency[w][i]) < weights[i])) { // для всех не исп узлов, пересчитываем минимальный вес
                    weights[i] = weights[w] + matrixAdjacency[w][i];
                    routes[i] = w;
                }
        }
        //Вывод результата
        if (w!=endNode)
            System.out.println("Нет дороги между перекрестками!");
        else {
            StringBuilder route = new StringBuilder();//маршрут от конечного перекрёстка до начального

            for (int i = endNode; i != -1 && i != routes[i]; ) {
                route.append(i + 1).append(" ");
                i = routes[i];
            }
            route.append(startNode + 1);
            System.out.print("Кратчайший по времени маршрут:");
            System.out.println(route.reverse());
            System.out.println("время:"+(weights[endNode]+degrees[endNode]));

        }
    }
    //Добавить дорогу
    public int addLink(int i,int j,int weight){
        if (i==j) {
            System.out.println("В сети дорог не должно быть петель!");
            return -1;
        }
        if (i<1||i>n) {
            System.out.println("Перекрестка "+i+" нет!");
            return -1;
        }
        if (j<1||j>n) {
            System.out.println("Перекрестка "+j+" нет!");
            return -1;
        }
        if (weight<=0) {
            System.out.println("Не положительное время!");
            return -1;
        }
        matrixAdjacency[i-1][j-1]=weight;
        matrixAdjacency[j-1][i-1]=weight;
        return 1;
    }

    public void addDegrees(){

        for(int i = 0; i < n; ++i)
        {
            int count = 0;
            for(int j = 0; j < n; ++j)
                if(matrixAdjacency[i][j] >0)
                    count++;

            degrees[i]=count;

            for (int j = 0; j < n; j++) //добавляем к весам степени узлов
                if(matrixAdjacency[i][j] >0)
                    matrixAdjacency[i][j]+=degrees[i];
        }

    }

    public void print(){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                System.out.printf("%4d",matrixAdjacency[i][j]);

            System.out.println();
        }
        System.out.println();

    }

}
