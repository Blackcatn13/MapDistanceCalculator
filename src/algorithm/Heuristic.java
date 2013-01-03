package algorithm;

import graph.components.Graph;
import graph.components.Node;
import graph.components.Transports;

/**
 * File: Heuristic.java
 * Created on 26/12/2012 by Marc
 *
 * Class that represents an abstract heuristic.
 */
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
     * @param s node from where calculate the heuristic.
     * @param d node to where calculate the heuristic.
     * @return The estimated cost to got from n to d.
     */
    public abstract float calculate(Node s, Node d);

    /**
     * Function that returns the heuristic value from a node in the graph saved.
     * @param s node from where calculate the heuristic.
     * @param d node to where calculate the heuristic.
     * @param t transport to use.
     * @return The estimated cost to got from n to d.
     */
    public abstract float calculate(Node s, Node d, Transports t);

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
