import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static BufferedReader br;
	private static int N;
	private static boolean[][] map;
	private static int[][][] dp;

	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new boolean[N][N];
		StringTokenizer st = null;
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = (st.nextToken().equals("1"))? true : false;
			}
		}
		
		dp = new int[N][N][3];
		for(int r = N-1; r>=0; r--)
			for(int c = N-1; c>=0; c--) {
				if(r == N-1 && c == N-1) {
					dp[r][c][0] = dp[r][c][1] = dp[r][c][2] = 0;
				}else if(r == N-1 && c == N-2) {
					dp[r][c][0] = (isThereWall(r, c, 0))? 0 : 1;
				}else if(r == N-2 && c == N-1) {
					dp[r][c][1] = (isThereWall(r, c, 1))? 0 : 1;
				}else if(r == N-2 && c == N-2) {
					dp[r][c][2] = (isThereWall(r, c, 2))? 0 : 1;
				}else if(r == N-1) {
					dp[r][c][0] = (isThereWall(r, c, 0))? 0 : dp[r][c+1][0];
					//dp[r][c][1] = 0;
					//dp[r][c][2] = 0;
				}else if(c == N-1) {
					//dp[r][c][0] = 0;
					dp[r][c][1] = (isThereWall(r, c, 1))? 0 : dp[r+1][c][1];
					//dp[r][c][2] = 0;
				}else {
					dp[r][c][0] = (isThereWall(r, c, 0))? 0 : dp[r][c+1][0] + dp[r][c+1][2];
					dp[r][c][1] = (isThereWall(r, c, 1))? 0 : dp[r+1][c][1] + dp[r+1][c][2];
					dp[r][c][2] = (isThereWall(r, c, 2))? 0 : dp[r+1][c+1][0] + dp[r+1][c+1][1] + dp[r+1][c+1][2];
				}
				
//				for(int r1 = 0; r1<N; r1++) {
//					for(int c1 = 0; c1<N; c1++) {
//						System.out.print(String.format("(%d,%d,%d) ", dp[r1][c1][0],dp[r1][c1][1],dp[r1][c1][2]));
//					}
//					System.out.println();
//				}
//				System.out.println();
			}
		System.out.println(dp[0][0][0]);
		
		
		
		br.close();
	}

	private static boolean isThereWall(int r, int c, int shape) {
		switch(shape) {
		case 0:
			return map[r][c] || map[r][c+1];
		case 1:
			return map[r][c] || map[r+1][c];
		case 2:
			return map[r][c] || map[r+1][c] || map[r][c+1] || map[r+1][c+1];
		}
		return false;
	}

}