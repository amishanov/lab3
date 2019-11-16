package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Map {
      private class Path{
        private String [] points;
        private int time;
        Path(String points[],int time){
            this.points=points;
            this.time=time;
        }
        public String showPath(){
            return (points[0]+" "+points[1]+" "+time);
        }
    }
    private ArrayList<Path> paths;
    private HashMap<String,Integer> points;
    private ArrayList pot;
    private int ways[][];
     Map(){
         paths=new ArrayList<>();
         points=new HashMap<String,Integer>();
         pot=new ArrayList();
         ways=new int[100][100];
         for (int i=0;i<100;i++)
             for(int j=0;j<100;j++)
                 ways[i][j]=1000000;
     }
    public void addPath(String place1,String place2,int time){
        String[] arr=new String[2];
        arr[0]=place1; arr[1]=place2;
        Path path=new Path(arr,time);
        paths.add(path);
        if (!pot.contains(place1))
            pot.add(place1);
        if (!pot.contains(place2))
            pot.add(place2);
        if (!points.containsKey(place1))
            points.put(place1,points.size());
        if (!points.containsKey(place2))
            points.put(place2,points.size());
         //делаем матрицу смежности
        ways[pot.indexOf(place1)][pot.indexOf(place2)]=time;
        ways[pot.indexOf(place2)][pot.indexOf(place1)]=time;

    }
    public ArrayList getAllPlace(){
        return paths;
    }

    public ArrayList genShortWay(String place1, String place2){

         int MAX=1000000; int pointCount=pot.size();
        int D[]=new int[pointCount]; int P[]=new int[pointCount]; int startPoint=pot.indexOf(place1);//int startPoint=points.get(place1);
        boolean []visited=new boolean[pointCount];
        Arrays.fill(D,1000000); Arrays.fill(P,-1);
        Arrays.fill(visited,false);
        D[startPoint]=0;
        for (int i=0;i<pointCount;i++)
            D[i]=ways[startPoint][i];
        int index=0,u=0;
        for (int i=0;i<pointCount;i++){
            int min=MAX;
            for (int j=0;j<pointCount;j++){
                if (!visited[j] &&D[j]<min){
                    min=D[j]; index=j;
                }
            }
            u=index;
            visited[u]=true;
            for (int j=0;j<pointCount;j++){
                if (!visited[j] && ways[u][j]!=MAX && D[u]!=MAX && (D[u]+ways[u][j]<D[j])){
                    D[j]=D[u]+ways[u][j];
                    P[j]=u;
                }
            }
        }

        ArrayList stack=new ArrayList();
        int target=pot.indexOf(place2);
        for (int v=target;v!=-1;v=P[v])
            stack.add(0,v);
        ArrayList sp=new ArrayList(stack.size());
        for (int i=0;i<stack.size();i++)
            sp.add(stack.get(i));
        ArrayList res= new ArrayList();
        res.add(place1);
        for (int i=0; i<sp.size();i++)
            res.add(pot.get((Integer)sp.get(i)));
        System.out.println("Время: "+D[pot.indexOf(place2)]);
        return res;
    }
    public String show(){
         String res="";
         for (int i=0; i<paths.size();i++){
             res+=paths.get(i).showPath()+"\n";
         }
         return res;
    }
}
