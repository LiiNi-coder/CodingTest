import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

class Solution
{
	static Set<String> dp = null;
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			dp = new HashSet<>();
			String[] temp = br.readLine().split(" ");
			char[] number = temp[0].toCharArray();
			int m = Integer.parseInt(temp[1]);
			
			ArrayList<ArrayList<Integer>> combs = getCombination(number.length, 2);
			
			//boolean visited[] = new boolean[combs.size()];
			Set<Integer> answers = new HashSet<>();
			//dfs(visited, number, combs, m, answers);
			dfs(number, combs, m, answers, -1);
			System.out.printf("#%d %d\n", i+1, Collections.max(answers));
		}
	}
		
	
	
	private static void dfs(char[] number, ArrayList<ArrayList<Integer>> combs, int i, Set<Integer> answers, int previous) {
		// TODO Auto-generated method stub
		String key = new String(number) + " " + Integer.toString(i);
		if(dp.contains(key)) {
			return;
		}
		if(i == 0) {
			answers.add(Integer.parseInt(new String(number)));
			return;
		}
		for (int j = 0; j < combs.size(); j++) {
			if (j == previous) {
				continue;
			}
			dp.add(key);
//			visited[j] = true;
			swapChar(number, combs.get(j));
//			dfs(visited, number, combs, i-1, answers);
			dfs(number, combs, i-1, answers, j);
			swapChar(number, combs.get(j));
	//		visited[j] = false;
		}
	}



	private static void swapChar(char[] number, ArrayList<Integer> arrayList) {
		// TODO Auto-generated method stub
		int a = arrayList.get(0);
		int b = arrayList.get(1);
		char temp = number[a];
		number[a] = number[b];
		number[b] = temp;
	}



	private static ArrayList<ArrayList<Integer>> getCombination(int length, int r) {
		// TODO Auto-generated method stub
		ArrayList<ArrayList<Integer>> result = new ArrayList<>();
		ArrayList<Integer> temp = new ArrayList<>();
		boolean visited[] = new boolean[length];
		recur(result, temp, 0, length, r, visited);
		return result;
	}



	private static void recur(ArrayList<ArrayList<Integer>> result, ArrayList<Integer> temp, int i, int length, int r, boolean[] visited) {
		// TODO Auto-generated method stub
		if (temp.size() == r) {
			result.add(temp);
			return;
		}
		for (int j = 0; j < visited.length; j++) {
			if (visited[j]) {
				continue;
			}
			visited[j] = true;
			ArrayList<Integer> new_temp = new ArrayList<>(temp);
			new_temp.add(j);
			recur(result, new_temp, j, length, r, visited);
			visited[j] = false;
		}
		
	}
	
}
