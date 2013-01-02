/**
 * File: Pair.java
 * Created on 02/01/2013 by Marc
 * 
 * Class to hold two elements.
 */
package algorithm;

public class Pair {

    private float c;
    
    private String n;
    
    public Pair() {
	c = 0;
	n = "";
    }
    
    public Pair(float nc, String nn) {
	c = nc;
	n = nn;
    }

    /**
     * @return the c
     */
    public final float getC() {
        return c;
    }

    /**
     * @param c the c to set
     */
    public final void setC(float c) {
        this.c = c;
    }

    /**
     * @return the n
     */
    public final String getN() {
        return n;
    }

    /**
     * @param n the n to set
     */
    public final void setN(String n) {
        this.n = n;
    }
    
}
