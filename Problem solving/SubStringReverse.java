import java.util.Scanner;

public class SubStringReverse {
	private static int index = 0;

	public static void main(String[] args) {
		Scanner myObj = new Scanner(System.in);
		String yourString;
		System.out.println("Enter Your String : ");
		yourString = myObj.nextLine();

		System.out.println(reverseSubStringBetweenParentheses(yourString));

	}

	// Reverse SubString
	public static String reverseSubStringBetweenParentheses(String se) {
		StringBuilder result = new StringBuilder();
		while (index < se.length()) {
			if (se.charAt(index) == '(') {
				++index;
				result.append(reverseSubStringBetweenParentheses(se));
			} else if (se.charAt(index) == ')') {
				index++;
				return result.reverse().toString();
			} else {
				result.append(se.charAt(index++));
			}
		}
		return result.toString();
	}
}
