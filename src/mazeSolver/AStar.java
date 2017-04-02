package mazeSolver;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

//Application packages imports
import core.Algo;
import core.utils.Position;
import maze.Maze;
import maze.Node;
import maze.entity.Checkpoint;

public class AStar extends Algo{

	@Override
	public void init() {
		m_heuristicWeight = 1;
		m_distanceBetweenNeighbors = 1;
		m_currentNode = Maze.INSTANCE.getStartNode();
		
		m_openSet.add(m_currentNode);
		m_cameFrom.put(m_currentNode,null);
	}

	@Override
	public boolean next() {
		if (!m_openSet.isEmpty()) {	
			m_currentNode = getBestNode();
			if (m_currentNode == Maze.INSTANCE.getEndNode()) {
				return false;
			}
			
			m_openSet.remove(m_currentNode);
			m_closedSet.add(m_currentNode);
			m_currentNode.setCheckpoint(Checkpoint.POTENTIAL_SOLUTION);
			
			for (Node neighbor: m_currentNode.getFloorNeighbors()) {
				if (m_closedSet.contains(neighbor)) {
					continue;
				}
				if (!m_openSet.contains(neighbor)) {
					m_openSet.add(neighbor);
				}
				// Determine if the neighbor leads to a better path
				//Integer gScore = getGScore(m_currentNode) + m_distanceBetweenNeighbors;
				//if (gScore >= getGScore(neighbor)) {
				//	continue; // It's not a better path
				//}
				// Best path until now
				m_cameFrom.put(neighbor, m_currentNode);
			}
			return true;
		}		
		// TODO: Print failure, A star failed to find path
		return false;
	}

	@Override
	public void end() {
		// Print solution by reconstructing path
		HashSet<Node>	totalPath = new HashSet<Node>();
		totalPath.add(m_currentNode);
		Node n = m_cameFrom.get(m_currentNode);

		while (n != null) {
			m_currentNode = n;
			n = m_cameFrom.get(m_currentNode);
			totalPath.add(m_currentNode);
		}
		
		for (Node solutionNode : totalPath) {
			solutionNode.setCheckpoint(Checkpoint.SOLUTION);
		}
		m_cameFrom.clear();
		m_closedSet.clear();
		m_openSet.clear();
	}
	
	private Node getBestNode() {
		Integer lowestFValue = Integer.MAX_VALUE;
		Node	bestNode = null;
		for (Node n: m_openSet) {
			Integer fScore = getFScore(n);
			if (getFScore(n) < lowestFValue) {
				bestNode = n;
				lowestFValue = fScore;
			}
		}
		return bestNode;
	}
	
	private int	getFScore(Node n) {
		return getGScore(n) + m_heuristicWeight*getHScore(n);
	}
	
	// Returns the cost of node n
	private int getGScore(Node n) {
		// TODO: Should return the length of the path when reaching node n
		// Or maybe just the distance with the start
		return getDistanceBetweenNodes(n, Maze.INSTANCE.getStartNode());
		//return 0;
	}

	// Returns the heuristic of node n
	private int getHScore(Node n) {
		// Return distance from n to end node
		return getDistanceBetweenNodes(n, Maze.INSTANCE.getEndNode());
	}
	
	private int getDistanceBetweenNodes(Node n1, Node n2) {
		Position p1 = n1.getPosition(), 
				 p2 = n2.getPosition();
		return Math.abs(p1.getX() - p2.getX()) + Math.abs(p1.getY() - p2.getY());
	}
	
	Random 					m_rand 			= new Random();
	Node 					m_currentNode 	= null;
	HashMap<Node,Node> 		m_cameFrom		= new HashMap<Node, Node>();
	HashSet<Node>			m_closedSet		= new HashSet<Node>();
	HashSet<Node> 			m_openSet		= new HashSet<Node>();

	int 		m_heuristicWeight;
	int			m_distanceBetweenNeighbors;
}
