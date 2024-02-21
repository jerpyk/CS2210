/* Class Name: GraphEdge.java
 * Class Description: The class is for each GraphEdge object of the graph
 * created for the pathways of the maze.
 * Author: Eunsung Kim, 251275156
 */
public class GraphEdge {
	// Instance Variables
	private GraphNode end1;
	private GraphNode end2;
	private int type; // number of coins needed to pass the edge (0-9)
	private String label; // label of the edge (corridor or door)

	// Constructor
	public GraphEdge(GraphNode u, GraphNode v, int type, String label) {
		this.end1 = u;
		this.end2 = v;
		this.type = type;
		this.label = label;
	}

	// Returns first end point GraphNode
	public GraphNode firstEndpoint() {
		return this.end1;
	}

	// Returns second end point GraphNode
	public GraphNode secondEndpoint() {
		return this.end2;
	}

	// Returns type of the graph
	public int getType() {
		return this.type;
	}

	// Set type of the GraphEdge
	public void setType(int newType) {
		this.type = newType;
	}

	// Returns label of the GraphEdge
	public String getLabel() {
		return this.label;
	}

	// Set label of the GraphEdge
	public void setLabel(String newLabel) {
		this.label = newLabel;
	}
}
