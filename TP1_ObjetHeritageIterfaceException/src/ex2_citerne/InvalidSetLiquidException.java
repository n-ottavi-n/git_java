package ex2_citerne;

public class InvalidSetLiquidException extends Exception {
	
	public InvalidSetLiquidException() {
		super("use nettoyage() first");
	}

}
