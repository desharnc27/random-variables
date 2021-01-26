/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import exception.NIFE;
import multirandvar.FullCrescendo;
import randvar.example.ExponentialLaw;
import randvar.example.ExponentialLaw;
import randvar.example.ExponentialLaw;
import randvar.example.Crescendo;
import randvar.example.ExponentialLaw;
import randvar.example.ExponentialLaw;

/**
 *
 * @author desha
 */
public class Test {
    public static void main(String [] args){
        ExponentialLaw el=new ExponentialLaw(.022);
        //el.compareAnalyticToSample(10000);
        System.out.println(el.getName());
        //double [] ends = new double []{1,4,6,7,8.1,15.3,19.5,20,20.5,25,28.8,34,37,44,53,70}; 
        double [] ends = new double []{0,21,21.5}; 
        
        System.out.println(el.compareAnalyticCumulativeToSample(ends,10000,0.02));
    }
    
}
