/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

/**
 *
 * @author desha
 */

//TODO: bring to myTools

public class Normal {
    private static double  epsilon = 1.0/(2<<28);
    private static double bigEpsilon = Math.sqrt(epsilon);
    
    /**
     * Computes P(x>X), where X follows normal standard distribution 
     * @param x
     * @return P(x>X), where X follows normal standard distribution
     */
    public static double normalRep(double x) {
        
        //Method: Computes the MacLaurin serie of  the integral of the standard normal density.
        //Stops when accuracy is sufficient.
        
        double toler = epsilon;
        double val = 0;

        double bobo = -x * x / 2;
        double curTerm = x;

        int i = 0;
        do {
            val += curTerm;
            curTerm *= bobo;
            curTerm *= (2 * i + 1);
            i++;
            curTerm /= (2 * i + 1);
            curTerm /= i;
            //System.out.println(curTerm);

        } while (curTerm > toler || curTerm < -toler);

        double coeff = Math.sqrt(1 / (2 * Math.PI));
        return val * coeff + 0.5;
    }
    /**
     * Computes z such that P(z>X)=alpha, where X follows normal standard distribution 
     * @param alpha
     * @return z
     */
    public static double zOf2(double alpha) {
        
        //Method: approximative binary search
        double zDown = -4;
        double zUp = 4;
        double alphaDown = 0;
        double alphaUp = 1;
        double zMid = 0;
        double alphaMid = 0.5;
        while (alphaUp - alphaDown > bigEpsilon) {
            zMid = (zDown + zUp) / 2;
            System.out.println(zMid);
            alphaMid = normalRep(zMid);
            if (alpha > alphaMid) {
                zDown = zMid;
                alphaDown = alphaMid;
            } else {
                zUp = zMid;
                alphaUp = alphaMid;
            }
        }
        return zMid;
    }
    /**
     * Computes z such that P(z>X)=alpha, where X follows normal standard distribution  
     * @param alpha
     * @return z
     */
    public static double zOf(double alpha) {
        
        //Method: Newton convergence
        
        double coeff = Math.sqrt(1 / (2 * Math.PI));
        double x = 0;
        double nextX = x;
        do{
            x = nextX;
            double denom =coeff * Math.exp(-x*x/2);
            nextX = x - (normalRep(x)-alpha)/denom;
            System.out.println(nextX);
        }while(Math.abs(nextX-x)>bigEpsilon);
        return nextX;
    }

    public static double randNormal() {
        double ran = Math.random();
        return zOf(ran);
    }

    public static void main(String[] args) {
        double alpha=Math.random();
        System.out.println("ans0: "+zOf2(alpha));
        System.out.println("ans1: "+zOf(alpha));
    }
}
