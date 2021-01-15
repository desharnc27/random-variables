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
public class AnalyticSummary {
    
    double estMean;
    double estMeanSq;
    //int sampleSize;
    public AnalyticSummary(double estMean, double estMeanSq){
        this.estMean=estMean;
        this.estMeanSq=estMeanSq;
        
    }
    //TODO:change name (-pop)
    public double getVar(){
        return estMeanSq - estMean*estMean;
    }
    public static AnalyticSummary buildByVar(double estMean, double vari){
        return new AnalyticSummary(estMean,estMean*estMean+vari);
    }
    
}
