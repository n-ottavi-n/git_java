package ex2_citerne;

public class CiterneOverflowException extends Exception {
	
	public CiterneOverflowException(double d, int cap) {	
		super("max capacity exceeded, "+(d-cap)+" litres dumped in overflow tank");
	}
}
