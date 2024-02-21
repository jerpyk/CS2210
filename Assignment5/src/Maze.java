
/* Class Name: Maze.java
 * Class Description: The class for creating a maze using the Graph object.
 * Author: Eunsung Kim, 251275156
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Stack;
import java.util.StringTokenizer;

public class Maze {
	// Instance Variables
	private Graph graph; // graph of the maze
	private GraphNode entrance, exit; // entrance and exit nodes (rooms)
	private int numbCoin; // number of coin available for unlocking doors
	private Stack<GraphNode> pathStack = new Stack<>(); // Stack for storing exit path

	// Constructor
	public Maze(String inputFile) throws MazeException {
		String line;
		int mazeWidth = 0;
		int mazeLength = 0;
		try (BufferedReader freader = new BufferedReader(new FileReader(inputFile))) {
			// get the first 4 lines of the maze input file, skip unused scale factor
			for (int i = 1; i <= 4; i++) {
				line = freader.readLine();
				switch (i) {
				// get the width of the maze
				case 2:
					mazeWidth = Integer.parseInt(line);
					break;
				// get the length of the maze
				case 3:
					mazeLength = Integer.parseInt(line);
					break;
				// get the number of coin available for the maze
				case 4:
					this.numbCoin = Integer.parseInt(line);
					break;
				}
			}

			// create a graph with specified maze size
			graph = new Graph(mazeWidth * mazeLength);
			// number for keeping track of the node
			int nodeNumb = 0;

			// for every row in the maze input file
			for (int i = 0; i < (mazeLength * 2 - 1); i++) {
				line = freader.readLine();
				line = line.replace("", " ").trim();
				StringTokenizer st = new StringTokenizer(line, " ");
				char ch;
				// for every RHRHRH... line of maze input file
				if (i % 2 == 0) {
					for (int j = 0; j < (mazeWidth * 2 - 1); j++) {
						ch = st.nextToken().charAt(0);
						if (j % 2 == 0) {
							// if it is a starting room
							if (ch == 's') {
								entrance = graph.getNode(nodeNumb);
								// if it is an exiting room
							} else if (ch == 'x') {
								exit = graph.getNode(nodeNumb);
							}
							// increase the name of the graph node
							nodeNumb++;
						} else {
							// if it is a corridor, insert the edge to the adjacency matrix, connecting
							// side-by-side nodes
							if (ch == 'c') {
								graph.insertEdge(graph.getNode(nodeNumb - 1), graph.getNode(nodeNumb), 0, "corridor");
								// if it is a door, insert the edge
							} else if (Character.isDigit(ch)) {
								graph.insertEdge(graph.getNode(nodeNumb - 1), graph.getNode(nodeNumb),
										Character.getNumericValue(ch), "door");
							}
						}
					}

				}
				// for every VWVWVW... line of maze input file
				else {
					for (int j = 0; j < mazeWidth; j++) {
						ch = st.nextToken().charAt(0);
						// if the path is a corridor
						if (ch == 'c') {
							// insert to the graph an edge from top room to the bottom room connected by the
							// corridor
							graph.insertEdge(graph.getNode(nodeNumb - mazeWidth + j), graph.getNode(nodeNumb + j), 0,
									"corridor");

						}
						// if the path is a door with a digit
						else if (Character.isDigit(ch)) {
							// insert to the graph an edge from top room to the bottom room connected by the
							// door with the digit of coin to enter
							graph.insertEdge(graph.getNode(nodeNumb - mazeWidth + j), graph.getNode(nodeNumb + j),
									Character.getNumericValue(ch), "door");
						}
						// skip the walls
						if (j != mazeWidth - 1) {
							ch = st.nextToken().charAt(0);
						}
					}
				}
			}
			// throw MazeExceptionf if the file does not exist, or the format of the input
			// file is incorrect
		} catch (IOException e) {
			throw new MazeException("Input file does not exist, or format of the input file is incorrect");
			// error handling for GraphException
		} catch (GraphException e) {
			System.out.println(e.getMessage());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Returns the graph of the maze
	public Graph getGraph() throws MazeException {
		if (this.graph == null) {
			throw new MazeException("Maze of the graph is empty");
		} else
			return this.graph;
	}

	// Returns the iterator of stack storing the GraphNodes as a path
	public Iterator<GraphNode> solve() throws GraphException {
		// if there is a path, return the iterator of the path stack
		if (this.DFSPath(entrance, exit)) {
			return pathStack.iterator();
		} else {
			return null;
		}
	}

	// Helper method that returns true if there is a path from s to d, returns false otherwise
	private boolean DFSPath(GraphNode s, GraphNode d) throws GraphException {
		pathStack.push(s);
		s.mark(true);
		// if the current node is the destination node, return true
		if (s == d) {
			return true;
		}

		// Get the list of incident edges
		Iterator<GraphEdge> incEdges = graph.incidentEdges(s);

		// for every incident edge of node s
		while (incEdges.hasNext()) {
			GraphEdge edge = incEdges.next();
			GraphNode u = edge.secondEndpoint(); // get the other node u of the edge
			// if the u is not marked, and the coin required is less than or equal to the
			// number of coins available
			if (!u.isMarked() && edge.getType() <= this.numbCoin) {
				// temporarily take away the coin
				this.numbCoin -= edge.getType();
				// recursively call the DFSPath method from node u to d
				if (this.DFSPath(u, d)) {
					return true; 
				}
				// restore the coin for the door
				this.numbCoin += edge.getType();
			}
		}
		pathStack.pop(); 
		s.mark(false); // if there is no path, unmark the node
		return false;
	}
}
