/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

/**
 *
 * @author desharnc27
 *
 * Methods to print by System.out
 */
public class PrintMeths {

    /**
     * Prints a line (for esthetic purpose only)
     *
     *
     */
    public static void trait() {
        System.out.println("--****---****--");
    }

    /**
     * Prints all values of an array of int and breaks line
     *
     * @param tab An array of int
     */
    public static void print(int[] tab) {
        for (int i : tab) {
            System.out.print(i + " ");

        }
        System.out.println();
    }
    /**
     * Prints all values of an array and breaks line
     *
     * @param <T> any class
     * @param tab an array
     */
    public static <T> void print(T []tab) {
        for (T i : tab) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    /**
     * Prints all values of an array of int and breaks line.Each int takes a minimal space of maxLength.
     *
     * @param tab An array of int
     * @param maxLength minimal number spaces used by every int 
     */
    public static void print(int[] tab, int maxLength) {
        //TODO: arrange format
        String format="%"+maxLength+"d";
        for (int i = 0; i < tab.length; i++) {
            String val = String.format(format, tab[i]);
            System.out.print(val + " ");

        }
        System.out.println();
    }

    /**
     * Prints all arrays of tab, breaks line between each of them
     *
     * @param tab
     * @see print(int[] tab)
     */
    public static void print(int[][] tab) {
        for (int[] tab1 : tab) {
            print(tab1);
        }
    }

    /**
     * Prints all arrays of tab, breaks line between each of them. Each int
     * takes a minimal space of maxLength.
     *
     * @param tab an array of int
     * @param maxLength minimal number spaces used by every int
     *
     */

    public static void print(int[][] tab, int maxLength) {
        for (int[] tab1 : tab) {
            print(tab1, maxLength);
        }
    }
    
    /**
     * Prints all values of an array of double and breaks line
     *
     * @param tab An array of double
     */
    public static void print(double[] tab) {
        for (double i : tab) {
            System.out.print(i + " ");

        }
        System.out.println();
    }



    /**
     * Prints all arrays of tab, breaks line between each of them
     *
     * @param tab
     * @see print(double[] tab)
     */
    public static void print(double[][] tab) {
        for (double[] tab1 : tab) {
            print(tab1);
        }
    }
}
