package ex2_citerne;

import java.time.LocalDate;



public class Citerne implements EstComparable,Cloneable {
	
	private static int nb_citernes=0;
	public final int ID_CITERNE;
	public final int CAPACITE;
	public static final int CAP_MAX=20000;
	protected Liquides liquide;
	private final LocalDate BIRTHDATE;
	protected double qty=0;
	protected boolean nettoyee=true;
	
	
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
	
	public void ajouterLiquide(double qte, Liquides liquide) throws CapacityExceededException, InvalidLiquidException, CiterneOverflowException { //qte en litres
		if(qty+qte>CAPACITE) {
			qty=CAPACITE;
			throw new CapacityExceededException(qte+qty,CAPACITE);//on crée une exception pour ce probleme specifique
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
		nettoyee=true;
	}
	
	public String toString() {
		return "Citerne n°"+ID_CITERNE+", "+liquide+", capacite: "+CAPACITE+
				" m3, mise en service: "+BIRTHDATE.toString()+", volume occupé: "+qty;
	}
	
	public boolean equals(Object obj) {
		boolean res=false;
		if((obj!=null) && (obj instanceof Citerne)) {
			Citerne objCiterne=(Citerne)obj;
			res=(objCiterne.getCapacite()==this.CAPACITE && 
					objCiterne.getBirthdate().equals(this.BIRTHDATE) &&
					objCiterne.getLiquidType()==this.liquide &&
					objCiterne.getQty()==this.qty);
		}
		return res;
	}

	private double getCapacite() {		
		return CAPACITE;
	}

	@Override
	public int compareA(Object o) {
		double res=999;
		double x=this.getQty();
		try {			
			Citerne objCiterne=(Citerne) o;
			double y=objCiterne.getQty();
			if(x==y) {
				x=this.getCapacite();
				y=objCiterne.getCapacite();
			}
			res=x-y;
			if (res!=0) {
				res=res/Math.abs(res);//division entiere
			}
		}
		catch(NullPointerException e) {
			System.out.println("L'objet passé en parametre est vide");
		}			
		catch(ClassCastException e) {
			System.out.println("les objets ne sont pas comparable");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return (int) res;
	}
	
	public Object clone() throws CloneNotSupportedException{
		Citerne c=null;
		try {
			c = (Citerne)super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			c.setLiquid(liquide);
		} catch (InvalidSetLiquidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			c.ajouterLiquide(qty, liquide);
		} catch (CapacityExceededException | InvalidLiquidException | CiterneOverflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}
	

}

