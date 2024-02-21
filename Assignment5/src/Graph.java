/* Class Name: Graph.java
 * Class Description: The class for Graph object used to store the nodes and edges of the maze
 * Author: Eunsung Kim, 251275156
 */
import java.util.ArrayList;
import java.util.Iterator;

public class Graph implements GraphADT {
	private GraphNode[] vertexArr; // array of nodes (vertices)
	private GraphEdge[][] adjMatrix; // adjacency matrix of edges

	// Constructor
	public Graph(int n) {
		// initialize empty vertex array and adjacency matrix
		vertexArr = new GraphNode[n];
		adjMatrix = new GraphEdge[n][n];
		// fill the vertex array with nodes starting of name 0 to n-1
		for (int i = 0; i < n; i++) {
			vertexArr[i] = new GraphNode(i);
		}
	}

	// Inserts the specified GraphEdge to the adjacency matrix
	public void insertEdge(GraphNode u, GraphNode v, int type, String label) throws GraphException {
		// if the any of the two nodes is not in the vertex array, throw an exception
		if (vertexArr[u.getName()] != u || vertexArr[v.getName()] != v) {
			throw new GraphException("Graph node of the edge does not exist.");
			// if the adjacency matrix at the location is not null, throw an excpetion
		} else if (adjMatrix[u.getName()][v.getName()] != null) {
			throw new GraphException("Graph edge already exists.");
		}
		// insert edges (u, v) and (v, u) to the adjacency matrix the correct position
		adjMatrix[u.getName()][v.getName()] = new GraphEdge(u, v, type, label);
		adjMatrix[v.getName()][u.getName()] = new GraphEdge(v, u, type, label);
	}

	// Returns the node with the name u
	public GraphNode getNode(int u) throws GraphException {
		// if the graph node does not exist in the vertex array
		if (u < 0 || u > vertexArr.length) {
			throw new GraphException("Graph node does not exist.");
		}
		return vertexArr[u];
	}

	// Returns the Iterator storing all the edges incident on node u
	public Iterator<GraphEdge> incidentEdges(GraphNode u) throws GraphException {
		ArrayList<GraphEdge> incEdgeList = new ArrayList<>();
		// traverse the row with node u as the first node of the edge
		for (int i = 0; i < vertexArr.length; i++) {
			if (adjMatrix[u.getName()][i] != null) {
				// add to the ArrayList all the edges in the row
				incEdgeList.add(adjMatrix[u.getName()][i]);
			}
		}
		if (incEdgeList.isEmpty()) {
			return null;
		} else {
			// return the iterator of the ArrayList of GraphEdge objects if it is not empty
			return incEdgeList.iterator();
		}
	}

	// Returns the GraphEdge between GraphNode u and v
	public GraphEdge getEdge(GraphNode u, GraphNode v) throws GraphException {
		// if the edge does not exist at the expected position of the adjacency matrix
		if (adjMatrix[u.getName()][v.getName()] == null) {
			throw new GraphException("Graph Edge does not exist.");
		} else {
			// return the edge if not null
			return adjMatrix[u.getName()][v.getName()];
		}
	}

	// Returns true if GraphNode u and v are adjacent, false otherwise
	public boolean areAdjacent(GraphNode u, GraphNode v) throws GraphException {
		// if any of the two nodes are not in the vertex array
		if (vertexArr[u.getName()] != u || vertexArr[v.getName()] != v) {
			throw new GraphException("Graph node of the edge does not exist.");
		}
		// if the edge is not null, return the edge, otherwise, return null
		if (adjMatrix[u.getName()][v.getName()] != null) {
			return true;
		} else {
			return false;
		}
	}

}
