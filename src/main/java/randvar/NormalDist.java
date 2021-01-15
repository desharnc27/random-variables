/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package randvar;

import exception.IPVE;
import tools.Normal;

/**
 *
 * @author desha
 */
public class NormalDist extends RandomLaw {

    private double mean;
    private double sd;

    public NormalDist() {
        mean = 0;
        sd = 1;
    }

    public NormalDist(double mean, double sd) {
        setMean(mean);
        setSD(sd);
    }

    @Override
    public double randomExec() {
        double z = Normal.randNormal();
        return (z + mean) * sd;
    }

    @Override
    public AnalyticSummary analyticEval() {
        return AnalyticSummary.buildByVar(mean, sd * sd);
    }

    public void setMean(double mean) {
        this.mean = mean;
    }

    public void setSD(double sd) {
        this.sd = sd;
        if (sd <= 0) {
            throw IPVE.positive("sd", sd);
        }
    }

}
