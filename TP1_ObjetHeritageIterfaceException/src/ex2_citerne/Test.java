package ex2_citerne;

public class Test {

	public static void main(String[] args) throws MaxCapacityExceededException, BelowMinCapacityException, InvalidLiquidException {
		// TODO Auto-generated method stub
		int qty1=10;
		int qty2=400;
		assert(qty1>0 && qty1<Citerne.CAP_MAX): "quantity mustbe between 0 and "+Citerne.CAP_MAX;
		assert(qty2>0 && qty2<Citerne.CAP_MAX): "quantity mustbe between 0 and "+Citerne.CAP_MAX;
		
		//Liquides liq=null;
		//assert(liq instanceof Liquides): "liquid must be eau, vin or huile";
		try {
			Citerne c1=new Citerne(4000,Liquides.eau);
			CiterneSecurisee c2=new CiterneSecurisee(3000,Liquides.vin,1000);
			CiterneSecurisee c3=new CiterneSecurisee(3000,Liquides.vin,1000);
			try {
				Object c4=c1.clone();
				Object c5=c3.clone();
				System.out.println("c5: "+c5);
				System.out.println("c3: "+c3);
			} catch (CloneNotSupportedException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			System.out.println(c2.equals(c3));
			System.out.println(c1);
			System.out.println(c2);
			try {
				
				c1.ajouterLiquide(qty1, Liquides.eau);
				c2.ajouterLiquide(qty2, Liquides.vin);
				try {
					c2.changeOvflwTank(c1);
					//System.out.println(c2);
				} catch (InvalidRemoveLiquidException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (InvalidSetLiquidException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				System.out.println(c1.compareA(c2));
				try {
					assert(c2.getQty()>=300);
					c2.enleverLiquide(300);
				} catch (InvalidRemoveLiquidException e1) {
					e1.printStackTrace();
				}
				try {
					c2.nettoyage();
				} catch (InvalidRemoveLiquidException e1) {
					e1.printStackTrace();
				}
				try {
					//Liquides liq=null;
					//assert(liq instanceof Liquides):"liquid must be eau, vin or huile";
					c2.setLiquid(Liquides.vin);
				} catch (InvalidSetLiquidException e1) {
					e1.printStackTrace();
				}
				c2.ajouterLiquide(2000, Liquides.vin);
				Citerne.plusAncienne(c1, c2);
				//System.out.println(c1.equals(c2));
				try {
					assert(c1.getQty()>200):"not enough liquid remaining";
					c1.enleverLiquide(200);
					System.out.println(c1);
					System.out.println(c2);
				} catch (InvalidRemoveLiquidException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch ( InvalidLiquidException | CapacityExceededException | CiterneOverflowException e) {
				e.printStackTrace();
			}
			}catch(BelowMinCapacityException e) {
				System.out.println("Capacity must be >0");
			}
			catch(MaxCapacityExceededException e) {
				System.out.println("Capacity must be <20000");
			}
		
			}
		
	}


