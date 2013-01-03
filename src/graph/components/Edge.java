package graph.components;

import java.util.BitSet;
import java.util.Date;

/**
 * File: Edge.java. Created on 07/12/2012 by Marc
 *
 * @author Marc Class to describe a edge of a graph.
 */
@Deprecated
public class Edge {

    /**
     * Variable to hold a cost class.
     */
    private Cost cost;

    /**
     * Variable to one of the nodes.
     */
    private Node lneighbor;

    /**
     * Variable to the other node.
     */
    private Node rneighbor;

    /**
     * Constructor.
     *
     * @param nlneighbor
     *            First node of the edge.
     * @param nrneighbor
     *            Second node of the edge.
     * @param bc
     *            Cost to travel with bus.
     * @param sc
     *            Cost to travel with subway.
     * @param wc
     *            Cost to travel walking.
     */
    public Edge(Node nlneighbor, Node nrneighbor, float bc, float sc, float wc) {
        lneighbor = nlneighbor;
        rneighbor = nrneighbor;
        cost = new Cost(bc, sc, wc);
    }

    /**
     * Getter of the cost.
     *
     * @return The cost in that edge.
     */
    public float getCost() {
        return cost.getCost();
    }

    /**
     * Function that return the cost in function of the hour.
     *
     * @param hour
     *            time of the day to get the cost.
     * @return The cost in function of the hour.
     */
    public float getCost(Date hour) {
        return cost.getCost(hour);
    }

    /**
     * Function to get the "left" neighbor of the edge.
     *
     * @return The "left" neighbor.
     */
    public Node getLneighbor() {
        return lneighbor;
    }

    /**
     * Function to get the "right" neighbor of the edge.
     *
     * @return The "right" neighbor.
     */
    public Node getRneighbor() {
        return rneighbor;
    }

    /**
     * Function to get the other Node of the edge.
     *
     * @param me
     *            Node that represents one nodes of the edge.
     * @return The other Node of the edge.
     */
    public Node getNeighbor(Node me) {
        if (lneighbor == me) {
            return rneighbor;
        } else {
            return lneighbor;
        }
    }

    /**
     * Function to now if the node is on one of the edge's side.
     *
     * @param n
     *            node to check.
     * @return True if the node is in false in other cases.
     */
    public boolean isNode(Node n) {
        return lneighbor == n || rneighbor == n;
    }

    /**
     * Function to get the bus cost to the neighbor node.
     *
     * @return the bus cost.
     */
    public float getBusCost() {
        return cost.getBusC();
    }

    /**
     * Function to get the subway cost to the neighbor node.
     *
     * @return the subway cost.
     */
    public float getSubWCost() {
        return cost.getSubWC();
    }

    /**
     * Function to get the walking cost to he neighbor node.
     *
     * @return the walking cost.
     */
    public float getWalkCost() {
        return cost.getWalkC();
    }

    /**
     * Function to get which transport have the minimum cost.
     *
     * @param b
     *            transports to check.
     * @return the transport name.
     */
    public Transports getTransMin(BitSet b) {
        return cost.getMinTrans(b);
    }
}
