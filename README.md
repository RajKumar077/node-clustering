# node-clustering
undirected weighted graph | clustering | The basic idea of the clustering is to start with each document as its own cluster and to repeatedly merge clusters until we reach a balance point.


Solution Overview:
The objective of the application is to create an undirected graph that contains vertices connected by
weighted edges. Lower the edge value higher the similarity between the vertices connected by that
edge. This application forms the clusters for the given graph, where each cluster contains the vertices
similar to each other. The formation of cluster depends on the “Tolerance” value passed as an input.
If the weight of an edge between two clusters divided by a minimum weight of clusters is less than or
equal to the tolerance value, then both clusters are merged to a single cluster. By default, each vertex
is a cluster with weight as one.
Files and Structure:
There are a total of three java files in the application
1. VertexCluster: This is the main class for the application, which contains core methods like
addEdge and clusterVerticies.
2. Graph: This class is used to store and represent the graph structure. Used adjacency list and
priority queues to store the graph structure and have methods to manipulate graphs and
forms cluster from the given graph.
3. Edge: Each Edge contains a source, destination and weight between both the vertices. This
class provides the implementation of the edge.
Data Structures:
1. Priority-queue is used to store the vertices and weight between those. Priority is based on
the weights of the edges. Lowest weight gets the highest priority and dequeues the element
with less weight to form clusters.
2. Adjacency List is used to storing the clustered graph.
3. Set is used to store the vertices of the same cluster.
Methods and Implementation:
VertexCluster
1. Boolean addEdge: this method adds the given source, destination and weight to the priority
queue. Implementation of this method is as follows:
I. Check source and destination are not null or empty and weight is a positive integer.
II. Check given vertices not part of the existing vertex
III. Create an edge object and insert it into the priority queue.
2. Set<Set<String>> clusterVertices: this method returns the set of clusters resulted from the
given set vertices for the given tolerance. Implementation of this method is as follows:
I. Check if tolerance is a positive value.
II. Until the priority queue is empty, do the following.
III. Get the shortest edge from the priority queue.
IV. Check if the adjacency list is empty.
V. If empty, add given vertices to the list.
VI. Else, if given vertices are not forming any loop, then add to the adjacency list.
VII. Return the clusters.
3. Boolean isStrBlank: this method is used to check whether given vertices is null or empty.
Graph
1. Boolean isEdgeExists: to check if the given edge already exists in the priority queue.
I. For each element in the priority queue.
II. Check if given vertices match with the source and destination
III. It true, return false.
2. addEdgeToMap: This method stores the similar vertices form the priority queue into an
adjacency list. The implementation of this method is as follows.
I. By default, consider the weight of each cluster as one.
II. Add source to the adjacency list if the source already does not exist.
III. As this is a bidirected graph, add destination as a source in the list.
IV. Source to destination’s weight is the weight of the cluster.
V. Get the weights of the cluster.
VI. If the given vertices belong to the same cluster, link source and destination.
VII. Else, don’t link source and destination.
3. createClusters: from the formed adjacency list, this method forms the cluster sets.
Implementation of this method is as follows:
I. if the map is not null and empty, for each element in the map.
II. Get the key.
III. Form the given key, get all destinations in the adjacency list.
IV. This forms the cluster.
4. Boolean checkLoop: this is a recursive method used to check whether given edge forms any
loop. Implementation of the method is as follows:
I. Add the given source to the set.
II. Get all destinations from the source.
III. If there are no destinations, then the given edge can not form any loop.
IV. For each destination, check if the destination is equal to the given vertex.
V. If not, continue to check for all destinations.
VI. Return false if the given edge is forming any loops.
5. Boolean isSameCluster: this method contains the formula for comparing the tolerance with
the given cluster weights.
I. Get the minimum weight of the clusters.
II. Divide the minimum weight with the weight of the edge.
III. If the value is less than tolerance, both vertices belong to the same cluster.
IV. Else, vertices do not belong to the same cluster.
6. int min: this method compares the given two weights of the clusters and returns the minimum
weight.
7. createSet: this method creates sets from the adjacency list. Implementation of this method is
as follows:
I. Add the given source to the set.
II. Get all destinations from the source.
III. If there are no destinations, then return the set with the only source.
IV. For each destination.
V. If destination not in the set
VI. Call createSet for the destination to iterate all the destinations of the source.
VII. And repeat step I, until all sources are covered.
Key Algorithm and Design:
The key implementation of the application is identifying whether the given vertex is part of the existing
cluster or it forms a new cluster. The design is as follow:
I. Get the minimum weighted edge from the priority queue.
II. Check whether it forms any loop by iterating through the adjacency list.
III. If it forms any loop, don’t add source and destination to the adjacency list.
IV. If no loop is formed, check whether the source and destination belong to the same cluster or
not by executing the given formula.
V. If they don’t belong to the same cluster add source and destination to the adjacency list but
don’t link source and destination.
VI. If they belong to the same cluster add source and destination to the cluster and link source
and destination.
VII. Once all the vertices are added to the adjacency list, start from the first element in the
adjacency list
VIII. Add all destinations from the source to a set
Assumptions:
1. Vertices are case sensitive; Two vertices with the same names and different case sensitivity
are considered different.
2. Graph can be unconnected; not all vertices are not necessary to connect to each other.
References:
1. https://en.wikipedia.org/wiki/Kruskal%27s_algorithm
2. https://www.geeksforgeeks.org/implementing-generic-graph-in-java/
3. https://algorithms.tutorialhorizon.com/graph-print-all-paths-between-source-anddestination/
