/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

/**
 *
 * @author desha
 */
public class Funcs {

    public static void lenMismatchWarning(int a, int b) {
        if (a == b) {
            return;
        }
        System.out.println("Leangth warning: " + a + "!=" + b);
    }

    public static double[][] diffArray(double[][] arr0, double[][] arr1) {
        //TODO:safety;
        int dim = arr0.length;

        lenMismatchWarning(arr0.length, arr1.length);

        double[][] res = new double[dim][dim];
        for (int i = 0; i < dim; i++) {
            lenMismatchWarning(arr0[0].length, arr1[1].length);
            for (int j = 0; j < dim; j++) {
                res[i][j] = arr0[i][j] - arr1[i][j];
            }
        }
        return res;
    }

    public static double[] diffArray(double[] arr0, double[] arr1) {
        //TODO:safety;
        int dim = arr0.length;
        double[] res = new double[dim];
        lenMismatchWarning(arr0.length, arr1.length);
        for (int i = 0; i < dim; i++) {
            res[i] = arr0[i] - arr1[i];
        }
        return res;
    }

    public static String doubleStrOfLen(double val, int len) {
        String fc = " ";
        if (val < 0) {
            fc = "-";
            val = -val;
        }
        int exp = 0;
        if (val < 0.1) {

        }
        return "0.0";
    }
}
