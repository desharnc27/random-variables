/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multirandvar;

/**
 * Param order: nb of tries (n), first try  
 * @author desha
 */

public class FullCrescendo extends MultiRandomLaw {
    public FullCrescendo(){
        super(3);
    }
    @Override
    public double [] randomExec() {
        double count = 0;
        double val = 0;
        double previousVal = 0;
        do {
            previousVal = val;
            val = Math.random();
            count += 1;
        } while (val > previousVal);
        return new double[]{count,previousVal,val};
    }

    @Override
    public MultiAnalyticSummary analyticEval() {
        double e= Math.E;
        double [] esp = new double[]{e,e-2,(e-2)/2};
        double [][]espConj = new double [3][3];
        espConj[0][0]=3*e;
        espConj[0][1]=2;
        espConj[1][1]=6-2*e;
        espConj[0][2]=1;
        espConj[1][2]=3-e;
        espConj[2][2]=2-2*e/3;
        for (int i=0;i<dim;i++){
            for (int j=0;j<i;j++){
                espConj[i][j]=espConj[j][i];
            }
        }
        
        return new MultiAnalyticSummary(esp,espConj);
    }
    
}
