import java.util.Scanner;

public class WordRepresentation {
	private static Scanner sc;

	public static void main(String[] args) throws Exception {
		sc = new Scanner(System.in);
		System.out.print("Input: ");
		String word = sc.nextLine().trim();
		
		if(!word.matches("^[A-z]+$") || word.length() <= 0) {
			throw new Exception("Please input a valid word");
		}		
		
		String[] mapArray = {"2", "22", "222", "3", "33", "333", "4", "44", "444", "5", "55", "555", "6", "66", "666", "7", "77", "777", "7777", "8", "88", "888", "9", "99", "999", "9999"}; 	
		System.out.println("Output: " + findNumberRepresentation(word, mapArray));
	}
	
	public static String findNumberRepresentation(String word, String[] mapArray) {
		StringBuffer ans = new StringBuffer();
		
		int a_ascii = (int) 'a', length = word.length();
		
		for(int i=0 ; i<length ; i++) {
			char temp = word.charAt(i);
			int index = (int) temp - a_ascii;
			ans.append(mapArray[index].charAt(0));
		}
		
		return ans.toString();
	}
}
