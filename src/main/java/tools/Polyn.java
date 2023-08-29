/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 *
 * @author desharnc27
 */
public class Polyn {

    final static double TINY = 1.0 / (1 << 24);
    private double[] coeffs;

    public Polyn() {
        coeffs = new double[]{};
    }

    public Polyn(double... floats) {
        coeffs = floats;
    }

    public void purify() {
        int worthIdx = degree();
        while (Math.abs(coeffs[worthIdx]) < TINY) {
            worthIdx--;
        }
        double[] newArray = new double[worthIdx + 1];
        System.arraycopy(coeffs, 0, newArray, 0, worthIdx + 1);
        coeffs = newArray;

    }

    public int degree() {
        return coeffs.length - 1;
    }

    public double f(double x) {
        double res = coeffs[degree()];
        for (int i = degree() - 1; i >= 0; i--) {
            res *= x;
            res += coeffs[i];
        }
        return res;
    }

    @Override
    public String toString() {
        return Arrays.toString(coeffs);
    }

    public static Polyn mult(Polyn p0, Polyn p1) {
        double[] res = new double[p0.degree() + p1.degree() + 1];
        for (int i = 0; i <= p0.degree(); i++) {
            for (int j = 0; j <= p1.degree(); j++) {
                res[i + j] += p0.coeffs[i] * p1.coeffs[j];
            }
        }
        return new Polyn(res);
    }

    public static Polyn sum(Polyn p0, Polyn p1) {
        if (p0.degree() < p1.degree()) {
            return sum(p1, p0);
        }
        int degree = p0.degree();
        int minDegree = p1.degree();
        double[] outCoeffs = new double[degree + 1];
        for (int i = 0; i <= minDegree; i++) {
            outCoeffs[i] = p0.coeffs[i] + p1.coeffs[i];
        }
        for (int i = minDegree + 1; i <= degree; i++) {
            outCoeffs[i] = p0.coeffs[i];
        }
        Polyn res = new Polyn(outCoeffs);
        res.purify();
        return res;
    }

    public static Polyn polynGoingBy(double[][] points) {
        Polyn res = new Polyn();
        for (int i = 0; i < points.length; i++) {
            Polyn term = new Polyn(1);
            for (int j = 0; j < points.length; j++) {
                if (i == j) {
                    term = Polyn.mult(term, new Polyn(points[i][1]));
                    continue;
                }
                Polyn factA = new Polyn(-points[j][0], 1);
                Polyn factB = new Polyn(1 / (points[i][0] - points[j][0]));
                term = Polyn.mult(term, factA);
                term = Polyn.mult(term, factB);

            }
            res = Polyn.sum(term, res);
        }
        return res;
    }

    public static Polyn integral(Polyn p) {
        double[] coeffs = new double[p.degree() + 2];
        for (int i = 0; i <= p.degree(); i++) {
            coeffs[i + 1] = p.coeffs[i] / (i + 1.0);
        }
        return new Polyn(coeffs);
    }

    public static double definiteIntegral(Polyn p, double from, double to) {
        Polyn integral = integral(p);
        return integral.f(to) - integral.f(from);
    }

    public static void main(String[] args) {
        double[] arr0 = new double[]{1, 0, 1};
        double[] arr1 = new double[]{1, -1, -1};
        Polyn p0 = new Polyn(arr0);
        Polyn p1 = new Polyn(arr1);

        Polyn res0 = Polyn.sum(p0, p1);
        Polyn res1 = Polyn.mult(p0, p1);

        System.out.println("res0: " + res0);
        System.out.println("res1: " + res1);

        double[][] points = new double[][]{
            {11, 2},
            {3, 4},
            {5, 67},};

        Polyn res2 = Polyn.polynGoingBy(points);
        System.out.println("res2: " + res2);

        double[] testx = new double[]{3, 5, 7, 11};
        for (double x : testx) {
            System.out.printf("f(%f)= %f\n", x, res2.f(x));
        }

        System.out.println("definiteIntegral(res1,-3,7): " + definiteIntegral(res1, -3, 7));

        Student stu = new Student(5);
        System.out.println("stu.f(2):" + stu.f(2));
        double cumul = 0.5 + stu.approximateSimpsonArea(0, 2, 2, 3);
        System.out.println("cumul: " + cumul);
    }
}
