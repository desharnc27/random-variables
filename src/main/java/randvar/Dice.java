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
 * 
 * X is the number obtained by throwing a dice.
 * Every dice has nFaces faces, numbered from 1 to nFaces inclusively.
 * 
 */
public class Dice extends RandomLaw {
    private int nFaces=6;
    
    public Dice (){
        this(6);
    }
    public Dice( int nbFaces){
        setNbFaces(nbFaces);
        
    }
    @Override
    public String getName(){
        return "Dice"+Funcs.paramStr(nFaces);
    }
    public final void setNbFaces(int i){
        nFaces=i;
        if (nFaces<1){
            throw (IPVE.create("nFaces", String.valueOf(nFaces), "[1,inf"));
        }
    }
    @Override
    public double randomExec() {
        return Math.ceil(nFaces*Math.random());
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
        return new AnalyticSummary(espX,espX2);
    }
    
    
}
