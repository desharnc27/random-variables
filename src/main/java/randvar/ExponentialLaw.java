/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package randvar;

import exception.IPVE;
import tools.Funcs;

/**
 *
 * @author desha
 */
public class ExponentialLaw extends RandomLaw {

    private double lambda;

    public ExponentialLaw(double lambda) {
        setLambda(lambda);
    }
    @Override
    public String getName(){
        return "Exponential"+Funcs.paramStr(lambda);
    }

    public final void setLambda(double lambda) {
        this.lambda = lambda;
        if (lambda <= 0) {
            throw (IPVE.positive("lambda", lambda));
        }
    }

    @Override
    public double randomExec() {
        double ran = Math.random();
        return Math.log(ran) / (-lambda);
    }

    @Override
    public AnalyticSummary analyticEval() {
        return new AnalyticSummary(1 / lambda, 2 / (lambda * lambda));
    }

}
