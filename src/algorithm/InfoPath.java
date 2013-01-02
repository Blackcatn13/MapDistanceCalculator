/**
 * File: InfoPath.java
 * Created on 31/12/2012 by Marc
 * 
 * Class that holds the information to the path.
 */
package algorithm;

import graph.components.Node;
import graph.components.Transports;

public class InfoPath implements Cloneable{

    /**
     * Variable that holds the source node in the path.
     */
    private Node Snode;
    
    /**
     * Variable that holds the destination node in the path.
     */
    private Node Dnode;
    
    /**
     * Variable that holds the transport to the next node.
     */
    private Transports transport;
    
    private String line;

    /**
     * Default constructor.
     */
    public InfoPath() {
	Snode = new Node();
	Dnode = new Node();
	transport = Transports.NOTHING;
	line = "";
    }
    
    /**
     * Constructor that takes the two nodes and the transport used to got from the sn to the dn.
     * @param sn Source node.
     * @param dn Destination node.
     * @param t Transport used.
     */
    public InfoPath(Node sn, Node dn, Transports t, String n) {
	Snode = sn;
	Dnode = dn;
	transport = t;
	line = n;
    }
    
    /**
     * Constructor to make a copy of an InfoPath.
     * @param ip the InfoPath that have the values to copy.
     */
    public InfoPath(InfoPath ip) {
	Snode = ip.Snode;
	Dnode = ip.Dnode;
	transport = ip.transport;
	line = ip.line;
    }

    /**
     * Transport getter.
     * @return the transport
     */
    public Transports getTransport() {
	return transport;
    }

    /**
     * Transport setter.
     * @param transport the transport to set
     */
    public void setTransport(Transports transport) {
	this.transport = transport;
    }

    /**
     * Node source getter.
     * @return the node
     */
    public Node getSNode() {
	return Snode;
    }

    /**
     * Node source setter.
     * @param node the node to set
     */
    public void setSNode(Node node) {
	this.Snode = node;
    } 
    
    /**
     * Node destination getter.
     * @return the node
     */
    public Node getDNode() {
	return Dnode;
    }

    /**
     * Node destination setter.
     * @param node the node to set
     */
    public void setDNode(Node node) {
	this.Dnode = node;
    }
    
    /**
     * @return the line
     */
    public final String getLine() {
        return line;
    }

    /**
     * @param line the line to set
     */
    public final void setLine(String line) {
        this.line = line;
    }
}
