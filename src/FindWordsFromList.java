import java.io.File;
import java.io.FileReader;
import java.util.*;

public class FindWordsFromList {
	private static Scanner sc;
	private static Scanner scanfile;

	public static void main(String[] args) throws Exception {
		sc = new Scanner(System.in);
		System.out.print("Input a number: ");
		String number = sc.nextLine().trim();

		if(!number.matches("\\d+") || number.length() <= 0 || number.contains("0") || number.contains("1")) {
			throw new Exception("Please input a valid number");
		}
		
		// Mapping each number to word 
		String[] mapArray = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
		
		// Read in list within range of first alphabet in given input 
		// E.g. 2355 : stores words from A - C only
		int num = Integer.parseInt(number.substring(0, 1));
		Set<String> list = readFile(mapArray[num], mapArray);
		ArrayList<String> result = new ArrayList<String>();
	
		// Changing string to number representation & checking against input 
		for(String str : list) {
			if(str.length() != number.length() || str.matches(".*\\p{Punct}")) {
				continue;
			}
			String numrep = findNumberRepresentation(str.toLowerCase());
			if(numrep.equals(number)) {
				result.add(str);
			}
		}
		System.out.println(Arrays.toString(result.toArray()));
	}
	
	public static Set<String> readFile(String word, String[] mapArray) {
		String startword = word;
		startword = startword.substring(0, 1).toUpperCase() + "\\";
		
		String endword = "";
		int length = word.length();
		char endchar = word.charAt(length-1);
		int endascii = (int) endchar + 1;
		
		// check if its below z 
		if(endascii <= 122) {
			endword = Character.toString ((char) endascii);
			endword = endword.toUpperCase() + "\\";
		}
		
		return readFileLimit(startword, endword);
	}
	
	public static Set<String> readFileLimit(String startword, String endword) {
		File file = new File("WordsRTF.txt");
		Set<String> set = new HashSet<String>();
		try {
			scanfile = new Scanner(new FileReader(file));
			boolean start = false; 
			
			while(scanfile.hasNextLine()) {
				String line = scanfile.nextLine().trim();

				if(line.equals(startword)) {
					start = true;
				} else if(endword != "" && line.equals(endword)) {
					start = false;
				}
				
				if(start && !line.equals(startword)) {
					set.add(line.replace("\\", ""));
				}
			}
		} catch (Exception e) {
			System.out.println("File not found");
		}
		return set;
	}
	
	public static String findNumberRepresentation(String word) {
		StringBuffer ans = new StringBuffer();
		
		String[] mapArray = {"2", "22", "222", "3", "33", "333", "4", "44", "444", "5", "55", "555", "6", "66", "666", "7", "77", "777", "7777", "8", "88", "888", "9", "99", "999", "9999"}; 
		int a_ascii = (int) 'a', length = word.length();
		
		for(int i=0 ; i<length ; i++) {
			char temp = word.charAt(i);
			int index = (int) temp - a_ascii;
			ans.append(mapArray[index].charAt(0));
		}
		
		return ans.toString();
	}
}
