package algorithm;

import graph.components.Graph;
import graph.components.Node;
import graph.components.Transports;

import java.util.ArrayList;
import java.util.BitSet;

/**
 * File: AStar.java. Created on 24/12/2012 by Marc
 *
 * Class that represents the A* algorithm
 */
public class AStar {
    /**
     * Variable to hold a graph for the heuristic function.
     */
    private Graph graph;
    /**
     * Node source for the A*.
     */
    private Node source;
    /**
     * Node destination for the A*.
     */
    private Node destination;
    /**
     * OrderedList to save the nodes in f(x) order.
     */
    private OrderedList list;
    /**
     * Heuristic used in the A*.
     */
    private Heuristic h;

    /**
     * Number of maximum transports transfers.
     */
    private int maxTransfers;

    /**
     * Number of maximum line transfers.
     */
    private int maxLines;

    /**
     * Default constructor of the class.
     */
    public AStar() {
        graph = new Graph();
        source = new Node();
        destination = new Node();
        list = new OrderedList();
    }

    /**
     * Constructor with all the parameters to run the A*.
     *
     * @param g
     *            Graph used for the A*.
     * @param s
     *            node source.
     * @param d
     *            node destination.
     * @param heu
     *            heuristic to check.
     * @param tt
     *            transport transfers.
     * @param lt
     *            line transfers.
     */
    public AStar(Graph g, Node s, Node d, Heuristic heu, int tt, int lt) {
        graph = g;
        source = s;
        destination = d;
        list = new OrderedList();
        h = heu;
        maxTransfers = tt;
        maxLines = lt;
    }

    /**
     * Setter of the source Node.
     *
     * @param s
     *            new source node.
     */
    public void setSource(Node s) {
        source = s;
    }

    /**
     * Setter of the destination Node.
     *
     * @param d
     *            new destination node.
     */
    public void setDestination(Node d) {
        destination = d;
    }

    /**
     * Function that calculates the A*.
     *
     * @param b
     *            types of transports to use.
     * @return The path with then nodes to go from source to destination.
     */
    public ArrayList<InfoPath> getPath(BitSet b) {
        ArrayList<InfoPath> path = new ArrayList<InfoPath>();
        ArrayList<Node> auxl = new ArrayList<Node>();
        ArrayList<Node> visited = new ArrayList<Node>();
        ArrayList<Pair> costs;
        Node aux;
        float cost = 0;
        float oldcost;
        Triplet t;
        InfoPath ip;
        int position;

        ip = new InfoPath();
        ip.setSNode(source);
        // We add the source in a clear path to start.
        path.add(ip);
        // We add the new triplet that holds the f(x), the g(x) and the path in
        // our ordered list.
        list.add(new Triplet(cost + h.calculate(source, destination), 0, path));
        // While the list holds an element or the first element is equals to the
        // destination.
        while (!list.empty() && !list.first().getFirst().equals(destination)) {
            // Get the first element of the list.
            t = list.getFirst();
            // Get the last node in that path.
            aux = t.getFirst();
            // Mark the node as visited.
            visited.add(aux);
            // Get the neighbors of the node.
            auxl = aux.getNeighbors();
            // For every neighbor.
            for (Node n : auxl) {
                // If the neighbor has been visited we skip it.
                if (visited.contains(n))
                    continue;
                // For the different types of transports.
                for (int j = 0; j < b.length(); j++) {
                    // If the transport method is selected.
                    if (b.get(j)) {
                        // We get all the costs with the transport to got to the
                        // node.
                        costs = aux.costTo(n, Transports.values()[j]);
                        // If not cost exist exit.
                        if (costs.size() == 0)
                            continue;
                        // For every cost from the node to the other node.
                        for (Pair p : costs) {
                            // If the cost not exist exit.
                            if (p.getC() == -1)
                                continue;
                            // Copy the path.
                            path = copyList(t.getPath());
                            position = path.size() - 1;
                            // Update the last InfoPath with the destination
                            // node, the transport used and the line of the
                            // transport.
                            path.get(position).setDNode(n);
                            path.get(position).setTransport(
                                    Transports.values()[j]);
                            path.get(position).setLine(p.getN());
                            // We get the cost accumulated and the new cost and
                            // save it in the path.
                            oldcost = t.getGx();
                            path.get(position).setCost(oldcost + p.getC());
                            // Create a new InfoPath with the source node.
                            ip = new InfoPath();
                            ip.setSNode(n);
                            // Add it to the path.
                            path.add(ip);
                            // We create a new Triplet with the new f(x), the
                            // new g(x), the path,
                            // the number of transport transfers in this path,
                            // the last transport type used,
                            // the number of line transfers in this path,
                            // and the last line used.
                            Triplet nt = new Triplet(p.getC()
                                    + oldcost
                                    + h.calculate(n, destination,
                                            Transports.values()[j]), p.getC()
                                            + oldcost, path, t.getTransTransfers(),
                                            t.getLastTransport(), t.getLineTransfers(),
                                            t.getLastLine());
                            // Check if with the transfer parameters given the
                            // path is in or not.
                            if (nt.updateTransport(Transports.values()[j],
                                    p.getN(), maxTransfers, maxLines)) {
                                // If it's in, we put it in the list.
                                list.addWithoutRep(nt);
                            }

                        }
                    }
                }
            }
        }
        // If the list is empty we return an empty list.
        if (list.empty()) {
            return new ArrayList<InfoPath>();
        }
        // else we return the path to go form the node source to the destination
        // node.
        return list.first().getPath();
    }

    /**
     * Function to copy an ArrayList.
     *
     * @param list
     *            list to copy.
     * @return The new ArrayList copied.
     */
    private ArrayList<InfoPath> copyList(ArrayList<InfoPath> list) {
        ArrayList<InfoPath> newList = new ArrayList<InfoPath>();
        for (InfoPath p : list) {
            newList.add(new InfoPath(p));
        }
        return newList;
    }
}
