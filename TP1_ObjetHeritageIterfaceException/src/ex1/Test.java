package ex1;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a=new int[] {1,2,3,4};
		int[] b=new int[] {-1,2,-3,4};
		int[] c=null;
		int d=1;
		MonTableau m1=new MonTableau(a);
		MonTableau m2=new MonTableau(c);

		System.out.println(m1.compareA(d));

		
		

	}

}
