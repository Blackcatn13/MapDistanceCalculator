package algorithm;

import graph.components.Node;
import graph.components.Transports;

import java.util.ArrayList;

/**
 * File: Triplet.java
 * Created on 26/12/2012 by Marc
 *
 * Class to hold three elements in one.
 * Aggregated Value.
 * G(x).
 * Path.
 */
public class Triplet implements Comparable<Object> {

    /**
     * Variable to hold the f(x) value.
     */
    private float fx;

    /**
     * Variable to hold the g(x) value.
     */
    private float gx;

    /**
     * Variable to hold the path.
     */
    private ArrayList<InfoPath> path;

    /**
     * Variable to hold the last transport used in this path.
     */
    private Transports lastTransport;

    /**
     * Variable to hold the number of transfers make's between transports in this path.
     */
    private int transTransfers;

    /**
     * Variable to hold the last line used in this path.
     */
    private String lastLine;

    /**
     * Variable to hold the number of transfers make's between lines in this path.
     */
    private int lineTransfers;

    /**
     * Default constructor of the class.
     */
    public Triplet() {
        fx = 0;
        gx = 0;
        path = new ArrayList<InfoPath>();
        transTransfers = 0;
        lineTransfers = 0;
        lastLine = "";
        lastTransport = Transports.NOTHING;
    }

    /**
     * Constructor of the class that take all the arguments to save it.
     * @param f value of the f(x).
     * @param g value of the g(x).
     * @param p path.
     */
    public Triplet(float f, float g, ArrayList<InfoPath> p) {
        fx = f;
        gx = g;
        path = p;
        transTransfers = 0;
        lineTransfers = 0;
        lastLine = "";
        lastTransport = Transports.NOTHING;
    }


    /**
     * Constructor of the class that take all the arguments to save it.
     * @param f value of the f(x).
     * @param g value of the g(x):
     * @param p path.
     * @param ttsf number of transport transfers.
     * @param t last transport used.
     * @param ltsf number of line transfers.
     * @param n last line used.
     */
    public Triplet(float f, float g, ArrayList<InfoPath> p, int ttsf, Transports t, int ltsf, String n) {
        fx = f;
        gx = g;
        path = p;
        transTransfers = ttsf;
        lineTransfers = ltsf;
        lastLine = n;
        lastTransport = t;
    }

    /**
     * Getter of the f(x) value.
     * @return the f(x) value.
     */
    public float getFx() {
        return fx;
    }

    /**
     * Getter of the g(x) value.
     * @return the g(x) value.
     */
    public float getGx() {
        return gx;
    }

    /**
     * Getter of the path.
     * @return the path.
     */
    public ArrayList<InfoPath> getPath() {
        return path;
    }

    /**
     * Function to now if the path is the same.
     * @param a triplet to compare.
     * @return true if the path is the same.
     */
    public boolean equalPath(Triplet a) {
        if (this.path == a.path) {
            return true;
        }
        return false;
    }

    /**
     * Function to get the last node of the path.
     * @return the last node of the path.
     */
    public Node getFirst() {
        return path.get(path.size() - 1).getSNode();
    }

    /**
     * Function to compare two elements to add it in order.
     * @param a Object to compare.
     * @return 0 if equals, 1 if smaller and -1 if greater.
     */
    @Override
    public int compareTo(Object a) {
        Triplet au = (Triplet) a;
        int aux = 0;
        if (au.path.equals(path)) {
            aux = 0;
        } else if (au.fx <= fx) {
            aux = 1;
        } else {
            aux = -1;
        }
        return aux;
    }

    /**
     * Function to get the number of transfer in that path, and change the last transport used.
     * @param t The new transport used for this path.
     * @param name The new line used for this path.
     * @param mT The maximum number of transport transfers.
     * @param mL The maximum number of line transfers.
     * @return If this Triplet transfers are in the mT and mL range.
     */
    public boolean updateTransport(Transports t, String name, int mT, int mL) {
        if (lastTransport != Transports.NOTHING) {
            if (t != lastTransport) {
                transTransfers++;
            }
        }
        lastTransport = t;
        if (lastLine != "") {
            if (!equalString(name, lastLine)) {
                lineTransfers++;
            }
        }
        lastLine = name;
        return ((transTransfers <= mT) && (lineTransfers <= mL));
    }

    /**
     * Getter of the last transport used.
     * @return The last transport used.
     */
    public Transports getLastTransport() {
        return lastTransport;
    }

    /**
     * Function to return if two strings are equals.
     * @param n1 First string.
     * @param n2 Second string.
     * @return True if equals, false otherwise.
     */
    private boolean equalString(String n1, String n2) {
        return (n1.toLowerCase().equals(n2.toLowerCase()));
    }

    /**
     * Getter of the transport transfers.
     * @return The number of transport transfers.
     */
    public int getTransTransfers() {
        return transTransfers;
    }

    /**
     * Getter of the line transfers.
     * @return The number of line transfers.
     */
    public int getLineTransfers() {
        return lineTransfers;
    }

    /**
     * Getter of the last line used.
     * @return The last line used.
     */
    public String getLastLine() {
        return lastLine;
    }

}
