/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import multirandvar.FullCrescendo;
import randvar.ExponentialLaw;
import randvar.BinomNeg;
import randvar.BinomNeg;
import randvar.Crescendo;
import randvar.Dice;
import randvar.Poisson;

/**
 *
 * @author desha
 */
public class Test {
    public static void main(String [] args){
        Dice el=new Dice(-1);
        el.compareAnalyticToSample(10000);
    }
    
}
