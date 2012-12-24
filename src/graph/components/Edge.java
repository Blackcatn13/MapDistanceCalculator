/**
 * File: Edge.java
 * Created on 07/12/2012 by Marc
 * 
 * TODO : <insrt description here>
 */
package graph.components;

import java.util.Date;

public class Edge {

    private Cost cost;
    private Node Lneighbor;
    private Node Rneighbor;
    
    public Edge(Node lneighbor, Node rneighbor, float c) {
	Lneighbor = lneighbor;
	Rneighbor = rneighbor;
	cost = new Cost(c);
    }
    
    public float getCost() {
	return cost.getCost();
    }
    
    public float getCost(Date hour) {
	return cost.getCost(hour);
    }
    
    public Node getLneighbor() {
	return Lneighbor;
    }
    
    public Node getRneighbor() {
	return Rneighbor;
    }
    
    public Node getNeighbor(Node me) {
	if(Lneighbor == me) {
	    return Rneighbor;
	}
	else {
	    return Lneighbor;
	}
    }
    
}
