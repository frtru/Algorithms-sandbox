package maze;

public enum Maze {
	
	INSTANCE; // Singleton yeaaah!
	
	public int getWidth() 		{ return m_width; }
	public int getHeight()		{ return m_height;}
	
	public Node getStartNode() 	{ return m_start; }
	public Node getEndNode()	{ return m_end;	  }
	
	public Node getNodeAt(int a_X, int a_Y) {
		if (a_X >= m_width || a_X < 0 ||
		    a_Y >= m_height || a_Y < 0) {
			System.err.println("Ouf of bounds error:");
			System.err.println("Maze width: " + m_width + ", X value caught: " + a_X);
			System.err.println("Maze height: " + m_height + ", Y value caught: " + a_Y);
			Thread.dumpStack();
			System.exit(-1);
		}
		return m_grid[a_X*m_height + a_Y]; 
	}
	
	public void setStartNode(Node a_node)	{ m_start = a_node; }
	public void setEndNode(Node a_node)		{ m_end = a_node; 	}
	
	public void setDimensions(int a_width, int a_height) {
		m_width = a_width; 
		m_height = a_height;
	}
	
	public void undoVisitedNodes() {
		for (Node n: m_grid) {
			n.setUnvisited();
		}
	}
	
	public void newGrid() {
		m_grid = new Node[m_width*m_height];
		
		// Allocate every node
		for (int i = 0; i < m_width; ++i) {
			for (int j = 0; j < m_height; ++j) {				
				m_grid[i*m_height + j] = new Node(i, j);
			}
		}
		
		// Set neighbors
		for (int i = 0; i < m_width; ++i) {
			for (int j = 0; j < m_height; ++j) {
				Node n = getNodeAt(i, j);
			    if (j > 0) {     // has south
			    	n.setSouthNeighbor(getNodeAt(i, j-1));
			    }
			    if (j < m_height - 1) { // has north
			    	n.setNorthNeighbor(getNodeAt(i, j+1));
			    }
			    if (i > 0) {     // has west
			    	n.setWestNeighbor(getNodeAt(i-1, j));
			    }
			    if (i < m_width - 1) { // has east
			    	n.setEastNeighbor(getNodeAt(i+1, j));
			    }					
			}
		}
	}
	
	private int 	m_width, m_height;
	private Node[] 	m_grid;
	private Node	m_start = null;
	private Node	m_end = null;

}
