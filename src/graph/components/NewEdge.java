package graph.components;

import java.util.ArrayList;

import algorithm.Pair;

/**
 * File: NewEdge.java Created on 02/01/2013 by Marc
 *
 * New Edge to hold lines.
 */
public class NewEdge {

    /**
     * Cost to walk between the nodes.
     */
    private float walkCost;

    /**
     * Different Lines between the nodes.
     */
    private ArrayList<Line> lines;

    /**
     * One of the nodes.
     */
    private Node lneighbor;

    /**
     * The other node.
     */
    private Node rneighbor;

    /**
     * Default constructor.
     */
    public NewEdge() {
        lneighbor = new Node();
        rneighbor = new Node();
        walkCost = -1;
        lines = new ArrayList<Line>();
    }

    /**
     * Constructor with the two neighbor nodes and the cost to go walking.
     *
     * @param ln
     *            First node.
     * @param rn
     *            Second node.
     * @param c
     *            Cost to walk.
     */
    public NewEdge(Node ln, Node rn, float c) {
        lneighbor = ln;
        rneighbor = rn;
        walkCost = c;
        lines = new ArrayList<Line>();
    }

    /**
     * Constructor with the two neighbor nodes, the cost to go walking and the
     * Lines between them.
     *
     * @param ln
     *            First node.
     * @param rn
     *            Second node.
     * @param c
     *            Cost to walk.
     * @param line
     *            Lines between the nodes.
     */
    public NewEdge(Node ln, Node rn, float c, ArrayList<Line> line) {
        lneighbor = ln;
        rneighbor = rn;
        walkCost = c;
        lines = line;
    }

    /**
     * Function to get the neighbor node.
     *
     * @param me
     *            is the node who call the function.
     * @return The neighbor node of me.
     */
    public Node getNeighbor(Node me) {
        if (lneighbor == me) {
            return rneighbor;
        } else {
            return lneighbor;
        }
    }

    /**
     * Function to now if the node is in the edge.
     *
     * @param n
     *            Node to search.
     * @return True if the node is in the edge, False otherwise.
     */
    public boolean isNode(Node n) {
        return lneighbor == n || rneighbor == n;
    }

    /**
     * Getter of the walkin cost.
     *
     * @return The walk cost.
     */
    public float getWalkCost() {
        return walkCost;
    }

    /**
     * Getter of the Lines name.
     *
     * @return An ArrayList with all the Lines name.
     */
    public ArrayList<String> getLines() {
        ArrayList<String> names = new ArrayList<String>();
        for (Line l : lines) {
            names.add(l.getName());
        }
        return names;
    }

    /**
     * Function to get the cost from a Line.
     *
     * @param name
     *            The name of the Line to get the cost.
     * @return The cost in that Line.
     */
    public float getCostInLine(String name) {
        for (Line l : lines) {
            if (l.sameLine(name)) {
                return l.getCost();
            }
        }
        return -1;
    }

    /**
     * Function to get the costs from one transport.
     *
     * @param t
     *            The transport.
     * @return An ArrayList of pairs holding the Line name and the cost.
     */
    public ArrayList<Pair> getCost(Transports t) {

        ArrayList<Pair> ret = new ArrayList<Pair>();
        if (t == Transports.WALK) {
            ret.add(new Pair(walkCost, "Walk"));
            return ret;
        }
        for (Line l : lines) {
            if (l.getTransport() == t) {
                ret.add(new Pair(l.getCost(), l.getName()));
            }
        }
        return ret;
    }

    /**
     * Setter of the Lines.
     *
     * @param lists
     *            new ArrayList to set.
     */
    public void setLine(ArrayList<Line> lists) {
        lines = lists;
    }
}
