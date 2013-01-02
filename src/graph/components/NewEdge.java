/**
 * File: NewEdge.java
 * Created on 02/01/2013 by Marc
 * 
 * New Edge to hold lines.
 */
package graph.components;

import java.util.ArrayList;

import algorithm.Pair;

public class NewEdge {

    private float WalkCost;
    
    private ArrayList<Line> lines;
    
    private Node Lneighbor;
   
    private Node Rneighbor;
    
    public NewEdge() {
	Lneighbor = new Node();
	Rneighbor = new Node();
	WalkCost = 0;
	lines = new ArrayList<Line>();
    }
    
    public NewEdge(Node ln, Node rn, float c) {
	Lneighbor = ln;
	Rneighbor = rn;
	WalkCost = c;
	lines = new ArrayList<Line>();
    }
    
    public Node getNeighbor(Node me) {
	if(Lneighbor == me) {
	    return Rneighbor;
	}
	else {
	    return Lneighbor;
	}
    }
    
    public boolean isNode(Node n) {
	return Lneighbor == n || Rneighbor == n;
    }
    
    public float getWalkCost() {
	return WalkCost;
    }
    
    public ArrayList<String> getLines() {
	ArrayList<String> names = new ArrayList<String>();
	for(Line l : lines) {
	    names.add(l.getName());
	}
	return names;
    }
    
    public float getCostInLine(String name) {
	for(Line l : lines) {
	    if(l.SameLine(name)) {
		return l.getCost();
	    }
	}
	return -1;
    }
    
    public ArrayList<Pair> getCost(Transports t) {
	
	ArrayList<Pair> ret = new ArrayList<Pair>();
	if(t == Transports.WALK) {
	    ret.add(new Pair(WalkCost, "Walk"));
	    return ret;
	}
	for(Line l : lines) {
	    if(l.getTransport() == t) {
		ret.add(new Pair(l.getCost(), l.getName()));
	    }
	}
	return ret;
    }
}
