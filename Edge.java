/**
 * Each edge contains a source, destination and a weighted between both the
 * vertices
 * 
 * @author RajKumar
 *
 */
public class Edge {
	String source;
	String destination;
	int weight;

	public Edge(String source, String destination, int weight) {
		this.source = source;
		this.destination = destination;
		this.weight = weight;
	}
}