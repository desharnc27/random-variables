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
public class Small {

    public final static double EPSILON = 1.0 / (2 << 28);
    public final static double BIG_EPSILON = Math.sqrt(EPSILON);

    public static boolean ishEq(double a, double b) {
        return Math.abs(a - b) < BIG_EPSILON;
    }
    public static boolean eq(double a, double b) {
        return Math.abs(a - b) < EPSILON;
    }

    public static boolean leqThan(double a, double b) {
        return a - b < EPSILON;
    }

    public static boolean geqThan(double a, double b) {
        return a - b > -EPSILON;
    }

    public static boolean lowerThan(double a, double b) {
        return a - b < -EPSILON;
    }

    public static boolean greaterThan(double a, double b) {
        return a - b > EPSILON;
    }
}
