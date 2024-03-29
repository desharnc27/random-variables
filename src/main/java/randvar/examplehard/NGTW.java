package randvar.examplehard;

import exception.IPVE;

import randvar.NNIRandomLaw;
import randvar.RandomLaw;
import tools.SomeFunctions;
import tools.Pascal;

/**
 *
 * @author desha
 *
 *
 */
/**
 *
 * Suppose player0 and player1 play multiple 1v1 games (example:chess) against
 * each other, where every game has three possible outcomes (W: player0 wins,
 * D:draw, L: player1 wins). In each game, player0 (resp. player1) has a
 * probability p0 (resp. p1) of winning. Therefore, the probability of a draw is
 * 1-p0-p1. They play until one player wins n games.
 *
 * X is the is the total number of games played.
 *
 * Example of execution (n=4): (W,D,W,L,D,D,L,L,W,D,L), player1 wins, X=11
 */
public class NGTW extends NNIRandomLaw {

    private double p0 = 0;
    private double p1 = 0;
    private int n;

    public NGTW(double p0, double p1, int n) {
        setP0(p0);
        setP1(p1);
        setN(n);

    }

    @Override
    public String getName() {
        return "NGTW" + SomeFunctions.paramStr(p0, p1, n);
    }

    public void checkDraw() {
        if (p0 + p1 > 1) {
            throw (IPVE.proba("p0+p1", p0 + p1));
        }
    }

    public final void setP0(double p) {
        p0 = p;
        if (p < 0 || p > 1) {
            throw (IPVE.proba("p0", p));
        }
        checkDraw();
    }

    public final void setP1(double p) {
        p1 = p;
        if (p < 0 || p > 1) {
            throw (IPVE.proba("p1", p));
        }
        checkDraw();
    }

    public final void setN(int i) {
        n = i;
        if (n < 1) {
            throw (IPVE.positive("n", n));
        }
    }

    @Override
    public double randomExec() {
        int n0 = n;
        int n1 = n;
        int nbg = 0;
        while (n0 > 0 && n1 > 0) {
            double ran = Math.random();
            if (ran < p0) {
                n0--;
            } else if (1 - ran < p1) {
                n1--;
            }
            nbg++;
        }
        return nbg;

    }

    @Override
    public double getMean() {
        double d = 1 - p0 - p1;
        double q0 = p0 / (1 - d);
        double q1 = p1 / (1 - d);
        //n = DEF_N;

        //mean_y0 = (d+p0)/p0;
        //mean_y1 = (d+p1)/p1;
        double mean_y0 = 1 / (1 - d);
        double mean_y1 = 1 / (1 - d);

        double[][] mean_case_arr = new double[2][n];
        double[][] prob_case_arr = new double[2][n];

        for (int i = 0; i < n; i++) {
            mean_case_arr[0][i] = n * mean_y0 + i * mean_y1;
            mean_case_arr[1][i] = i * mean_y0 + n * mean_y1;
            prob_case_arr[0][i] = Math.pow(q0, n) * Math.pow(q1, i) * Pascal.comb(i + n - 1, i);
            prob_case_arr[1][i] = Math.pow(q1, n) * Math.pow(q0, i) * Pascal.comb(i + n - 1, i);
        }

        double esp_X = 0;
        for (int i = 0; i < n; i++) {
            esp_X += mean_case_arr[0][i] * prob_case_arr[0][i];
            esp_X += mean_case_arr[1][i] * prob_case_arr[1][i];
        }

        return esp_X;
    }

    @Override
    public double getVar() {
        double d = 1 - p0 - p1;
        double q0 = p0 / (1 - d);
        double q1 = p1 / (1 - d);
        //n = DEF_N;

        //mean_y0 = (d+p0)/p0;
        //mean_y1 = (d+p1)/p1;
        double mean_y0 = 1 / (1 - d);
        double mean_y1 = 1 / (1 - d);
        double var_y0 = mean_y0 * mean_y0 * d;
        double var_y1 = var_y0;

        double[][] mean_case_arr = new double[2][n];
        double[][] prob_case_arr = new double[2][n];
        double[][] var_case_arr = new double[2][n];

        for (int i = 0; i < n; i++) {
            mean_case_arr[0][i] = n * mean_y0 + i * mean_y1;
            mean_case_arr[1][i] = i * mean_y0 + n * mean_y1;
            var_case_arr[0][i] = n * var_y0 + i * var_y1;
            var_case_arr[1][i] = i * var_y0 + n * var_y1;
            prob_case_arr[0][i] = Math.pow(q0, n) * Math.pow(q1, i) * Pascal.comb(i + n - 1, i);
            prob_case_arr[1][i] = Math.pow(q1, n) * Math.pow(q0, i) * Pascal.comb(i + n - 1, i);
        }

        double esp_X = 0;
        double esp_XSq = 0;
        double varesp_X = 0;
        for (int i = 0; i < n; i++) {
            esp_X += mean_case_arr[0][i] * prob_case_arr[0][i];
            esp_X += mean_case_arr[1][i] * prob_case_arr[1][i];
            varesp_X += var_case_arr[0][i] * prob_case_arr[0][i];
            varesp_X += var_case_arr[1][i] * prob_case_arr[1][i];
            esp_XSq += mean_case_arr[0][i] * mean_case_arr[0][i] * prob_case_arr[0][i];
            esp_XSq += mean_case_arr[1][i] * mean_case_arr[1][i] * prob_case_arr[1][i];
        }

        double espvar_X = esp_XSq - esp_X * esp_X;

        double tot_var = varesp_X + espvar_X;
        return tot_var;
    }

}
