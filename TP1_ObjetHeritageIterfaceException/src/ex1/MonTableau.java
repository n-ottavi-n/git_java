package ex1;

public class MonTableau implements EstComparable {
	
	public int[] tableau;
	
	public MonTableau(int[] tab) {
		this.tableau=tab;
	}
	
	public int[] getTableau() {
		return tableau;
	}
	
	public int sommeTab(int[] tab){
		int somme=0;
		for(int i=0; i<tab.length;i++) {
			somme+=tab[i];
	}
		return somme;
	}
	

	@Override
	public int compareA(Object o) {
		int res=999;
		int x=sommeTab(tableau);
		try {			
			int[] tab=((MonTableau) o).getTableau();
			int y =sommeTab(tab);
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
		return res;
	}
}


