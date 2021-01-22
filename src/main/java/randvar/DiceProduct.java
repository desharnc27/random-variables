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

/**
 * 
 * X is obtained by throwing nDices dice and multiplying all obtained numbers together. Every dice has nFaces faces,
 * numbered from 1 to nFaces inclusively.
 * 
 * Example (nDices=4,nFaces=7): throw 4 dice, get (5,1,7,2), therefore X = 1*2*5*7 = 70
 */
public class DiceProduct extends RandomLaw {
    private int nFaces;
    private int nDices;
    
    public DiceProduct(int nDices){
        this(nDices,6);
    }
    public DiceProduct(int nDices, int nFaces){
        setNbFaces(nFaces);
        setNbDices(nDices);
    }
    @Override
    public String getName(){
        return "DiceProduct"+Funcs.paramStr(nFaces,nDices);
    }
    public final void setNbDices(int i){
        nDices=i;
        if (nDices<1){
            throw  (IPVE.positive("nDices", nDices));
        }
    }
    public final void setNbFaces(int i){
        nFaces=i;
        if (nFaces<1){
            throw  (IPVE.positive("nFaces", nFaces));
        }
    }
    @Override
    public double randomExec() {
        double res=1;
        for (int i=0;i<nDices;i++)
            res*= Math.ceil(nFaces*Math.random());
        return res;
    }

    @Override
    public AnalyticSummary analyticEval() {
        int accX=0;
        int accX2=0;
        for (int i=1;i<=nFaces;i++){
            accX+=i;
            accX2+=i*i;
        }
        double espX = (accX+0.0)/nFaces;
        double espX2 = (accX2+0.0)/nFaces;
        
        
        
        return new AnalyticSummary(Math.pow(espX,nDices),Math.pow(espX2,nDices));
    }
    public static void main (String [] args){
        DiceProduct d = new DiceProduct(2);
        for (int i=0;i<1;i++)
            d.compareAnalyticToSample(10000);
    }
    
}
