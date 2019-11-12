package net.eanlr.gameoflife;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Coordinates {
	final int x;
	final int y;

	private Coordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}

	static Coordinates of(int x, int y) {
		return new Coordinates(x, y);
	}

	List<Coordinates> neighbours() {
		return new ArrayList<>(Arrays.asList(
				northEast(),
				north(),
				northWest(),
				east(),
				west(),
				southEast(),
				south(),
				southWest()
		));
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Coordinates) {
			Coordinates other = (Coordinates) obj;

			return this.x == other.x && this.y == other.y;
		}

		return false;
	}

	Coordinates northEast() {
		return of(x + 1, y - 1);
	}

	Coordinates north() {
		return of(x, y - 1);
	}

	Coordinates northWest() {
		return of(x - 1, y - 1);
	}

	Coordinates east() {
		return of(x - 1, y);
	}

	Coordinates west() {
		return of(x + 1, y);
	}

	Coordinates southEast() {
		return of(x + 1, y + 1);
	}

	Coordinates south() {
		return of(x, y + 1);
	}

	Coordinates southWest() {
		return of(x - 1, y + 1);
	}
}