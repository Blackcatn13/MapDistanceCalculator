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
	int node = 0;
	for(int i = 0; i < nodes.size(); i++) {
	    if(nodes.get(i).isThisNode(name, alias)) {
		node = i;
		break;
	    }
	}
	return nodes.get(node).getNeighbors();
    }
    
    /**
     * Function to get a node for the graph.
     * @param name of the node to get.
     * @param alias of the node to get.
     * @return The node with the name and alias passed as parameters.
     */
    public Node getNode(String name, String alias) {
	int node = 0;
	for(int i = 0; i < nodes.size(); i++) {
	    if(nodes.get(i).isThisNode(name, alias)) {
		node = i;
		break;
	    }
	}
	return nodes.get(node);
    }
    
    /**
     * Function to get one node by its name.
     * @param name of the node to get.
     * @return The node with the name.
     */
    public Node getNodebyName(String name) {
    	int node = 0;
    	for(int i = 0; i < nodes.size(); i++) {
    	    if(nodes.get(i).isThisNodebyName(name)) {
    		node = i;
    		break;
    	    }
    	}
    	return nodes.get(node);
    }
    
    /**
     * Function to get one node by its alias.
     * @param alias of the node to get.
     * @return The node with the alias.
     */
    public Node getNodebyAlias(String alias) {
    	int node = 0;
    	for(int i = 0; i < nodes.size(); i++) {
    	    if(nodes.get(i).isThisNodebyAlias(alias)) {
    		node = i;
    		break;
    	    }
    	}
    	return nodes.get(node);
    }
}
