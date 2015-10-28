import java.awt.*;
import javax.swing.*;

public class TicTacToe extends JFrame{
	
	public TicTacToe(){
		init();
	}
	
	private void init(){
		setSize(500, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void paint(Graphics g){
		g.fillRect(0, 167, 500, 10);
		g.fillRect(0, 333, 500, 10);
		g.fillRect(333, 0, 10, 500);
		g.fillRect(167, 0, 10, 500);
		//g.setFi3
	
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
