package application;
	
// JavaFX packages imports
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;

// Application packages imports
import core.*;
import maze.Maze;
import mazeGenerator.*; // Any maze generation algorithm
import mazeSolver.*; // Any maze solving algorithm

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Maze generator and solver");

			Group root = new Group();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			
			init();

			Canvas canvas = new Canvas(
					Settings.MAZE_WIDTH *Settings.NODE_WIDTH_PX, 
					Settings.MAZE_HEIGHT*Settings.NODE_HEIGHT_PX);
			root.getChildren().add(canvas);
			
			m_renderer = new Renderer(canvas.getGraphicsContext2D());
			
			m_renderer.setStory(m_mazeGenerator, m_mazeSolver);
			m_renderer.start();
			
			// check if javafx has some sort of storyboard or an entity that could drive the sequence of events
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
		
	public static void main(String[] args) {
		launch(args);
	}
	
	public void init() {
		Maze.INSTANCE.setDimensions(
				Settings.MAZE_WIDTH, 
				Settings.MAZE_HEIGHT);
		Maze.INSTANCE.newGrid();
		
		m_mazeGenerator = new DFS(); // TODO: Put something in the GUI in order to avoid hardcoding this 		
		m_mazeSolver = new AStar(); // TODO: Put something in the GUI in order to avoid hardcoding this
	}
	
	private Renderer  m_renderer;
	private Algo 	  m_mazeGenerator;
	private Algo	  m_mazeSolver;
	
}
