import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Board {
	// 0 for none, 1 for X, 2 for O
	private int[] spaces = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	private int id;
	private int size;
	private int xpos;
	private int ypos;
	private int winner = 0;
	private boolean active = false;
	Color transparent = new Color(255, 255, 255, 255);

	public int getWinner() {
		return winner;
	}

	public Board() {
		// Empty constructor
	}

	public Board(int id, int size, int xpos, int ypos) {
		this.id = id;
		this.size = size;
		this.xpos = xpos;
		this.ypos = ypos;
	}

	public void draw(Graphics g) {
		drawTicTac(g, this.xpos, this.ypos, this.size);
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				// take into account sizes of borders
				if (spaces[y * 3 + x] == 1)
					drawX(g, xpos + (x * size / 3) + 10 * size / 500, ypos + (y * size / 3) + 10 * size / 500,
							(int) (size / 3.15));
				else if (spaces[y * 3 + x] == 2)
					drawO(g, xpos + (x * size / 3) + 10 * size / 500, ypos + (y * size / 3) + 10 * size / 500,
							(int) (size / 3.15));
			}
		}
	}

	public void drawTicTac(Graphics g, int startx, int starty, int size) {
		g.setColor(Color.BLACK);
		if (winner > 0 || isFull())
			g.setColor(new Color(128, 128, 128));
		g.fillRect(startx, starty + size / 3, size, 10 * size / 500);
		g.fillRect(startx, starty + 2 * size / 3, size, 10 * size / 500);
		g.fillRect(startx + size / 3, starty, 10 * size / 500, size);
		g.fillRect(startx + 2 * size / 3, starty, 10 * size / 500, size);
	}

	public void drawX(Graphics g, int startx, int starty, int size) {
		g.setColor(Color.RED);
		if (winner > 0 || isFull())
			g.setColor(new Color(255, 128, 128));
		int[] xpoints = { 23, 93, 133, 178, 233, 163, 243, 173, 121, 70, 13, 93 };
		int[] ypoints = { 0, 0, 77, 0, 0, 118, 256, 256, 163, 256, 256, 120 };
		for (int i = 0; i < 12; i++) {
			xpoints[i] = (xpoints[i] * size / 256) + startx;
			ypoints[i] = (ypoints[i] * size / 256) + starty;
		}
		g.fillPolygon(xpoints, ypoints, 12);
	}

	public void drawO(Graphics g, int startx, int starty, int size) {
		g.setColor(Color.BLUE);
		if (winner > 0 || isFull())
			g.setColor(new Color(128, 128, 255));
		// g.fillOval(startx, starty, size, size);
		// g.setColor(transparent);
		// g.fillOval(startx+size/5, starty+size/5, (int)Math.round(.6*size),
		// (int)Math.round(.6*size));
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke((int) (size / 5.5)));
		g2.drawOval(startx + size / 10, starty + size / 10, (int) (.8 * size), (int) (.8 * size));
	}

	public int getXpos() {
		return xpos;
	}

	public int getYpos() {
		return ypos;
	}

	public int[] getSpaces() {
		return spaces;
	}

	public boolean processClick(int x, int y) {
		// TODO Auto-generated method stub

		boolean result = false;
		int xpart = x / (size / 3);
		int ypart = y / (size / 3);
		int space = 3 * ypart + xpart;
		System.out.println(space);
		if (spaces[space] == 0 && isActive()) {
			if (TicTacToe.isXturn()) {
				spaces[space] = 1;
				TicTacToe.setXturn(false);
			} else {
				spaces[space] = 2;
				TicTacToe.setXturn(true);
			}
			result = true;
		}
		checkWin();
		return result;
	}

	// AI process click
	public boolean processClick(int space) {
		// TODO Auto-generated method stub
		
		System.out.println(space);

		boolean result = false;
		if (spaces[space] == 0 && isActive()) {
			if (TicTacToe.isXturn()) {
				spaces[space] = 1;
				//make the computer play O
				TicTacToe.setXturn(false);
			} else {
				spaces[space] = 2;
				TicTacToe.setXturn(true);
			} 
			result = true;
		}
		checkWin();
		return result;
	}

	public void checkWin() {
		int win = 0;
		for (int p = 1; p < 3; p++) {
			// check horizontal
			for (int y = 0; y < 3; y++) {
				if (spaces[y * 3] == p && spaces[y * 3 + 1] == p && spaces[y * 3 + 2] == p)
					win = p;
			}
			// check vertical
			for (int x = 0; x < 3; x++) {
				if (spaces[x] == p && spaces[x + 3] == p && spaces[x + 6] == p)
					win = p;
			}
			// check diagonal
			if (spaces[2] == p && spaces[4] == p && spaces[6] == p)
				win = p;
			if (spaces[0] == p && spaces[4] == p && spaces[8] == p)
				win = p;
		}
		this.winner = win;
		if (winner > 0) {
			active = false;
			if (this.id != 9) {
				TicTacToe.boards[9].spaces[this.id] = winner;
				TicTacToe.boards[9].checkWin();
			} else {
				System.out.println("Player " + winner + " wins!");
				TicTacToe.setWin(winner);
			}
		} else {
			// check if the board is full

			if (isFull())
				active = false;
		}
	}

	public boolean isFull() {
		boolean full = true;
		for (int space : spaces)
			if (space == 0)
				full = false;
		return full;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
