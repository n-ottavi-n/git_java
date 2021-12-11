package ex2_citerne;

public class CapacityExceededException extends Exception {
	
	public CapacityExceededException(double d, int cap) {
		super("max capacity exceeded, "+(d-cap)+" overflow");
		
	}

}
