package gameOfLife;

//This class represents the application window and controls the stepwise progression of the game.  It supplies the user with a pause button to stop and restart the game progression.

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class GameOfLifeApp extends JFrame {

	private GameOfLifePanel panel;
	private JPanel control;
	private boolean paused = false;

	public GameOfLifeApp(String title, int numbrows, int numbcols, int squaresize) {

		// init window
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		// set window location to center of screen
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int left = (screenSize.width - numbcols*squaresize)/ 2;
		int top  = (screenSize.height - numbrows*squaresize)/ 2;
		setLocation(left, top);

		panel = new GameOfLifePanel(numbrows, numbcols, squaresize);
		add(panel, BorderLayout.CENTER);

		control = new JPanel();
		final JToggleButton pauseButton = new javax.swing.JToggleButton("Pause");
		pauseButton.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {
				if (pauseButton.getText().equals("Pause")) {
					pauseButton.setText("Start");
				} else {
					pauseButton.setText("Pause");
				}
				togglePaused();
			}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {}
			});
		control.add(pauseButton);
		add(control, BorderLayout.NORTH);

		pack();
		this.setVisible(true);
	}

	protected void togglePaused() {
		paused = !paused;
	}

	public void runGoL(int delay) {
		while (true) {
			try {
				Thread.sleep(delay);
			}
			catch (Exception e) {}
			if (!paused) panel.tick();
		}
	}

	public void tick() {
		this.advance();
		this.repaint();
	}


	public static void main(String[] args) {

		int width = 50;
		int height = 40;
		int squaresize = 10;
		int delay = 100;

		//Parameters
		try {
			for (int tmp = 0 ; tmp < args.length ; tmp++) {
				if (args[tmp].compareTo("-width") == 0)
					width = Integer.parseInt(args[++tmp]);
				else if (args[tmp].compareTo("-height") == 0)
					height = Integer.parseInt(args[++tmp]);
				else if (args[tmp].compareTo("-squaresize") == 0)
					squaresize = Integer.parseInt(args[++tmp]);
				else if (args[tmp].compareTo("-delay") == 0)
					delay = Integer.parseInt(args[++tmp]);
				else
					throw new Exception();
			}
			if ((39 >= width) || (width >= 501)) throw new Exception();
			if ((39 >= height) || (height >= 501)) throw new Exception();
			if ((1 >= squaresize) || (Math.max(width,height)*squaresize >= 1001)) throw new Exception();
			if (49 >= delay) throw new Exception();
		}
		catch(Exception e) {
			//e.printStackTrace();
			System.err.println("");
			System.out.println("USAGE: java gameOfLife.GameOfLifeApp [-parameter value]");
			System.err.println("");
			System.err.println("    Parameters");
			System.err.println("   ------------");
			System.err.println("     width (39 < value < 501)");
			System.err.println("     height (39 < value < 501)");
			System.err.println("     squaresize (1 < value && max(height,width)*squaresize < 1001)");
			System.err.println("     delay (49 < value)");
			System.err.println("");
			System.exit(-1);
		}

		GameOfLifeApp gol = new GameOfLifeApp("Game of Life", height, width, squaresize);
		gol.runGoL(delay);
	}



}
