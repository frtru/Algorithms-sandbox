package test;

import java.util.HashMap;

//JUnit packages imports
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

//Application packages imports
import core.Direction;
import maze.*;

public class NodeNeighborsTest {

	@Before
	public void setUp() throws Exception {
		Maze.INSTANCE.setDimensions(m_mazeWidth, m_mazeHeight);
		Maze.INSTANCE.newGrid();
	}

	@Test
	public void test() {
		// For each subtest, test
			// 1- Number of neighbors
			// 2- If neighbors are the ones expected

		testLeftBoundaryNeighbors();
		testRightBoundaryNeighbors();
		testNorthBoundaryNeighbors();
		testSouthBoundaryNeighbors();
		testNorthLeftCornerNeighbors();
		testNorthRightCornerNeighbors();
		testSouthLeftCornerNeighbors();
		testSouthRightCornerNeighbors();
	}
	
	private void testLeftBoundaryNeighbors() {
		int X = 0, Y = 5;
		Node node = Maze.INSTANCE.getNodeAt(X, Y);
		HashMap<Integer,Node> neighbors = node.getNeighbors();
		assertEquals(3, neighbors.size());
		
		Node south = neighbors.get(Direction.SOUTH),
			 north = neighbors.get(Direction.NORTH),
			 east = neighbors.get(Direction.EAST);
							
		assertNotEquals(null, south);
		assertNotEquals(null, north);
		assertNotEquals(null, east);

		assertEquals(X, south.getPosition().getX());
		assertEquals(Y-1, south.getPosition().getY());
		assertEquals(X, north.getPosition().getX());
		assertEquals(Y+1, north.getPosition().getY());
		assertEquals(X+1, east.getPosition().getX());
		assertEquals(Y, east.getPosition().getY());		
		System.out.println("LEFT BOUNDARY: OK");
	}	
	
	private void testRightBoundaryNeighbors() {
		int X = 9, Y = 5;
		Node node = Maze.INSTANCE.getNodeAt(X, Y);
		HashMap<Integer,Node> neighbors = node.getNeighbors();
		assertEquals(3, neighbors.size());
		
		Node south = neighbors.get(Direction.SOUTH),
			 north = neighbors.get(Direction.NORTH),
			 west = neighbors.get(Direction.WEST);
							
		assertNotEquals(null, south);
		assertNotEquals(null, north);
		assertNotEquals(null, west);

		assertEquals(X, south.getPosition().getX());
		assertEquals(Y-1, south.getPosition().getY());
		assertEquals(X, north.getPosition().getX());
		assertEquals(Y+1, north.getPosition().getY());
		assertEquals(X-1, west.getPosition().getX());
		assertEquals(Y, west.getPosition().getY());		
		System.out.println("RIGHT BOUNDARY: OK");
	}
	
	private void testNorthBoundaryNeighbors() {
		int X = 5, Y = 9;
		Node node = Maze.INSTANCE.getNodeAt(X, Y);
		HashMap<Integer,Node> neighbors = node.getNeighbors();
		assertEquals(3, neighbors.size());
		
		Node south = neighbors.get(Direction.SOUTH),
			 east = neighbors.get(Direction.EAST),
			 west = neighbors.get(Direction.WEST);
							
		assertNotEquals(null, south);
		assertNotEquals(null, east);
		assertNotEquals(null, west);

		assertEquals(X, south.getPosition().getX());
		assertEquals(Y-1, south.getPosition().getY());
		assertEquals(X+1, east.getPosition().getX());
		assertEquals(Y, east.getPosition().getY());
		assertEquals(X-1, west.getPosition().getX());
		assertEquals(Y, west.getPosition().getY());		
		System.out.println("NORTH BOUNDARY: OK");
	}
	
	private void testSouthBoundaryNeighbors() {
		int X = 5, Y = 0;
		Node node = Maze.INSTANCE.getNodeAt(X, Y);
		HashMap<Integer,Node> neighbors = node.getNeighbors();
		assertEquals(3, neighbors.size());
		
		Node north = neighbors.get(Direction.NORTH),
			 east = neighbors.get(Direction.EAST),
			 west = neighbors.get(Direction.WEST);
							
		assertNotEquals(null, north);
		assertNotEquals(null, east);
		assertNotEquals(null, west);

		assertEquals(X, north.getPosition().getX());
		assertEquals(Y+1, north.getPosition().getY());
		assertEquals(X+1, east.getPosition().getX());
		assertEquals(Y, east.getPosition().getY());
		assertEquals(X-1, west.getPosition().getX());
		assertEquals(Y, west.getPosition().getY());			
		System.out.println("SOUTH BOUNDARY: OK");
	}
	
	private void testNorthLeftCornerNeighbors() {
		int X =0, Y = 9;
		Node node = Maze.INSTANCE.getNodeAt(X, Y);
		HashMap<Integer,Node> neighbors = node.getNeighbors();
		assertEquals(2, neighbors.size());
		
		Node south = neighbors.get(Direction.SOUTH),
			 east = neighbors.get(Direction.EAST);
							
		assertNotEquals(null, south);
		assertNotEquals(null, east);

		assertEquals(X, south.getPosition().getX());
		assertEquals(Y-1, south.getPosition().getY());
		assertEquals(X+1, east.getPosition().getX());
		assertEquals(Y, east.getPosition().getY());
		System.out.println("NORTH LEFT CORNER: OK");
	}
	
	private void testNorthRightCornerNeighbors() {
		int X =9, Y = 9;
		Node node = Maze.INSTANCE.getNodeAt(X, Y);
		HashMap<Integer,Node> neighbors = node.getNeighbors();
		assertEquals(2, neighbors.size());
		
		Node south = neighbors.get(Direction.SOUTH),
			 west = neighbors.get(Direction.WEST);
							
		assertNotEquals(null, south);
		assertNotEquals(null, west);

		assertEquals(X, south.getPosition().getX());
		assertEquals(Y-1, south.getPosition().getY());
		assertEquals(X-1, west.getPosition().getX());
		assertEquals(Y, west.getPosition().getY());		
		System.out.println("NORTH RIGHT CORNER: OK");
	}
	
	private void testSouthLeftCornerNeighbors() {
		int X =0, Y = 0;
		Node node = Maze.INSTANCE.getNodeAt(X, Y);
		HashMap<Integer,Node> neighbors = node.getNeighbors();
		assertEquals(2, neighbors.size());
		
		Node north = neighbors.get(Direction.NORTH),
			 east = neighbors.get(Direction.EAST);
							
		assertNotEquals(null, north);
		assertNotEquals(null, east);

		assertEquals(X, north.getPosition().getX());
		assertEquals(Y+1, north.getPosition().getY());
		assertEquals(X+1, east.getPosition().getX());
		assertEquals(Y, east.getPosition().getY());		
		System.out.println("SOUTH LEFT CORNER: OK");
	}
	
	private void testSouthRightCornerNeighbors() {
		int X =9, Y = 0;
		Node node = Maze.INSTANCE.getNodeAt(X, Y);
		HashMap<Integer,Node> neighbors = node.getNeighbors();
		assertEquals(2, neighbors.size());
		
		Node north = neighbors.get(Direction.NORTH),
			 west = neighbors.get(Direction.WEST);
							
		assertNotEquals(null, north);
		assertNotEquals(null, west);

		assertEquals(X, north.getPosition().getX());
		assertEquals(Y+1, north.getPosition().getY());
		assertEquals(X-1, west.getPosition().getX());
		assertEquals(Y, west.getPosition().getY());		
		System.out.println("SOUTH RIGHT CORNER: OK");
	}
	
	int m_mazeWidth = 10, m_mazeHeight = 10;

}
