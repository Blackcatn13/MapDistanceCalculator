/**
 * File: Cost.java
 * Created on 07/12/2012 by Marc
 * 
 * Class that represents a cost depending the time.
 */
package graph.components;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Date;
import java.util.EnumMap;
import java.util.Iterator;

public class Cost {

    private ArrayList<Date> schedule = new ArrayList<Date>();
    private ArrayList<Float> cost = new ArrayList<Float>();
    private EnumMap<Transports, Float> costs = new EnumMap<Transports, Float>(Transports.class);
    private float defaultCost;
    private boolean haveSchedule = false;
    
    public Cost(float Bc, float Sc, float Wc) {
	costs.put(Transports.BUS, Bc);
	costs.put(Transports.SUBWAY, Sc);
	costs.put(Transports.WALK, Wc);
	
    }
    public Cost(boolean time) {
	defaultCost = 5;
	haveSchedule = time;
    }
    
    public Cost(float c) {
	defaultCost = c;
    }
    
    public void addCost(Date hour, float nCost) {
	schedule.add(hour);
	cost.add(nCost);
    }
    
    public float getCost(Date hour) {
	Iterator<Date> it = schedule.iterator();
	int i = 0;
	while(it.hasNext()) {
	    if(it.next().after(hour)) {
		return cost.get(i);
	    }
	    i++;
	}
	return defaultCost;
    }
    
    public boolean haveSchedule() {
	return haveSchedule;
    }
    
    public float getCost() {
	return defaultCost;
    }
    
    public void setBusC(float c) {
	costs.put(Transports.BUS, c);
    }
    
    public void setSubWC(float c) {
	costs.put(Transports.SUBWAY, c);
    }
    
    public void setWalkC(float c) {
	costs.put(Transports.WALK, c);
    }
    
    public float getBusC() {
	return costs.get(Transports.BUS);
    }
    
    public float getSubWC() {
	return costs.get(Transports.SUBWAY);
    }
    
    public float getWalkC() {
	return costs.get(Transports.WALK);
    }
    
    public Transports getMinTrans(BitSet b) {
	float bus = (b.get(0) && costs.get(Transports.BUS) != -1) ? costs.get(Transports.BUS) : 1000;
	float sub = (b.get(1) && costs.get(Transports.SUBWAY) != -1) ? costs.get(Transports.SUBWAY) : 1000;
	float walk = (b.get(2) && costs.get(Transports.WALK) != -1) ? costs.get(Transports.WALK) : 1000;
	
	Transports t = Transports.NOTHING;
	
	if(bus < sub) {
	    if(bus < walk) {
		t = Transports.BUS;
	    }
	    else {
		t = Transports.WALK;
	    }
	}
	else {
	    if(sub < walk) {
		t = Transports.SUBWAY;
	    }
	    else {
		t = Transports.WALK;
	    }
	}
	return t;
    }
}
