package ch04.sec04_controlkeyword.EX01_BreakControlKeyword;

public class BreakControlKeyword {
	
	public static void main(String[] args) {
		
		for(int i=0;i<10;i++) {
			System.out.println(i); // 0
			break;
		}
		
		for(int i=0;i<10;i++) {
			if(i==5) {
				break;
			}
			System.out.print(i+" "); // 0 1 2 3 4 
		}
			
			System.out.println();
			System.out.println();
			
		
		POS1: for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				if(j==2) {
					break POS1;
				}
				System.out.println(i+", "+j); // 0,0 0,1 
			}
		}
		System.out.println();
		
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				if(j==2) {
					i=100;
					break;
				}
				System.out.println(i+", "+j); // 0,0 0,1
			}
		}
		System.out.println();
		}
	}


