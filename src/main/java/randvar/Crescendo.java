/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package randvar;

/**
 *
 * @author desha
 */
public class Crescendo extends RandomLaw {

    @Override
    public double randomExec() {
        double count = 0;
        double val = 0;
        double previousVal = 0;
        do {
            previousVal = val;
            val = Math.random();
            count += 1;
        } while (val > previousVal);
        return count;
    }

    @Override
    public AnalyticSummary analyticEval() {
        double e =Math.E;
        return new AnalyticSummary(e,3*e);
    }

}
