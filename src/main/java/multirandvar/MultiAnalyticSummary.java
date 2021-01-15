/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multirandvar;

import java.util.Arrays;

/**
 *
 * @author desha
 */
class MultiAnalyticSummary {
    double [] esp;
    double [] [] espConj;
    int dim;
    //int sampleSize;
    public MultiAnalyticSummary(double[] esp, double[][] espConj) {
        this.dim=esp.length;
        this.esp = Arrays.copyOf(esp, dim);
        this.espConj=new double[dim][dim];
        for (int i=0;i<dim;i++)
            for(int j=0;j<dim;j++)
                this.espConj[i][j]=espConj[i][j];
    }
    //TODO:change name (-pop)
    public double [][] getCovar(){
        double [][] res=new double[dim][dim];
        for (int i=0;i<dim;i++)
            for(int j=0;j<dim;j++)
                res[i][j]=espConj[i][j]-esp[i]*esp[j];
        return res;
    }
    public static MultiAnalyticSummary BuildByVar(double [] esp, double [][]covar ){
        int dim =esp.length;
        double espConj [][]=new double [dim][dim];
        for (int i=0;i<dim;i++)
            for(int j=0;j<dim;j++)
                espConj[i][j]=covar[i][j]+esp[i]*esp[j];
        return new MultiAnalyticSummary(esp,espConj);
    }
}
