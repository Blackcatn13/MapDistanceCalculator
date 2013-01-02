/**
 * File: Line.java
 * Created on 02/01/2013 by Marc
 * 
 * Class to unify a Line of a transport.
 */
package graph.components;

public class Line {

    private String name;
    private float cost;
    private Transports transport;
    
    public Line(String n, Transports t) {
	name = n;
	transport = t;
    }
    
    public Line(String n, Transports t, float c) {
	name = n;
	transport = t;
	cost = c;
    }
    
    public Line() {
	name = "";
	cost = 0;
	transport = Transports.NOTHING;
    }

    /**
     * Getter of the name.
     * @return the name
     */
    public final String getName() {
        return name;
    }

    /**
     * Getter of the cost.
     * @return the cost
     */
    public final float getCost() {
        return cost;
    }

    /**
     * Getter of the transport.
     * @return the transport
     */
    public final Transports getTransport() {
        return transport;
    }
    
    /**
     * @param name the name to set
     */
    public final void setName(String name) {
        this.name = name;
    }

    /**
     * @param cost the cost to set
     */
    public final void setCost(float cost) {
        this.cost = cost;
    }

    /**
     * @param transport the transport to set
     */
    public final void setTransport(Transports transport) {
        this.transport = transport;
    }

    public boolean SameLine(String n) {
	return (name.toLowerCase().equals(n.toLowerCase()));
    }
    
}
