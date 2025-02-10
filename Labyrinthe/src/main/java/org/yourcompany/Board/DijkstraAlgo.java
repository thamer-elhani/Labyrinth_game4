/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package org.yourcompany.Board;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author thamer
 */

//I will not explain any think in this code
//it's just two functions Shortway and drawpath
//call DijkstraAlgo.shortway just before calling the main loop
//and DijkstraAlgo.drawpath in the repaint function
public class DijkstraAlgo {

    public static ArrayList<Integer[]>minheap ;
    public static ArrayList<Integer[]>allheap ;

    public static void Shortway(Node Point1, Node Point2)
    {
        int cost ;
        boolean run ;
        Integer[] help ;
        Point1.coste = 0 ;

        Node tmp = Point1 ;

        DijkstraAlgo.minheap = new ArrayList<>() ;
        DijkstraAlgo.allheap = new ArrayList<>() ;

        help = new Integer[]{tmp.coste,tmp.getNum()} ;

        DijkstraAlgo.minheap.add(help) ;

        run = true ;

        while(run)
        {
            for (Integer[] ptr : DijkstraAlgo.minheap) {

                tmp = Playground.nodes[ptr[1]] ;

                if(!tmp.visited)
                {
                    tmp.visited = true ;
                    
                    for (Node i = tmp.nextNode(); i != null; i = i.nextNode()) {
                        
                        if (!Playground.nodes[i.getNum()].visited && Playground.nodes[i.getNum()].pred.isEmpty()) {
                            cost = ptr[0] + i.dist ;
                            
                            if (cost < Playground.nodes[i.getNum()].coste) {
                                Playground.nodes[i.getNum()].coste = cost ;
                                Playground.nodes[i.getNum()].pred.addAll(tmp.pred) ;
                                Playground.nodes[i.getNum()].pred.add(tmp.getNum()) ;
                            }
                            help = new Integer[]{Playground.nodes[i.getNum()].coste, i.getNum()} ;

                            DijkstraAlgo.allheap.add(help) ;
                        }
                    }
                    if(tmp.getNum() == Point2.getNum())
                    {
                        //System.out.println("Shortest distance = "+Point2.coste);
                        //System.out.println("Shortest path = "+Point2.pred);
                        Point2.path = (ArrayList<Integer>) Point2.pred.clone() ;
                        Point2.takecoste = Point2.coste ;
                        run = false ;
                    }
                }
            }
            
            DijkstraAlgo.min_heap() ;

        }
    }


    static void min_heap()
    {
        if (!DijkstraAlgo.allheap.isEmpty()) {
            int min = DijkstraAlgo.allheap.get(0)[0] ;
            
            DijkstraAlgo.minheap.clear();

            DijkstraAlgo.minheap.add(DijkstraAlgo.allheap.get(0)) ;
            
            for (Integer[] i : DijkstraAlgo.allheap.subList(1,DijkstraAlgo.allheap.size())) {

                if (i[0] < min) {
                    min = i[0] ;

                    DijkstraAlgo.minheap.clear();

                    DijkstraAlgo.minheap.add(i) ;
                }else if(i[0] == min)
                {
                    DijkstraAlgo.minheap.add(i) ;
                }

            }
            DijkstraAlgo.allheap.clear();
        }else{
            DijkstraAlgo.minheap.clear();
            DijkstraAlgo.allheap.clear();
        }
        
    }

    static void drawpath(Graphics2D g2, Node Point2)
    {
        int n, m = 0 ;
        int offsetX = (int)(GlobalVar.TILEWIDTH / 2) ;
        int offsetY = (int)(GlobalVar.TILEWIDTH / 2) ;
        
        for(int i = 0; i < Point2.pred.size()-1; i++ )
        {
            n = Point2.pred.get(i);
            m = Point2.pred.get(i+1);
            g2.setColor(Color.BLUE) ;
            g2.drawLine(Playground.nodes[n].getX()+offsetX, Playground.nodes[n].getY()+offsetY, Playground.nodes[m].getX()+offsetX , Playground.nodes[m].getY()+offsetY);
        }
        g2.drawLine(Playground.nodes[m].getX()+offsetX, Playground.nodes[m].getY()+offsetY, Point2.getX()+offsetX, Point2.getY()+offsetY);
    }

    static void initializeall()
    {
        for (Node node : Playground.nodes) {
            if(!node.isWall())
            {
                node.visited = false ;
                node.coste = Integer.MAX_VALUE ;
                node.pred.clear();
                DijkstraAlgo.minheap.clear();
                DijkstraAlgo.allheap.clear();
            }
        }
    }

}
