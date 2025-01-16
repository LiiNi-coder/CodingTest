import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

class Main{
	static long return_unit;
	static long n;
	static int fine;
	static HashMap<String, HashMap<String, Long>> name_books = new HashMap<>();
	static TreeMap<String, Long> fines = new TreeMap<>();
	static int[] days_of_month = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] token = br.readLine().split(" ");
		n = Integer.parseInt(token[0]);
		int[] temp = changeFormat(token[1]);
		return_unit = convertToMinutes(temp);
		fine = Integer.parseInt(token[2]);
		
		for (int i = 0; i < n; i++) {
			token = br.readLine().split(" ");
			String name = token[3];
			String book_name = token[2];
			int[] mm_dd_hh_mm = changeFormat(new String[]{token[0], token[1]});
			
			if (!name_books.containsKey(name)) {
				name_books.put(name, new HashMap<>());
			}
			if (!name_books.get(name).containsKey(book_name)) {
				name_books.get(name).put(book_name, convertToMinutes(mm_dd_hh_mm));
			}else {
				long diff = convertToMinutes(mm_dd_hh_mm) - name_books.get(name).get(book_name);
				long late = diff - return_unit;
				if (late > 0) {
					if(!fines.containsKey(name))
						fines.put(name, (long) 0);
					fines.put(name, fines.get(name) + late * fine);
				}
				name_books.get(name).remove(book_name);
			}
		}
		
		if (fines.size() >0) {
			for (Map.Entry<String, Long> entry : fines.entrySet()) {
				System.out.println( entry.getKey() + " "+ entry.getValue().toString());
			}
		}else
			System.out.println(-1);
		return;
	}
	private static long convertToMinutes(int[] mm_dd_hh_mm) {
		assert mm_dd_hh_mm.length == 4;
		long result = mm_dd_hh_mm[3];
		result += mm_dd_hh_mm[2]*60;
		result += mm_dd_hh_mm[1]*24*60;
		for (int i = 0; i < mm_dd_hh_mm[0]-1; i++) {
			result+= days_of_month[i]*24*60;
		}
		return result;
	}
	private static int[] changeFormat(String token) {
		// TODO Auto-generated method stub
		int[] mm_dd_hh_mm = new int[4];
		String[] temp = token.split("/");
		assert temp.length == 2;
		mm_dd_hh_mm[1] = Integer.parseInt(temp[0]);
		temp = temp[1].split(":");
		mm_dd_hh_mm[2] = Integer.parseInt(temp[0]);
		mm_dd_hh_mm[3] = Integer.parseInt(temp[1]);
		mm_dd_hh_mm[0] = 0;
		return mm_dd_hh_mm;
	}
	private static int[] changeFormat(String[] token) {
		// TODO Auto-generated method stub
		int[] mm_dd_hh_mm = new int[4];
		String[] temp = token[0].split("-");
		mm_dd_hh_mm[0] = Integer.parseInt(temp[1]);
		mm_dd_hh_mm[1] = Integer.parseInt(temp[2]);
		temp = token[1].split(":");
		mm_dd_hh_mm[2] = Integer.parseInt(temp[0]);
		mm_dd_hh_mm[3] = Integer.parseInt(temp[1]);
		
		return mm_dd_hh_mm;
	}
}
