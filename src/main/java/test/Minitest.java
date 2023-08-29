/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import randvar.example.NormalDist;
import tools.Pascal;
import tools.Student;

/**
 *
 * @author desharnc27
 */
public class Minitest {

    public static double zhoka(int doubledN) {
        if (doubledN == 0) {
            return 1;
        }
        if (doubledN == 1) {
            return (Math.sqrt(Math.PI)) / 2;
        }
        if (doubledN > 1) {
            return (doubledN / 2.0) * zhoka(doubledN - 2);
        }
        return zhoka(doubledN + 2) / (doubledN / 2.0 + 1);
    }

    public static double naiveKala(int doubledN) {
        return zhoka(doubledN - 1) / zhoka(doubledN - 2);
    }

    public static double wiseKala(int doubledN) {
        if (doubledN == 1 || doubledN == 2) {
            return naiveKala(doubledN);
        }
        if (doubledN <= 0) {
            System.out.println("TODO:exception...");
            return 0;
        } else {
            return wiseKala(doubledN - 2) * (doubledN - 1) / (doubledN - 2);
        }
    }

    public static void main(String[] args) {
        double a = zhoka(-1);
        System.out.println(naiveKala(12));
        System.out.println(wiseKala(12));
        
        
        int df = 41;
        double t = -2.556;
        
        Student st = new Student(df);
        System.out.println(0.5 - st.approximateSingleSimpsonArea(t,0,2));
        System.out.println(0.5 - st.approximateSingleSimpsonArea(t,0,6));
        System.out.println(0.5 - st.approximateSimpsonArea(t, 0, 6, 4));
        
        NormalDist nor = NormalDist.buildByVar(0,df/(df-2.0));
        System.out.println(nor.cumulative(t));
        
    }
    
}
