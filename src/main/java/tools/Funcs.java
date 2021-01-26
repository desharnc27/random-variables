/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.util.Arrays;

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
    /*private static String paramStrX( int maxLenParam,double ... params){
        System.out.println(Arrays.toString(params));
        String res = "(";
        for (int i=0;i<params.length;i++){
            if (i!=0)
                res+=",";
            String val =String.valueOf(params[i]);
            if (val.length()>maxLenParam)
                val=val.substring(0, maxLenParam);
            res+=val;
        }
        return res + ")";
    }
    private static String paramStrX(double ... params){
        return paramStr(4,params);
    }*/
    private static String barb(String ... params){
        String res = "(";
        String joint = String.join(",", params);
        return "("+joint+")";
    }
    /*public static String paramStr(int ... params){
        double [] arr = new double[params.length];
        for (int i = 0;i<params.length;i++){
            arr[i]=params[i]+0.0;
        }
        return paramStr(4,arr);
    }*/
    public static String purify(double d) {
        int a = (int) Math.round(d);
        if (Math.abs(d - a) < Small.EPSILON) {
            return String.valueOf(a);
        }
        String res = String.valueOf(d);
        if (res.length()>5)
            res=res.substring(0,5);
        return res;
    }
        
    public static String paramStr(){
        return barb("");
    }
    public static String paramStr(double d0){
        String s0 =purify(d0);
        return barb(s0);
    }
    public static String paramStr(double d0,double d1){
        String s0 =purify(d0);
        String s1 =purify(d1);

        return barb(s0,s1);
    }
    public static String paramStr(double d0,double d1,double d2){
        String s0 =purify(d0);
        String s1 =purify(d1);
        String s2 =purify(d2);

        return barb(s0,s1,s2);
    }
    public static String paramStr(double d0,double d1,double d2,double d3){
        String s0 =purify(d0);
        String s1 =purify(d1);
        String s2 =purify(d2);
        String s3 =purify(d3);
        return barb(s0,s1,s2,s3);
    }
    
    public static void main(String [] args){
        //String ans=paramStr(new double[]{5,1});
        //System.out.println(ans);
    }
}
