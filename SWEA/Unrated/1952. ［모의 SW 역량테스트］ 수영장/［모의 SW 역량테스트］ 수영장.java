import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	private static BufferedReader br;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			System.out.println(String.format("#%d %d", i+1, solution()));
		}
		br.close();
	}
	private static int solution() throws IOException {
		int[] fees = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] goes = new int[13];
		for (int i = 1; i < 13; i++) {
			goes[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[13];
		boolean[] threemonth_year;
		for(int m = 1; m<=12;m++) {
			dp[m] = Integer.MAX_VALUE;
			int[] candidate_dp = new int[4];
			threemonth_year = new boolean[2];
			
			candidate_dp[0] = dp[m-1] + goes[m]*fees[0];
			
			candidate_dp[1] = dp[m-1] + fees[1];
			
			if(m-3>=0) {
				candidate_dp[2] = dp[m-3] + fees[2];
				threemonth_year[0] = true;
			}
			if(m-12>=0) {
				candidate_dp[3] = dp[m-12] + fees[3];
				threemonth_year[1] = true;
			}
			
			for(int i = 0; i<2; i++) {
				dp[m] = Math.min(dp[m], candidate_dp[i]);
			}
			for(int i = 0; i<2; i++) {
				if(threemonth_year[i])
					dp[m] = Math.min(dp[m], candidate_dp[i+2]);
			}
		}
		return dp[12];
	}
}