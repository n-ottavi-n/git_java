package ex2_citerne;



public class CiterneSecurisee extends Citerne implements Cloneable {
	
	public Citerne overflowTank;

	public CiterneSecurisee(int capacite, Liquides liquide, int capaciteOvflw)
			throws MaxCapacityExceededException, BelowMinCapacityException, InvalidLiquidException {
		super(capacite, liquide);
		
		if(capaciteOvflw>Citerne.CAP_MAX) {
			capaciteOvflw=capacite/10;
			throw new MaxCapacityExceededException();
		}
		if(capaciteOvflw<=0) {
			capaciteOvflw=capacite/10;
			throw new BelowMinCapacityException();
		}
		if(!(liquide instanceof Liquides)) {
			throw new InvalidLiquidException();
		}
		
		overflowTank=new Citerne(capaciteOvflw,liquide);
	}
	
	public void ajouterLiquide(double qte, Liquides liquide) throws CapacityExceededException, InvalidLiquidException, CiterneOverflowException { //qte en litres
		if(qty+qte>CAPACITE) {
			qty=CAPACITE;
			overflowTank.ajouterLiquide(qty-CAPACITE, liquide);
			throw new CiterneOverflowException(qte,CAPACITE);//on crée une exception pour ce probleme specifique
		}
		if(liquide!=this.liquide) {
			throw new InvalidLiquidException();//de meme pour le type de liquide
		}
		else {
			qty+=qte;
			nettoyee=false;
		}
	}
	
	public void changeOvflwTank(Citerne cit) throws InvalidRemoveLiquidException, InvalidSetLiquidException {
		if(!(cit.getLiquidType()==this.getLiquidType())) {
			cit.enleverLiquide(cit.getQty());//on vide prealablement au nettoyage et au changement de liquide
			cit.nettoyage();
			cit.setLiquid(this.getLiquidType());
		}
		else {
			this.overflowTank=cit;
		}
	}
	
	public String toString() {
		return super.toString()+" capacite cuve de trop plein: "+overflowTank.CAPACITE+
		", volume cuve de trop plein: "+overflowTank.qty+"/"+overflowTank.CAPACITE;
	}
	
	public boolean equals(Object obj) {
		boolean res=false;
		boolean res2=false;
		if((obj!=null) && (obj instanceof CiterneSecurisee)) {
			CiterneSecurisee objCiterneS=(CiterneSecurisee)obj;
			res=super.equals(objCiterneS);
			res2=(objCiterneS.overflowTank.equals(this.overflowTank));
		}
		return (res && res2);//vrai seulement si les 2 sont vrais
	}
	
	public Object clone() throws CloneNotSupportedException{
		CiterneSecurisee c=null;
		Citerne oTank=null;
		try {
			c = (CiterneSecurisee)super.clone();
			oTank=(Citerne)overflowTank.clone();
			
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			c.changeOvflwTank(oTank);
		} catch (InvalidRemoveLiquidException | InvalidSetLiquidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}
}
