package Prog.ProgAssign;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ProgrammingAssignment {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub

		String token = "3";
		String tokenValue = "372";
		List<String> keyList = new ArrayList<String>();
		HashMap map = new HashMap();
		// Creating Array list of array list to get all the tokens
		ArrayList<ArrayList<String>> a1 = new ArrayList<ArrayList<String>>();

		String currentDir = System.getProperty("user.dir");
		// Get the input file
		File file = new File(currentDir + "./Input/feed_sample.txt");

		// Using Scanner class to read each line
		Scanner scan = new Scanner(file);
		// Using while loop to iterator through each line
		while (scan.hasNextLine()) {
			// Creating Array list to store all the token
			ArrayList<String> a2 = new ArrayList<String>();
			// Split string with pipe "|"
			String[] arrayWithPipe = scan.nextLine().split("\\|");

			//Taking the first token value pair
			String s = arrayWithPipe[0];
			// System.out.println(s);
			//Splitting the token value pair to get value of from first pair 
			String s1[] = s.split("=");
			// System.out.println(s1[1]);
			//Checking the given token 3 value contains "372"
			if (s1[1].contains(tokenValue)) {
				// Using for loop to read all token/value pair
				for (String a : arrayWithPipe) {
					// System.out.println(a);
					// Split token value pair with "="
					String[] arrayWithequal = a.split("=");

					// Adding token in array list for each line
					a2.add(arrayWithequal[0]);
				}
				// Adding all the token list
				a1.add(a2);
			}
		}

		// System.out.println("ArrayList - a1 : " + a1);

		// Hash set for storing the unique token set only
		HashSet<ArrayList<String>> hsetForUniqueKey = new HashSet<ArrayList<String>>(a1);

		// Using for loop to get the count of unique token list
		for (ArrayList<String> count : hsetForUniqueKey) {
			// Storing in map unique list and count
			map.put(count, Collections.frequency(a1, count));
		}

		// Calling method to get the sorted list
		Map<ArrayList<String>, Integer> map1 = sortValues(map);

		// Storing sorted token list and count
		map1.forEach((k, v) -> keyList.add(k + "=" + v));

		System.out.println("Token: " + token + " = " + tokenValue + " : " + "unique signatures :- " + keyList.size());

		// Printing top three unique token list
		System.out.println("Top three combinations :");
		System.out.println("First: ");
		System.out.println(keyList.get(keyList.size() - 1));
		System.out.println("Second: ");
		System.out.println(keyList.get(keyList.size() - 2));
		System.out.println("Third: ");
		System.out.println(keyList.get(keyList.size() - 3));
	}

	// Method for sorting
	private static HashMap sortValues(HashMap map) {
		List list = new LinkedList(map.entrySet());
		// Custom Comparator
		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o1)).getValue()).compareTo(((Map.Entry) (o2)).getValue());
			}
		});

		// Copying the sorted list in HashMap to preserve the iteration order
		HashMap sortedHashMap = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			sortedHashMap.put(entry.getKey(), entry.getValue());
		}
		return sortedHashMap;
	}

}
