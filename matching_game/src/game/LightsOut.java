package game;

/**
 * Program that plays the Lights Out game. Includes various options such as solve for me,
 * changing dimensions, manual setup and randomization of the grid.
 * 
 * @author Michael Zhao
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class LightsOut extends JFrame implements ActionListener{

	private int count = 0;
	private int m = 1; //rows of grid -- different from n, in case of future modifications
	private int n = 1; //columns of grid
	private int x = 350; //width of panel on screen for 5 by 5 grid
	private int y = 650; //height of panel on screen for 5 by 5 grid
	
	//GUI Components made into instance variables for reference in other methods. 
	private JPanel panel; 
	private JButton manualSet;
	private JButton randomize;
	private JLabel moves;
	private JLabel ons;
	private JButton solveForMe;
	private JTextField rowsTextField;
	private JTextField columnsTextField;
	
	private LightsButton[][] buttons; //array to keep track of LightsButton buttons
	
	private Phrases gamePhrases;

	public static void main(String[] args) {
		LightsOut l = new LightsOut(5, 6);
		l.setVisible(true);
	}
	
	/**
	 * Constructor method, taking in arguments for the dimension of the grid that
	 * LightsOut will be played on. 
	 */
	public LightsOut(int _n, int _m) {
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Sets the dimension
		n = _n;
		m = _m;
		
		//Creates the phrases.
		gamePhrases = new Phrases();
		
		//Creates an array based on the dimension
		buttons = new LightsButton[n][m];
		
		//Panel will contain the buttons
		panel = new JPanel();
		panel.setLayout(new GridLayout(n, m));
		
		//Adds buttons to the grid and randomizes them. See randomizeGrid documentation for details.
		randomizeGrid(panel);
				
//		randomize = new JButton("Randomize");
//		randomize.addActionListener(this);
//		
//		manualSet = new JButton("Enter Manual Setup");
//		manualSet.addActionListener(this);
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new BorderLayout());
		panel2.add(panel, BorderLayout.CENTER);
		
		panel = panel2;

		setTitle("LightsOut");
		setContentPane(panel);
		setPreferredSize(new Dimension(650, 350));
		pack();

		//Creates a left side of the panel that will contain various implements.
		JPanel leftSide = new JPanel();
		leftSide.setLayout(new BorderLayout());
		
		//Creates the upper left side.		
		JPanel upperLeft = new JPanel();
		upperLeft.setLayout(new FlowLayout());
		
		leftSide.add(upperLeft, BorderLayout.NORTH);
		
//		upperLeft.add(manualSet);
//		upperLeft.add(randomize);
		
		//Creates the center left side.
		JPanel centerLeft = new JPanel();
		centerLeft.setLayout(new GridLayout(3,1));
		
//		moves = new JLabel("Number of moves: " + count);
//		moves.setHorizontalAlignment(JLabel.CENTER);
//		centerLeft.add(moves);
//		
//		ons = new JLabel("Number of lights still on: " + totalOn(buttons));
//		ons.setHorizontalAlignment(JLabel.CENTER);
//		ons.setVerticalAlignment(JLabel.TOP);
//		centerLeft.add(ons);
//		
//		solveForMe = new JButton("<html><center> Solve for me, I give up. <br> <center> (This may take a while, <br> depending on dimensions.) </center> </center> </html>");
//		solveForMe.addActionListener(this);
//		centerLeft.add(solveForMe);
//	    solveForMe.setPreferredSize(new Dimension(50, 100));

//		leftSide.add(centerLeft, BorderLayout.CENTER);

		//Creates and adds the bottom left side.
		JPanel bottomLeft = new JPanel();
		bottomLeft.setLayout(new GridLayout(2, 1));
		
		//Creates and adds the upper bottom left to the bottom left.
//		JPanel bottomLeftUpper = new JPanel();
//		JLabel dimensions = new JLabel("<html> Play the current game, or enter the <br> dimensions of the game you want: </html>");

//		bottomLeftUpper.setLayout(new FlowLayout());
//		bottomLeftUpper.add(dimensions);
				
		//Creates and adds the lower bottom left to the bottom left.
		JPanel bottomLeftLower = new JPanel();
		
		bottomLeftLower.setLayout(new FlowLayout());
		
		rowsTextField = new JTextField(5);
		rowsTextField.addActionListener(this);
		columnsTextField = new JTextField(5);
		columnsTextField.addActionListener(this);
		
		bottomLeftLower.add(rowsTextField);
		bottomLeftLower.add(columnsTextField);

//		bottomLeft.add(bottomLeftUpper);
//		bottomLeft.add(bottomLeftLower);
		leftSide.add(bottomLeft, BorderLayout.SOUTH);

		panel.add(leftSide, BorderLayout.WEST);
	}
	
	/**
	 * A method which creates a new instance of the LightsOut game with the same dimensions.
	 * This is invoked when we randomize the grid.
	 */
	public void startNewGame() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		int tempM = m;
		int tempN = n;

		buttons = new LightsButton[tempM][tempN];
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(tempM, tempN));
		
		randomizeGrid(panel);
		
		randomize = new JButton("Randomize");
		randomize.addActionListener(this);
		
		manualSet = new JButton("Enter Manual Setup");
		manualSet.addActionListener(this);
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new BorderLayout());
		panel2.add(panel, BorderLayout.CENTER);

		JPanel leftSide = new JPanel();
		leftSide.setLayout(new BorderLayout());
		
		JPanel upperLeft = new JPanel();
		upperLeft.setLayout(new FlowLayout());
		
		upperLeft.add(manualSet);
		upperLeft.add(randomize);
		
		leftSide.add(upperLeft, BorderLayout.NORTH);
		
	
		JPanel centerLeft = new JPanel();
		centerLeft.setLayout(new GridLayout(3,1));
		
		moves = new JLabel("Number of moves: " + count);
		moves.setHorizontalAlignment(JLabel.CENTER);
		centerLeft.add(moves);
		
		ons = new JLabel("Number of lights still on: " + totalOn(buttons));
		ons.setHorizontalAlignment(JLabel.CENTER);
		ons.setVerticalAlignment(JLabel.TOP);
		centerLeft.add(ons);
		
		solveForMe = new JButton("<html><center> Solve for me, I give up. <br> <center> (This may take a while, <br> depending on dimensions.) </center> </center> </html>");
		solveForMe.addActionListener(this);
		solveForMe.setHorizontalAlignment(JLabel.CENTER);
		centerLeft.add(solveForMe);

		leftSide.add(centerLeft, BorderLayout.CENTER);
		
		JPanel bottomLeft = new JPanel();
		bottomLeft.setLayout(new GridLayout(2, 1));
		
		JPanel bottomLeftUpper = new JPanel();
		JLabel dimensions = new JLabel("<html> Play the current game, or enter the <br> dimensions of the game you want: </html>");

		bottomLeftUpper.setLayout(new FlowLayout());
		bottomLeftUpper.add(dimensions);
		
		JPanel bottomLeftLower = new JPanel();
		
		bottomLeftLower.setLayout(new FlowLayout());
		
		rowsTextField = new JTextField(5);
		rowsTextField.addActionListener(this);
		columnsTextField = new JTextField(5);
		columnsTextField.addActionListener(this);
		
		bottomLeftLower.add(rowsTextField);
		bottomLeftLower.add(columnsTextField);

		bottomLeft.add(bottomLeftUpper);
		bottomLeft.add(bottomLeftLower);
		leftSide.add(bottomLeft, BorderLayout.SOUTH);
		
		panel2.add(leftSide, BorderLayout.WEST);
		
		panel = panel2;
		
		setTitle("LightsOut");
		setContentPane(panel);
		setPreferredSize(new Dimension(y, x));
		pack();
	}

	/**
	 * A method which creates a new instance of the LightsOut game with different dimensions.
	 * This is invoked when numbers are put into the text field.
	 * @param _m -- the new number of rows
	 * @param _n -- the new number of columns
	 */
	public void startNewGame(int _n, int _m) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		m = _m;
		n = _n;

		buttons = new LightsButton[m][n];
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(m, n));
		
		randomizeGrid(panel);
		
		randomize = new JButton("Randomize");
		randomize.addActionListener(this);
		
		manualSet = new JButton("Enter Manual Setup");
		manualSet.addActionListener(this);
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new BorderLayout());
		panel2.add(panel, BorderLayout.CENTER);

		JPanel leftSide = new JPanel();
		leftSide.setLayout(new BorderLayout());
		
		JPanel upperLeft = new JPanel();
		upperLeft.setLayout(new FlowLayout());
		
		upperLeft.add(manualSet);
		upperLeft.add(randomize);
		
		leftSide.add(upperLeft, BorderLayout.NORTH);
		
	
		JPanel centerLeft = new JPanel();
		centerLeft.setLayout(new GridLayout(3,1));
		
		moves = new JLabel("Number of moves: " + count);
		moves.setHorizontalAlignment(JLabel.CENTER);
		centerLeft.add(moves);
		
		ons = new JLabel("Number of lights still on: " + totalOn(buttons));
		ons.setHorizontalAlignment(JLabel.CENTER);
		ons.setVerticalAlignment(JLabel.TOP);
		centerLeft.add(ons);
		
		solveForMe = new JButton("<html><center> Solve for me, I give up. <br> <center> (This may take a while, <br> depending on dimensions.) </center> </center> </html>");
		solveForMe.addActionListener(this);
		solveForMe.setHorizontalAlignment(JLabel.CENTER);
		centerLeft.add(solveForMe);

		leftSide.add(centerLeft, BorderLayout.CENTER);
		
		JPanel bottomLeft = new JPanel();
		bottomLeft.setLayout(new GridLayout(2, 1));
		
		JPanel bottomLeftUpper = new JPanel();
		JLabel dimensions = new JLabel("<html> Play the current game, or enter the <br> dimensions of the game you want: </html>");

		bottomLeftUpper.setLayout(new FlowLayout());
		bottomLeftUpper.add(dimensions);
		
		JPanel bottomLeftLower = new JPanel();
		
		bottomLeftLower.setLayout(new FlowLayout());
		
		rowsTextField = new JTextField(5);
		rowsTextField.addActionListener(this);
		columnsTextField = new JTextField(5);
		columnsTextField.addActionListener(this);
		
		bottomLeftLower.add(rowsTextField);
		bottomLeftLower.add(columnsTextField);

		bottomLeft.add(bottomLeftUpper);
		bottomLeft.add(bottomLeftLower);
		leftSide.add(bottomLeft, BorderLayout.SOUTH);
		
		panel2.add(leftSide, BorderLayout.WEST);
		
		panel = panel2;
		
		setTitle("LightsOut");
		setContentPane(panel);
		setPreferredSize(new Dimension(y, x));
		pack();
	}
	
	/**
	 * Randomizes the grid of the given panel.
	 * 
	 * @param panel -- the panel that contains the grid.
	 */
	public void randomizeGrid(JPanel panel) {
		
		//Creates random variables to be used to randomize the grid later.		
		Random rand = new Random();
		Random rand2 = new Random();
		
		List<String> phrases = new ArrayList<String>();
		
		for (String s : gamePhrases.associatePhrases.keySet())
			phrases.add(s);
		
		for (String s : gamePhrases.associatePhrases.values())
			phrases.add(s);
		
		//Adds buttons to the grid, adds action listeners, sets them to opaque.
		Iterator<String> iter = phrases.iterator();
		while (iter.hasNext()) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					buttons[i][j] = new LightsButton(OnOrOff.OFF,i,j, iter.next());
					buttons[i][j].setOpaque(true);
					buttons[i][j].addActionListener(this);
					panel.add(buttons[i][j]);
				}
			}
		}
		//Toggles 30 random buttons.
		for (int i = 0; i < 30; i++) {
			int k = rand.nextInt(n);
			int h = rand2.nextInt(m);
			buttons[k][h].toggleNeighbors(buttons[k][h].determineNeighbors(buttons));
		}
	}
	
	/**
	 * Private helper method that counts the total number of buttons on.
	 * @param buttons
	 * @return
	 */
	private int totalOn(LightsButton[][] buttons) {
		int count = 0;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				if (buttons[i][j].getState() == OnOrOff.ON)
					count++;
		return count;
	}
	
	/**
	 * Method that handles an event--relies on several helper methods below.
	 * @param e -- the event
	 */
	public void actionPerformed(ActionEvent e) {
		
		//Is kind and lets the user win, in the off chance that the grid starts with all buttons turned off.
		if (areAllOff() == true) {
			for (int i = 0; i < m; i++)
				for (int j = 0; j < n; j++)
					buttons[i][j].setEnabled(false);
			ons.setText("You win! Click on Randomize to play again.");
		}
			
		//See documentation for each of the methods called.
		try {
			solveForMeClicked(e);
		} catch (ClassCastException f) {
		}
		
		try {
			newDimensions(e);
		} catch (ClassCastException f) {
		}
		
		try {
			jButtonClicked(e);
		} catch (RuntimeException f) {
		}
		
	}
	
	/**
	 * Private helper method that deals with the case where e is the clicking of a JButton (or LightsButton).
	 * @param e
	 */
	@SuppressWarnings("deprecation")
	private void jButtonClicked(ActionEvent e) {
		LightsButton lightsButt;
		JButton buttonTest;
		
		buttonTest = (JButton)e.getSource();
		
		buttonTest.setText(buttonTest.getLabel());
		
		if (buttonTest.getText().equals("Exit Manual Setup") && totalOn(buttons) == 0) {
			//Handles the user's attempt to cheat. Not critical for game play.
			for (int i = 0; i < buttons.length; i++)
				for (int j = 0; j < buttons[0].length; j++)
					buttons[i][j].setEnabled(false);
			ons.setText("<html> You won! (By cheating, of course.) <br> <center> Randomize to play again. </center> </html>");
			manualSet.setEnabled(false);
			solveForMe.setEnabled(false);
			manualSet.setText("Enter Manual Setup");
		} else {
			if (buttonTest.getText().equals("Off") || buttonTest.getText().equals("On")) {
				//Deals with the manual setup option. 
				lightsButt = (LightsButton)e.getSource();
				if (manualSet.getText().equals("Enter Manual Setup")) {
					lightsButt.toggleNeighbors(lightsButt.determineNeighbors(buttons)); //Toggles the neighbors (and the button itself).
					count++; //Increases the count (which counts number of moves made).
					moves.setText("Number of moves: " + count);
				} else {
					lightsButt.toggle(); //Toggles just the button.
				}
			}
	
			if (buttonTest.getText().equals("Randomize")) {
				startNewGame(); //Starts a new game with the same dimensions as before.
				count = 0;
				moves.setText("Number of moves: " + count);
			} else if (buttonTest.getText().equals("Enter Manual Setup")) {
				manualSet.setText("Exit Manual Setup");
				for (int i = 0; i < buttons.length; i++)
					for (int j = 0; j < buttons[0].length; j++)
						buttons[i][j].setEnabled(true); //Allows manipulation of buttons after entering manual setup.
			} else if (buttonTest.getText().equals("Exit Manual Setup")){
				manualSet.setText("Enter Manual Setup"); //Returns text to Enter Manual Setup after exiting. 
			}
		}
	}
	
	/**
	 * Private helper method that deals with the case where e is the clicking of the button
	 * that solves the puzzle for the user.
	 * @param e
	 */
	private void solveForMeClicked(ActionEvent e) {
		JButton test = (JButton)e.getSource();
		if (test.getText().equals("<html><center> Solve for me, I give up. <br> <center> (This may take a while, <br> depending on dimensions.) </center> </center> </html>")) {
			this.randomClick();
			if (areAllOff() == true) {
				ons.setText("I won! (Not you, me. The developer.)");
				solveForMe.setEnabled(false);
				manualSet.setEnabled(false);
				for (int i = 0; i < m; i++)
					for (int j = 0; j < n; j++)
						buttons[i][j].setEnabled(false);
			}
		}
	}
	
	/**
	 * Private helper method that deals with the case where e is the entering of text into
	 * the two textfields.
	 * @param e
	 */
	private void newDimensions(ActionEvent e) {
	    String rowsText = rowsTextField.getText();
	    String columnsText = columnsTextField.getText();
	    try {
	    	m = Integer.parseInt(rowsText);
	    	n = Integer.parseInt(columnsText);
	    	if (m > 13 || n > 13) {
	    		//Doesn't allow dimensions larger than 13, for sake of windows space. Easily modified.
	    		JFrame frame = new JFrame();
	    		JOptionPane.showMessageDialog(frame, "<html> Keep the integers at a reasonable size... <br> <center> Plus it won't fit on the screen.");
	    		JOptionPane.showMessageDialog(frame, "<html> <center> Did you know it's mathematically proven <br> that every (square) board size has at least one solution? </center> </html>");
	    	} else if (m != n) {
	    		//Doesn't allow non-square shapes... for now.
	    		JFrame frame = new JFrame();
	    		setDefaultCloseOperation(EXIT_ON_CLOSE);
	    		JOptionPane.showMessageDialog(frame, "<html> Only square boards, yo. Rectangles <br> <center> would make adjustments hard. </center> </html>");
	    		
	    		JFrame frame2 = new JFrame();
    			JOptionPane.showMessageDialog(frame2, "<html> m by n grids for m != n will be implemented in version 10.0.<br>" +
				"<center> Right now we are in version 0.1. Apologies. </center></html>");
	    	} else {
	    		//The following code helps to adjust the screen size as the dimensions increase.
	    		//Of course, the user can always drag the screen out.
	    		if (m >= 5 && n >= 5) {
		    		x = x + 21*(m - 5); //The (m-5) and (n-5) are to keep the window the same, since the grid starts at m = n = 5.
		    		y = y + 21*(n-5);
			    	startNewGame(m,n);
	    		} else if (m < 5 && n < 5) {
			    	startNewGame(m,n);
	    		}
	    	}
	    		
	    		
	    } catch (NumberFormatException g) {
	    	if (!(rowsText.equals("") && columnsText.equals(""))) {
	    		//This is to prevent an error from hitting enter when the textfields are empty or when
	    		//things I am too tired to explain at 2AM.
	    		JFrame frame = new JFrame();
	    		setDefaultCloseOperation(EXIT_ON_CLOSE);
	    		JOptionPane.showMessageDialog(frame, "You must enter an integer in both fields!");
	    	}
	    } 
	}

	/**
	 * Private helper method that returns whether all the buttons are off.
	 * @return -- false if the lights are all off, true otherwise.
	 */
	private boolean areAllOff() {
		for (int i = 0; i < buttons.length; i++)
			for (int j = 0; j < buttons[0].length; j++)
				if (buttons[i][j].getState() == OnOrOff.ON)
					return false;
		return true;
	}
	
	/**
	 * Method that contains the "algorithm" that tries to solve the puzzle.
	 */
	public void randomClick() {
		
		//Creates random variables for randomly clicking, later.
		Random rand = new Random();
		Random rand2 = new Random();

		int i;
		int j;
		i = rand.nextInt(m);
		j = rand2.nextInt(n);
		
		boolean areAllOff;		
		areAllOff = this.areAllOff();
					
		long startTime = System.nanoTime(); //Used to begin timing the random "algorithm". 
		
		while (areAllOff == false) {
			if (buttons[i][j].getState() == OnOrOff.ON) {
				//If the button is on, it will be toggled.
				buttons[i][j].toggleNeighbors(buttons[i][j].determineNeighbors(buttons));
				long endTime = System.nanoTime();
				if ((endTime - startTime)/1000000000.0 > 15) {
					ons.setText("<html> Sorry, we couldn't solve it in under 15 seconds. <br> <center> Randomize to play again. </center> </html>");
					for (int r = 0; r < m; r++)
						for(int s = 0; s < n; s++)
							buttons[r][s].setEnabled(false);
					break;
					//Prevents the random "algorithm" for going on longer than 15 seconds.
				}
			}
			
			//Primes i and j for the next iteration of the while loop.
			i = rand.nextInt(m);
			j = rand.nextInt(n);
			
			//Runs a check to see if all buttons are off, so that the loop will terminate if it should.
			if (this.areAllOff() == true) {
				for (int r = 0; r < m; r++)
					for (int s = 0; s < n; s++)
						buttons[r][s].setEnabled(false);
				break;
			}
		} 
	}
	
}
