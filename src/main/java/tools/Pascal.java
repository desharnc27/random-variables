/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import exception.TLVE;
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
    static final int maxLen = 1024;

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
        if (max > maxLen) {
            System.out.println("Too big size was asked for Pascal Triangle. We will limit height at " + maxLen);
            max = maxLen;
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
        for (int n = 1; n < max; n++) {
            pascalTriangle[n][n] = 1;
            pascalTriangle[n][0] = 1;
            //boolean worthedLevel = n < 8;
            for (int k = 1; k < n; k++) {
                long left = pascalTriangle[n - 1][k - 1];
                long right = pascalTriangle[n - 1][k];
                long sum = left + right;
                if (left == -1 || right == -1 || sum < 0) {
                    //Overflow
                    pascalTriangle[n][k] = -1;
                } else {

                    pascalTriangle[n][k] = left + right;
                    /*if (k > 2 && n - k > 2) {
                        worthedLevel = true;
                    }*/
                }
            }

            /*if (!worthedLevel && !fullyDevelopped) {
                finalDevelop = true;
                if (n + 1 != max) {
                    createPascal(n + 1);
                    return;
                }

            }*/
        }
        /*f (finalDevelop) {
                fullyDevelopped = true;
            }*/
    }

    /**
     * Returns C(n,k)
     *
     * @param n
     * @param k
     * @return C(n,k)
     */
    public static long get(int n, int k) {
        if (n < k) {
            //System.out.println("Fatal error: C(n,k) requested with n<k, not allowed");
            throw new ArithmeticException("Fatal error: C(" + n + "," + k + ") requested with first arg smaller, not allowed");
        }
        if (n >= maxLen) {
            //throw new TLVE("Fatal error: C(" + n + "," + k + ") for too big first arg, not allowed");
            double ans = recupComb(n,k);
            if (Double.isInfinite(ans)){
                throw new TLVE("Fatal error: C(" + n + "," + k + ") has too big value");
            }
            return (long)ans;
        }
        if (n < 2 * k) {
            return get(n, n - k);
        }
        /*if (k == 0) {
            return 1;
        }
        if (k == 1) {
            return n;
        }
        if (k == 2) {
            return ((long) n * n - n) / 2;
        }*/
        if (pascalTriangle == null) {
            createPascal(4);
        }
        if (n >= pascalTriangle.length) {
            int newMax = n + 1;
            if (newMax <= 2 * pascalTriangle.length) {
                newMax = 2 * pascalTriangle.length + 1;
            }
            createPascal(newMax);
            return get(n, k);
        }
        if (pascalTriangle[n][k] == -1) {
            throw new TLVE("Fatal error: C(" + n + "," + k + ") has too big value");
        }
        /*if (n >= pascalTriangle.length && fullyDevelopped) {
            //System.out.println("Warning C(n,k) requested for n too big, not allowed");
            throw new TLVE("Fatal error: C(" + n + "," + k + ") for too big first arg, not allowed");

        }
        
        long res = pascalTriangle[n][k];
        if (res ==-1)
            throw new TLVE("Fatal error: C(" + n + "," + k + ") has too big value");*/

 /*if (pascalTriangle == null || pascalTriangle.length <= n) {
            int newLen = 0;

        }*/
        return pascalTriangle[n][k];
    }

    private static double recupComb(int n, int k) {
        if (pascalTriangle==null || pascalTriangle.length != maxLen)
            createPascal(maxLen);
        if (k >=maxLen || pascalTriangle[maxLen - 1][k] == -1) {
            return Double.POSITIVE_INFINITY;
        }
        double ans = 1;
        int low = 1;
        int high = n;
        
        while (low <= k && high > n - k) {
            if (ans <= 1) {
                ans *= high--;
            } else {
                ans /= low++;
            }
        }
        while (high > n - k) {
            ans *= high--;
            if (Double.isInfinite(ans))
                return Double.POSITIVE_INFINITY;
        }
        return ans;
    }

    public static void debugPrintAll() {
        for (int i = 0; i < pascalTriangle.length; i++) {
            System.out.println(Arrays.toString(pascalTriangle[i]));
        }
    }

    public static long fact(int n) {
        long res = 1;
        if (n < 0) {
            throw new TLVE("Factorial does not take negative values. Found : " + n);
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
            throw new TLVE("Factorial does not take negative values. Found : " + n);
        }
        for (int i = 1; i <= n; i++) {
            res *= i;
            if (Double.isInfinite(res)) {
                return res;
            }
        }
        return res;
    }
}
