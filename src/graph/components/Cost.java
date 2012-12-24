/**
 * File: Cost.java
 * Created on 07/12/2012 by Marc
 * 
 * Class that represents a cost depending the time.
 */
package graph.components;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class Cost {

    private ArrayList<Date> schedule = new ArrayList<Date>();
    private ArrayList<Float> cost = new ArrayList<Float>();
    private float defaultCost;
    private boolean haveSchedule = false;
    
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
}
