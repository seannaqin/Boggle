
import java.util.*;

public class WordComparator implements Comparator<String> {
	

	@Override
	public int compare(String o1, String o2) {
		// Compare the lengths of the strings
		if (o1.length() < o2.length()) {
			return 1;
		}
		else if (o1.length() > o2.length()) {
			return -1;
		}
		else {
			// Alphabetize words if they're the same length
			for (int i = 0; i < Math.min(o1.length(), o2.length()); i++) {
				if (o1.charAt(i) < o2.charAt(i)) {
					return -1;
				}
				else if (o1.charAt(i) > o2.charAt(i)) {
					return 1;
				}
			}
			return o1.length() - o2.length();
			
		}
	}


}
