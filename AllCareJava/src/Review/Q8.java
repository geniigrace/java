package Review;

public class Q8 {

	public static void main(String[] args) {
		
		System.out.println("구구단");
		for(int i=2; i<10; i++) {
			System.out.println();
			System.out.println(i+"단");
			for(int j=1; j<10; j++) {
				System.out.println(i+" x "+j+" = "+(i*j));
			}
		}
	}
}
