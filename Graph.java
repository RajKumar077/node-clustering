import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;

/**
 * this file implements the graph structure to store all the edges, used
 * priority queues to sort the weights of edges and adj list to store clustered
 * graph
 * 
 * @author RajKumar
 *
 */
public class Graph {

	Edge edge = null;
	Map<String, List<Edge>> map = null;
	PriorityQueue<Edge> pq = null;

	/**
	 * initializes the pq and hashmap.
	 */
	public Graph() {
		map = new HashMap<>();
		pq = new PriorityQueue<Edge>(Comparator.comparingInt(x -> x.weight));
	}

	/**
	 * check whether given vertex exists or not.
	 * 
	 * @param vertex1
	 * @param vertex2
	 * @return
	 */
	public boolean isEdgeExists(String vertex1, String vertex2) {
		for (Edge e : pq) {
			if (e.destination == vertex1 && e.source == vertex2 || e.destination == vertex2 && e.source == vertex1) {
				return true;
			}
		}
		return false;
	}

	/**
	 * this method stores the given edge in a hashmap.
	 * 
	 * @param source
	 * @param destination
	 * @param weight
	 * @param tolerance
	 */
	public void addEdgeToMap(String source, String destination, int weight, float tolerance) {
		// by default weight of single node cluster is 1.
		int v1 = 1;
		int v2 = 1;
		// store source & destinations
		if (!map.containsKey(source)) {
			map.put(source, new LinkedList<Edge>());
		}
		if (!map.containsKey(destination)) {
			map.put(destination, new LinkedList<Edge>());
		}
		List<Edge> sourceClust = map.get(source);
		List<Edge> destClust = map.get(destination);
		// get the heavy weight of the cluster
		if (!sourceClust.isEmpty()) {
			v1 = sourceClust.get(0).weight;
		}
		if (!destClust.isEmpty()) {
			v2 = destClust.get(0).weight;
		}
		// link two clusters only if the belong to same cluster
		if (isSameCluster(weight, v1, v2, tolerance)) {
			// update weight of the cluster with the heavy weight
			if (!sourceClust.isEmpty() && weight > sourceClust.get(0).weight) {
				sourceClust.get(0).weight = weight;
			}
			if (!destClust.isEmpty() && weight > destClust.get(0).weight) {
				destClust.get(0).weight = weight;
			}
			// store destination.
			edge = new Edge(source, destination, weight);
			sourceClust.add(edge);
			// store destination for undirected graph.
			edge = new Edge(destination, source, weight);
			destClust.add(edge);
		}

	}

	/**
	 * from the given map, this method create the clusters
	 * 
	 * @param map
	 * @return
	 */
	public Set<Set<String>> createClusters(Map<String, List<Edge>> map) {
		if (!map.isEmpty()) {
			Set<String> checkSet = new HashSet<String>();
			Set<Set<String>> clusterSet = new TreeSet<Set<String>>(Comparator.comparing(x -> x.iterator().next()));
			for (Map.Entry element : map.entrySet()) {
				String source = (String) element.getKey();
				// iterate through elements that are not part of existing set
				if (!checkSet.contains(source)) {
					Set<String> set = new TreeSet<String>();
					// create set
					set = createSet(source, set);
					checkSet.addAll(set);
					clusterSet.add(set);
				}
			}
			return clusterSet;
		}

		return null;
	}

	/**
	 * a recursive method, used to check whether given edge forms any loop
	 * 
	 * @param start
	 * @param end
	 * @param set
	 * @return
	 */
	public boolean checkLoop(String start, String end, Set<String> set) {
		set.add(start);
		List<Edge> list = map.get(start);
		if (list == null) {
			return true;
		}
		for (int i = 0; i < list.size(); i++) {
			// get all destinations linked to start(source).
			Edge node = list.get(i);

			if (node.destination != end && !set.contains(node.destination)) {
				return checkLoop(node.destination, end, set);
			} else if (node.destination == end) {
				return false;
			}
		}
		set.remove(start);
		return true;
	}

	/**
	 * to check whether given edge belongs to same cluster
	 * 
	 * @param weight
	 * @param v1
	 * @param v2
	 * @param tolerance
	 * @return
	 */
	private boolean isSameCluster(int weight, int v1, int v2, float tolerance) {
		return (float) weight / min(v1, v2) <= tolerance;
	}

	/**
	 * returns minimum number
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	private int min(int v1, int v2) {
		if (v1 > v2) {
			return v2;
		}
		return v1;
	}

	/**
	 * this method creates the clusters.
	 * 
	 * @param start
	 * @param set
	 * @return
	 */
	private Set<String> createSet(String start, Set<String> set) {
		// adds same cluster elements to set.
		set.add(start);
		List<Edge> list = map.get(start);
		if (list == null) {
			return set;
		}
		for (int i = 0; i < list.size(); i++) {
			Edge node = list.get(i);

			if (!set.contains(node.destination)) {
				// set.add(node.destination);
				createSet(node.destination, set);
			}
		}
		return set;
	}

}
