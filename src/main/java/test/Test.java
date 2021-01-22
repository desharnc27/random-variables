/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import exception.NIFE;
import multirandvar.FullCrescendo;
import randvar.ExponentialLaw;
import randvar.Binomial;
import randvar.Binomial;
import randvar.Crescendo;
import randvar.Binomial;
import randvar.Binomial;

/**
 *
 * @author desha
 */
public class Test {
    public static void main(String [] args){
        Binomial el=new Binomial(5,0.7);
        el.compareAnalyticToSample(10000);
        System.out.println(el.getName());
        try{
            throw NIFE.cumulative();
        }catch (NIFE ex ){
            System.out.println("caught: "+ex);
        }
    }
    
}
