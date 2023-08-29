/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

/**
 *
 * @author desharnc27
 */
public class MiscFunctions {

    private static double zhoka(int doubledN) {
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

    private static double naiveKala(int doubledN) {
        return zhoka(doubledN - 1) / zhoka(doubledN - 2);
    }

    private static double wiseKala(int doubledN) {
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

    public static double StudentDensity(double t, int v) {
        double val0 = 1 / Math.sqrt(Math.PI * v);
        double val1 = wiseKala(v);
        double val2 = Math.pow(1 + t * t / v, -(1.0 + v) / 2);
        return val0 * val1 * val2;
    }
    public static double studentCumulativeApprox(double t, int v){
        if (t<0){
            return 1 - studentCumulativeApprox(-t,v);
        }
        int nbIntervals = (int)t + 1;
        Student stu = new Student(v);
        return 0.5 + stu.approximateSimpsonArea(0, t, 4, nbIntervals);
    }
}
