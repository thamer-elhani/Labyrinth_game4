/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package org.yourcompany.Board;

import javax.swing.JFrame;

/**
 *
 * @author thamer
 */
public class GarphicsUI extends JFrame{

    final JFrame window ;

    public GarphicsUI() {
        this.window = new JFrame("Labyrinthe") ;
    }
    
    public void showwindow()
    {   
        this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.window.setResizable(false);
        this.window.setVisible(true);
        this.window.setLocationRelativeTo(null);

        
    }
    public void addpanel(ScreenUI gamepanel)
    {
        this.window.add(gamepanel) ;
        this.window.pack();
    }

}
