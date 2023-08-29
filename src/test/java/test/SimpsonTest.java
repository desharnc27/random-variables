/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.ArrayList;
import java.util.Iterator;
import tools.Function1P;

/**
 *
 * @author desharnc27
 */
public class SimpsonTest {
    private static class Sin extends Function1P{

        @Override
        public double f(double x) {
            return Math.sin(x);
        }
        
    }
    public static void main0(){
        ArrayList<Function1P> list = new ArrayList<>();
        Sin sin = new Sin();
        list.add(sin);
        
        Iterator<Function1P> iter = list.iterator();
        while (iter.hasNext()){
            Function1P func = iter.next();
            double approx122 = func.approximateSimpsonArea(1.0,2.0,4,1);
            System.out.println("integ(sin(x)dx,1,2): "+ approx122);
        }
    }
    public static void main(String [] args){
        main0();
    }
}
