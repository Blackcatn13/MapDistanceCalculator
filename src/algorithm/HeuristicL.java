package algorithm;

import java.util.ArrayList;

import graph.components.Graph;
import graph.components.Node;
import graph.components.Transports;
import graph.parser.ParserLineSpec;

/**
 * File: HeuristicL.java Created on 04/01/2013 by Marc
 *
 * Heuristic that takes the time in the line to aproximate the distance between
 * nodes.
 */
public class HeuristicL extends Heuristic {

    /**
     * Variable with all the lines to check.
     */
    private ArrayList<LineSpec> lines;

    /*
     * (non-Javadoc)
     *
     * @see algorithm.Heuristic#init(graph.components.Graph)
     */
    @Override
    public void init(Graph g) {
        this.setGraph(g);
        lines = new ArrayList<LineSpec>();
    }

    /*
     * (non-Javadoc)
     *
     * @see algorithm.Heuristic#calculate(graph.components.Node,
     * graph.components.Node)
     */
    @Override
    public float calculate(Node s, Node d) {
        return calculate(s, d, Transports.BUS, "", 50, 50);
    }

    /*
     * (non-Javadoc)
     *
     * @see algorithm.Heuristic#calculate(graph.components.Node,
     * graph.components.Node, graph.components.Transports)
     */
    @Override
    public float calculate(Node s, Node d, Transports t) {
        return calculate(s, d, t, "", 50, 50);
    }

    /*
     * (non-Javadoc)
     *
     * @see algorithm.Heuristic#setParams(java.util.ArrayList)
     */
    @Override
    public void setParams(ArrayList<String> filenames) {
        ParserLineSpec p = new ParserLineSpec();
        for (String s : filenames) {
            lines.add(p.getLine(s));
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see algorithm.Heuristic#calculate(graph.components.Node,
     * graph.components.Node, graph.components.Transports, java.lang.String,
     * int, int)
     */
    @Override
    public float calculate(Node s, Node d, Transports t, String lastLine,
            int maxTt, int maxLt) {
        float costinLine = 1000;
        int apparitionsB = 0;
        float sumCostB = 0;
        int apparitionsSW = 0;
        float sumCostSW = 0;
        int apparitionsW = 0;
        float sumCostW = 0;

        for (LineSpec ls : lines) {
            if (ls.isInLine(s.getAlias()) && ls.isInLine(s.getAlias())) {
                if (ls.sameLine(lastLine)) {
                    costinLine = ls.getCostByNode();
                } else {
                    switch (ls.getTransport()) {
                        case BUS:
                            sumCostB = ls.getCostByNode();
                            apparitionsB++;
                            break;
                        case SUBWAY:
                            sumCostSW = ls.getCostByNode();
                            apparitionsSW++;
                            break;
                        case WALK:
                            sumCostW = ls.getCostByNode();
                            apparitionsW++;
                            break;
                        default:
                    }
                }
            }
        }
        if (maxLt == 0) {
            return costinLine;
        } else if (maxTt == 0) {
            return Math.min(sumCostB / apparitionsB, Math.min(sumCostSW
                    / apparitionsSW, sumCostW / apparitionsW));
        }
        return Math.min(
                Math.min(
                        sumCostB / apparitionsB,
                        Math.min(sumCostSW / apparitionsSW, sumCostW
                                / apparitionsW)), costinLine);
    }
}
