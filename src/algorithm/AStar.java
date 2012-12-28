/**
 * File: AStar.java
 * Created on 24/12/2012 by Marc
 * 
 * Class that represents the A* algorithm
 */
package algorithm;

import graph.components.Graph;
import graph.components.Node;
import graph.components.Transports;

import java.util.ArrayList;
import java.util.BitSet;

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
    
    public ArrayList<Node> getPath(BitSet b){
	ArrayList<Node> path = new ArrayList<Node>();
	ArrayList<Node> auxl = new ArrayList<Node>();
	ArrayList<Node> visited = new ArrayList<Node>();
	Node aux;
	float cost = 0;
	float oldcost;
	Triplet t;
	
	path.add(source);
	int costaux = 50;
	list.add(new Triplet(cost + costaux + h.Calculate(source, destination), 0, path));
	while(!list.First().getFirst().equals(destination) && !list.empty()) {
	    t = list.getFirst();
	    aux = t.getFirst();
	    visited.add(aux);
	    auxl = aux.getNeighbors();
	    System.out.println(aux.getAlias());
	    for(int i = 0; i < auxl.size(); i++) {
		if(visited.contains(auxl.get(i))) continue;
		for(int j = 0; j < b.length(); j++) {
		    if(b.get(j)) {
                	path = new ArrayList<Node>(t.getPath());
                	path.add(auxl.get(i));
                	cost = aux.costTo(auxl.get(i), Transports.values()[j]);
                	if(cost != -1) {
                	    oldcost = t.getGx();
                	    list.addWithoutRep(new Triplet(cost + costaux + oldcost + h.Calculate(auxl.get(i), destination), cost + oldcost, path));
                	}
		    }
		}
	    }
	}
	if(list.empty()) {
	    ArrayList<Node> a = new ArrayList<Node>();
	    a.add(new Node("Not path to go", "Not path to go"));
	    return a;
	}

	return list.First().getPath();
    }
    

}
