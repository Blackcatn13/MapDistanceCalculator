package algorithm;

import graph.components.Node;
import graph.components.Transports;

/**
 * File: InfoPath.java Created on 31/12/2012 by Marc
 *
 * Class that holds the information to the path.
 */
public class InfoPath implements Cloneable {

    /**
     * Variable that holds the source node in the path.
     */
    private Node snode;

    /**
     * Variable that holds the destination node in the path.
     */
    private Node dnode;

    /**
     * Variable that holds the transport to the next node.
     */
    private Transports transport;

    /**
     * Variable that holds the name of a line.
     */
    private String line;

    /**
     * Variable that hold the cost to go from the first node to this.
     */
    private float cost;

    /**
     * Default constructor.
     */
    public InfoPath() {
        snode = new Node();
        dnode = new Node();
        transport = Transports.NOTHING;
        line = "";
        cost = 0;
    }

    /**
     * Constructor that takes the two nodes and the transport used to got from
     * the sn to the dn.
     *
     * @param sn
     *            Source node.
     * @param dn
     *            Destination node.
     * @param t
     *            Transport used.
     * @param n
     *            Name of the line used.
     * @param c
     *            Cost accumulated.
     */
    public InfoPath(Node sn, Node dn, Transports t, String n, float c) {
        snode = sn;
        dnode = dn;
        transport = t;
        line = n;
        cost = c;
    }

    /**
     * Constructor to make a copy of an InfoPath.
     *
     * @param ip
     *            the InfoPath that have the values to copy.
     */
    public InfoPath(InfoPath ip) {
        snode = ip.snode;
        dnode = ip.dnode;
        transport = ip.transport;
        line = ip.line;
        cost = ip.cost;
    }

    /**
     * Transport getter.
     *
     * @return the transport
     */
    public Transports getTransport() {
        return transport;
    }

    /**
     * Transport setter.
     *
     * @param transport
     *            the transport to set
     */
    public void setTransport(Transports transport) {
        this.transport = transport;
    }

    /**
     * Node source getter.
     *
     * @return the node
     */
    public Node getSNode() {
        return snode;
    }

    /**
     * Node source setter.
     *
     * @param node
     *            the node to set
     */
    public void setSNode(Node node) {
        this.snode = node;
    }

    /**
     * Node destination getter.
     *
     * @return the node
     */
    public Node getDNode() {
        return dnode;
    }

    /**
     * Node destination setter.
     *
     * @param node
     *            the node to set
     */
    public void setDNode(Node node) {
        this.dnode = node;
    }

    /**
     * @return the line
     */
    public final String getLine() {
        return line;
    }

    /**
     * @param line
     *            the line to set
     */
    public final void setLine(String line) {
        this.line = line;
    }

    /**
     * @return the cost
     */
    public float getCost() {
        return cost;
    }

    /**
     * @param cost
     *            the cost to set
     */
    public void setCost(float cost) {
        this.cost = cost;
    }
}
