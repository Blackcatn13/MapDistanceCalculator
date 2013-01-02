/**
 * File: Node.java
 * Created on 07/12/2012 by Marc
 * 
 * Class that represents a graph node
 */
package graph.components;

import java.util.ArrayList;
import java.util.BitSet;

import algorithm.Pair;

public class Node {
    
    /**
     * ArrayList with the edges to the neighbors.
     */
    private ArrayList<NewEdge> neighbors;
    
    /**
     * NodeName variable to hold the name and the alias of the node.
     */
    private NodeNames meName;
    
    /**
     * Constructor that takes the name and the alias of the node.
     * @param name of the new node.
     * @param alias of the new node.
     */
    public Node(String name, String alias) {
	meName = new NodeNames(name, alias);
	neighbors = new ArrayList<NewEdge>();
    }
    
    /**
     * Default constructor.
     */
    public Node() {
	meName = new NodeNames();
	neighbors = new ArrayList<NewEdge>();
    }
    
    /**
     * Getter of the node name.
     * @return The name of the node.
     */
    public String getName() {
	return meName.getName();
    }
    
    /**
     * Getter of the node alias.
     * @return The alias of the node.
     */
    public String getAlias() {
	return meName.getAlias();
    }
    
    /**
     * Function to compare this node by name and alias.
     * @param n name of the node.
     * @param a alias of the node.
     * @return True if the given name and alias correspond to this node.
     */
    public boolean isThisNode(String n, String a) {
	return meName.Same(n, a);
    }
    
    /**
     * Function to compare this node by name. 
     * @param n name of the node.
     * @return True if the given name correspond to this node.
     */
    public boolean isThisNodebyName(String n) {
    	return meName.SameName(n);
    }
   
    /**
     * Function to compare this node by alias.
     * @param a alias of the node.
     * @return True if the given alias correspond to this node.
     */
    public boolean isThisNodebyAlias(String a) {
    	return meName.SameAlias(a);
    }
    
    /**
     * Function to add a neighbor to this node.
     * @param name of the new neighbor.
     * @param alias of the new neighbor.
     * @param Bcost cost to go by bus to the neighbor (-1 if can't go).
     * @param Scost cost to go by subway to the neighbor (-1 if can't go).
     * @param Wcost cost to go walking to the neighbor (-1 if can't go).
     */
    public void addNeighbor(String name, String alias, float Bcost, float Scost, float Wcost) {
	//neighbors.add(new NewEdge(this, new Node(name, alias), Bcost, Scost, Wcost));
    }
    
    /**
     * Function to add a neighbor to this node.
     * @param n node of the new neighbor.
     * @param Bcost cost to go by bus to the neighbor (-1 if can't go).
     * @param Scost cost to go by subway to the neighbor (-1 if can't go).
     * @param Wcost cost to go walking to the neighbor (-1 if can't go).
     */
    public void addNeighbor(Node n, float Bcost, float Scost, float Wcost) {
	//neighbors.add(new NewEdge(this, n, Bcost, Scost, Wcost));
    }
    
    /**
     * Setter of the node name.
     * @param n new name for the node.
     */
    public void setName(String n) {
	meName.setName(n);
    }
    
    /**
     * Setter of the node alias.
     * @param a new alias for the node.
     */
    public void setAlias(String a) {
	meName.setAlias(a);
    }
    
    /**
     * Function to get the neighbors of this node.
     * @return An ArrayList with all the neighbors.
     */
    public ArrayList<Node> getNeighbors(){
	ArrayList<Node> nb = new ArrayList<Node>();
	for(NewEdge n : neighbors) {
	    nb.add(n.getNeighbor(this));
	}
	return nb;
    }
    
    /**
     * Function to get the cost to go from this node a neighbor.
     * @param n node to go.
     * @param t transports we use.
     * @return The cost to go with this transport to the n node (-1 if we can go to it).
     */
    public ArrayList<Pair> costTo(Node n, Transports t) {
	for(NewEdge nb : neighbors) {
	    if(nb.isNode(n)) {
		return nb.getCost(t);
	    }
	}
	return new ArrayList<Pair>();
    }
    
    /**
     * Function to comparer to nodes.
     * @param a node to compare.
     * @return True if the node is the same.
     */
    public boolean equals(Node a) {
	return meName.Same(a.meName.getName(), a.meName.getAlias());
    }
    
    /**
     * Function to get the transport with the minimum cost to the node n in a possible transports list.
     * @param n node to go.
     * @param b possible transports used.
     * @return The transport name with the minimum cost.
     */
    public Transports getTransMinTo(Node n, BitSet b) {
	/*for(NewEdge nb : neighbors) {
	    if(nb.getNeighbor(this).equals(n)) {
		return nb.getTransMin(b);
	    }
	}*/
	return Transports.NOTHING;
    }
}
