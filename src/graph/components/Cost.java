package graph.components;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Date;
import java.util.EnumMap;
import java.util.Iterator;

/**
 * File: Cost.java Created on 07/12/2012 by Marc
 *
 * Class that represents a cost depending the time.
 */
@Deprecated
public class Cost {

    /**
     * Array to hold a schedule date.
     */
    private ArrayList<Date> schedule = new ArrayList<Date>();

    /**
     * Array to hold the cost by schedule.
     */
    private ArrayList<Float> cost = new ArrayList<Float>();

    /**
     * Map to hold the cost of a transport.
     */
    private EnumMap<Transports, Float> costs = new EnumMap<Transports, Float>(
            Transports.class);

    /**
     * Default cost of the class.
     */
    private float defaultCost;

    /**
     * Boolean to know if the cost have a schedule.
     */
    private boolean haveSchedule = false;

    /**
     * Constructor that take the cost of the bus, subway and walk.
     *
     * @param bc
     *            Bus cost.
     * @param sc
     *            Subway cost.
     * @param wc
     *            Walk cost.
     */
    public Cost(float bc, float sc, float wc) {
        costs.put(Transports.BUS, bc);
        costs.put(Transports.SUBWAY, sc);
        costs.put(Transports.WALK, wc);

    }

    /**
     * Constructor with a schedule.
     *
     * @param time
     *            Set the schedule.
     */
    public Cost(boolean time) {
        defaultCost = 5;
        haveSchedule = time;
    }

    /**
     * Constructor with a default cost.
     *
     * @param c
     *            Default cost value.
     */
    public Cost(float c) {
        defaultCost = c;
    }

    /**
     * Function to add a cost to the schedule.
     *
     * @param hour
     *            of the schedule.
     * @param nCost
     *            for the schedule.
     */
    public void addCost(Date hour, float nCost) {
        schedule.add(hour);
        cost.add(nCost);
    }

    /**
     * Function to get the cost by an hour in the schedule.
     *
     * @param hour
     *            to look in the schedule.
     * @return The cost in that hour.
     */
    public float getCost(Date hour) {
        Iterator<Date> it = schedule.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (it.next().after(hour)) {
                return cost.get(i);
            }
            i++;
        }
        return defaultCost;
    }

    /**
     * Function to know if the cost have schedule or not.
     *
     * @return True if have schedule, False otherwise.
     */
    public boolean haveSchedule() {
        return haveSchedule;
    }

    /**
     * Getter of the cost.
     *
     * @return The cost.
     */
    public float getCost() {
        return defaultCost;
    }

    /**
     * Setter of the bus cost.
     *
     * @param c
     *            The new bus cost.
     */
    public void setBusC(float c) {
        costs.put(Transports.BUS, c);
    }

    /**
     * Setter of the subway cost.
     *
     * @param c
     *            The new subway cost.
     */
    public void setSubWC(float c) {
        costs.put(Transports.SUBWAY, c);
    }

    /**
     * Setter of the walking cost.
     *
     * @param c
     *            The new walking cost.
     */
    public void setWalkC(float c) {
        costs.put(Transports.WALK, c);
    }

    /**
     * Getter of the bus cost.
     *
     * @return The bus cost.
     */
    public float getBusC() {
        return costs.get(Transports.BUS);
    }

    /**
     * Getter of the subway cost.
     *
     * @return The subway cost.
     */
    public float getSubWC() {
        return costs.get(Transports.SUBWAY);
    }

    /**
     * Getter of the walking cost.
     *
     * @return The walking cost.
     */
    public float getWalkC() {
        return costs.get(Transports.WALK);
    }

    /**
     * Function to get the transport used to go from one node to other.
     *
     * @param b
     *            Types of transports to check.
     * @return The transport used.
     */
    public Transports getMinTrans(BitSet b) {
        float bus = (b.get(0) && costs.get(Transports.BUS) != -1) ? costs
                .get(Transports.BUS) : 1000;
        float sub = (b.get(1) && costs.get(Transports.SUBWAY) != -1) ? costs
                .get(Transports.SUBWAY) : 1000;
        float walk = (b.get(2) && costs.get(Transports.WALK) != -1) ? costs
                .get(Transports.WALK) : 1000;

        Transports t = Transports.NOTHING;

        if (bus < sub) {
            if (bus < walk) {
                t = Transports.BUS;
            } else {
                t = Transports.WALK;
            }
        } else {
            if (sub < walk) {
                t = Transports.SUBWAY;
            } else {
                t = Transports.WALK;
            }
        }
        return t;
    }
}
