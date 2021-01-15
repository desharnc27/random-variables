/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multirandvar;

import java.util.Arrays;
import tools.Funcs;

/**
 *
 * @author desha
 */
public class MultiSampleStatSummary {
    int dim;
    int sampleSize;
    double [] esp;
    double [][] espConj;
    
    public MultiSampleStatSummary(double[] esp, double[][] espConj, int sampleSize) {
        this.dim=esp.length;
        this.sampleSize = sampleSize;
        this.esp = Arrays.copyOf(esp, dim);
        this. espConj=new double[dim][dim];
        Funcs.lenMismatchWarning(dim, esp.length);
        Funcs.lenMismatchWarning(dim, espConj.length);
        for (int i = 0; i < dim; i++) {
            Funcs.lenMismatchWarning(dim, espConj[i].length);
            for (int j = 0; j < dim; j++) {

                this.espConj[i][j] = espConj[i][j];
            }
        }
    }
    public double [][] estPopCovar(){
        double [][] res=new double[dim][dim];
        for (int i=0;i<dim;i++){
            for(int j=0;j<dim;j++)
                res[i][j]=espConj[i][j]-esp[i]*esp[j];
        }
        return res;
    }

    public double [][] estSampCovar() {
        double[][] estVar = estPopCovar();
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                //sample adjustment       
                estVar[i][j] *= sampleSize / (sampleSize - 1);
            }
        }

        return estVar;
    }
    public double [][] estVarByKnownMeans(double [] means){
        double [][] res=new double[dim][dim];
        for (int i=0;i<dim;i++)
            for(int j=0;j<dim;j++)
                res[i][j]=espConj[i][j]-means[i]*means[j];
        return res;
    }
    
}
