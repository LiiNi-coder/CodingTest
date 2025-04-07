import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static BufferedReader br;
	private static int N;
	private static int K;
	private static int[] dp_table;
	private static int[] vs;
	private static int[] ws;

	public static void main(String[] args) throws IOException {
		br  = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		ws = new int[N];
		vs = new int[N];
		
		dp_table = new int[K+1];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			ws[i] = Integer.parseInt(st.nextToken());
			vs[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int n = 0; n<N; n++)
			for (int w = K; w >=0; w--) {
				if(w - ws[n] >= 0) {
					dp_table[w] = Math.max(vs[n] + dp_table[w - ws[n]], dp_table[w]);										
				}
			}
		System.out.println(dp_table[K]);
		br.close();
	}
}