package algorithm;

import java.util.ArrayList;

import graph.components.Transports;

/**
 * File: LineSpec.java Created on 05/01/2013 by Marc
 * 
 * Class to represent an specification of a line, containing the name, the total
 * time to go form the first to the last node, and the nodes in the line.
 */
public class LineSpec {

    private String name;

    private Transports transport;

    private float cost;

    private int numNodes;

    private ArrayList<String> nodes;

    public LineSpec() {
        name = "";
        transport = Transports.NOTHING;
        cost = 0;
        numNodes = 0;
        nodes = new ArrayList<String>();
    }

    public LineSpec(String n, Transports t, float c, int num,
            ArrayList<String> nds) {
        name = n;
        transport = t;
        cost = c;
        numNodes = num;
        nodes = nds;
    }

    public boolean isInLine(String n) {
        for (String s : nodes) {
            if (s.toLowerCase().equals(n.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
    
    public boolean sameLine(String n) {
        return name.toLowerCase().equals(n.toLowerCase());
    }

    public float getCostByNode() {
        return cost / numNodes;
    }

    /**
     * @return the name
     */
    public final String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public final void setName(String name) {
        this.name = name;
    }

    /**
     * @return the transport
     */
    public final Transports getTransport() {
        return transport;
    }

    /**
     * @param transport
     *            the transport to set
     */
    public final void setTransport(Transports transport) {
        this.transport = transport;
    }

    /**
     * @return the cost
     */
    public final float getCost() {
        return cost;
    }

    /**
     * @param cost
     *            the cost to set
     */
    public final void setCost(float cost) {
        this.cost = cost;
    }

    /**
     * @return the numNodes
     */
    public final int getNumNodes() {
        return numNodes;
    }

    /**
     * @param numNodes
     *            the numNodes to set
     */
    public final void setNumNodes(int numNodes) {
        this.numNodes = numNodes;
    }

    /**
     * @return the nodes
     */
    public final ArrayList<String> getNodes() {
        return nodes;
    }

    /**
     * @param nodes
     *            the nodes to set
     */
    public final void setNodes(ArrayList<String> nodes) {
        this.nodes = nodes;
    }

}
