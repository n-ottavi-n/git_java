package ex2_citerne;

public class InvalidLiquidException extends Exception {
	
	public InvalidLiquidException() {
		super("invalid liquid, use nettoyage() first");
	}

}
