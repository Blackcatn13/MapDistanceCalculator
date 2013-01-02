/**
 * File: Triplet.java
 * Created on 26/12/2012 by Marc
 * 
 * Class to hold three elements in one.
 * Aggregated Value.
 * G(x).
 * Path.
 */
package algorithm;

import graph.components.Node;
import graph.components.Transports;

import java.util.ArrayList;

public class Triplet implements Comparable<Object>{

    /**
     * Variable to hold the f(x) value.
     */
    private float Fx;
    
    /**
     * Variable to hold the g(x) value.
     */
    private float Gx;
    
    /**
     * Variable to hold the path.
     */
    private ArrayList<InfoPath> path;
    
    /**
     * Variable to hold the last transport used in this path.
     */
    private Transports lastTransport;
    
    /**
     * Variable to hold the number of transfers make's in this path.
     */
    private int TransTransfers;
    
    private String lastLine;
    
    private int LineTransfers;
    
    /**
     * Default constructor of the class.
     */
    public Triplet() {
	Fx = 0;
	Gx = 0;
	path = new ArrayList<InfoPath>();
	TransTransfers = 0;
	LineTransfers = 0;
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
	Fx = f;
	Gx = g;
	path = p;
	TransTransfers = 0;
	LineTransfers = 0;
	lastLine = "";
	lastTransport = Transports.NOTHING;
    }
    

    /**
     * Constructor of the class that take all the arguments to save it.
     * @param f value of the f(x).
     * @param g value of the g(x):
     * @param p path.
     * @param tsf number of transfers.
     * @param t last transport used.
     */
    public Triplet(float f, float g, ArrayList<InfoPath> p, int Ttsf, Transports t, int Ltsf, String n) {
	Fx = f;
	Gx = g;
	path = p;
	TransTransfers = Ttsf;
	LineTransfers = Ltsf;
	lastLine = n;
	lastTransport = t;
    }
    
    /**
     * Getter of the f(x) value.
     * @return the f(x) value.
     */
    public float getFx() {
	return Fx;
    }
    
    /**
     * Getter of the g(x) value.
     * @return the g(x) value.
     */
    public float getGx() {
	return Gx;
    }
    
    /**
     * Getter of the path.
     * @return the path.
     */
    public ArrayList<InfoPath> getPath(){
	return path;
    }
    
    /**
     * Function to now if the path is the same.
     * @param a triplet to compare.
     * @return true if the path is the same.
     */
    public boolean EqualPath(Triplet a) {
	if(this.path == a.path) {
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
     */
    @Override
    public int compareTo(Object a) {
	Triplet a_ = (Triplet)a;
	int aux = 0;
	if(a_.path.equals(path)) {
	    aux = 0;
	}
	else if(a_.Fx <= Fx) {
    	    aux = 1;
    	} 
	else {
    	    aux = -1;
    	}
	return aux;
    }
    
    /**
     * Function to get the number of transfer in that path, and change the last transport used.
     * @param t The new transport used for this path.
     * @return The number of transfers realized in this path.
     */
    public boolean updateTransport(Transports t, String name, int mT, int mL) {
	if(lastTransport != Transports.NOTHING) {
	    if(t != lastTransport) {
		TransTransfers++;
	    }
	}
	lastTransport = t;
	if(lastLine != "") {
	    if(!equalString(name, lastLine)){
		LineTransfers++;
	    }
	}
	lastLine = name;
	return ((TransTransfers <= mT) && (LineTransfers <= mL));
    }
    
    /**
     * Getter of the transfers.
     * @return The number of transfers.
     */
    public int getTransfers() {
	return TransTransfers;
    }
    
    /**
     * Getter of the last transport used.
     * @return The last transport used.
     */
    public Transports getLastTransport() {
	return lastTransport;
    }
    
    private boolean equalString(String n1, String n2) {
	return (n1.toLowerCase().equals(n2.toLowerCase()));
    }

    public int getTransTransfers() {
	return TransTransfers;
    }
    
    public int getLineTransfers() {
	return LineTransfers;
    }
    
    public String getLastLine() {
	return lastLine;
    }
    
}
