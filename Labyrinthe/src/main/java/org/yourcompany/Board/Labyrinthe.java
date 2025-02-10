/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.yourcompany.Board;

import java.io.FileNotFoundException;

/**
 *
 * @author thamer
 */
public class Labyrinthe {
    public static void main(String[] args) throws FileNotFoundException {
        /*Node c = new Node(0) ;
        Node q = c ;

        for (int i = 1; i < 10; i++) {
            Node p = new Node(i) ;
            q.addNode(p);
            q=q.nextNode();
        }

        q = c ;
        while(q != null)
        {
            System.out.println(q.dataNode());
            q=q.nextNode() ;
        }*/
        GarphicsUI window = new GarphicsUI() ;
        ScreenUI gamepanel = new ScreenUI() ;

        window.addpanel(gamepanel);
        window.showwindow();

        gamepanel.stratGameThread();
        
    }
}
