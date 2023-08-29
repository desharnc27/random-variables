/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.ArrayList;
import java.util.Iterator;
import tools.Function1P;
import tools.MiscFunctions;

/**
 *
 * @author desharnc27
 */
public class StudentCumulTest {

    public static void main0(){
        double t = - 3.7;
        int v = 11;
        double cumul = MiscFunctions.studentCumulativeApprox(t,v);
        System.out.printf("student(%d).cumulative(%f)=%f\n",v,t,cumul);
                
    }
    public static void main(String [] args){
        main0();
    }
}
