/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package org.yourcompany.Board;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.FileNotFoundException;
import static java.lang.Math.random;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author thamer
 */
public class Playground {

    int nodenum = GlobalVar.BOARDCOL * GlobalVar.BOARDROW; //this will calculate the node number, it will define the exact number of nodes

    static Node[] nodes ; //this will generate the links to all nodes

    Playground() throws FileNotFoundException
    {
        nodes = new Node[nodenum] ;
    }

    //here we are going to generate grid without graph
    public void generategrid()
    {
        int x = GlobalVar.BOARDX * GlobalVar.TILEWIDTH ;//start of x
        int y = GlobalVar.BOARDY * GlobalVar.TILEHEIGHT ;//start of y
        int dx = GlobalVar.TILEWIDTH ;//distance between each node on x
        int dy = GlobalVar.TILEHEIGHT ;//distance between each node on y
        boolean b ;


        //here i will tell each node what place will take in panel 
        //and other data
        for (int i = 0; i < this.nodenum ; i++) {
            nodes[i] = new Node() ;
            nodes[i].setNum(i);
            b = 0.5 >= random() ;
            if(b)
            {
                if(i - (GlobalVar.BOARDCOL) >= 0)
                {
                    if(nodes[i-(GlobalVar.BOARDCOL)].isWall())
                    {
                        b=false ;
                    }
                }
            }
            nodes[i].setWall(b) ;
            nodes[i].setX(x);
            nodes[i].setY(y);

            x+=dx ;
            if(x >= GlobalVar.TILEWIDTH * (GlobalVar.BOARDCOL + GlobalVar.BOARDX))
            {
                x = GlobalVar.BOARDX * GlobalVar.TILEWIDTH;
                y+=dy ;
            }
        
        }

        //this fonction will generate from nodes a graph
        generategraph() ;
    }

    private void generategraph()
    {
        //i will tell each node what node should have a relation with
        Node newnode ;
        Node q ;
        for (int i = 0; i < this.nodenum; i++) {
            //i didn't figure how to make this in loop so i wrote it 8 times with diffenrent conditions
            //these conditions identify wich one of the 8 naborhood should visite and put them into a linked liste
            q = nodes[i] ;
            if(!q.isWall())
            {
                if ((i+1) >= 0 && (i+1) < this.nodenum && !(i % GlobalVar.BOARDCOL == GlobalVar.BOARDCOL-1)&& !(nodes[i+1].isWall()))
                {
                    newnode = new Node(nodes[i+1].getNum()) ;
                    q.addNode(newnode);
                    q = q.nextNode() ;
                }
                if ((i+(GlobalVar.BOARDCOL+1)) >= 0 && ((i+(GlobalVar.BOARDCOL+1)) < this.nodenum) && (i % GlobalVar.BOARDCOL != GlobalVar.BOARDCOL-1) && !(nodes[i+GlobalVar.BOARDCOL+1].isWall()))
                {
                    newnode = new Node(nodes[i+GlobalVar.BOARDCOL+1].getNum()) ;
                    q.addNode(newnode);
                    q=q.nextNode() ;
                }
                if ((i+GlobalVar.BOARDCOL) >= 0 && (i+GlobalVar.BOARDCOL) < nodenum && !(nodes[i+GlobalVar.BOARDCOL].isWall()))
                {
                    newnode = new Node(nodes[i+GlobalVar.BOARDCOL].getNum()) ;
                    q.addNode(newnode);
                    q = q.nextNode() ;
                }
                if ((i+GlobalVar.BOARDCOL-1) >= 0 && (i+GlobalVar.BOARDCOL-1) < this.nodenum && (i % GlobalVar.BOARDCOL != 0) && !(nodes[i+GlobalVar.BOARDCOL-1].isWall()))
                {
                    newnode = new Node(nodes[i+GlobalVar.BOARDCOL-1].getNum()) ;
                    q.addNode(newnode);
                    q = q.nextNode() ;
                }
                if ((i-1) >= 0 && (i-1) < this.nodenum && !(i % GlobalVar.BOARDCOL == 0)&& !(nodes[i-1].isWall()))
                {
                    newnode = new Node(nodes[i-1].getNum()) ;
                    q.addNode(newnode);
                    q = q.nextNode() ;
                }
                if ((i-(GlobalVar.BOARDCOL-1)) >= 0 && (i-(GlobalVar.BOARDCOL-1)) < nodenum && (i % GlobalVar.BOARDCOL != GlobalVar.BOARDCOL-1) && !(nodes[i-(GlobalVar.BOARDCOL-1)].isWall()))
                {
                    newnode = new Node(nodes[i-(GlobalVar.BOARDCOL-1)].getNum()) ;
                    q.addNode(newnode);
                    q = q.nextNode() ;
                }
                if ((i-GlobalVar.BOARDCOL) >= 0 && (i-GlobalVar.BOARDCOL) < nodenum && !(nodes[i-GlobalVar.BOARDCOL].isWall()))
                {
                    newnode = new Node(nodes[i-GlobalVar.BOARDCOL].getNum()) ;
                    q.addNode(newnode);
                    q = q.nextNode() ;
                }
                if ((i-(GlobalVar.BOARDCOL+1)) >= 0 && (i-(GlobalVar.BOARDCOL+1)) < nodenum && (i % GlobalVar.BOARDCOL != 0) && !(nodes[i-(GlobalVar.BOARDCOL+1)].isWall()))
                {
                    newnode = new Node(nodes[i-(GlobalVar.BOARDCOL+1)].getNum()) ;
                    q.addNode(newnode);
                }
            }
        }
    }

    private Node randdir(Node n)
    {
        if(n.nextNode() != null)
        {
            Random r = new Random() ;
            Node q = n.nextNode() ;
            ArrayList <Node> nodeList = new ArrayList<>() ;
            
            while (q!=null) {
                if(nodes[q.getNum()].getC() == '?')
                    nodeList.add(nodes[q.getNum()]) ;
                q=q.nextNode() ;
            }
            if(nodeList.isEmpty())
                return null ;
            else
                return nodeList.get(r.nextInt(nodeList.size())) ;
        }else{
            return null ;
        }

    }

    private boolean givechartoothers(Node node, String s, ArrayList<Node> nodepath)
    {
        //ArrayList<Node> tmp = new ArrayList<>() ;
        int i = 0 ;

        if (s.charAt(i) == node.getC()) {
            i++ ;
            node = randdir(node) ;
        }
        else if(node.getC() != '?')
        {
            node = randdir(node) ;
        }

        while(i < s.length() && node!=null)
        {
            node.setC(s.charAt(i));
            nodepath.add(node) ;
            if(i != s.length()-1)
                node = randdir(node) ;
            i++ ;
        }

        for (Node elem : (ArrayList<Node>) nodepath.clone()) {
            boolean hasnabor = false ;
            Node p = elem.nextNode() ;
            while (p != null && !hasnabor) { 
                if(nodes[p.getNum()].getC() == '?')
                {
                    hasnabor = true ;
                }
                p = p.nextNode() ;
            }
            if (!hasnabor) {
                nodepath.remove(elem) ;
            }
        }

        return i == s.length();
        
    }

    private String getrandomword(ArrayList<String> s)
    {
        int i ;
        Random r = new Random() ;
        
        i = r.nextInt(s.size()) ;

        return s.get(i) ;
    }
    private ArrayList<String> getsuitword(ArrayList<String> wordlist, Node node)
    {
        ArrayList<String> suitword = new ArrayList<>() ;
        for (String word : wordlist) {
            if(node != null && word.charAt(0) == node.getC())
            {
                suitword.add(word) ;
            }
        }
        return suitword ;
    }

    private Node getrandomNode(ArrayList<Node> n)
    {
        Random r = new Random() ;
        int i ;
        if(!n.isEmpty())
        {
            i = r.nextInt(n.size()) ;
            return n.get(i) ;
        }else{
            return null ;
        }
    }

    public ArrayList<String> givechar(Player player)
    {
        ArrayList<String> wordlist = (ArrayList)FileHandler.data.clone() ;
        ArrayList<String> suitword ;
        ArrayList<Node> nodepath = new ArrayList<>() ;

        String s = getrandomword(wordlist) ;

        Node node = player.position ;

        ArrayList<String> words = new ArrayList<>() ;
        while(node != null && !wordlist.isEmpty()) {

            if(givechartoothers(node, s, nodepath))
            {
                words.add(s) ;
            }
            wordlist.remove(s) ;
            
            node = getrandomNode(nodepath) ;

            suitword = getsuitword(wordlist, node) ;

            if(suitword.isEmpty())
            {
                s = getrandomword(wordlist) ;
            }else
            {
                s = getrandomword(suitword) ;
            }
            //System.out.println(s);
        }
        return words ;
    }

    public void showgraph()
    {
        for(Node node : nodes)
        {
            Node q = node ;
            while (q != null) { 
                System.out.print("node"+q.getNum()+ "\t"+ q.visited);
                q = q.nextNode() ;
            }
            System.out.println("\n");
        }
    }

    public void shownodes(Graphics2D g2)
    {
        /*for (Node node : nodes) {
            System.out.println(node.getX());
            System.out.println(node.getY());
            System.out.println();
        }*/


        for(int i = 0; i < nodenum; i++)
        {
            if(nodes[i].isWall())
            {
                g2.setColor(Color.yellow);
            }else
            {
                g2.setColor(Color.white);
            }
            int gapX = (int)(GlobalVar.TILEWIDTH*GlobalVar.GPPX) ;
            int gapY = (int)(GlobalVar.TILEHEIGHT*GlobalVar.GPPY) ;

            g2.fillRect(nodes[i].getX()+gapX, nodes[i].getY()+gapY, GlobalVar.TILEWIDTH-gapX*2, GlobalVar.TILEHEIGHT-gapY*2);
            g2.setColor(Color.black);
            if(!nodes[i].isWall())
                g2.drawString(nodes[i].getC()+"", nodes[i].getX()+20, nodes[i].getY()+20);
        }
        
    }
}
