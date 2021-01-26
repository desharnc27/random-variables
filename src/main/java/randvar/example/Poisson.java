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
import tools.Small;

/**
 *
 * @author desha
 */
public class Poisson extends NNIRandomLaw{
    private double lambda;
    
    public Poisson(double lambda){
        setLambda(lambda);
    }
    @Override
    public String getName(){
        return "Poisson"+Funcs.paramStr(lambda);
    }
    public final void setLambda(double lambda){
        this.lambda=lambda;
        if (lambda<=0){
            throw (IPVE.positive("lambda", lambda));
        }
    }
    @Override
    public double randomExec() {
        double timeCumul =0;
        double answer = -1;
        ExponentialLaw el = new ExponentialLaw(lambda);
        while (timeCumul<1){
            timeCumul += el.randomExec();
            answer+=1;
        }
        return answer;
            
    }

    @Override
    public AnalyticSummary analyticEval() {
        return AnalyticSummary.buildByVar(lambda, lambda);
    }
    
    //These 2 methods of calculation are slower than optimal but avoid overflow risk of calling a factorial
    
    @Override
    public double cumulative(double d){
        int i = (int)(d+Small.EPSILON);
        if (i<0)
            return 0;
        double term= 1;
        int act =1;
        double sum =term;
        while (act<=i){
            term *= lambda/act;
            sum +=term;
            act++;
        }
        return sum*Math.exp(-lambda);
    }
    @Override
    public double exactProb(int i){
        if (i<0)
            return 0;
        double term= 1;
        int act =1;
        while (act<=i){
            term *= lambda/act;
            act++;
        }
        return term*Math.exp(-lambda);
    }
    
    
}
