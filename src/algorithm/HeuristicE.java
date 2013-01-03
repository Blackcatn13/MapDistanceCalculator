package algorithm;

import java.util.ArrayList;

import graph.components.Graph;
import graph.components.Node;
import graph.components.Transports;
import graph.parser.ParserQuadrant;

/**
 * File: HeuristicE.java Created on 30/12/2012 by Marc
 *
 * Heuristic that takes the Euclidean distance.
 */
public class HeuristicE extends Heuristic {

    /**
     * Array to hold all the positions of the nodes in a square map.
     */
    private ArrayList<ArrayList<Position>> positions;

    /*
     * (non-Javadoc)
     *
     * @see algorithm.Heuristic#init(graph.components.Graph)
     */
    @Override
    public void init(Graph g) {
        this.setGraph(g);
        positions = new ArrayList<ArrayList<Position>>();
    }

    /*
     * (non-Javadoc)
     *
     * @see algorithm.Heuristic#Calculate(graph.components.Node,
     * graph.components.Node)
     */
    @Override
    public float calculate(Node s, Node d) {
        ArrayList<Position> p = positions.get(2);
        double distx2 = Math.pow(
                (getX(p, s.getAlias()) - getX(p, d.getAlias())), 2.0);
        double disty2 = Math.pow(
                (getY(p, s.getAlias()) - getY(p, d.getAlias())), 2.0);
        return (float) Math.sqrt(distx2 + disty2);
    }

    /*
     * (non-Javadoc)
     *
     * @see algorithm.Heuristic#Calculate(graph.components.Node,
     * graph.components.Node, graph.components.Transports)
     */
    @Override
    public float calculate(Node s, Node d, Transports t) {
        ArrayList<Position> p;

        switch (t) {
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
        double distx2 = Math.pow(
                (getX(p, s.getAlias()) - getX(p, d.getAlias())), 2.0);
        double disty2 = Math.pow(
                (getY(p, s.getAlias()) - getY(p, d.getAlias())), 2.0);
        return (float) Math.sqrt(distx2 + disty2);
    }

    /**
     * Function to initialized the heuristic with given files.
     *
     * @param filenames
     *            THe given files to parse.
     */
    public void setDistances(ArrayList<String> filenames) {
        ParserQuadrant p = new ParserQuadrant();
        ArrayList<Position> q;
        for (String file : filenames) {
            try {
                q = p.quadrantInit(file);
                positions.add(q);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Function to get the X value of a node.
     *
     * @param list
     *            where to get the X value.
     * @param alias
     *            of the node to get the X value.
     * @return The X value of the node.
     */
    private int getX(ArrayList<Position> list, String alias) {
        for (Position p : list) {
            if (p.getName().toLowerCase().equals(alias.toLowerCase())) {
                return p.getX();
            }
        }
        return -1;
    }

    /**
     * Function to get the Y value of a node.
     *
     * @param list
     *            where to get the Y value.
     * @param alias
     *            of the node to get the Y value.
     * @return The Y value of the node.
     */
    private int getY(ArrayList<Position> list, String alias) {
        for (Position p : list) {
            if (p.getName().toLowerCase().equals(alias.toLowerCase())) {
                return p.getY();
            }
        }
        return -1;
    }

}
