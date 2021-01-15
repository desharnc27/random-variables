/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

/**
 * This class contains data of:
 * 
 * 1-Unsuited 5-combination scores,  
 * 2-Pascal Triangle.
 * 
 * The usefulness of this class is to compute all values they contain only once, so 
 * there is no need to restart calculations of these values if asked repeatedly.
 * 
 * @author desharnc27
 */
public class Pascal {
    private static long[][] pascalTriangle;
    static final int maxLen=62;
    
    
    /**
     * Fills scores of unsuited combination scores and Pascal Triangle
     * 
     */
    public static void proceed() {
        createPascal(25);
    }
    /**
     * Fills Pascal triangle up to a value that will 
     * be enough for all calculations of this project.
     * 
     */
    public static void createPascal(int max) {
        pascalTriangle = new long[max][max];
        pascalTriangle[0][0] = 1;
        for (int i = 1; i < max; i++) {
            pascalTriangle[i][i] = 1;
            pascalTriangle[i][0] = 1;
            for (int j = 1; j < i; j++) {
                pascalTriangle[i][j] = pascalTriangle[i - 1][j - 1] + pascalTriangle[i - 1][j];
            }
        }
    }
    

    /**
     * Returns C(i,j)
     * @param i
     * @param j
     * @return C(i,j)
     */
    public static long get(int i, int j) {
        if (i<j){
            System.out.println("Warning C(i,j) requested with i<j, not allowed");
            return -1;
        }
        if (i>maxLen){
            System.out.println("Warning C(i,j) requested for i too big, not allowed");
            return -1;
        }
        
        if (pascalTriangle==null || pascalTriangle.length<=i){
            int newLen= 0;
            
        }
            createPascal(i+1);
        return pascalTriangle[i][j];
    }
}
