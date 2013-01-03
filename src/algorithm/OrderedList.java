package algorithm;

import java.util.TreeSet;

/**
 * File: OrderedList.java Created on 26/12/2012 by Marc
 *
 * Class to hold an ordered list of Triplet elements.
 */
public class OrderedList {

    /**
     * TreeSet that represents internally the list.
     */
    private TreeSet<Triplet> list;

    /**
     * Default constructor.
     */
    public OrderedList() {
        list = new TreeSet<Triplet>();
    }

    /**
     * Add a new triplet to the list.
     *
     * @param t
     *            new triplet to add.
     */
    public void add(Triplet t) {
        list.add(t);
    }

    /**
     * Add a new triplet to the list, and if the last node of the path exist in
     * the list override it if the cost to get is minor, else don't add it.
     *
     * @param t
     *            Triplet to add in the list.
     */
    public void addWithoutRep(Triplet t) {
        TreeSet<Triplet> listaux = new TreeSet<Triplet>(list);
        boolean added = false;
        // We check for all the elements of the list if one path to the same
        // node exists.
        for (Triplet tr : list) {
            if (tr.getFirst().equals(t.getFirst())) {
                // If it exists and the cost is greater than the new we take the
                // previous and add the new.
                if (tr.getGx() > t.getGx()) {
                    listaux.remove(tr);
                    listaux.add(t);
                }
                // Otherwise we keep the old path and don't add the new.
                added = true;
                break;
            }
        }
        // If we don't have modified the list the path to the node not exist and
        // we add it.
        if (!added) {
            listaux.add(t);
        }
        list = listaux;
    }

    /**
     * Function to get the first triplet of the list.
     *
     * @return the first triplet of the list.
     */
    public Triplet first() {
        return list.first();
    }

    /**
     * Function to get and remove the first triplet of the list.
     *
     * @return the first triplet of the list.
     */
    public Triplet getFirst() {
        Triplet t = list.pollFirst();
        return t;
    }

    /**
     * Function to now if the list is empty.
     *
     * @return true if the list is empty.
     */
    public boolean empty() {
        return list.isEmpty();
    }

}
