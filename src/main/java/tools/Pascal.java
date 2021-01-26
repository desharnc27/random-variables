/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.util.Arrays;

/**
 * This class contains data of:
 *
 * 1-Unsuited 5-combination scores, 2-Pascal Triangle.
 *
 * The usefulness of this class is to compute all values they contain only once,
 * so there is no need to restart calculations of these values if asked
 * repeatedly.
 *
 * @author desharnc27
 */
public class Pascal {

    private static long[][] pascalTriangle;
    //private static boolean fullyDevelopped = false;
    //private static boolean finalDevelop = false;
    static final int maxLen = 2048;

    /**
     * Fills scores of unsuited combination scores and Pascal Triangle
     *
     */
    /*public static void proceed() {
        createPascal(25);
    }*/

    /**
     * Fills Pascal triangle up to a value that will be enough for all
     * calculations of this project.
     *
     */
    public static void createPascal(int max) {
        System.out.println("Rebuilding Pascal Triangle");
        if (max>maxLen){
            System.out.println("Too big size was asked for Pascal Triangle. We will limit height at "+maxLen);
            max=maxLen;
        }
            
        /*if (fullyDevelopped)
            return;
        if (!finalDevelop && max > maxLen){
            finalDevelop=true;
            createPascal(maxLen);
            return;
        }*/
        pascalTriangle = new long[max][max];
        pascalTriangle[0][0] = 1;
        for (int i = 1; i < max; i++) {
            pascalTriangle[i][i] = 1;
            pascalTriangle[i][0] = 1;
            //boolean worthedLevel = i < 8;
            for (int j = 1; j < i; j++) {
                long left = pascalTriangle[i - 1][j - 1];
                long right = pascalTriangle[i - 1][j];
                long sum = left + right;
                if (left == -1 || right == -1 || sum < 0) {
                    //Overflow
                    pascalTriangle[i][j] = -1;
                } else {

                    pascalTriangle[i][j] = left + right;
                    /*if (j > 2 && i - j > 2) {
                        worthedLevel = true;
                    }*/
                }
            }
            
            /*if (!worthedLevel && !fullyDevelopped) {
                finalDevelop = true;
                if (i + 1 != max) {
                    createPascal(i + 1);
                    return;
                }

            }*/
        }
        /*f (finalDevelop) {
                fullyDevelopped = true;
            }*/
    }

    /**
     * Returns C(i,j)
     *
     * @param i
     * @param j
     * @return C(i,j)
     */
    public static long get(int i, int j) {
        if (i < j) {
            //System.out.println("Fatal error: C(i,j) requested with i<j, not allowed");
            throw new ArithmeticException("Fatal error: C(" + i + "," + j + ") requested with first arg smaller, not allowed");
        }
        if (i>=maxLen){
            throw new ArithmeticException("Fatal error: C(" + i + "," + j + ") for too big first arg, not allowed");
        }
        if (i < 2 * j) {
            return get(i, i - j);
        }
        /*if (j == 0) {
            return 1;
        }
        if (j == 1) {
            return i;
        }
        if (j == 2) {
            return ((long) i * i - i) / 2;
        }*/
        if (pascalTriangle==null)
            createPascal(4);
        if (i >= pascalTriangle.length) {
            int newMax = i+1;
            if (newMax<=2 * pascalTriangle.length)
                newMax = 2 * pascalTriangle.length+1;
            createPascal(newMax);
            return get(i,j);
        }
        if (pascalTriangle[i][j]==-1)
            throw new ArithmeticException("Fatal error: C(" + i + "," + j + ") has too big value");
        /*if (i >= pascalTriangle.length && fullyDevelopped) {
            //System.out.println("Warning C(i,j) requested for i too big, not allowed");
            throw new ArithmeticException("Fatal error: C(" + i + "," + j + ") for too big first arg, not allowed");

        }
        
        long res = pascalTriangle[i][j];
        if (res ==-1)
            throw new ArithmeticException("Fatal error: C(" + i + "," + j + ") has too big value");*/

        /*if (pascalTriangle == null || pascalTriangle.length <= i) {
            int newLen = 0;

        }*/
        
        return pascalTriangle[i][j];
    }
    public static void debugPrintAll(){
        for (int i=0;i<pascalTriangle.length;i++)
            System.out.println(Arrays.toString(pascalTriangle[i]));
    }

    public static long fact(int n) {
        long res = 1;
        if (n < 0) {
            throw new ArithmeticException("Factorial does not take negative values. Found : " + n);
        }
        for (int i = 1; i <= n; i++) {
            res *= i;
            if (res < 0) {
                System.out.println("Warning: overflow in factorial");
            }
        }
        return res;
    }

    public static double doubleFact(int n) {
        double res = 1;
        if (n < 0) {
            throw new ArithmeticException("Factorial does not take negative values. Found : " + n);
        }
        for (int i = 1; i <= n; i++) {
            res *= i;
        }
        return res;
    }
}
