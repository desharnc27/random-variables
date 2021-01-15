/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 *
 * @author desha
 */
public class IPVE extends UnsupportedOperationException {

    //String wrongVariable;
    //String wrongVal;
    //String reqSet;

    public static IPVE proba(String wrongVariable, double p) {
        return create (wrongVariable,String.valueOf(p),"[0,1]");
    }
    public static IPVE nonNeg(String wrongVariable, double p) {
        return create (wrongVariable,String.valueOf(p),"[0,inf");
    }
    public static IPVE positive(String wrongVariable, double p) {
        return create (wrongVariable,String.valueOf(p),"(0,inf");
    }

    public static IPVE create(String wrongVariable, String wrongVal, String reqSet) {
        String message = "faulty parameter: "+wrongVariable;
        message += " = " + wrongVal;
        message += ". Value must be in " +reqSet;
        return new IPVE(message);
        
    }


    
    public IPVE(String message) {
        super(message);
    }
}
