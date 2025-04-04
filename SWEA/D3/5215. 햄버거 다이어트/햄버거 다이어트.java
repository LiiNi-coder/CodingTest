import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.StringTokenizer;

public class Solution {
	static private BufferedReader br;
	private static int N;
	private static int L;
	private static int[] W;
	private static int[] V;
	private static HashMap<DpElement, Integer> dp;
	static class DpElement{
		int n;
		int l;
		DpElement(int n, int l){
			this.n = n;
			this.l = l;
		}
		@Override
		public int hashCode() {
			return Objects.hash(n, l);
		}
		@Override
		public boolean equals(Object obj) {
			if(!(obj instanceof DpElement)) {
				return false;
			}
			DpElement o = (DpElement)obj;
			return n == o.n && l == o.l;
		}
		public String toString() {
			return n + ", " + l;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			System.out.println(String.format("#%d %d", i+1, solution()));	
		}


		
		br.close();
	}
	private static int solution() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		V = new int[N];
		W = new int[N];
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			V[i] = Integer.parseInt(st.nextToken());
			W[i] = Integer.parseInt(st.nextToken());
		}
		dp = new HashMap<DpElement, Integer>();
		return getDp(N, L);
	}

	private static int getDp(int n, int l) {
		DpElement key = new DpElement(n, l);
		if(!dp.containsKey(key)) {
			if(n == 0)
				return 0;
			int next_l = l - W[N-n];
			int result;
			if(next_l < 0)
				result = getDp(n-1, l);
			else {
				result = Math.max(
						V[N-n] + getDp(n-1, l - W[N-n]),
						getDp(n-1, l)
						); 
			}
			dp.put(key, result);
		}
		//System.out.println(key + " = " + dp.get(key));
		return dp.get(key);
	}

}