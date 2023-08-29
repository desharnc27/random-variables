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
public class Student extends Function1P{
    int v;
    public Student(int v){
        this.v = v;
    }
    @Override
    public double f(double x) {
        return MiscFunctions.StudentDensity(x, v);
    }
    
}
