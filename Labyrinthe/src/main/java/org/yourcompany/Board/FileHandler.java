/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package org.yourcompany.Board;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author thamer
 */
public class FileHandler extends File {

    static final ArrayList<String> data = new ArrayList<>() ;
    public Scanner sc ;

    public FileHandler() throws FileNotFoundException {
        super("random_words.txt");
        sc = new Scanner(this) ;
    }

    public void loadfromfile() throws IOException
    {
        if (this.exists())
        {
            while (sc.hasNextLine()) { 
                FileHandler.data.add(sc.nextLine()) ;
            }
        }
    }

}
