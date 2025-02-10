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
public class Player {

    Node position ;
    String word = new String() ;
    boolean collect = false ;
    ArrayList<String> words = new ArrayList() ;
    ArrayList<Node> record = new ArrayList<>() ;
    ArrayList<Node> playerpath = new ArrayList<>() ;
    String[] sugestions = new String[3] ;
    int d;
    int pathScore ;
    int wordScore ;
    int finalScore ;
    private int minscore ;

    

    Player(Playground grid)
    {
        int i = 0 ;
        while (i < grid.nodenum) { 
            if(!Playground.nodes[i].isWall() && Playground.nodes[i].nextNode() != null)
            {
                this.position = Playground.nodes[i] ;
                break ;
            }
            i+=GlobalVar.BOARDCOL ;
        }
        this.d = 1 ;
        this.pathScore = 0 ;
        this.wordScore = 0 ;
        this.finalScore = 0 ;
        this.playerpath.add(this.position) ;
    }

    public void updateplayer(KeyHandler keys, Node[] nodes, Enemy enemy)
    {
        int futurepos ;

        if(keys.downpress)
        {
            futurepos = this.position.getNum() + GlobalVar.BOARDCOL ;
            if (cango(this.position, futurepos) && !this.record.contains(Playground.nodes[futurepos])) {
                this.position = nodes[futurepos] ;
                this.docollect();
                this.dosugest();
                this.playerpath.add(this.position) ;
                if (this.pathScore > this.minscore) {
                    this.d = -this.d ;
                }
                this.pathScore += this.d ;
                DijkstraAlgo.initializeall();
                DijkstraAlgo.Shortway(this.position, enemy.position);
            }
            keys.downpress = false ;
        }

        if(keys.uppress)
        {
            futurepos = this.position.getNum() - GlobalVar.BOARDCOL ;
            if (cango(this.position, futurepos) && !this.record.contains(Playground.nodes[futurepos])) {
                this.position = nodes[futurepos] ;
                this.docollect();
                this.dosugest();
                this.playerpath.add(this.position) ;
                if (this.pathScore > this.minscore) {
                    this.d = -this.d ;
                }
                this.pathScore += this.d ;
                DijkstraAlgo.initializeall();
                DijkstraAlgo.Shortway(this.position, enemy.position);
            }
            keys.uppress = false ;
        }

        if(keys.leftpress)
        {
            futurepos = this.position.getNum() - 1 ;
            if (cango(this.position, futurepos) && !this.record.contains(Playground.nodes[futurepos])) {
                this.position = nodes[futurepos] ;
                this.docollect();
                this.dosugest();
                this.playerpath.add(this.position) ;
                if (this.pathScore > this.minscore) {
                    this.d = -this.d ;
                }
                this.pathScore += this.d ;
                DijkstraAlgo.initializeall();
                DijkstraAlgo.Shortway(this.position, enemy.position);
            }
            keys.leftpress = false ;
        }

        if(keys.rightpress)
        {
            futurepos = this.position.getNum() + 1 ;
            if (cango(this.position, futurepos) && !this.record.contains(Playground.nodes[futurepos])) {
                this.position = nodes[futurepos] ;
                this.docollect();
                this.dosugest();
                this.playerpath.add(this.position) ;
                if (this.pathScore > this.minscore) {
                    this.d = -this.d ;
                }
                this.pathScore += this.d ;
                DijkstraAlgo.initializeall();
                DijkstraAlgo.Shortway(this.position, enemy.position);
            }
            keys.rightpress = false ;
        }
        if(keys.uprightpress)
        {
            futurepos = this.position.getNum() - (GlobalVar.BOARDCOL-1) ;
            if (cango(this.position, futurepos) && !this.record.contains(Playground.nodes[futurepos])) {
                this.position = nodes[futurepos] ;
                this.docollect();
                this.dosugest();
                this.playerpath.add(this.position) ;
                if (this.pathScore > this.minscore) {
                    this.d = -this.d ;
                }
                this.pathScore += this.d ;
                DijkstraAlgo.initializeall();
                DijkstraAlgo.Shortway(this.position, enemy.position);
            }
            keys.uprightpress = false ;
        }
        if(keys.upleftpress)
        {
            futurepos = this.position.getNum() - (GlobalVar.BOARDCOL+1) ;
            if (cango(this.position, futurepos) && !this.record.contains(Playground.nodes[futurepos])) {
                this.position = nodes[futurepos] ;
                this.docollect();
                this.dosugest();
                this.playerpath.add(this.position) ;
                if (this.pathScore > this.minscore) {
                    this.d = -this.d ;
                }
                this.pathScore += this.d ;
                DijkstraAlgo.initializeall();
                DijkstraAlgo.Shortway(this.position, enemy.position);
            }
            keys.upleftpress = false ;
        }
        if(keys.downrightpress)
        {
            futurepos = this.position.getNum() + (GlobalVar.BOARDCOL+1) ;
            if (cango(this.position, futurepos) && !this.record.contains(Playground.nodes[futurepos])) {
                this.position = nodes[futurepos] ;
                this.docollect();
                this.dosugest();
                this.playerpath.add(this.position) ;
                if (this.pathScore > this.minscore) {
                    this.d = -this.d ;
                }
                this.pathScore += this.d ;
                DijkstraAlgo.initializeall();
                DijkstraAlgo.Shortway(this.position, enemy.position);
            }
            keys.downrightpress = false ;
        }
        if(keys.downleftpress)
        {
            futurepos = this.position.getNum() + GlobalVar.BOARDCOL-1 ;
            if (cango(this.position, futurepos) && !this.record.contains(Playground.nodes[futurepos])) {
                this.position = nodes[futurepos] ;
                this.docollect();
                this.dosugest();
                this.playerpath.add(this.position) ;
                if (this.pathScore > this.minscore) {
                    this.d = -this.d ;
                }
                this.pathScore += this.d ;
                DijkstraAlgo.initializeall();
                DijkstraAlgo.Shortway(this.position, enemy.position);
            }
            keys.downleftpress = false ;
        }
        if(keys.collectpress)
        {
            keys.collectpress = false ;

            this.collect = !this.collect;

            if(this.word.length() == 0 )
            {
                this.docollect();
            }

            if(!this.collect)
            {
                System.out.println(submit()) ;
                System.out.println(this.words);
                System.out.println(this.word);
            }
        }
        if(keys.undopress)
        {
            keys.undopress = false ;
            int idx = this.playerpath.size()-2;
            if(idx >= 0)
            {
                this.position = this.playerpath.get(idx) ;
                this.playerpath.remove(idx+1);
                if (this.pathScore > this.minscore) {
                    this.d = -this.d ;
                }
                this.pathScore -= this.d ;
                if(this.collect)
                {
                    this.word = this.word.substring(0, this.word.length()-1) ;
                    this.record.remove(this.record.size()-1) ;
                    System.out.println(this.word);
                }
            }
        }
    }

    private void dosugest()
    {
        int i = 0;
        for (String str : FileHandler.data) {
            if(str.charAt(0) == this.position.getC() && i < 3)
            {
                this.sugestions[i] = str ;
                i++ ;
            }
        }
        /*for(String str : this.sugestions)
            System.out.print(str+" ");
        System.out.println("");*/
    }

    public void docollect()
    {
        if (this.collect)
        {
            this.word += this.position.getC() ;
            record.add(this.position) ;
            System.out.println(this.word);
        }
    }

    

    public byte submit()//this function will submit the collected word to the file to see if it is in the dictionary
    {
        this.record.clear();

        if(FileHandler.data.contains(this.word))
        {
            if(this.words.isEmpty())
            {
                this.words.add(word) ;
            }else if (this.words.indexOf(word) == -1)
            {
                this.words.add(word) ;
            }else
            {
                this.word = "" ;
                System.out.println("this word is already exist in your collected words");
                return 2 ; // 2 if the word exists in collected words
            }
            int l = this.word.length() ;
            if(l > 5)
            {
                this.wordScore += l*2 ;
            }else{
                this.wordScore += l ;
            }
            
            this.word = "" ;
            System.out.println("your score = "+this.wordScore);
            return 1;// 1 if the word exists in file (succes)
        }else{
            this.word = "" ;
            return 0 ;// 0 if the word not exists in file
        }
        
    }

    public boolean win(FinishPoint finish)
    {
        if(this.position == finish.position)
        {
            if (this.pathScore == this.minscore) {
                this.pathScore += this.pathScore ;
            }
            this.finalScore = this.pathScore + this.wordScore ;
            System.out.println("You Win");
            System.out.println("your Path Score :"+this.pathScore);
            System.out.println("your collected words Score :"+this.wordScore);
            System.out.println("your Score :"+this.finalScore);
            return true ;
        }else{
            return false ;
        }
    }

    public void drawplayer(Graphics2D g2)
    {
        g2.setColor(Color.RED) ;
        int gapX = (int)(GlobalVar.TILEWIDTH*GlobalVar.GPPX) ;
        int gapY = (int)(GlobalVar.TILEHEIGHT*GlobalVar.GPPY) ;
        g2.drawRect(this.position.getX()+gapX, this.position.getY()+gapY, GlobalVar.TILEWIDTH-gapX*2, GlobalVar.TILEHEIGHT-gapY*2);
    }

    boolean cango(Node pos, int i)
    {
        if (pos != null) {
            if(pos.getNum() == i)
                return true ;
        }else{
            return false ;
        }
        return cango(pos.nextNode(), i) ;
    }

    public void setMinscore(int minscore) {
        this.minscore = minscore;
    }

    public int getMinscore() {
        return minscore;
    }
}
