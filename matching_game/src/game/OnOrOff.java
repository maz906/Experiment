package game;

/**
 * An enum class for the state of the LightsButton. Admittedly not necessary,
 * but too much effort to change at the time of typing this (2AM).
 * @author Michael
 */

public enum OnOrOff {
	
	ON, OFF;
	
	public String toString() {
		if (this == ON)
			return "On";
		return "Off";
	}

}
