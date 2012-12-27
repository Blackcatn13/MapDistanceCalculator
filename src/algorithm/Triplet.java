/**
 * File: Triplet.java
 * Created on 26/12/2012 by Marc
 * 
 * Class to hold three elements in one.
 * Aggregated Value.
 * G(x).
 * Path.
 *
 */
package algorithm;

import graph.components.Node;

import java.util.ArrayList;

public class Triplet implements Comparable<Object>{

    private float Fx;
    private float Gx;
    private ArrayList<Node> path;
    
    public Triplet() {
	Fx = 0;
	Gx = 0;
	path = new ArrayList<Node>();
    }
    
    public Triplet(float f, float g, ArrayList<Node> p) {
	Fx = f;
	Gx = g;
	path = p;
    }
    
    public float getFx() {
	return Fx;
    }
    
    public float getGx() {
	return Gx;
    }
    
    public ArrayList<Node> getPath(){
	return path;
    }
    
    public boolean EqualPath(Triplet a) {
	if(this.path == a.path) {
	    return true;
	}
	return false;
    }
    
    public Node getFirst() {
	return path.get(path.size() - 1);
    }

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
    
}
