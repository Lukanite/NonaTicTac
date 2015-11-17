import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TicTacToe extends JPanel {
	private static final long serialVersionUID = 9106403499068281271L;
	static Board boards[] = new Board[10];
	static Color background = new Color(255, 255, 200);
	static Color transparent = new Color(255, 255, 255, 255);
	static boolean xturn = true;
	boolean playC = false;

	public static boolean isXturn() {
		return xturn;
	}

	public static void setXturn(boolean xturn) {
		TicTacToe.xturn = xturn;
	}

	static int win = 0;

	public static int getWin() {
		return win;
	}

	public static void setWin(int win) {
		TicTacToe.win = win;
	}

	public TicTacToe() {
		setLayout(null);
		JButton playComp = new JButton("Play the Computer");
		playComp.setBounds(200, 500, 189, 23);
		add(playComp);
		JButton resetButton = new JButton("Reset");
		resetButton.setBounds(0, 500, 89, 23);
		add(resetButton);
		JButton exitButton = new JButton("Exit");
		exitButton.setBounds(100, 500, 89, 23);
		add(exitButton);
		ActionListener resetListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				for (int y = 0; y < 3; y++) {
					for (int x = 0; x < 3; x++) {
						boards[y * 3 + x] = new Board(y * 3 + x, 500 / 3, x * 500 / 3, y * 500 / 3);
					}
				}
				boards[9] = new Board(9, 500, 0, 0);
				win = 0;
				repaint();
				xturn = true;
			}

		};
		ActionListener exitlistener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		};

		ActionListener playCompListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				playC = true;
				for (int y = 0; y < 3; y++) {
					for (int x = 0; x < 3; x++) {
						boards[y * 3 + x] = new Board(y * 3 + x, 500 / 3, x * 500 / 3, y * 500 / 3);
					}
				}
				boards[9] = new Board(9, 500, 0, 0);
				win = 0;
				repaint();
				xturn = true;
			}
		};

		MouseListener listener = new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				boolean outcome = TicTacToe.processClick(arg0.getX(), arg0.getY());
				repaint();
				/*
				 * if (outcome && playC) { AI(); repaint(); }
				 */
				if (playC) {
					Board activeBoard = null;
					for (int i = 0; i < 9; i++) {

						if (boards[i].isActive()) {
							activeBoard = boards[i];
						}
					}
					if (activeBoard == null && isXturn() == false) {
						ArrayList<Board> empBoards = new ArrayList<Board>();
						for (int i = 0; i < 9; i++) {
							if (boards[i].getWinner() == 0)
								empBoards.add(boards[i]);
						}
						Random generator = new Random();
						int index = generator.nextInt(empBoards.size());
						Board move = empBoards.get(index);
						move.setActive(true);
						AI();
					}
					else{
						AI();
						repaint();
					}
				}
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

		};
		addMouseListener(listener);
		resetButton.addActionListener(resetListener);
		exitButton.addActionListener(exitlistener);
		playComp.addActionListener(playCompListener);
		setFocusable(true);
	}

	protected static boolean processClick(int x, int y) {
		int boardxpos = x / (500 / 3);
		int boardypos = y / (500 / 3);
		// check if no boards are active
		boolean boardactive = false;
		for (Board b : boards)
			if (b.isActive())
				boardactive = true;
		if (!boardactive && boards[3 * boardypos + boardxpos].getWinner() == 0)
			boards[3 * boardypos + boardxpos].setActive(true);
		return boards[3 * boardypos + boardxpos].processClick(x - boards[3 * boardypos + boardxpos].getXpos(),
				y - boards[3 * boardypos + boardxpos].getYpos());
	}

	public void paint(Graphics g) {
		super.paint(g);
		boolean draw = true;

		if (win == 0) {
			for (int i = 0; i < boards.length - 1; i++) {
				if (!boards[i].isFull()) {
					draw = false;
				}
				boards[i].draw(g);
			}
			boards[9].draw(g);

			if (draw) {
				g.setFont(new Font("TimesRoman", Font.BOLD, 50));
				g.drawString("It's a draw!", 120, 250);
			}
		} else {
			g.setFont(new Font("TimesRoman", Font.BOLD, 50));
			g.drawString("Player " + win + " wins!", 120, 250);
		}
	}

	public void AI() {
		Board activeBoard = new Board();
		for (Board b : boards) {
			if (b.isActive()) {
				activeBoard = b;
			}
		}
		System.out.println(activeBoard);

		ArrayList<Integer> emptySpaces = new ArrayList<Integer>();

		for (int i = 0; i < activeBoard.getSpaces().length; i++) {
			if (activeBoard.getSpaces()[i] == 0) {
				emptySpaces.add(i);
				System.out.print(i);
			}
		}
		System.out.println();

		Random generator = new Random();
		int index = generator.nextInt(emptySpaces.size());
		int move = emptySpaces.get(index);
		System.out.print("AI: ");
		System.out.println(move);
		activeBoard.processClick(move);

	}

	public static void main(String[] args) {

		JFrame frame = new JFrame("Tic Tac Nine");
		TicTacToe tictac = new TicTacToe();
		tictac.setBackground(background);
		frame.add(tictac);
		frame.setSize(520, 580);
		frame.setLocationRelativeTo(null);

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				boards[y * 3 + x] = new Board(y * 3 + x, 500 / 3, x * 500 / 3, y * 500 / 3);
			}
		}
		boards[9] = new Board(9, 500, 0, 0);
		tictac.repaint();
		xturn = true;
	}

}
