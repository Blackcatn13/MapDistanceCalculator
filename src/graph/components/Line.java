package graph.components;

/**
 * File: Line.java Created on 02/01/2013 by Marc
 *
 * Class to unify a Line of a transport.
 */
public class Line {

    /**
     * Name of the Line.
     */
    private String name;

    /**
     * Cost to travel with that Line.
     */
    private float cost;

    /**
     * Transport of the Line.
     */
    private Transports transport;

    /**
     * Constructor with a name and a transport for the Line.
     * @param n Name of the Line.
     * @param t Transport of the Line.
     */
    public Line(String n, Transports t) {
        name = n;
        transport = t;
    }

    /**
     * Constructor with a name, a transport and a cost for the Line.
     * @param n Name of the Line.
     * @param t Transport of the Line.
     * @param c Cost of the Line.
     */
    public Line(String n, Transports t, float c) {
        name = n;
        transport = t;
        cost = c;
    }

    /**
     * Default constructor.
     */
    public Line() {
        name = "";
        cost = 0;
        transport = Transports.NOTHING;
    }

    /**
     * Getter of the name.
     *
     * @return the name
     */
    public final String getName() {
        return name;
    }

    /**
     * Getter of the cost.
     *
     * @return the cost
     */
    public final float getCost() {
        return cost;
    }

    /**
     * Getter of the transport.
     *
     * @return the transport
     */
    public final Transports getTransport() {
        return transport;
    }

    /**
     * @param name
     *            the name to set
     */
    public final void setName(String name) {
        this.name = name;
    }

    /**
     * @param cost
     *            the cost to set
     */
    public final void setCost(float cost) {
        this.cost = cost;
    }

    /**
     * @param transport
     *            the transport to set
     */
    public final void setTransport(Transports transport) {
        this.transport = transport;
    }

    /**
     * Function to know if two Lines are the same.
     * @param n Name of the second line.
     * @return True if are the same, False otherwise.
     */
    public boolean sameLine(String n) {
        return (name.toLowerCase().equals(n.toLowerCase()));
    }

}
