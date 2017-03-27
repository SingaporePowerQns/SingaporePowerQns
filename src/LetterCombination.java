import java.util.*;

public class LetterCombination {
	private static Scanner sc;

	public static void main(String[] args) throws Exception {
		sc = new Scanner(System.in);
		System.out.print("Input: ");
		String number = sc.nextLine().trim();

		if(!number.matches("\\d+") || number.length() <= 0 || number.contains("0") || number.contains("1")) {
			throw new Exception("Please input a valid number");
		}

		String[] mapArray = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
		ArrayList<String> result = findCombi(number, mapArray);
		System.out.println("Output: " + Arrays.toString(result.toArray()));
	}
	
	public static ArrayList<String> findCombi(String number, String[] mapArray) {
		if(number.length() == 0) {
			return new ArrayList<String>();
		}
		
		ArrayList<String> ans = new ArrayList<String>();
		int index = Integer.parseInt(number.substring(0, 1));
		String word = mapArray[index];
		
		for(int i=0 ; i<word.length() ; i++) {
			ArrayList<String> temp = findCombi(number.substring(1), mapArray);
			if(temp.isEmpty()) {
				ans.add(word.substring(i, i+1));
			} else {
				addCharFirst(temp, ans, word.substring(i, i+1));
			}
		}
		return ans;
	}
	
	public static void addCharFirst(ArrayList<String> temp, ArrayList<String> ans, String word) {
		for(int i=0 ; i<temp.size() ; i++) {
			ans.add(word + temp.get(i));
		}
	}
}
