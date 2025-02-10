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
public class FinishPoint {

    public Node position ;
    boolean locked ;
    public ArrayList<Integer> ownmap ;

    public void locate(Playground grid)
    {
        int i = grid.nodenum - 1 ;
        
        while(i > 0 && Playground.nodes[i].isWall())
            i -= GlobalVar.BOARDCOL ;
        this.position = Playground.nodes[i] ;
        this.locked = true ;
    }

    void drawfinish(Graphics2D g2)
    {
        g2.setColor(Color.green);
        int gapX = (int)(GlobalVar.TILEWIDTH*GlobalVar.GPPX) ;
        int gapY = (int)(GlobalVar.TILEHEIGHT*GlobalVar.GPPY) ;
        g2.drawRect(this.position.getX()+gapX, this.position.getY()+gapY, GlobalVar.TILEWIDTH-gapX*2, GlobalVar.TILEHEIGHT-gapY*2);
        //g2.setColor(Color.black);
        //g2.drawString(this.position.getC()+"", this.position.getX() + 20, this.position.getY() + 20);
    }

}
