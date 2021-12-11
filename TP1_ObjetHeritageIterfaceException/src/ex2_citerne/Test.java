package ex2_citerne;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
		Citerne c1=new Citerne(5,Liquides.eau);
		Citerne c2=new Citerne(10,Liquides.vin);
			try {
				c1.ajouterLiquide(3000, Liquides.eau);
				System.out.println(c1);
				try {
					c1.enleverLiquide(2000);
				} catch (InvalidRemoveLiquidException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch ( InvalidLiquidException | CapacityExceededException e) {
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


