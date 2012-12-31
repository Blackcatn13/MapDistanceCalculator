/**
 * File: HeuristicE.java
 * Created on 30/12/2012 by Marc
 * 
 * Heuristic that takes the Euclidean distance.
 */
package algorithm;

import java.util.ArrayList;

import graph.components.Graph;
import graph.components.Node;
import graph.components.Transports;
import graph.parser.ParserQuadrant;

public class HeuristicE extends Heuristic {

    private ArrayList<ArrayList<Position>> positions;
    
    /* (non-Javadoc)
     * @see algorithm.Heuristic#init(graph.components.Graph)
     */
    @Override
    public void init(Graph g) {
	this.setGraph(g);
	positions = new ArrayList<ArrayList<Position>>();
    }

    /* (non-Javadoc)
     * @see algorithm.Heuristic#Calculate(graph.components.Node, graph.components.Node)
     */
    @Override
    public float Calculate(Node s, Node d) {
	ArrayList<Position> p = positions.get(2);
	double distx2 = Math.pow((getX(p, s.getAlias()) - getX(p, d.getAlias())), 2.0);
	double disty2 = Math.pow((getY(p, s.getAlias()) - getY(p, d.getAlias())), 2.0);
	return (float)Math.sqrt(distx2 + disty2);
    }

    /* (non-Javadoc)
     * @see algorithm.Heuristic#Calculate(graph.components.Node, graph.components.Node, graph.components.Transports)
     */
    @Override
    public float Calculate(Node s, Node d, Transports t) {
	ArrayList<Position> p;
	
	switch(t) {
	case BUS:
	    p = positions.get(0);
	    break;
	case SUBWAY:
	    p = positions.get(1);
	    break;
	case WALK:
	    p = positions.get(2);
	    break;
	default:
	    p = new ArrayList<Position>();
	}
	double distx2 = Math.pow((getX(p, s.getAlias()) - getX(p, d.getAlias())), 2.0);
	double disty2 = Math.pow((getY(p, s.getAlias()) - getY(p, d.getAlias())), 2.0);
	return (float)Math.sqrt(distx2 + disty2);
    }
    
    public void setDistances(ArrayList<String> filenames) {
	ParserQuadrant p = new ParserQuadrant();
	ArrayList<Position> q;
	for(String file : filenames) {
	    try {
		q = p.quadrantInit(file);
		positions.add(q);
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
    }
    
    public int getX(ArrayList<Position> list, String alias) {
	for(Position p : list) {
	    if(p.getName().toLowerCase().equals(alias.toLowerCase())) {
		return p.getX();
	    }
	}
	return -1;
    }
    
    public int getY(ArrayList<Position> list, String alias) {
	for(Position p : list) {
	    if(p.getName().toLowerCase().equals(alias.toLowerCase())) {
		return p.getY();
	    }
	}
	return -1;
    }

}
