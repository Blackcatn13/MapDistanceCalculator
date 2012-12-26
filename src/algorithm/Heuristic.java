/**
 * File: Heuristic.java
 * Created on 26/12/2012 by Marc
 * 
 * Class that represents an abstract heuristic.
 */
package algorithm;

import graph.components.Graph;
import graph.components.Node;

public abstract class Heuristic {

    /**
     * Graph to perform the calculus.
     */
    
    private Graph graph;
    /**
     * Function to initialize the heuristic.
     * @param g graph to work with.
     */
    public abstract void init(Graph g);
    
    /**
     * Function that returns the heuristic value from a node in the graph saved.
     * @param n node to get the heuristic.
     * @return The heuristic value.
     */
    public abstract float Calculate(Node n);

    /**
     * Function to get the graph.
     * @return the graph
     */
    public Graph getGraph() {
	return graph;
    }

    /**
     * Function to set the graph.
     * @param graph the graph to set
     */
    public void setGraph(Graph graph) {
	this.graph = graph;
    }
}
