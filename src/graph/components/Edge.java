/**
 * File: Edge.java
 * Created on 07/12/2012 by Marc
 * 
 * @author Marc
 * Class to describe a edge of a graph.
 */
package graph.components;

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
    public Edge(Node lneighbor, Node rneighbor, float c) {
	Lneighbor = lneighbor;
	Rneighbor = rneighbor;
	cost = new Cost(c);
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
    
}
