/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

/**
 * This class is
 *
 * @author desha
 */
public class PriLev {

    /*
    
    Categories:
    
    0: debug
    1: statistical values
    2: statistical remarks
    3: automatic adjustements
    
    */
    private final static int[] levels = new int[]{5,2,2,0,0};

    public static void print(int category, int level, String message) {
        if (levels[category] >= level) {
            System.out.print(message);
        }
    }
    public static void println(int category, int level, String message) {
        if (levels[category] >= level) {
            System.out.print(message);
            System.out.println();
        }
        
    }

    public static void setLevel(int category, int level) {
        levels[category] = level;
    }

}
