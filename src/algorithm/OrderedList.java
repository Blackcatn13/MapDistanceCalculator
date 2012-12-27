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
	TreeSet<Triplet> listaux = new TreeSet<Triplet>(list);
	boolean added = false;
	for(Triplet tr : list) {
	    if(tr.getFirst().equals(t.getFirst())) {
		if(tr.getGx() > t.getGx()) {
		    listaux.remove(tr);
		    listaux.add(t);   
		}
		added = true;
	    }
	}
	if(!added) {
	    listaux.add(t);
	}
	list = listaux;
    }
    
    public Triplet First() {
	return list.first();
    }
    
    public Triplet getFirst() {
	Triplet t = list.first();
	list.remove(t);
	return t;
    }
    
    public boolean empty() {
	return list.size() == 0;
    }

}
