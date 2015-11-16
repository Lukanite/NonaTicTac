import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class TicTacToe extends JPanel{
	private static final long serialVersionUID = 9106403499068281271L;
	static Board boards[] = new Board[10];
	static boolean xturn = true;
	static Color background = new Color(255,255,200);
	static Color transparent = new Color(255,255,255,255);
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

	public TicTacToe(){
		setLayout(null);
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
				for (int y = 0; y<3; y++) {
					for (int x = 0; x<3; x++) {
						boards[y*3+x] = new Board(y*3+x, 500/3, x*500/3, y*500/3);
					}
				}
				boards[9] = new Board(9, 500, 0, 0);
				win = 0;
				repaint();
			}
			
		};
		ActionListener exitlistener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		};
		MouseListener listener = new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				TicTacToe.processClick(arg0.getX(),arg0.getY());
				repaint();
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
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		};
		addMouseListener(listener);
		resetButton.addActionListener(resetListener);
		exitButton.addActionListener(exitlistener);
		setFocusable(true);
	}
	
	
	protected static void processClick(int x, int y) {
		int boardxpos = x/(500/3);
		int boardypos = y/(500/3);
		//check if no boards are active
		boolean boardactive = false;
		for (Board b:boards) if (b.isActive()) boardactive = true;
		if (!boardactive && boards[3*boardypos+boardxpos].getWinner()==0) boards[3*boardypos+boardxpos].setActive(true);
		boards[3*boardypos+boardxpos].processClick(x-boards[3*boardypos+boardxpos].getXpos(), y-boards[3*boardypos+boardxpos].getYpos());
	}

	
	public void paint(Graphics g){
		super.paint(g);
		if (win == 0) {
			if (boards[9].isFull()) g.drawString("It's a draw!", 100, 100);
			else for (Board b : boards) b.draw(g);
		}
		else {
			g.drawString("Player " + win + " wins!", 100, 100);
		}
	}
	
	public static void main(String[] args){
		
		JFrame frame = new JFrame("Tic Tac Nine");
		TicTacToe tictac  = new TicTacToe();
		tictac.setBackground(background);
		frame.add(tictac);
		frame.setSize(520, 580);
		frame.setLocationRelativeTo(null);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		for (int y = 0; y<3; y++) {
			for (int x = 0; x<3; x++) {
				boards[y*3+x] = new Board(y*3+x, 500/3, x*500/3, y*500/3);
			}
		}
		boards[9] = new Board(9, 500, 0, 0);
		tictac.repaint();
	}
	
	
}

