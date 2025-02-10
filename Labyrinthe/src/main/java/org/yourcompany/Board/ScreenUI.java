/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package org.yourcompany.Board;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 *
 * @author thamer
 */
public class ScreenUI extends JPanel implements Runnable {

    Thread gameThread ;

    KeyHandler keys = new KeyHandler() ;

    FileHandler file ;

    Playground grid = new Playground() ;
    Player player ;
    FinishPoint finish ;
    Enemy enemy ;
    int framecount = 0 ;

    

    ScreenUI() throws FileNotFoundException
    {
        //adapte screen width and height for the game
        this.setPreferredSize(new Dimension(GlobalVar.TILEWIDTH*GlobalVar.COL, GlobalVar.TILEHEIGHT*GlobalVar.ROW));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keys);
        this.setFocusable(true);
        this.file = new FileHandler() ;
    }

    public void stratGameThread()
    {
        gameThread = new Thread(this) ;
        gameThread.start();
    }

    @Override
    public void run() {
        
        double drawinterval = 1000000000/GlobalVar.FPS ;
        double lasttime = System.nanoTime() ;
        double deltat = 0 ;
        long currenttime ;
        ArrayList<String> words ;
        

        try {
            this.file.loadfromfile();
        } catch (IOException ex) {
        }
        this.file.sc.close();
        this.grid.generategrid() ;  
        this.player = new Player(this.grid) ;
        this.finish = new FinishPoint() ;
        this.finish.locate(this.grid);
        DijkstraAlgo.Shortway(this.player.position, this.finish.position);
        words = this.grid.givechar(this.player);
        System.out.println(words);
        this.enemy = new Enemy(this.finish) ;
        this.player.setMinscore(this.finish.position.coste);
        System.out.println("your minimum score :"+this.player.getMinscore()) ;
        //grid.showgraph();

        while(gameThread != null)
        {
            currenttime = System.nanoTime() ;
            deltat += (currenttime - lasttime) /drawinterval ;
            
            lasttime = currenttime ;

            if(deltat >= 1)
            {
                update();

                repaint();

                finishgame();

                deltat-- ;

            }
        }
    }

    public void update()
    {
        player.updateplayer(this.keys, Playground.nodes, this.enemy);

        if(this.framecount > GlobalVar.FPS*3)
        {
            this.enemy.updateEnemy();
            this.framecount = 0 ;
        }else{
            this.framecount++ ;
        }
    }
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g ;

        this.grid.shownodes(g2) ;
        this.player.drawplayer(g2);
        this.finish.drawfinish(g2);
        //DijkstraAlgo.drawpath(g2, this.finish.position);
        this.enemy.drawEnemy(g2);

        g2.dispose();
    }
    public void finishgame()
    {
        if(this.player.win(this.finish))
        {
            gameThread = null ;
        }
        if(this.enemy.attackplayer(this.player))
        {
            gameThread = null ;
        }
    }
}
