package gameOfLife;

//This class remembers the game state and implements the dynamics of the game.  It uses the Observer pattern to draw the game state in its observers.

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

public class GameOfLife {

	private boolean[][] grid;
	private int squaresize;

	protected void toggleGridValue(int x, int y) {
		int i = x/squaresize;
		int j = y/squaresize;
		grid[i][j] = !grid[i][j];
		repaint();
	}

	public void tick() {
		this.advance();
		this.repaint();
	}

	private void advance() {
		boolean[][] newgrid = new boolean[grid.length][grid[0].length];

		for (int i=0; i<grid.length; i++)
			for (int j=0; j<grid[0].length; j++)
				newgrid[i][j] = false;

		for (int i=0; i<grid.length; i++)
			for (int j=0; j<grid[0].length; j++)
				if ((grid[i][j]) && (nbrOfNeighbors(i,j) < 2))
					newgrid[i][j] = false;
				else if ((grid[i][j]) && (2 <= nbrOfNeighbors(i,j)) && (nbrOfNeighbors(i,j) <= 3))
					newgrid[i][j] = true;
				else if ((grid[i][j]) && (3 < nbrOfNeighbors(i,j)))
					newgrid[i][j] = false;
				else if ((!grid[i][j]) && (nbrOfNeighbors(i,j) == 3))
					newgrid[i][j] = true;

		grid = newgrid;
	}

	private int nbrOfNeighbors(int x, int y) {
		int result = 0;
		if ((0 <= x-1) && (0 <= y-1) && (grid[x-1][y-1])) result++;
		if ((0 <= x-1) && (grid[x-1][y])) result++;
		if ((0 <= x-1) && (y+1 < grid[0].length) && (grid[x-1][y+1])) result++;
		if ((0 <= y-1) && (grid[x][y-1])) result++;
		if ((y+1 < grid[0].length) && (grid[x][y+1])) result++;
		if ((x+1 < grid.length) && (0 <= y-1) && (grid[x+1][y-1])) result++;
		if ((x+1 < grid.length) && (grid[x+1][y])) result++;
		if ((x+1 < grid.length) && (y+1 < grid[0].length) && (grid[x+1][y+1])) result++;
		return result;
	}

	private void initSmallExploder() {
		grid[30][31] = true;
		grid[30][32] = true;
		grid[31][30] = true;
		grid[31][31] = true;
		grid[31][33] = true;
		grid[32][31] = true;
		grid[32][32] = true;
	}

	private void initGlider() {
		grid[21][20] = true;
		grid[22][21] = true;
		grid[22][22] = true;
		grid[21][22] = true;
		grid[20][22] = true;
	}

	private void initTumbler() {
		grid[30][23] = true;
		grid[30][24] = true;
		grid[30][25] = true;
		grid[31][20] = true;
		grid[31][21] = true;
		grid[31][25] = true;
		grid[32][20] = true;
		grid[32][21] = true;
		grid[32][22] = true;
		grid[32][23] = true;
		grid[32][24] = true;
		grid[34][20] = true;
		grid[34][21] = true;
		grid[34][22] = true;
		grid[34][23] = true;
		grid[34][24] = true;
		grid[35][20] = true;
		grid[35][21] = true;
		grid[35][25] = true;
		grid[36][23] = true;
		grid[36][24] = true;
		grid[36][25] = true;
	}
}
