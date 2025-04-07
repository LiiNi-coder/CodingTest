import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static BufferedReader br;
	private static int M;
	private static int N;
	private static int[] cs;
	private static int[] ms;
	private static int[] dp_table;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		ms = new int[N];
		cs = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++) {
			ms[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++) {
			cs[i] = Integer.parseInt(st.nextToken());
		}
		
		dp_table = new int[M+1];
		for(int i = 0; i<dp_table.length; i++)
			dp_table[i] = 9999999;
		for(int n = 0; n<N;n++)
			for(int m = M; m>=0; m--) {
				if(m - ms[n] >0)
					dp_table[m] = Math.min(cs[n] + dp_table[m - ms[n]], dp_table[m]);
				else {
					dp_table[m] = Math.min(cs[n], dp_table[m]);
				}
			}
		System.out.println(dp_table[M]);
		br.close();
	}

}