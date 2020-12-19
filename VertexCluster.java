import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;

/**
 * This is the core file for the application this class contains methods addEdge
 * and ClusterVertices
 * 
 * @author RajKumar
 *
 */
public class VertexCluster {
	Graph graph = null;

	/**
	 * Constructor to initialize the Graph
	 */
	public VertexCluster() {
		graph = new Graph();
	}

	/**
	 * adds given source, destination and weight to priority queue
	 * 
	 * @param vertex1
	 * @param vertex2
	 * @param weight
	 * @return
	 */
	public boolean addEdge(String vertex1, String vertex2, int weight) {
		// returns false if given vertex is blank or -ve weight
		// or node edge already exists in the PQueue.
		if (!isStrBlank(vertex1) && !isStrBlank(vertex2) && weight > 0 && !graph.isEdgeExists(vertex1, vertex2)
				&& vertex1 != vertex2) {
			Edge edge = new Edge(vertex1, vertex2, weight);
			graph.pq.add(edge);
			return true;
		}
		return false;
	}

	/**
	 * this method forms the clusters from the given graph
	 * 
	 * @param tolerance
	 * @return
	 */
	public Set<Set<String>> clusterVertices(float tolerance) {
		// proceed only if tolerance is non -ve value.
		if (tolerance >= 0) {
			PriorityQueue<Edge> pq1 = new PriorityQueue<Edge>(Comparator.comparingInt(x -> x.weight));
			for (Edge e : graph.pq) {
				pq1.add(e);
			}
			while (!graph.pq.isEmpty()) {
				Edge e = graph.pq.remove();
				// for first edge, directly add the edge to the graph.
				if (!graph.map.isEmpty()) {
					// if edge dosen't creates loop, add edge to graph
					if (graph.checkLoop(e.source, e.destination, new HashSet<String>())) {
						graph.addEdgeToMap(e.source, e.destination, e.weight, tolerance);
					}
				} else {
					graph.addEdgeToMap(e.source, e.destination, e.weight, tolerance);
				}
			}
			graph.pq = pq1;
			return graph.createClusters(graph.map);
		}
		return null;
	}

	/**
	 * check given string is null or blank else return true.
	 * 
	 * @param str
	 * @return
	 */
	private static boolean isStrBlank(String str) {
		if (str != null && !str.isEmpty()) {
			return str.split(" +").length == 0;
		}

		return true;
	}

}
