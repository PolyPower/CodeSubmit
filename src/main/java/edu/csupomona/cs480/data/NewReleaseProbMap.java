package edu.csupomona.cs480.data;

import java.util.HashMap;

import org.apache.commons.collections15.multimap.MultiHashMap;

/**
 * This class is a HashMap, but we extend the HashMap
 * class so that we can rename it to something meaningful.
 * <p>
 * Basically, the key of the map is the user ID, and the
 * value is the actual User object.
 * <p>
 * Using a HashMap allows us to quickly query the student
 * object.
 *
 * @author hssakaria
 *
 */
@SuppressWarnings("serial")
public class NewReleaseProbMap extends HashMap<String, NewReleaseProb> {

}

