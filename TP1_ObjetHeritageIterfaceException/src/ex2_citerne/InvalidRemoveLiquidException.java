package ex2_citerne;

public class InvalidRemoveLiquidException extends Exception {

	public InvalidRemoveLiquidException(double volume, double qty) {
		super("not enough liquid in container "+(volume-qty)+" liters missing");
	}

}
