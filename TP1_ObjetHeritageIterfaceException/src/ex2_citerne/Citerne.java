package ex2_citerne;

import java.time.LocalDate;

public class Citerne {
	
	private static int nb_citernes=0;
	public final int ID_CITERNE;
	public final int CAPACITE;
	public static final int CAP_MAX=20000;
	private Liquides liquide;
	private final LocalDate BIRTHDATE;
	private double qty=0;
	private boolean nettoyee=true;
	
	
	public Citerne(int capacite, Liquides liquide) throws MaxCapacityExceededException, BelowMinCapacityException  {
		nb_citernes++;
		ID_CITERNE=nb_citernes;
		if(capacite<=0) {
			throw new BelowMinCapacityException();
		}
		else if(capacite>CAP_MAX) {
			throw new MaxCapacityExceededException();
		}
		else {
			this.CAPACITE=capacite;		
		}
		this.liquide=liquide;
		BIRTHDATE=LocalDate.now();
		
	}
	
	public double getQty() {
		return qty;
	}
	
	public Liquides getLiquidType() {
		return liquide;
	}
	
	public LocalDate getBirthdate() {
		return BIRTHDATE;
	}
	
	public static int getNbCiternes() {
		return nb_citernes;
	}
	
	public void setLiquid(Liquides l) throws InvalidSetLiquidException {
		if(!nettoyee) {
			throw new InvalidSetLiquidException();
		}
		else {
			liquide=l;
		}
	}
	
	public void enleverLiquide(double volume) throws InvalidRemoveLiquidException {
		if(volume>qty) {
			double qty_saved=qty;
			qty=0;
			throw new InvalidRemoveLiquidException(volume,qty_saved);
		}
		else {
			qty-=volume;
		}
	}
	
	public static Citerne plusAncienne(Citerne c1,Citerne c2) {
		Citerne res=c1;
		if(c2.getBirthdate().isBefore(c1.getBirthdate())) {
			res=c2;
		}
		return res;
	}
	
	public void ajouterLiquide(double qte, Liquides liquide) throws CapacityExceededException, InvalidLiquidException { //qte en litres
		if(qty+qte>CAP_MAX) {
			qty=CAP_MAX;
			throw new CapacityExceededException(qte+qty,CAP_MAX);//on crée une exception pour ce probleme specifique
		}
		if(liquide!=this.liquide) {
			throw new InvalidLiquidException();//de meme pour le type de liquide
		}
		else {
			qty+=qte;
			nettoyee=false;
		}
	}
	
	public void nettoyage() throws InvalidRemoveLiquidException {
		enleverLiquide(qty);
		liquide=null;
	}
	
	public String toString() {
		return "Citerne n°"+ID_CITERNE+", "+liquide+", capacite: "+CAPACITE+
				" m3, mise en service: "+BIRTHDATE.toString()+", volume occupé: "+qty;
	}
	

}

