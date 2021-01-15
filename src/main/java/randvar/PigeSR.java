/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package randvar;

import exception.IPVE;

/**
 *
 * @author desha
 */
public class PigeSR extends RandomLaw{
    private int kYes;
    private int kNo;
    private int n;

    public PigeSR(int kYes, int kNo, int n) {
        setN(n);
        setKYes(kYes);
        setKNo(kNo);
    }
    
    public final void setN(int i){
        n=i;
        if (n<1){
            System.out.println(IPVE.positive("n", n));
        }
    }
    
    public final void setKYes(int i){
        kYes=i;
        if (n<1){
            System.out.println(IPVE.positive("kYes", kYes));
        }
    }
    public final void setKNo(int i){
        kNo=i;
        if (n<1){
            System.out.println(IPVE.positive("kNo", kNo));
        }
    }

    @Override
    public double randomExec() {
        int actYes=kYes;
        int actTot=kNo+kYes;
        double res=0.0;
        for (int i=0;i<n;i++){
            boolean success = Math.random()<(actYes/(actTot+0.0));
            if (success){
                actYes--;
                res +=1.0;
            }
            actTot--;
                
        }
        return res;
    }

    @Override
    public AnalyticSummary analyticEval() {
        double kTot = kYes +kNo;
        double p = kYes/kTot;
        double esp = (n*kYes)/(kTot+0.0);
        double vari = n * (1-(n-1)/(kTot-1+0.0))*p*(1-p);
        return AnalyticSummary.buildByVar(esp, vari);
    }
    
    
            
}
