/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package org.yourcompany.Board;

import java.awt.Color;
import java.awt.Graphics2D;


/**
 *
 * @author thamer
 */
public class Enemy {
    Node position ;

    public Enemy(FinishPoint finish) {
        this.position = finish.position ;
    }

    public void updateEnemy()
    {
        if (!this.position.pred.isEmpty()) {
            int i = this.position.pred.size()-1 ;
            int nextpos = this.position.pred.get(i) ;
            this.position = Playground.nodes[nextpos];
        }
        
        
        //if(this.currentpos > 0)
        //    this.currentpos-- ;
    }

    public boolean attackplayer(Player player)
    {
        if(this.position == player.position)
        {
            System.out.println("You lose");
            return true ;
        }else{
            return false ;
        }
    }

    public void drawEnemy(Graphics2D g2)
    {
        g2.setColor(Color.pink) ;
        int gapX = (int)(GlobalVar.TILEWIDTH*GlobalVar.GPPX) ;
        int gapY = (int)(GlobalVar.TILEHEIGHT*GlobalVar.GPPY) ;
        g2.fillRect(this.position.getX()+gapX, this.position.getY()+gapY, GlobalVar.TILEWIDTH-gapX*2, GlobalVar.TILEHEIGHT-gapY*2);
    }

}
