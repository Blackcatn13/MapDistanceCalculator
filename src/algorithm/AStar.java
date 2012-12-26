/**
 * File: AStar.java
 * Created on 24/12/2012 by Marc
 * 
 * Class that represents the A* algorithm
 */
package algorithm;

import java.util.ArrayList;

import graph.components.Graph;
import graph.components.Node;

public class AStar {
    
    private Graph graph;
    private Node source;
    private Node destination;
    private OrderedList list;
    private Heuristic h;
    
    public AStar() {
	graph = new Graph();
	source = new Node();
	destination = new Node();
	list = new OrderedList();
    }
    
    public AStar(Graph g, Node s, Node d, Heuristic heu) {
	graph = g;
	source = s;
	destination = d;
	list = new OrderedList();
	h = heu;
	h.init(g);
    }
    
    public void setSource(Node s) {
	source = s;
    }
    
    public void setDestination(Node d) {
	destination = d;
    }
    
    public ArrayList<Node> getPath(){
	ArrayList<Node> path = new ArrayList<Node>();
	ArrayList<Node> auxl = new ArrayList<Node>();
	Node aux;
	float cost;
	float oldcost;
	
	path.add(source);
	list.add(new Triplet(h.Calculate(source, destination), 0, path));
	while(list.getFirst().getFirst() == destination || list.empty()) {
	    aux = list.getFirst().getFirst();
	    auxl = aux.getNeighbors();
	    for(int i = 0; i < auxl.size(); i++) {
		path = new ArrayList<Node>(list.getFirst().getPath());
		path.add(auxl.get(i));
		cost = aux.costTo(auxl.get(i));
		oldcost = list.getFirst().getGx();
		list.add(new Triplet(cost + h.Calculate(auxl.get(i), destination), cost + oldcost, path));
	    }
	}
	if(list.empty()) {
	    return new ArrayList<Node>();
	}

	return list.getFirst().getPath();
    }
    

}
