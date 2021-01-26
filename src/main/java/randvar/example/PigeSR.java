/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package randvar.example;

import exception.IPVE;
import randvar.AnalyticSummary;
import randvar.NNIRandomLaw;
import tools.Funcs;
import tools.Pascal;

/**
 *
 * @author desha
 */
public class PigeSR extends NNIRandomLaw{
    private int kYes;
    private int kNo;
    private int n;

    public PigeSR(int kYes, int kNo, int n) {
        fastSetN(n);
        fastSetKYes(kYes);
        setKNo(kNo);
    }
    @Override
    public String getName(){
        return "PigeSR"+Funcs.paramStr(kYes,kNo,n);
    }
    private void fastSetN(int i){
        n=i;
        if (n<1){
            throw(IPVE.positive("n", n));
        }
    }
    public final void setN(int i){
        fastSetN(i);
        checkSum();
    }
    private void fastSetKYes(int i){
        kYes=i;
        if (n<1){
            throw(IPVE.positive("kYes", kYes));
        }
    }
    public final void setKYes(int i){
        fastSetKYes(i);
        checkSum();
    }
    private void fastSetKNo(int i){
        kNo=i;
        if (n<1){
            throw(IPVE.positive("kNo", kNo));
        }
    }
    public final void setKNo(int i){
        fastSetKNo(i);
        checkSum();
    }
    public final void checkSum(){
        if (kNo+kYes<n){
            throw IPVE.create("n",String.valueOf(n), "(0,kYes+kNo]");
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
    
    @Override
    public double cumulative(double d){
        if (d>n || d>kYes)
            return 1;
        return super.cumulative(d);
    }
    @Override
    public double exactProb(int i){
        if (i<0 || i>n || i>kYes || n-i>kNo)
            return 0;
            
        int kTot =kNo+kYes;
        return Pascal.get(kYes,i)*Pascal.get(kNo,n-i)/(double)Pascal.get(kTot,n);
    }
    
    
            
}
