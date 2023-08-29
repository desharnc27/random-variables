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
public abstract class Function1P {
    public abstract double f(double x);
    

    public double approximateSimpsonArea(double from, double to, int polynDegree, int nbIntervals) {
        double cumul = 0;
        double step = (to - from) / nbIntervals;
        double low = from;
        double high = low + step;
        for (int i = 0; i < nbIntervals; i++) {

            if (i == nbIntervals - 1) {
                high = to;//just to minimize numerical errors
            }
            cumul += approximateSingleSimpsonArea(low, high, polynDegree);
            low = high;
            high = low + step;

        }
        return cumul;
    }

    public double approximateSingleSimpsonArea(double from, double to, int polynDegree) {
        double step = (to - from) / polynDegree;
        double[][] points = new double[polynDegree + 1][2];
        for (int i = 0; i < points.length; i++) {

            double x = from + step * i;
            if (i == polynDegree) {
                x = to; //just to minimize numerical errors
            }
            points[i][0] = x;
            points[i][1] = f(x);
        }
        Polyn approx = Polyn.polynGoingBy(points);
        return Polyn.definiteIntegral(approx, from, to);
    }
}
