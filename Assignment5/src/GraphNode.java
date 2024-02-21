/* Class Name: GraphNode.java
 * Class Description: The class is for each GraphNode object of the graph
 * created for the rooms of the maze.
 * Author: Eunsung Kim, 251275156
 */
public class GraphNode {
	private int name; // ranges from 0 to n-1 (n is the number of nodes in the graph)
	private boolean mark; // 

	// Constructor
	public GraphNode(int name) {
		this.name = name;
		this.mark = false;
	}

	// Marks the node of the graph with the specified mark, either true or false
	public void mark(boolean mark) {
		this.mark = mark;
	}

	// Returns true if marked, false if not marked
	public boolean isMarked() {
		return this.mark;
	}

	// Returns the name of the graph node
	public int getName() {
		return this.name;
	}
}
