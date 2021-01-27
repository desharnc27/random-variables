/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import exception.NIFE;
import multirandvar.FullCrescendo;
import randvar.example.Binomial;
import randvar.example.NormalDist;

import randvar.example.Crescendo;

import tools.Funcs;

/**
 *
 * @author desha
 */
public class Test {
    
    public static void main0(){
        int n =70;
        double p =2.0/n;
        
        int [] vals =  new int []{0,1,3,5,8};
        int [] vals2 =  new int [vals.length];
        for (int i=0;i<vals.length;i++){
            vals2[i]=n-1-vals[i];
        }
        Binomial el = new Binomial(n,p);
        Binomial e2 = new Binomial(n,1-p);
        for (int i=0;i<vals.length;i++){
            int val =vals[i];
            int val2 =vals2[i];
            System.out.println("B"+Funcs.paramStr(n,p)+" at "+val+": "+el.cumulative(val));
            System.out.println("B"+Funcs.paramStr(n,1-p)+" at "+val2+": "+e2.cumulative(val2));
        }
        
    }
        
    
    
    public static void main1(){
        NormalDist el=new NormalDist();
        //el.compareAnalyticToSample(10000);
        System.out.println(el.getName());
        //double [] ends = new double []{1,4,6,7,8.1,15.3,19.5,20,20.5,25,28.8,34,37,44,53,70}; 
        double [] ends = new double []{-4}; 
        
        System.out.println(el.compareAnalyticCumulativeToSample(ends,10000,0.02));
    }
    public static void main(String [] args){
        main1();
    }
    
}
