/**
 * File: Graph.java
 * Created on 24/12/2012 by Marc
 * 
 * Class that represents a graph
 */
package graph.components;

import java.util.ArrayList;


public class Graph {

    /**
     * Variable that holds all the nodes of the graph.
     */
    private ArrayList<Node> nodes;

    /**
     * Default constructor.
     */
    public Graph() {
	nodes = new ArrayList<Node>();
    }

    /**
     * Function to add a new node.
     * @param n new node to add.
     */
    public void addNode(Node n) {
	nodes.add(n);
    }

    /**
     * Function to get the neighbors of a node.
     * @param name of the node to get the neighbors.
     * @param alias of the node to get the neighbors.
     * @return An ArrayList with all the neighbors of the node.
     */
    public ArrayList<Node> getNeighbors(String name, String alias) {
	for(Node n : nodes) {
	    if(n.isThisNode(name, alias)) {
		return n.getNeighbors();
	    }
	}
	return new ArrayList<Node>();
    }

    /**
     * Function to get a node for the graph.
     * @param name of the node to get.
     * @param alias of the node to get.
     * @return The node with the name and alias passed as parameters.
     */
    public Node getNode(String name, String alias) {
	for(Node n : nodes) {
	    if(n.isThisNode(name, alias)) {
		return n;
	    }
	}
	return new Node();
    }

    /**
     * Function to get one node by its name.
     * @param name of the node to get.
     * @return The node with the name.
     */
    public Node getNodebyName(String name) {
	for(Node n : nodes) {
	    if(n.isThisNodebyName(name)) {
		return n;
	    }
	}
	return new Node();
    }

    /**
     * Function to get one node by its alias.
     * @param alias of the node to get.
     * @return The node with the alias.
     */
    public Node getNodebyAlias(String alias) {
	for(Node n : nodes) {
	    if(n.isThisNodebyAlias(alias)) {
		return n;
	    }
	}
	return new Node();
    }

    /**
     * Function to get one node by its name or alias.
     * @param na Name or alias of the node.
     * @return The node with the given name or alias.
     */
    public Node getNodeby(String na) {
	for(Node n : nodes) {
	    if(n.isThisNodebyAlias(na) || n.isThisNodebyName(na)) {
		return n;
	    }
	}
	return new Node();
    }

    /**
     * Function to get the number of nodes.
     * @return The number of nodes of the graph.
     */
    public int getGraphSize(){
	return nodes.size();
    }
    
    /**
     * Function to get the nodes of the graph.
     * @return The list of nodes of the graph.
     */
    
    public ArrayList<Node> getNodes(){
    	return nodes;
    }
    
}