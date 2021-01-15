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
public class Poisson extends RandomLaw{
    private double lambda;
    
    public Poisson(double lambda){
        setLambda(lambda);
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
    
    
}
