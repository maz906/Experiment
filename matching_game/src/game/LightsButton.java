package game;

/**
 * Class that encapsulates the information of a Lights Out button, LightsButton, an extension of JButton.
 */

import java.awt.Color;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class LightsButton extends JButton {
	
	public OnOrOff state;
	private int row;
	private int column;
	protected String label;
	
	/**
	 * Constructor method which takes the state, row and column of the LightsButton as parameters.
	 * @param _state -- OnOrOff.ON or OnOrOff.OFF
	 * @param _row -- row of the LightsButton
	 * @param _column -- column of the LightsButton
	 */
	public LightsButton(OnOrOff _state, int _row, int _column, String _label) {
		super();
		label = _label;
//		setText(label);
		
		if (_state == OnOrOff.ON) {
			this.setBackground(Color.YELLOW);
		} else {
			this.setBackground(Color.WHITE);
		}
		
		state = _state;
		row = _row;
		column = _column;
	}
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String s) {
		label = s;
	}
	
	/**
	 * Obtains the row.
	 * @return -- the row
	 */
	public int getRow() {
		return this.row;
	}
	
	/**
	 * Obtains the column.
	 * @return
	 */
	public int getColumn() {
		return this.column;
	}
	
	/**
	 * Obtains the state of the LightsButton.
	 * @return
	 */
	public OnOrOff getState() {
		return state;
	}
	
	/**
	 * Alters the LightsButton on which toggle() is called.
	 */
	public void toggle() {
		if (this.state == OnOrOff.ON) {
			this.state = OnOrOff.OFF;
//			this.setBackground(Color.WHITE);
		} else {
			this.state = OnOrOff.ON; 
//			this.setBackground(Color.YELLOW);
		}
	}
	
	
	/**
	 * Checks the four potential neighbors of a LightsButton, and adds them to an array.
	 * @param -- a matrix of buttons
	 * @return -- an array of buttons
	 */
	public LightsButton[] determineNeighbors(LightsButton[][] buttons) {
		LightsButton[] neighbors = new LightsButton[4];
		try {
			neighbors[0] = buttons[this.getRow()-1][this.getColumn()];
		} catch (ArrayIndexOutOfBoundsException e) {
		} try {
			neighbors[1] = buttons[this.getRow()+1][this.getColumn()];
		} catch (ArrayIndexOutOfBoundsException e) {
		} try {
			neighbors[2] = buttons[this.getRow()][this.getColumn()+1];
		} catch (ArrayIndexOutOfBoundsException e) {
		} try {
			neighbors[3] = buttons[this.getRow()][this.getColumn()-1];
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		return neighbors;
	}
	
	/**
	 * Toggles the neighbors of a button and the button itself.
	 * @param -- an array of buttons
	 */
	public void toggleNeighbors(LightsButton[] buttons) {	
		this.toggle();		
		for (LightsButton b : buttons)
			if (b != null)
				b.toggle();
	}
	

	
	

}
