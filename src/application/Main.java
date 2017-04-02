package application;
	
// JavaFX packages imports
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;

// Application packages imports
import core.Algo;
import maze.Maze;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Algorithm sandbox");

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
			
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
		
	public static void main(String[] args) {
		launch(args);
	}
	
	public void init() {
		Maze.INSTANCE.setDimensions(Settings.MAZE_WIDTH, Settings.MAZE_HEIGHT);
		Maze.INSTANCE.newGrid();
		
		m_mazeGenerator = Settings.GENERATOR;
		m_mazeSolver 	= Settings.SOLVER;
	}
	
	private Renderer  m_renderer;
	private Algo 	  m_mazeGenerator;
	private Algo	  m_mazeSolver;
	
}
