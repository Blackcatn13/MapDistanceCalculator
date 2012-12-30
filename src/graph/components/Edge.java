/**
 * File: Edge.java
 * Created on 07/12/2012 by Marc
 * 
 * @author Marc
 * Class to describe a edge of a graph.
 */
package graph.components;

import java.util.BitSet;
import java.util.Date;

public class Edge {

    /**
     * Variable to hold a cost class.
     */
    private Cost cost;
    /**
     * Variable to one of the nodes.
     */
    private Node Lneighbor;
    /**
     * Variable to the other node.
     */
    private Node Rneighbor;
    
    /**
     * Constructor.
     * @param lneighbor First node of the edge.
     * @param rneighbor Second node of the edge.
     * @param c Cost to travel form the first to the second or otherwise node.
     */
    public Edge(Node lneighbor, Node rneighbor, float Bc, float Sc, float Wc) {
	Lneighbor = lneighbor;
	Rneighbor = rneighbor;
	cost = new Cost(Bc, Sc, Wc);
    }
    
    /**
     * Getter of the cost.
     * @return The cost in that edge.
     */
    public float getCost() {
	return cost.getCost();
    }
    /**
     * Function that return the cost in function of the hour.
     * @param hour time of the day to get the cost.
     * @return The cost in function of the hour. 
     */
    public float getCost(Date hour) {
	return cost.getCost(hour);
    }
    
    /**
     * Function to get the "left" neighbor of the edge.
     * @return The "left" neighbor.
     */
    public Node getLneighbor() {
	return Lneighbor;
    }
    
    /**
     * Function to get the "right" neighbor of the edge.
     * @return The "right" neighbor.
     */
    public Node getRneighbor() {
	return Rneighbor;
    }
    
    /**
     * Function to get the other Node of the edge.
     * @param me Node that represents one nodes of the edge. 
     * @return The other Node of the edge.
     */
    public Node getNeighbor(Node me) {
	if(Lneighbor == me) {
	    return Rneighbor;
	}
	else {
	    return Lneighbor;
	}
    }
    
    /**
     * Function to now if the node is on one of the edge's side.
     * @param n node to check.
     * @return True if the node is in false in other cases.
     */
    public boolean isNode(Node n) {
	return Lneighbor == n || Rneighbor == n;
    }
    
    public float getBusCost() {
	return cost.getBusC();
    }
    
    public float getSubWCost() {
	return cost.getSubWC();
    }
    
    public float getWalkCost() {
	return cost.getWalkC();
    }
    
    public Transports getTransMin(BitSet b) {
	return cost.getMinTrans(b);
    }
}
