/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import tools.Pascal;

import tools.Normal;

/**
 *
 * @author desha
 */
public class NoProbs {
    public static void main0(){
        //int n=300;
        //System.out.println(Pascal.doubleFact(n));
        int [][] combs=new int[][]{
            {56,22},
            {74,22},
            {5,3},
            {16233,3},
            {16233,15},
        };
        try{
            for (int [] arr: combs){
                int n = arr[0];
                int k = arr[1];
               System.out.println("C("+n+","+k+")= "+Pascal.get(n,k)); 
            }
            //System.out.println(Pascal.get(16672,4));
        }catch(ArithmeticException e){
            throw e;
        }
        
    }
    public static void main1(){
        System.out.println(Normal.cumulativeProb(-4));
    }
    public static void main(String [] args){
        main1();
    }
}
