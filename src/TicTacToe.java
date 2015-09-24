import java.awt.*;
import javax.swing.*;

public class TicTacToe extends JFrame{
	private static final long serialVersionUID = 9106403499068281271L;
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
		init();
	}
	
	private void init(){
		setSize(500, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		THIS IS A GITHUB TEST BY ADITI
	}
	
	public void paint(Graphics g){
		//g.fillRect(0, 167, 500, 10);
		//g.fillRect(0, 333, 500, 10);
		//g.fillRect(333, 0, 10, 500);
		//g.fillRect(167, 0, 10, 500);
		drawTicTac(g, 0, 0, 500);
		for (int x = 0; x<3; x++) {
			for (int y = 0; y<3; y++) {
				drawTicTac(g,x*500/3, y*500/3, 500/3);
			}
		}
		drawTicTac(g,333,333,500/3);
		drawX(g,0,0,500/9);
		drawO(g,0,500/9,500/9);
	}
	public void drawTicTac(Graphics g, int startx, int starty, int size) {
		//System.out.println(startx + "," + starty + "," + size);
		g.fillRect(startx, starty+size/3, size, 10*size/500);
		g.fillRect(startx, starty+2*size/3, size, 10*size/500);
		g.fillRect(startx+size/3, starty, 10*size/500, size);
		g.fillRect(startx+2*size/3, starty, 10*size/500, size);
	}
	public void drawX(Graphics g, int startx, int starty, int size) {
		int[] xpoints = {23,93,133,178,233,163,243,173,121,70,13,93};
		int[] ypoints = {0,0,77,0,0,118,256,256,163,256,256,120};
		for (int i = 0; i<12; i++) {
			xpoints[i] = xpoints[i]*size/256;
			ypoints[i] = ypoints[i]*size/256;
		}
		g.fillPolygon(xpoints, ypoints, 12); 
	}
	public void drawO(Graphics g, int startx, int starty, int size) {
		g.fillOval(startx, starty, size, size);
		g.setColor(Color.LIGHT_GRAY); //Currently hardcoded, should change
		g.fillOval(startx+size/5, starty+size/5, (int)Math.round(.6*size), (int)Math.round(.6*size));
	}
	public void drawBoard(Graphics g){
		
	}
	
	public static void main(String[] args){
		
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				TicTacToe x  = new TicTacToe();
				x.setVisible(true);
			}
		});
	}
	
	
}
