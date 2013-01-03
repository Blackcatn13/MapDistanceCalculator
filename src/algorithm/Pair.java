package algorithm;

/**
 * File: Pair.java
 * Created on 02/01/2013 by Marc
 *
 * Class to hold two elements.
 */
public class Pair {

    /**
     * Variable that holds a cost.
     */
    private float c;

    /**
     * Variable that holds the name of the line.
     */
    private String n;

    /**
     * Default constructor of the class.
     */
    public Pair() {
        c = 0;
        n = "";
    }

    /**
     * Constructor that takes the cost and the line name.
     * @param nc Cost.
     * @param nn Line name.
     */
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
