package pl.allegro;

import java.util.List;
import java.util.Random;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameOfLife extends Application {

	public static final String APPLICATION_TITLE = "Game of Life";
	public static final int CELL_SIZE = 10;
	public static final int GRID_COLS = 30;
	public static final int GRID_ROWS = 30;
	public static final int GRID_HEIGHT = GRID_COLS * CELL_SIZE;
	public static final int GRID_WIDTH = GRID_ROWS * CELL_SIZE;

	private Canvas gameCanvas;
	private GraphicsContext gc;
	private boolean[][] grid = new boolean[GRID_COLS][GRID_ROWS];


	public static void main(String[] args) {
		System.out.println("Game of Life starting...");
		launch(args);
	}

	@Override
	public void init() throws Exception {
		initGrid();
	}

	private void initGrid() {
		randomGrid();
//		createBlinker(10, 10);
//		createGlider(22, 2);
	}

	private void createBlinker(int x, int y) {
		grid[y][x] = true;
		grid[y][x+1] = true;
		grid[y][x+2] = true;
	}

	private void createGlider(int x, int y) {
		grid[y][x+1] = true;
		grid[y+1][x+2] = true;
		grid[y+2][x] = true;
		grid[y+2][x+1] = true;
		grid[y+2][x+2] = true;
	}

	private void randomGrid() {
		Random random = new Random();
		for(int i = 0; i < GRID_ROWS; i++) {
			for(int j = 0; j < GRID_COLS; j++) {
				grid[i][j] = random.nextInt() % 6 == 0;
			}
		}
	}

	@Override
	public void start(Stage stage) {
		stage.setTitle(APPLICATION_TITLE);
		Scene gameScene = createScene();
		stage.setScene(gameScene);
		renderGrid();
		stage.show();
	}

	private Scene createScene() {
		FlowPane rootNode = new FlowPane();
		rootNode.setAlignment(Pos.CENTER);
		gameCanvas = new Canvas(GRID_WIDTH, GRID_HEIGHT);
		gc = gameCanvas.getGraphicsContext2D();
		gameCanvas.setFocusTraversable(true);
		gameCanvas.setOnKeyPressed(event -> {
			switch (event.getCode()) {
				case RIGHT:
					nextIteration();
					break;
			}
		});
		rootNode.getChildren().add(gameCanvas);
		return new Scene(rootNode, GRID_WIDTH, GRID_HEIGHT);
	}

	private void nextIteration() {
		boolean[][] newGrid = new boolean[GRID_COLS][GRID_ROWS];
		for(int i = 0; i< GRID_ROWS; i++) {
			for(int j = 0; j< GRID_COLS; j++) {
				newGrid[i][j] = calculateLife(grid, Position.of(j, i));
			}
		}
		grid = newGrid;
		renderGrid();
	}

	private boolean calculateLife(boolean[][] grid, Position position) {
		List<Position> neighbours = position.neighbours();
		boolean alive = isAliveAt(grid, position);
		long aliveNeighbours = neighbours.stream()
				.map(pos -> isAliveAt(grid, pos))
				.filter(status -> status)
				.count();
		if (alive) {
			return aliveNeighbours >= 2 && aliveNeighbours <= 3;
		} else {
			return aliveNeighbours == 3;
		}
	}

	private boolean isAliveAt(boolean[][] grid, Position position) {
		return grid[position.y][position.x];
	}

	private void renderGrid() {
		clearGrid();
		gc.setFill(Color.BLACK);
		for(int i = 0; i< GRID_ROWS; i++) {
			for(int j = 0; j< GRID_COLS; j++) {
				if (grid[i][j]) {
					drawCell(j, i);
				}
			}
		}
	}

	private void clearGrid() {
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, GRID_WIDTH, GRID_HEIGHT);
	}

	private void drawCell(int column, int row) {
		int x = column * CELL_SIZE;
		int y = row * CELL_SIZE;
		gc.fillRect(x, y, CELL_SIZE, CELL_SIZE);
	}

	@Override
	public void stop() {
	}
}
