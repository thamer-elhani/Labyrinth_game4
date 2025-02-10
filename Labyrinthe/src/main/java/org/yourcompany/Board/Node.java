/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package org.yourcompany.Board;

import java.util.ArrayList;

/**
 *
 * @author thamer
 */
public class Node {

    private char c ;
    private int num ;
    private int x,y ;
    private boolean wall ;
    boolean visited ;
    int coste ;
    int takecoste ;
    int dist ;
    ArrayList<Integer> pred ;
    ArrayList<Integer> path ;
    private Node next ;

    Node()
    {
        this.x = 0 ;
        this.y = 0 ;
        this.num = 0 ;
        this.wall = false ;
        this.c = '?' ;
        this.visited = false ;
        this.dist = 0 ;
        this.coste = Integer.MAX_VALUE ;
        this.pred = new ArrayList<>();
        this.next = null ;
    }

    public Node(int num) {
        this.num = num;
        this.next = null ;
        this.dist = 1 ;
    }

    void addNode(Node n)
    {
        this.next = n;
    }
    Node nextNode()
    {
        return this.next ;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public boolean isWall() {
        return wall;
    }

    public void setWall(boolean wall) {
        this.wall = wall;
    }

    public char getC() {
        return c;
    }

    public void setC(char c) {
        this.c = c;
    }
}
