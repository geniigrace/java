package ch14.sec02_exceptionhandlingsyntax.EX02_Multicatch_1;

public class Multicatch_1 {

	public static void main(String[] args) {

		try {
			System.out.println(3/0);
		}
		catch(ArithmeticException e) {
			System.out.println("숫자는 0으로 나눌 수 없습니다.");
		}
		finally {
			System.out.println("프로그램 종료");
		}
		
		try {
			int num=Integer.parseInt("10A");
		}
		catch(NumberFormatException e) {
			System.out.println("숫자로 바꿀 수 없습니다.");
		}
		finally {
			System.out.println("프로그램 종료");
		}
		
		System.out.println();
		System.out.println();
		
		try {
			System.out.println(3/0);
			int num=Integer.parseInt("10A");
		}
		catch (ArithmeticException e) { //위의 실행예외가 먼저 실행되므로 이 블록은 실행되지 않음
			System.out.println("숫자는 0으로 나눌수 없습니다.");
		}
		catch (NumberFormatException e) {
			System.out.println("숫자로 바꿀 수 없습니다.");
		}
		finally {
			System.out.println("프로그램 종료");
		}
	}


}
