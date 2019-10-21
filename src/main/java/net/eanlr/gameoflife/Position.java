package net.eanlr.gameoflife;

import java.util.ArrayList;
import java.util.List;

class Position {
	final int x;
	final int y;

	private Position(int row, int col) {
		assert row >= 0;
		assert col >= 0;
		assert row < GameOfLife.GRID_ROWS;
		assert col < GameOfLife.GRID_HEIGHT;
		this.x = row;
		this.y = col;
	}

	static Position of(int row, int col) {
		if (row < 0) row += GameOfLife.GRID_ROWS;
		if (col < 0) col += GameOfLife.GRID_COLS;
		return new Position(row % GameOfLife.GRID_ROWS, col % GameOfLife.GRID_COLS);
	}

	List<Position> neighbours() {
		List<Position> result = new ArrayList<>();
		result.add(of(x-1, y-1));
		result.add(of(x, y-1));
		result.add(of(x+1, y-1));

		result.add(of(x-1, y));
		result.add(of(x+1, y));

		result.add(of(x-1, y+1));
		result.add(of(x, y+1));
		result.add(of(x+1, y+1));
		return result;
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}