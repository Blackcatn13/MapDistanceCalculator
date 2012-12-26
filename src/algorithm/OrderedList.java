/**
 * File: OrderedList.java
 * Created on 26/12/2012 by Marc
 * 
 * Class to hold an ordered list of Triplet elements.
 */
package algorithm;

import java.util.TreeSet;

public class OrderedList {
    
    private TreeSet<Triplet> list;
    
    public OrderedList() {
	list = new TreeSet<Triplet>();
    }
    
    public void add(Triplet t) {
	list.add(t);
    }
    
    public void addWithoutRep(Triplet t) {
	// TODO : implement this method to take out the same path's with more distance
    }
    
    public Triplet getFirst() {
	return list.first();
    }

}
